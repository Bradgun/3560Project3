package view;

import javax.swing.*;
import java.awt.*;

import controller.MainController;
import model.AppModel;
import model.WritingMode;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.domain.SaveWritingSession;
import model.repository.JsonSessionRepository;
import model.repository.SessionRepository;

import java.util.List;

//builds UI
public class MainFrame extends JFrame {

    //shared model
    private final AppModel model = new AppModel();

    //controller now has model
    private final MainController controller = new MainController(model);

    //saving/loading sessions are handled here
    private final SessionRepository repository = new JsonSessionRepository("sessions.json");

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
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");

        JComboBox<WritingMode> writingModeComboBox = new JComboBox<>(WritingMode.values());

        //save the current writing mode, input, and output as as session
        saveMenuItem.addActionListener(e -> {
            try {
                WritingMode mode = (WritingMode) writingModeComboBox.getSelectedItem();
                String input = inputArea.getText();
                String output = outputArea.getText();
                if (input == null || input.isBlank() || output == null || output.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Please fill all the fields");
                    return;
                }

                repository.save(new SaveWritingSession(mode, input, output));
                JOptionPane.showMessageDialog(this, "Session Saved");
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Save Failed.");
            }
        });

        //load previously saved sessions
        loadMenuItem.addActionListener( e -> {
            try {
                List<SaveWritingSession> sessions = repository.loadSessions();

                if (sessions.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No sessions found");
                    return;
                }

                SaveWritingSession selectedSession = (SaveWritingSession) JOptionPane.showInputDialog(this, "Choose session:", "Load session", JOptionPane.PLAIN_MESSAGE, null, sessions.toArray(), sessions.get(0));

                if (selectedSession != null) {
                    writingModeComboBox.setSelectedItem((selectedSession.getWritingMode()));
                    inputArea.setText(selectedSession.getInputText());
                    outputArea.setText(selectedSession.getOutputText());
                    statusLabel.setText("Loaded");
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Load Failed.");
            }
        });


        //view listens to model changes
        model.addPropertyChangeListener(new PropertyChangeListener() {
            @Override

            public void propertyChange(PropertyChangeEvent evt) {

                //update label if output text changes
                if ("outputText".equals(evt.getPropertyName())) {
                    outputArea.setText(model.getOutputText());
                }

                //update label if status changed
                if ("statusText".equals(evt.getPropertyName())) {
                    statusLabel.setText(model.getStatusText());
                }

                //enable or disable buttons if "busy" changed
                if("busy".equals(evt.getPropertyName())) {
                    boolean busy = model.isBusy();
                    generateButton.setEnabled(!busy);
                    cancelButton.setEnabled(busy);
                }

                //show error in status if error changed
                if ("errorText".equals(evt.getPropertyName())) {
                    if(model.getErrorText() != null) {
                        statusLabel.setText(model.getErrorText());
                        String error = model.getErrorText();

                        //returns a pop up of the error received
                        JOptionPane.showMessageDialog(MainFrame.this, error, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        generateButton.addActionListener(e -> {
            WritingMode mode = (WritingMode) writingModeComboBox.getSelectedItem();
            String input = inputArea.getText();
            controller.generateInBackground(mode, input);
        });

        cancelButton.addActionListener(e -> controller.cancel());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(generateButton);
        topPanel.add(cancelButton);
        topPanel.add(statusLabel);
        topPanel.add(writingModeComboBox);

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  new JScrollPane(inputArea), new JScrollPane(outputArea));

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);
    }
}
