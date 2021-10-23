package ru.academits.biluta.minesweeper.logic.record_table;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class HighScoreTable extends LinkedList<HighScoreRecord> implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private final static int RECORD_TABLE_CAPACITY = 10;

    LinkedList<HighScoreRecord> highScoreRecords;

    public HighScoreTable() {
        highScoreRecords = new LinkedList<>();
    }

    public void addHighScoreRecord(HighScoreRecord newHighScoreRecord) {
        highScoreRecords.add(newHighScoreRecord);
        highScoreRecords.sort(new HighScoreRecordsComparator());

        while (highScoreRecords.size() > RECORD_TABLE_CAPACITY) {
            highScoreRecords.removeLast();
        }
    }

    public LinkedList<HighScoreRecord> getHighScoreRecords() {
        return highScoreRecords;
    }

    @Override
    public String toString(){
        for (HighScoreRecord record: highScoreRecords) {
            System.out.println(record);
        }

        return "done";
    }
}
