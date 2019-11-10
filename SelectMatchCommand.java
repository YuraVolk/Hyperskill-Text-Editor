package editor;

public class SelectMatchCommand extends Command {
    boolean searchNext;

    SelectMatchCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        Pair history = textEditor.occurrenceHistory.occurrences;
        if (searchNext) {
            textEditor.currentMatch++;
            if (textEditor.currentMatch >= history.startIndexes.size()) {
                textEditor.currentMatch = 0;
            }
        } else {
            textEditor.currentMatch--;
            if (textEditor.currentMatch < 0) {
                textEditor.currentMatch = history.startIndexes.size() - 1;
            }
        }

        textEditor.textArea.requestFocus();
        textEditor.textArea.select(history.startIndexes.get(textEditor.currentMatch),
                history.endIndexes.get(textEditor.currentMatch));
    }

    void setSearchNext(boolean isNext) {
        searchNext = isNext;
    }
}
