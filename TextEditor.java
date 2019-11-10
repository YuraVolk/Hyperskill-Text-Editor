package editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class TextEditor extends JFrame {
    JTextField textField;
    JTextArea textArea;
    JFileChooser chooser = new JFileChooser(
            FileSystemView.getFileSystemView().getHomeDirectory()
    );
    boolean isChecked = false;
    JButton buttonSave;
    JButton buttonLoad;
    JButton buttonSearch;
    JButton buttonNext;
    JButton buttonPrev;

    OccurrenceHistory occurrenceHistory;

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



        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(59, 65, 1, 2);
        getContentPane().add(separator_1);

        buttonLoad.addActionListener(e -> {
            new ChooseFileCommand(this).execute();
        });
        buttonSave.addActionListener(e -> {
            new SaveFileCommand(this).execute();
        });
        buttonSearch.addActionListener(e -> {
            FindOccurrencesCommand command =
                    new FindOccurrencesCommand(this);
            command.run();
            occurrenceHistory = command.getHistory();
        });

        mntmExit.addActionListener(event -> exit());

    }

    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage() ;
        Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    private void exit() {
        System.gc();
        dispose();
        System.exit(0);
    }

    private void initializeIcons() {
        ImageIcon icon = new ImageIcon("load.png");
        resizeIcon(icon);
        buttonLoad.setIcon(icon);

        icon = new ImageIcon("save.png");
        resizeIcon(icon);
        buttonSave.setIcon(icon);

        icon = new ImageIcon("search.png");
        resizeIcon(icon);
        buttonSearch.setIcon(icon);

        icon = new ImageIcon("prev.png");
        resizeIcon(icon);
        buttonPrev.setIcon(icon);

        icon = new ImageIcon("next.png");
        resizeIcon(icon);
        buttonNext.setIcon(icon);
    }

    private void initializePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(11, 28, 423, 28);
        getContentPane().add(panel);
        panel.setLayout(null);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Use regex");
        chckbxNewCheckBox.setBounds(335, 3, 92, 23);
        panel.add(chckbxNewCheckBox);

        buttonLoad = new JButton("");
        buttonLoad.setBounds(0, 0, 28, 28);
        panel.add(buttonLoad);

        buttonSave = new JButton("");

        buttonSave.setBounds(32, 0, 28, 28);
        panel.add(buttonSave);

        textField = new JTextField();
        textField.setBounds(61, 4, 177, 20);
        panel.add(textField);
        textField.setColumns(10);

        buttonSearch = new JButton("");
        buttonSearch.setBounds(303, 0, 28, 28);
        panel.add(buttonSearch);

        buttonNext = new JButton("");
        buttonNext.setBounds(271, 0, 28, 28);
        panel.add(buttonNext);

        buttonPrev = new JButton("");
        buttonPrev.setBounds(239, 0, 28, 28);
        panel.add(buttonPrev);
    }


    public TextEditor() {
        initializePanel();
        initializeIcons();
        init();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(460, 315);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setTitle("Text Editor");
    }
}
