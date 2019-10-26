package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    private void initTextArea() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        Container pane = getContentPane();
        GroupLayout groupLayout = new GroupLayout(pane);
        pane.setLayout(groupLayout);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup().addComponent(scrollPane));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addComponent(scrollPane));
        pack();

        textArea.setName("TextArea");
    }

    public TextEditor() {
        initTextArea();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setLayout(null);
        setTitle("The first stage");
    }
}
