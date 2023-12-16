package GUI;

import Models.Length;
import Models.Mood;
import Services.PromptService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Email extends JFrame {
    PromptService service;
    JComboBox<String> moodComboBox;
    JComboBox<String> lengthComboBox;
    JTextField subjectTextField;
    JTextArea emailTextArea;
    JTextArea additionalTextArea;
    private Timer debounceTimer;

    int gen;
    public Email(PromptService service) {
        this.service = service;
        this.debounceTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePrompt();
            }
        });
        this.debounceTimer.setRepeats(false);           setTitle("Email GUI");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        String[] moodOptions = this.service.getMoods();
        moodComboBox = new JComboBox<>(moodOptions);

        String[] lengthOptions = this.service.getLengths();
        lengthComboBox = new JComboBox<>(lengthOptions);

        subjectTextField = new JTextField(10);
        subjectTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Adjust the empty border as needed
        ));

        emailTextArea = new JTextArea(10, 35);

        emailTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Adjust the empty border as needed
        ));
        emailTextArea.setText(this.service.getText());
        emailTextArea.setLineWrap(true);
        emailTextArea.setWrapStyleWord(true);
        emailTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textChanged();
            }

            private void textChanged() {
                // Call makePrompt when the text changes
                try {
                    gen++;
                    System.out.println(gen);
                    debounceTimer.restart(); // Restart the timer upon text change
                    generatePrompt();

                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle exception appropriately
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(emailTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel moodLabel = new JLabel("Mood");
        JLabel lengthLabel = new JLabel("Length");
        JLabel subjectLabel = new JLabel("Subject");
        JLabel emailLabel = new JLabel("Email");

        // Create panels
        JPanel moodPanel = new JPanel(new FlowLayout());
        moodPanel.add(moodLabel);
        moodPanel.add(moodComboBox);

        JPanel lengthPanel = new JPanel(new FlowLayout());
        lengthPanel.add(lengthLabel);
        lengthPanel.add(lengthComboBox);

        JPanel subjectPanel = new JPanel(new FlowLayout());
        subjectPanel.add(subjectLabel);
        subjectPanel.add(subjectTextField);

        // Create a panel to hold mood and subject components horizontally
        JPanel moodSubjectPanel = new JPanel();
        moodSubjectPanel.setLayout(new BoxLayout(moodSubjectPanel, BoxLayout.X_AXIS));
        moodSubjectPanel.add(subjectPanel);
        moodSubjectPanel.add(moodPanel);
        moodSubjectPanel.add(lengthPanel);

        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.add(emailLabel, BorderLayout.NORTH);
        emailPanel.add(scrollPane, BorderLayout.CENTER);

        // Set layout for the main content pane
        getContentPane().setLayout(new BorderLayout());

        // Create a panel to hold mood, subject, and email components vertically
        JPanel mainPanel = new JPanel();
        mainPanel.add(moodSubjectPanel);
        mainPanel.add(emailPanel);


        // Create an additional uneditable text area
        additionalTextArea = new JTextArea(10, 35);
        additionalTextArea.setEditable(false);
        additionalTextArea.setLineWrap(true);
        additionalTextArea.setWrapStyleWord(true);
        additionalTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Adjust the empty border as needed
        ));
        additionalTextArea.setWrapStyleWord(true);
        JScrollPane additionalScrollPane = new JScrollPane(additionalTextArea);
        additionalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create buttons
        JButton button1 = new JButton("Accept");
        JButton button2 = new JButton("Retry");

        // Add ActionListener for Button 1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailTextArea.setText(additionalTextArea.getText());
            }
        });

        // Add ActionListener for Button 2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reGeneratePrompt();
            }
        });

        // Create a panel to hold buttons horizontally centered
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button1);
        buttonPanel.add(button2);


        mainPanel.add(additionalScrollPane);
        mainPanel.add(buttonPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add the main panel to the content pane
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setSize(600,700);
        setVisible(true);
    }

    private void updateMood() {
        String selectedMood = (String) moodComboBox.getSelectedItem();
        service.setMood(Mood.valueOf(selectedMood.toUpperCase())); // Assuming Mood is an enum
    }

    private void updateLength() {
        String selectedLength = (String) lengthComboBox.getSelectedItem();
        service.setLength(Length.valueOf(selectedLength.toUpperCase())); // Assuming Length is an enum
    }

    private void updateSubject() {
        String subject = subjectTextField.getText();
        service.setSubject(subject);
    }

    private void generatePrompt() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    if(gen > 10) {
                        gen = 0;
                        service.setText(emailTextArea.getText());
                        service.makePrompt();
                        additionalTextArea.setText(service.getOutput());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle exception appropriately
                }
                return null;
            }
        };

        worker.execute();
    }

    private void reGeneratePrompt() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    service.setText(emailTextArea.getText());
                    updateMood();
                    updateLength();
                    updateSubject();
                    service.makePrompt();
                    additionalTextArea.setText(service.getOutput());
                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle exception appropriately
                }
                return null;
            }
        };

        worker.execute();
    }
}
