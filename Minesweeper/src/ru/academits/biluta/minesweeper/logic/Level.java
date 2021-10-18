package ru.academits.biluta.minesweeper.logic;

public enum Level {
    EASY(9, 9, 10),
    NORMAL(16, 16, 40),
    HARD(25, 20, 80);

    private final int mineFieldWidth;
    private final int mineFieldHeight;
    private final int minesCount;

    Level(int mineFieldWidth, int mineFieldHeight, int minesCount) {
        this.mineFieldHeight = mineFieldHeight;
        this.mineFieldWidth = mineFieldWidth;

        double MAXIMUM_MINES_COUNT_TO_CAPACITY_RATIO = 0.8;

        // Assure that mines count is not exceeding field capacity * ratio
        this.minesCount = Math.min(minesCount, (int) (mineFieldHeight * mineFieldWidth * MAXIMUM_MINES_COUNT_TO_CAPACITY_RATIO));
    }

    public int getMineFieldWidth() {
        return mineFieldWidth;
    }

    public int getMineFieldHeight() {
        return mineFieldHeight;
    }

    public int getMinesCount() {
        return minesCount;
    }
}
