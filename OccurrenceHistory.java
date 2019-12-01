package editor;

import java.util.ArrayList;
import java.util.List;

class OccurrenceHistory {
    private List<Interval> occurrenceList;

    protected TextEditor textEditor;

    OccurrenceHistory(TextEditor editor) {
        this.textEditor = editor;
        occurrenceList = new ArrayList<>();
    }

    void addElement(int start, int end) {
        occurrenceList.add(new Interval(start, end));
    }

    Interval getInterval(int i) {
        return occurrenceList.get(i);
    }

    int getSize() {
        return occurrenceList.size();
    }

    void clear() {
        occurrenceList.clear();
        textEditor.currentStartIndex = 0;
    }

    boolean isEmpty() {
        return occurrenceList.isEmpty();
    }
}
