package editor;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextEditor extends JFrame {
    private static final int PANE_WIDTH = 445;
    private static final int TEXTAREA_WIDTH = 425;
    private static final int PANEL_X_OFFSET = 11;
    private static final int BAR_HEIGHT = 25;
    private static final int PANE_Y_BOUND = 65;
    private static final int SEPARATOR_SIZE = 2;
    private static final int BUTTON_SIZE = 28;
    private static final int HEIGHT_BOUND = 20;
    private static final int END_SEPARATOR_X = 80;
    private static final int SEPARATOR_X = 60;

    JTextField textField;
    JTextArea textArea;
    JFileChooser chooser;
    private JCheckBox chckbxNewCheckBox;
    boolean isChecked;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JButton buttonSearch;
    private JButton buttonNext;
    private JButton buttonPrev;

    OccurrenceHistory occurrenceHistory = new OccurrenceHistory(this);
    int currentMatch = 0;
    int currentStartIndex = 0;

    private void init() {
        chooser = new JFileChooser();
        chooser.setName("FileChooser");
        add(chooser);

        getContentPane().setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(PANEL_X_OFFSET, PANE_Y_BOUND, TEXTAREA_WIDTH, 201);
        getContentPane().add(scrollPane);
        scrollPane.setName("ScrollPane");

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setName("TextArea");

        JPanel panelMenu = new JPanel();
        panelMenu.setBounds(0, 0, PANE_WIDTH, BAR_HEIGHT);
        getContentPane().add(panelMenu);
        panelMenu.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, PANE_WIDTH, BAR_HEIGHT);
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

        final JSeparator separator = new JSeparator();
        separator.setBounds(END_SEPARATOR_X, BAR_HEIGHT, SEPARATOR_SIZE, SEPARATOR_SIZE);
        getContentPane().add(separator);

        final JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(SEPARATOR_X, PANE_Y_BOUND, SEPARATOR_SIZE, SEPARATOR_SIZE);
        getContentPane().add(separator_1);

        mntmLoad.addActionListener(e -> {
            new ChooseFileCommand(this).execute();
            occurrenceHistory.clear();
        });

        buttonLoad.addActionListener(e -> {
            new ChooseFileCommand(this).execute();
            occurrenceHistory.clear();
        });

        buttonSave.addActionListener(e -> new SaveFileCommand(this).execute());

        mntmSave.addActionListener(e -> new SaveFileCommand(this).execute());


        buttonSearch.addActionListener(e -> {
            FindOccurrencesCommand command =
                    new FindOccurrencesCommand(this);
            command.execute();
            currentMatch = 0;
            if (occurrenceHistory.getSize() != 0) {
                textArea.requestFocus();
                Interval inter = occurrenceHistory.getInterval(0);
                textArea.select(inter.getStart(),
                        inter.getEnd());
            }

        });

        mntmStartSearch.addActionListener(e -> {
            FindOccurrencesCommand command =
                    new FindOccurrencesCommand(this);
            command.execute();
            currentMatch = 0;
            if (occurrenceHistory.getSize() != 0) {
                textArea.requestFocus();
                Interval inter = occurrenceHistory.getInterval(0);
                textArea.select(inter.getStart(),
                        inter.getEnd());
            }
        });


        buttonNext.addActionListener(e -> {
            if (!occurrenceHistory.isEmpty()) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.nextMatch();
            }
        });

        mntmNextMatch.addActionListener(e -> {
            if (!occurrenceHistory.isEmpty()) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.nextMatch();
            }
        });

        buttonPrev.addActionListener(e -> {
            if (!occurrenceHistory.isEmpty()) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.previousMatch();
            }
        });

        mntmPreviousSearch.addActionListener(e -> {
            if (!occurrenceHistory.isEmpty()) {
                SelectMatchCommand command = new SelectMatchCommand(this);
                command.previousMatch();
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                occurrenceHistory.clear();
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                occurrenceHistory.clear();
            }
        });

        mntmUseRegularExpressions.addActionListener(e -> {
            occurrenceHistory.clear();
            isChecked = !isChecked;
            chckbxNewCheckBox.setSelected(isChecked);
            System.out.println(isChecked);
        });

        mntmExit.addActionListener(event -> exit());
    }

    private void exit() {
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
        panel.setBounds(PANEL_X_OFFSET, BUTTON_SIZE, TEXTAREA_WIDTH, BUTTON_SIZE);
        getContentPane().add(panel);
        panel.setLayout(null);

        chckbxNewCheckBox = new JCheckBox("Use regex");
        chckbxNewCheckBox.setBounds(335, SEPARATOR_SIZE, 92, HEIGHT_BOUND);
        panel.add(chckbxNewCheckBox);
        chckbxNewCheckBox.setName("UseRegExCheckbox");

        buttonLoad = new JButton("");
        buttonLoad.setBounds(0, 0, BUTTON_SIZE, BUTTON_SIZE);
        panel.add(buttonLoad);
        buttonLoad.setName("OpenButton");

        buttonSave = new JButton("");
        buttonSave.setBounds(32, 0, BUTTON_SIZE, BUTTON_SIZE);
        buttonSave.setName("SaveButton");
        panel.add(buttonSave);

        textField = new JTextField();
        textField.setBounds(SEPARATOR_X, SEPARATOR_SIZE, 177, HEIGHT_BOUND);
        panel.add(textField);
        textField.setColumns(10);
        textField.setName("SearchField");

        buttonSearch = new JButton("");
        buttonSearch.setBounds(300, 0, BUTTON_SIZE, BUTTON_SIZE);
        panel.add(buttonSearch);
        buttonSearch.setName("StartSearchButton");

        buttonNext = new JButton("");
        buttonNext.setBounds(270, 0, BUTTON_SIZE, BUTTON_SIZE);
        panel.add(buttonNext);
        buttonNext.setName("NextMatchButton");

        buttonPrev = new JButton("");
        buttonPrev.setBounds(240, 0, BUTTON_SIZE, BUTTON_SIZE);
        panel.add(buttonPrev);
        buttonPrev.setName("PreviousMatchButton");

        chckbxNewCheckBox.addActionListener(e -> {
            isChecked = !isChecked;
            chckbxNewCheckBox.setSelected(isChecked);
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