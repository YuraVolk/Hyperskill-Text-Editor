package editor;

public class OccurrenceHistory {
    Pair occurrences;

    OccurrenceHistory() {
        occurrences = new Pair();
    }

    void addElement(int start, int end) {
        occurrences.startIndexes.add(start);
        occurrences.endIndexes.add(end);
    }
}
