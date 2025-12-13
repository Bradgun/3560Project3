package view;

import javax.swing.*;
import java.awt.*;

import controller.MainController;
import model.WritingMode;

//builds UI
public class MainFrame extends JFrame {

    private final MainController controller = new MainController();

    public MainFrame() {
        super("Writing Assistant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JTextArea inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        JTextArea outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);

        JButton generateButton = new JButton("Generate");
        JLabel statusLabel = new JLabel("Ready");

        JComboBox<WritingMode> writingModeComboBox = new JComboBox<>(WritingMode.values());

        generateButton.addActionListener(e -> {
            statusLabel.setText("Running");
            generateButton.setEnabled(false);

            WritingMode mode = (WritingMode) writingModeComboBox.getSelectedItem();
            String input = inputArea.getText();

            controller.generateInBackground(mode, input, result -> {
                        outputArea.setText(result);
                        statusLabel.setText("Done");
                        generateButton.setEnabled(true);
                        },
            error -> {
                statusLabel.setText(error);
                generateButton.setEnabled(true);
            }
            );
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(generateButton);
        topPanel.add(statusLabel);
        topPanel.add(writingModeComboBox);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  new JScrollPane(inputArea), new JScrollPane(outputArea)
        );

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);
    }
}
