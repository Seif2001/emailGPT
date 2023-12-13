package GUI;

import Models.Mood;
import Services.PromptService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Email extends JFrame {
    PromptService service;
    JComboBox<String> moodComboBox;
    JTextField subjectTextField;
    JTextArea emailTextArea;
    int gen;
    public Email(PromptService service) {
        this.service = service;
        // Set up the frame
        setTitle("Email GUI");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        String[] moodOptions = this.service.getMoods();
        moodComboBox = new JComboBox<>(moodOptions);

        subjectTextField = new JTextField(18);

        emailTextArea = new JTextArea(10, 35);

        emailTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Adjust the empty border as needed
        ));
        emailTextArea.setText(this.service.getText());
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
                    service.setText(emailTextArea.getText());
                    updateMood();
                    updateSubject();
                    generatePrompt();

                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle exception appropriately
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(emailTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel moodLabel = new JLabel("Mood");
        JLabel subjectLabel = new JLabel("Subject");
        JLabel emailLabel = new JLabel("Email");

        // Create panels
        JPanel moodPanel = new JPanel(new FlowLayout());
        moodPanel.add(moodLabel);
        moodPanel.add(moodComboBox);

        JPanel subjectPanel = new JPanel(new FlowLayout());
        subjectPanel.add(subjectLabel);
        subjectPanel.add(subjectTextField);

        // Create a panel to hold mood and subject components horizontally
        JPanel moodSubjectPanel = new JPanel();
        moodSubjectPanel.setLayout(new BoxLayout(moodSubjectPanel, BoxLayout.X_AXIS));
        moodSubjectPanel.add(subjectPanel);
        moodSubjectPanel.add(moodPanel);

        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.add(emailLabel, BorderLayout.NORTH);
        emailPanel.add(scrollPane, BorderLayout.CENTER);

        // Set layout for the main content pane
        getContentPane().setLayout(new BorderLayout());

        // Create a panel to hold mood, subject, and email components vertically
        JPanel mainPanel = new JPanel();
        mainPanel.add(moodSubjectPanel);
        mainPanel.add(emailPanel);

        // Add the main panel to the content pane
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateMood() {
        String selectedMood = (String) moodComboBox.getSelectedItem();
        service.setMood(Mood.valueOf(selectedMood.toUpperCase())); // Assuming Mood is an enum
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
                    if(gen == 10) {
                        service.setText(emailTextArea.getText());
                        service.makePrompt();
                        System.out.println(service.getOutput());
                        gen = 0;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle exception appropriately
                }
                return null;
            }
        };

        worker.execute();
    }
}
