package editor;

import java.util.ArrayList;
import java.util.List;

class OccurrenceHistory {
    private List<Interval> occurrenceList;

    OccurrenceHistory() {
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
}
