package editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindOccurrencesCommand extends Command implements Runnable {
    private OccurrenceHistory history;

    FindOccurrencesCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        history = new OccurrenceHistory();

        String toFind = textEditor.textField.getText();
        String text = textEditor.textArea.getText();

        Pattern pattern = Pattern.compile(!textEditor.isChecked ?
                ("\\Q" + toFind + "\\E") : toFind);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            history.addElement(matcher.start(), matcher.end());
        }
        System.out.println("done");
    }

    @Override
    public void run() {
        execute();
    }

    OccurrenceHistory getHistory() {
        return history;
    }
}
