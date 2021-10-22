package minesweeper_v0.logic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinesweeperGame implements Game {
    private final int mineFieldWidth;
    private final int mineFieldHeight;

    // Game state
    private final int[][] nearbyMinesCountMatrix; // -1 stands for mine
    private final int[][] revealedCells;
    private final int[][] flaggedCells;
    private int closedCellsCount;
    private final ArrayList<Cell> minedCells;

    private final Level level;

    public MinesweeperGame(Level level) {
        this.level = level;

        mineFieldHeight = level.getMineFieldHeight();
        mineFieldWidth = level.getMineFieldWidth();

        nearbyMinesCountMatrix = new int[mineFieldHeight][mineFieldWidth];
        revealedCells = new int[mineFieldHeight][mineFieldWidth];
        flaggedCells = new int[mineFieldHeight][mineFieldWidth];

        closedCellsCount = mineFieldHeight * mineFieldWidth;
        minedCells = new ArrayList<>(level.getMinesCount());
    }

    public Level getLevel() {
        return level;
    }

    public int getClosedCellsCount() {
        return closedCellsCount;
    }

    public int[][] getFlaggedCells() {
        return flaggedCells;
    }

    public int[][] getNearbyMinesCountMatrix() {
        return nearbyMinesCountMatrix;
    }

    public int[][] getRevealedCells() {
        return revealedCells;
    }

    public ArrayList<Cell> getMinedCells() {
        return minedCells;
    }

    @Override
    public Deque<Cell> getCellsRangeToReveal(int revealedCellX, int revealedCellY) {
        if (closedCellsCount == mineFieldHeight * mineFieldWidth) {
            // TODO: add timer, start counting
            initializeGame(revealedCellX, revealedCellY);
        }

        revealedCells[revealedCellY][revealedCellX] = 1;
        int nearbyMinesCountRevealed = nearbyMinesCountMatrix[revealedCellY][revealedCellX];
        Cell cell = new Cell(revealedCellX, revealedCellY, nearbyMinesCountRevealed);

        Deque<Cell> cellsRangeToReveal = new LinkedList<>();
        cellsRangeToReveal.addLast(cell);

        // Return deque with a single cell element
        if (nearbyMinesCountRevealed != 0) {
            // Mine is open
            closedCellsCount -= 1;
            return cellsRangeToReveal;
        }

        Deque<Cell> cellsQueue = new LinkedList<>();
        cellsQueue.addLast(cell);

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

    private void initializeGame(int firstRevealedCellX, int firstRevealedCellY) {
        int minesCount = level.getMinesCount();
        int excludedIndex = firstRevealedCellX + firstRevealedCellY * mineFieldWidth;

        // Get cells indices to place mines except first revealed cell
        Integer[] randomIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines among closed cells
        for (int counter = 0; counter < minesCount; ++counter) {
            int cellY = randomIndices[counter] / mineFieldWidth;
            int cellX = randomIndices[counter] % mineFieldWidth;

            nearbyMinesCountMatrix[cellY][cellX] = -1;
            minedCells.add(new Cell(cellX, cellY, nearbyMinesCountMatrix[cellY][cellX]));

            for (int j = cellX - 1; j < cellX + 2; ++j) {
                for (int i = cellY - 1; i < cellY + 2; ++i) {
                    if (isInRange(j, i) && nearbyMinesCountMatrix[i][j] != -1) {
                        nearbyMinesCountMatrix[i][j] += 1;
                    }
                }
            }
        }
    }

    private Integer[] getRandomCellsIndices(int numbersCount, int excludedNumber) {
        List<Integer> cellsIndices = IntStream.range(0, mineFieldHeight * mineFieldWidth - 2)
                .filter(index -> index != excludedNumber)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(cellsIndices);

        Integer[] randomCellIndices = new Integer[numbersCount];
        new ArrayList<>(cellsIndices.subList(0, numbersCount)).toArray(randomCellIndices);

        return randomCellIndices;
    }

    private boolean isInRange(int column, int row) {
        return column >= 0 && row >= 0 && column < mineFieldWidth && row < mineFieldHeight;
    }
}