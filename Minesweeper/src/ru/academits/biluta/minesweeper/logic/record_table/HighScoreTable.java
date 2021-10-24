package ru.academits.biluta.minesweeper.logic.record_table;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class HighScoreTable extends LinkedList<HighScoreRecord> implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private final static int HIGH_SCORES_TABLE_CAPACITY = 10;

    private int recordsCount;
    private long maximumTime;

    LinkedList<HighScoreRecord> highScoreRecords;

    public HighScoreTable() {
        highScoreRecords = new LinkedList<>();
    }

    public void addHighScoreRecord(HighScoreRecord newHighScoreRecord) {
        highScoreRecords.add(newHighScoreRecord);
        highScoreRecords.sort(new HighScoreRecordsComparator());

        while (highScoreRecords.size() > HIGH_SCORES_TABLE_CAPACITY) {
            highScoreRecords.removeLast();
        }

        recordsCount = highScoreRecords.size();
        maximumTime = highScoreRecords.getLast().getGameTime();
    }

    public LinkedList<HighScoreRecord> getHighScoreRecords() {
        return highScoreRecords;
    }

    public boolean isValidToAdd(long newGameTime) {
        return recordsCount < HIGH_SCORES_TABLE_CAPACITY || newGameTime < maximumTime;
    }

    // TODO: delete
    @Override
    public String toString() {
        for (HighScoreRecord record : highScoreRecords) {
            System.out.println(record);
        }

        return "done";
    }
}
