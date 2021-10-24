package ru.academits.biluta.minesweeper.logic.record_table;

import ru.academits.biluta.minesweeper.logic.Level;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class HighScoreTable implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private final static int HIGH_SCORES_TABLE_CAPACITY = 10;

    private int recordsCount;
    private long maximumTime;

    LinkedList<HighScoreRecord> highScoreRecords;

    public HighScoreTable() {
        highScoreRecords = new LinkedList<>();
    }

    public void addHighScoreRecord(Level level, String nickname, long gameTime) {
        highScoreRecords.addLast(new HighScoreRecord(level, nickname, gameTime));
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
}