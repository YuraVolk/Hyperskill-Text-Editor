package editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindOccurrencesCommand extends Command implements Runnable {
    FindOccurrencesCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        String toFind = textEditor.textField.getText();
        String text = textEditor.textArea.getText();

        System.out.println(textEditor.currentStartIndex);
        Pattern pattern = Pattern.compile(!textEditor.isChecked ?
                ("\\Q" + toFind + "\\E") : toFind);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find(textEditor.currentStartIndex)) {
            textEditor.occurrenceHistory.addElement(matcher.start(), matcher.end());
            textEditor.currentStartIndex = matcher.end();
        }
        System.out.println("done");
    }

    @Override
    public void run() {
        execute();
    }
}
