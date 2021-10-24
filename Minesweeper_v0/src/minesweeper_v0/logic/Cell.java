package minesweeper_v0.logic;

public class Cell {
    private final int x;
    private final int y;
    private final int neighbouringMinesCount;

    public Cell(int x, int y, int neighbouringMinesCount) {
        this.x = x;
        this.y = y;
        this.neighbouringMinesCount = neighbouringMinesCount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNeighbouringMinesCount() {
        return neighbouringMinesCount;
    }

    public boolean isEqual(Cell cell) {
        return (x == cell.getX() && y == cell.getY());
    }
}