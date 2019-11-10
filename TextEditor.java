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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(11, 65, 423, 201);
        getContentPane().add(scrollPane);
        scrollPane.setName("ScrollPane");

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setName("TextArea");

        JPanel panelMenu = new JPanel();
        panelMenu.setBounds(0, 0, 444, 26);
        getContentPane().add(panelMenu);
        panelMenu.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 444, 24);
        panelMenu.add(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        mnFile.setName("MenuFile");

        JMenuItem mntmSave = new JMenuItem("Save");
        mnFile.add(mntmSave);
        mntmSave.setName("MenuSave");

        JMenuItem mntmLoad = new JMenuItem("Load");
        mnFile.add(mntmLoad);
        mntmLoad.setName("MenuLoad");

        JSeparator separatorMenu = new JSeparator();
        mnFile.add(separatorMenu);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);
        mntmExit.setName("MenuExit");

        JMenu mnSearch = new JMenu("Search");
        menuBar.add(mnSearch);

        JMenuItem mntmStartSearch = new JMenuItem("Start Search");
        mnSearch.add(mntmStartSearch);

        JMenuItem mntmPreviousSearch = new JMenuItem("Previous search");
        mnSearch.add(mntmPreviousSearch);

        JMenuItem mntmNextMatch = new JMenuItem("Next Match");
        mnSearch.add(mntmNextMatch);

        JMenuItem mntmUseRegularExpressions = new JMenuItem("Use Regular Expressions");
        mnSearch.add(mntmUseRegularExpressions);

        JSeparator separator = new JSeparator();
        separator.setBounds(81, 24, 1, 2);
        getContentPane().add(separator);

        JPanel panel = new JPanel();
        panel.setBounds(11, 28, 423, 28);
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnA = new JButton("");
        btnA.setIcon(null);

        btnA.setBounds(0, 0, 28, 28);
        panel.add(btnA);

        JButton btnB = new JButton("");

        btnB.setBounds(32, 0, 28, 28);
        panel.add(btnB);

        textField = new JTextField();
        textField.setBounds(67, 4, 187, 20);
        panel.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("");
        button.setBounds(258, 0, 28, 28);
        panel.add(button);

        JButton button_1 = new JButton("");
        button_1.setBounds(294, 0, 28, 28);
        panel.add(button_1);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Use regex");
        chckbxNewCheckBox.setBounds(325, 3, 92, 23);
        panel.add(chckbxNewCheckBox);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(59, 65, 1, 2);
        getContentPane().add(separator_1);

        mntmSave.addActionListener(event -> writeToFile());
        mntmSave.addActionListener(event -> writeToFile());
        mntmLoad.addActionListener(event -> readFile());
        mntmLoad.addActionListener(event -> readFile());
        mntmExit.addActionListener(event -> exit());
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

    private void exit() {
        System.gc();
        dispose();
        System.exit(0);
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setTitle("Text Editor");
    }
}
