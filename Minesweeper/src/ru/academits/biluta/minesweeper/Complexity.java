package ru.academits.biluta.minesweeper;

public enum Complexity {
    EASY(9, 9, 10),
    NORMAL(15, 15, 15),
    HARD(35, 25, 20);

    private final int width;
    private final int height;
    private final int minesCount;

    Complexity(int width, int height, int minesCount) {
        this.height = height;
        this.width = width;

        double MAXIMUM_MINES_COUNT_TO_CAPACITY_RATIO = 0.5;

        // Assure that mines count is not exceeding field capacity multiplied by ratio
        int maximumMinesCount = (int) (height * width * MAXIMUM_MINES_COUNT_TO_CAPACITY_RATIO);
        this.minesCount = Math.min(minesCount, maximumMinesCount);
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
