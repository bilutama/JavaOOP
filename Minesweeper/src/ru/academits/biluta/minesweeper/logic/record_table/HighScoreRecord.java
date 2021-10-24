package ru.academits.biluta.minesweeper.logic.record_table;

import java.io.Serial;
import java.io.Serializable;

public class HighScoreRecord implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    private final String nickName;
    private final long gameTime;

    public HighScoreRecord(String nickName, long gameTime) {
        this.nickName = nickName;
        this.gameTime = gameTime;
    }

    public long getGameTime() {
        return gameTime;
    }

    public String getNickName() {
        return nickName;
    }

    public String toString() {
        return String.format("%s - %.1f sec", nickName, (double) gameTime / 1000);
    }
}
