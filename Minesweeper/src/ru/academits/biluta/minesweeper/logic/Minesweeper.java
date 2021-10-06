package ru.academits.biluta.minesweeper.logic;

import ru.academits.biluta.minesweeper.controller.GameState;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Minesweeper implements Game {
    private final int width;
    private final int height;

    // Game state
    private int[][] mines;
    private int[][] neighbouringMinesCount;
    private int[][] openedCells;
    private int closedCellsCount;

    // Game level and state
    private final Level level;
    private GameState gameState;

    public Minesweeper(Level level) {
        this.level = level;

        height = level.getHeight();
        width = level.getWidth();

        closedCellsCount = height * width;

        gameState = GameState.NEW_GAME;
    }

    @Override
    public Deque<Cell> resumeGame(Cell nextCell) {
        Deque<Cell> openedCells = new LinkedList<>();

        if (closedCellsCount == height * width) {
            startGame(nextCell);
            // TODO: start timer
            openedCells.addLast(nextCell);
            gameState = GameState.NEXT_TURN;
            --closedCellsCount;

            return openedCells;
        }

        --closedCellsCount;

        if (hasMine(nextCell)) {
            gameState = GameState.LOSE;
            endGame();
        }

        // NEXT TURN
        if (closedCellsCount == level.getMinesCount()) {
            gameState = GameState.WIN;
            endGame();
        }

        return openCells(nextCell);
    }

    private void endGame() {
        // TODO: stop timer
        if (gameState == GameState.WIN) {
            // check records table
        }

        // code for lost game
    }

    private boolean hasMine(Cell cell) {
        return mines[cell.getX()][cell.getY()] == 1;
    }

    private void startGame(Cell firstOpenedCell) {
        mines = new int[height][width];
        neighbouringMinesCount = new int[height][width];
        openedCells = new int[height][width];

        int firstOpenedCellX = firstOpenedCell.getX();
        int firstOpenedCellY = firstOpenedCell.getY();

        placeMines(mines, level.getMinesCount(), firstOpenedCellX + firstOpenedCellY / width);
        initializeAdjacentMinesCountMatrix();

        System.out.println("MINE FIELD");
        printArray(mines);

        System.out.println("NEIGHBOURING MINES COUNT");
        printArray(neighbouringMinesCount);
    }

    private void placeMines(int[][] mines, int minesCount, int excludedIndex) {
        // Get cells indices to place mines except first opened cell
        Integer[] randomCellIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines among closed cells
        for (int i = 0; i < minesCount; ++i) {
            mines[randomCellIndices[i] / width][randomCellIndices[i] % width] = 1;
        }

        //TODO: remove
        //printArray(mines);
    }

    private void initializeAdjacentMinesCountMatrix() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (mines[i][j] == 1) {
                    neighbouringMinesCount[i][j] = -1;
                    continue;
                }

                neighbouringMinesCount[i][j] = getAdjacentMinesCount(i, j);
            }
        }
    }

    public int getAdjacentMinesCount(int x, int y) {
        int adjacentMinesCount = 0;

        for (int j = y - 1; j < y + 2; ++j) {
            if (j >= 0 && j < height) {
                for (int i = x - 1; i < x + 2; ++i) {
                    if (i >= 0 && i < width) {
                        adjacentMinesCount += mines[i][j];
                    }
                }
            }
        }

        return adjacentMinesCount;
    }

    private Integer[] getRandomCellsIndices(int minesCount, int excludedValue) {
        List<Integer> cellsIndices = IntStream.range(0, height * width - 1).filter(x -> x != excludedValue).boxed().collect(Collectors.toList());
        Collections.shuffle(cellsIndices);

        Integer[] randomCellIndices = new Integer[minesCount];
        new ArrayList<>(cellsIndices.subList(0, minesCount)).toArray(randomCellIndices);

        return randomCellIndices;
    }

    private Deque<Cell> openCells(Cell cell) {
        Deque<Cell> cellsQueue = new LinkedList<>();
        Deque<Cell> cellsToOpen = new LinkedList<>();

        cellsQueue.addLast(cell);
        cellsToOpen.addLast(cell);

        openedCells[cell.getY()][cell.getY()] = 1;

        while (!cellsQueue.isEmpty()) {
            Cell current = cellsQueue.removeFirst();

            int currentX = current.getX();
            int currentY = current.getY();

            if (neighbouringMinesCount[currentX][currentY] == 0) {
                for (int j = currentY - 1; j < currentY + 2; ++j) {
                    for (int i = currentX - 1; i < currentX + 2; ++i) {
                        if (isInRange(i, j) && openedCells[i][j] == 0) {
                            openedCells[i][j] = 1;
                            Cell nextCell = new Cell(i, j, neighbouringMinesCount[i][j]);

                            cellsQueue.addLast(nextCell);
                            cellsToOpen.addLast(nextCell);
                        }
                    }
                }
            }
        }

        return cellsToOpen;
    }

    private boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    // TODO: remove debugging code
    private static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int number : row) {
                System.out.printf("%3d ", number);
            }

            System.out.println();
        }
    }
}