package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
        textField.setBounds(1, 0, 268, 39);
        getContentPane().add(textField);
        textField.setColumns(10);
        textField.setName("FilenameField");

        Button buttonSave = new Button("Save");
        buttonSave.setBounds(354, 0, 80, 39);
        getContentPane().add(buttonSave);
        buttonSave.setName("SaveButton");

        Button buttonLoad = new Button("Load");
        buttonLoad.setBounds(268, 0, 88, 39);
        getContentPane().add(buttonLoad);
        buttonLoad.setName("LoadButton");

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(1, 50, 433, 211);
        getContentPane().add(scrollPane);
        scrollPane.setName("ScrollPane");

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setName("TextArea");

        buttonSave.addActionListener(this::writeToFile);
        buttonLoad.addActionListener(this::readFile);
    }

    private void writeToFile(ActionEvent e) {
        String text = textField.getText();
        try (PrintWriter out = new PrintWriter(
                text.endsWith(".txt") ?
                        text : text + ".txt")) {
            out.println(textArea.getText());
        } catch (FileNotFoundException error) {
            System.out.println("No permission to save into this directory.");
        }
    }

    private void readFile(ActionEvent e) {
        try {
            String content = Files.readString(Paths.get("file.txt"));
            textArea.setText(content);
        } catch (IOException error) {
            System.out.println("No such file exists.");
            textArea.setText("No such file exists.");
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
