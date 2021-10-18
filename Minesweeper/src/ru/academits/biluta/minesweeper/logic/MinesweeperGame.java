package ru.academits.biluta.minesweeper.logic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinesweeperGame implements Game {
    private final int width;
    private final int height;

    // Game state
    private final int[][] nearbyMinesCountMatrix; // -1 stands for mine
    private final int[][] revealedCells;
    private int closedCellsCount;
    private final ArrayList<Cell> minedCells;

    // Game level and state
    private final Level level;

    public MinesweeperGame(Level level) {
        this.level = level;

        height = level.getHeight();
        width = level.getWidth();

        nearbyMinesCountMatrix = new int[height][width];
        revealedCells = new int[height][width];

        closedCellsCount = height * width;
        minedCells = new ArrayList<>(level.getMinesCount());
    }

    public Level getLevel() {
        return level;
    }

    public int getClosedCellsCount() {
        return closedCellsCount;
    }

    public ArrayList<Cell> getMines() {
        return minedCells;
    }

    @Override
    public Deque<Cell> getCellsRangeToReveal(int revealedCellX, int revealedCellY) {
        if (closedCellsCount == height * width) {
            // TODO: add timer, start counting
            initializeGame(revealedCellX, revealedCellY);
        }

        Deque<Cell> cellsQueue = new LinkedList<>();
        Deque<Cell> cellsRangeToReveal = new LinkedList<>();

        Cell cell = new Cell(revealedCellX, revealedCellY, nearbyMinesCountMatrix[revealedCellY][revealedCellX]);

        cellsQueue.addLast(cell);
        cellsRangeToReveal.addLast(cell);

        revealedCells[revealedCellY][revealedCellX] = 1;

        while (!cellsQueue.isEmpty()) {
            Cell currentCell = cellsQueue.removeFirst();

            int currentCellX = currentCell.getX();
            int currentCellY = currentCell.getY();

            // Collecting neighbouring cells
            if (nearbyMinesCountMatrix[currentCellY][currentCellX] == 0) {
                for (int j = currentCellY - 1; j < currentCellY + 2; ++j) {
                    for (int i = currentCellX - 1; i < currentCellX + 2; ++i) {
                        if (isInRange(i, j) && revealedCells[j][i] == 0) {
                            revealedCells[j][i] = 1;
                            Cell nextCell = new Cell(i, j, nearbyMinesCountMatrix[j][i]);

                            cellsQueue.addLast(nextCell);
                            cellsRangeToReveal.addLast(nextCell);
                        }
                    }
                }
            }
        }

        closedCellsCount -= cellsRangeToReveal.size();
        return cellsRangeToReveal;
    }

    private void initializeGame(int firstOpenedCellX, int firstOpenedCellY) {
        int minesCount = level.getMinesCount();
        int excludedIndex = firstOpenedCellX + firstOpenedCellY * width;

        // Get cells indices to place mines except first revealed cell
        Integer[] randomIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines among closed cells
        for (int counter = 0; counter < minesCount; ++counter) {
            int cellY = randomIndices[counter] / width;
            int cellX = randomIndices[counter] % width;

            nearbyMinesCountMatrix[cellY][cellX] = -1;
            minedCells.add(new Cell(cellX, cellY, nearbyMinesCountMatrix[cellY][cellX]));

            for (int j = cellX - 1; j < cellX + 2; ++j) {
                for (int i = cellY - 1; i < cellY + 2; ++i) {
                    if (isInRange(i, j) && nearbyMinesCountMatrix[i][j] != -1) {
                        nearbyMinesCountMatrix[i][j] += 1;
                    }
                }
            }
        }
    }

    private Integer[] getRandomCellsIndices(int numbersCount, int excludedNumber) {
        List<Integer> cellsIndices = IntStream.range(0, height * width - 2)
                .filter(index -> index != excludedNumber)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(cellsIndices);

        Integer[] randomCellIndices = new Integer[numbersCount];
        new ArrayList<>(cellsIndices.subList(0, numbersCount)).toArray(randomCellIndices);

        return randomCellIndices;
    }

    private boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }
}