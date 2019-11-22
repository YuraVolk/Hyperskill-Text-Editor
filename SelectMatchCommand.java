package editor;

class SelectMatchCommand extends Command {
    private boolean searchNext;

    SelectMatchCommand(TextEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        OccurrenceHistory history = textEditor.occurrenceHistory;
        if (searchNext) {
            textEditor.currentMatch++;
            if (textEditor.currentMatch >= history.getSize()) {
                textEditor.currentMatch = 0;
            }
        } else {
            textEditor.currentMatch--;
            if (textEditor.currentMatch < 0) {
                textEditor.currentMatch = history.getSize() - 1;
            }
        }

        textEditor.textArea.requestFocus();
        Interval inter = history
                .getInterval(textEditor.currentMatch);

        textEditor.textArea.select(
                inter.getStart(),
                inter.getEnd());
    }

    void setSearchNext(boolean isNext) {
        searchNext = isNext;
    }
}
