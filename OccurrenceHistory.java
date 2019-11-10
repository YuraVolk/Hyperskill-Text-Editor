package editor;

public class OccurrenceHistory {
    private Pair occurrences;
    private TextEditor editor;

    OccurrenceHistory(TextEditor editor) {
        occurrences = new Pair();
        this.editor = editor;
    }

    void addElement(int start, int end) {
        occurrences.startIndexes.add(start);
        occurrences.endIndexes.add(end);
    }
}
