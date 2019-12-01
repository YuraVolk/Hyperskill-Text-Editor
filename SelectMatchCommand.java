package editor;

class SelectMatchCommand extends Command implements OccurrenceSearchEngine {
    private OccurrenceHistory history;

    SelectMatchCommand(TextEditor editor) {
        super(editor);
    }


    @Override
    public void execute() {
       /* OccurrenceHistory history = textEditor.occurrenceHistory;
        FindOccurrencesCommand command = new FindOccurrencesCommand(textEditor);
        command.run();
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
*/
        textEditor.textArea.requestFocus();
        Interval inter = history
                .getInterval(textEditor.currentMatch);

        System.out.println(inter.getStart());
        textEditor.textArea.select(
                inter.getStart(),
                inter.getEnd());
    }


    @Override
    public void nextMatch() {
        history = textEditor.occurrenceHistory;
        FindOccurrencesCommand command = new FindOccurrencesCommand(textEditor);
        command.run();

        textEditor.currentMatch++;
        if (textEditor.currentMatch >= history.getSize()) {
            textEditor.currentMatch = 0;
        }

        execute();
    }

    @Override
    public void previousMatch() {
        history = textEditor.occurrenceHistory;
        FindOccurrencesCommand command = new FindOccurrencesCommand(textEditor);
        command.run();

        textEditor.currentMatch--;
        if (textEditor.currentMatch < 0) {
            textEditor.currentMatch = history.getSize() - 1;
        }

        execute();
    }
}
