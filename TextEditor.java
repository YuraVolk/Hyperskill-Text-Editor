package editor;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    private JTextField textField;
    private JTextArea textArea;

    private void init() {
        getContentPane().setLayout(null);
        textField = new JTextField();
        textField.setBounds(10, 41, 259, 21);
        getContentPane().add(textField);
        textField.setColumns(10);
        textField.setName("FilenameField");

        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(353, 41, 80, 21);
        getContentPane().add(buttonSave);
        buttonSave.setName("SaveButton");

        JButton buttonLoad = new JButton("Load");
        buttonLoad.setBounds(267, 41, 88, 21);
        getContentPane().add(buttonLoad);
        buttonLoad.setName("LoadButton");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(11, 65, 423, 201);
        getContentPane().add(scrollPane);
        scrollPane.setName("ScrollPane");

        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        JPanel panel = new JPanel();
        panel.setBounds(10, 13, 420, 24);
        getContentPane().add(panel);
        panel.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 420, 24);
        panel.add(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmSave = new JMenuItem("Save");
        mnFile.add(mntmSave);

        JMenuItem mntmLoad = new JMenuItem("Load");
        mnFile.add(mntmLoad);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);


        buttonSave.addActionListener(event -> writeToFile());
        buttonLoad.addActionListener(event -> readFile());
    }

    private void writeToFile() {
        String text = textField.getText();
        try (PrintWriter out = new PrintWriter(
                text.endsWith(".txt") ?
                        text : text + ".txt")) {
            out.println(textArea.getText());
        } catch (FileNotFoundException error) {
            System.out.println("No permission to save into this directory.");
        }
    }

    private void readFile() {
        String text = textField.getText();
        try {
            String content = Files.readString(Paths.get(text.endsWith(".txt") ?
                                            text : text + ".txt"));
            textArea.setText((content.substring(0, content.length() - 2)));
        } catch (IOException error) {
            textArea.setText("");
            //textArea.setText("No such file exists.");
        }

    }

    public TextEditor() {
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setTitle("Text Editor");
    }
}
