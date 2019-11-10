package editor;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextEditor extends JFrame {
    JTextField textField;
    JTextArea textArea;
    JFileChooser chooser;
    JCheckBox chckbxNewCheckBox;
    boolean isChecked = false;
    JButton buttonSave;
    JButton buttonLoad;
    JButton buttonSearch;
    JButton buttonNext;
    JButton buttonPrev;

    OccurrenceHistory occurrenceHistory;
    int currentMatch = 0;

    private void init() {
        chooser = new JFileChooser();
        chooser.setName("FileChooser");
        add(chooser);

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
        mntmLoad.setName("MenuOpen");

        JSeparator separatorMenu = new JSeparator();
        mnFile.add(separatorMenu);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);
        mntmExit.setName("MenuExit");

        JMenu mnSearch = new JMenu("Search");
        menuBar.add(mnSearch);
        mnSearch.setName("MenuSearch");

        JMenuItem mntmStartSearch = new JMenuItem("Start Search");
        mnSearch.add(mntmStartSearch);
        mntmStartSearch.setName("MenuStartSearch");

        JMenuItem mntmPreviousSearch = new JMenuItem("Previous search");
        mnSearch.add(mntmPreviousSearch);
        mntmPreviousSearch.setName("MenuPreviousMatch");

        JMenuItem mntmNextMatch = new JMenuItem("Next Match");
        mnSearch.add(mntmNextMatch);
        mntmNextMatch.setName("MenuNextMatch");

        JMenuItem mntmUseRegularExpressions = new JMenuItem("Use Regular Expressions");
        mnSearch.add(mntmUseRegularExpressions);
        mntmUseRegularExpressions.setName("MenuUseRegExp");

        JSeparator separator = new JSeparator();
        separator.setBounds(81, 24, 1, 2);
        getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(59, 65, 1, 2);
        getContentPane().add(separator_1);

        mntmLoad.addActionListener(e -> {
            new ChooseFileCommand(this).execute();
            occurrenceHistory = null;
        });

        buttonLoad.addActionListener(e -> {
            new ChooseFileCommand(this).execute();
            occurrenceHistory = null;
        });

        buttonSave.addActionListener(e -> {
            new SaveFileCommand(this).execute();
        });

        mntmSave.addActionListener(e -> {
            new SaveFileCommand(this).execute();
        });


        buttonSearch.addActionListener(e -> {
            FindOccurrencesCommand command =
                    new FindOccurrencesCommand(this);
            command.run();
            occurrenceHistory = command.getHistory();
            currentMatch = 0;
            textArea.requestFocus();
            textArea.select(occurrenceHistory.occurrences.startIndexes.get(0),
                    occurrenceHistory.occurrences.endIndexes.get(0));
        });

        mntmStartSearch.addActionListener(e -> {
            FindOccurrencesCommand command =
                    new FindOccurrencesCommand(this);
            command.run();
            occurrenceHistory = command.getHistory();
            currentMatch = 0;
            textArea.requestFocus();
            textArea.select(occurrenceHistory.occurrences.startIndexes.get(0),
                    occurrenceHistory.occurrences.endIndexes.get(0));
        });


        buttonNext.addActionListener(e -> {
            if (occurrenceHistory != null) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.setSearchNext(true);
                command.execute();
            }
        });

        mntmNextMatch.addActionListener(e -> {
            if (occurrenceHistory != null) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.setSearchNext(true);
                command.execute();
            }
        });

        buttonPrev.addActionListener(e -> {
            if (occurrenceHistory != null) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.setSearchNext(false);
                command.execute();
            }
        });

        mntmPreviousSearch.addActionListener(e -> {
            if (occurrenceHistory != null) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.setSearchNext(false);
                command.execute();
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                occurrenceHistory = null;
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                occurrenceHistory = null;
            }
        });

        mntmUseRegularExpressions.addActionListener(e -> {
            if (isChecked == false) {
                chckbxNewCheckBox.setSelected(true);
                isChecked = true;
            } else {
                chckbxNewCheckBox.setSelected(false);
                isChecked = false;
            }
            System.out.println(isChecked);
        });

        mntmExit.addActionListener(event -> exit());
    }

    private void exit() {
        System.gc();
        dispose();
        System.exit(0);
    }

    private void initializeIcons() {
        ImageIcon icon = new ImageIcon("load.png");
        buttonLoad.setIcon(icon);

        icon = new ImageIcon("save.png");
        buttonSave.setIcon(icon);

        icon = new ImageIcon("search.png");
        buttonSearch.setIcon(icon);

        icon = new ImageIcon("prev.png");
        buttonPrev.setIcon(icon);

        icon = new ImageIcon("next.png");
        buttonNext.setIcon(icon);
    }

    private void initializePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(11, 28, 423, 28);
        getContentPane().add(panel);
        panel.setLayout(null);

        chckbxNewCheckBox = new JCheckBox("Use regex");
        chckbxNewCheckBox.setBounds(335, 3, 92, 23);
        panel.add(chckbxNewCheckBox);
        chckbxNewCheckBox.setName("UseRegExCheckbox");

        buttonLoad = new JButton("");
        buttonLoad.setBounds(0, 0, 28, 28);
        panel.add(buttonLoad);
        buttonLoad.setName("OpenButton");

        buttonSave = new JButton("");
        buttonSave.setBounds(32, 0, 28, 28);
        buttonSave.setName("SaveButton");
        panel.add(buttonSave);

        textField = new JTextField();
        textField.setBounds(61, 4, 177, 20);
        panel.add(textField);
        textField.setColumns(10);
        textField.setName("SearchField");

        buttonSearch = new JButton("");
        buttonSearch.setBounds(303, 0, 28, 28);
        panel.add(buttonSearch);
        buttonSearch.setName("StartSearchButton");

        buttonNext = new JButton("");
        buttonNext.setBounds(271, 0, 28, 28);
        panel.add(buttonNext);
        buttonNext.setName("NextMatchButton");

        buttonPrev = new JButton("");
        buttonPrev.setBounds(239, 0, 28, 28);
        panel.add(buttonPrev);
        buttonPrev.setName("PreviousMatchButton");

        chckbxNewCheckBox.addActionListener(e -> {
            if (isChecked == false) {
                chckbxNewCheckBox.setSelected(true);
                isChecked = true;
            } else {
                chckbxNewCheckBox.setSelected(false);
                isChecked = false;
            }
            System.out.println(isChecked);
        });
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
