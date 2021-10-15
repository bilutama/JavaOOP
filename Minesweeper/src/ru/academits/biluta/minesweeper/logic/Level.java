package ru.academits.biluta.minesweeper.logic;

public enum Level {
    EASY(9, 9, 10),
    NORMAL(16, 16, 40),
    HARD(25, 20, 80);

    private final int width;
    private final int height;
    private final int minesCount;

    Level(int width, int height, int minesCount) {
        this.height = height;
        this.width = width;

        double MAXIMUM_MINES_COUNT_TO_CAPACITY_RATIO = 0.8;

        // Assure that mines count is not exceeding field capacity * ratio
        this.minesCount = Math.min(minesCount, (int) (height * width * MAXIMUM_MINES_COUNT_TO_CAPACITY_RATIO));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinesCount() {
        return minesCount;
    }
}
