package GUI;

import javax.swing.*;
import java.awt.*;

public class Email extends JFrame {
    public Email() {
        // Set up the frame
        setTitle("Email GUI");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        String[] moodOptions = {"Happy", "Sad", "Excited", "Angry"};
        JComboBox<String> moodComboBox = new JComboBox<>(moodOptions);

        JTextField subjectTextField = new JTextField(18);

        JTextArea emailTextArea = new JTextArea(10, 35);

        emailTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Adjust the empty border as needed
        ));
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
}
