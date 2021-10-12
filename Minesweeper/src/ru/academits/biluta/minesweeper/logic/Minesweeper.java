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
    private int[][] nearbyMinesCountMatrix;
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

    public int[][] getMines() {
        return mines;
    }

    @Override
    public Deque<Cell> nextTurn(int column, int row) {
        if (closedCellsCount == height * width) {
            // TODO: start timer
            initializeGame(column, row);
        }

        Deque<Cell> cellsToOpen = new LinkedList<>();

//        if (hasMine(column, row)) {
//            gameState = GameState.LOSE;
//            endGame();
//        }

        // NEXT TURN
//        if (closedCellsCount == level.getMinesCount()) {
//            gameState = GameState.WIN;
//            endGame();
//        }

        cellsToOpen = openCells(column, row);
        closedCellsCount -= cellsToOpen.size();
        System.out.println("Closed cells:" + closedCellsCount);

        return cellsToOpen;
    }

    private void endGame() {
        // TODO: stop timer
        if (gameState == GameState.WIN) {
            // check records table
        }

        // code for lost game
    }

    private void initializeGame(int column, int row) {
        mines = new int[height][width];
        nearbyMinesCountMatrix = new int[height][width];
        openedCells = new int[height][width];

        placeMines(mines, level.getMinesCount(), column + row * width);
        initializeNearbyMinesCountMatrix();

//        System.out.println("MINE FIELD");
//        printArray(mines);
//
//        System.out.println("NEIGHBOURING MINES COUNT");
//        printArray(nearbyMinesCount);
    }

    private void placeMines(int[][] mines, int minesCount, int excludedIndex) {
        // Get cells indices to place mines except first opened cell
        Integer[] randomIndices = getRandomCellsIndices(minesCount, excludedIndex);

        // Place mines among closed cells
        for (int i = 0; i < minesCount; ++i) {
            mines[randomIndices[i] / width][randomIndices[i] % width] = 1;
        }
    }

    private Integer[] getRandomCellsIndices(int numbersCount, int excludedValue) {
        List<Integer> cellsIndices = IntStream.range(0, height * width - 2).filter(x -> x != excludedValue).boxed().collect(Collectors.toList());
        Collections.shuffle(cellsIndices);

        Integer[] randomCellIndices = new Integer[numbersCount];
        new ArrayList<>(cellsIndices.subList(0, numbersCount)).toArray(randomCellIndices);

        return randomCellIndices;
    }

    private void initializeNearbyMinesCountMatrix() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (mines[i][j] == 1) {
                    nearbyMinesCountMatrix[i][j] = -1;
                    continue;
                }

                nearbyMinesCountMatrix[i][j] = getNearbyMinesCount(j, i);
            }
        }
    }

    public Level getLevel() {
        return level;
    }

    public int getNearbyMinesCount(int column, int row) {
        int nearbyMinesCount = 0;

        for (int j = column - 1; j < column + 2; ++j) {
            if (j >= 0 && j < width) {
                for (int i = row - 1; i < row + 2; ++i) {
                    if (i >= 0 && i < height) {
                        nearbyMinesCount += mines[i][j];
                    }
                }
            }
        }

        return nearbyMinesCount;
    }

    private Deque<Cell> openCells(int column, int row) {
        Deque<Cell> cellsQueue = new LinkedList<>();
        Deque<Cell> cellsToOpen = new LinkedList<>();

        Cell cell = new Cell(column, row, nearbyMinesCountMatrix[row][column]);

        cellsQueue.addLast(cell);
        cellsToOpen.addLast(cell);

        openedCells[row][column] = 1;

        while (!cellsQueue.isEmpty()) {
            Cell current = cellsQueue.removeFirst();

            int currentX = current.getX();
            int currentY = current.getY();

            if (nearbyMinesCountMatrix[currentY][currentX] == 0) {
                for (int j = currentY - 1; j < currentY + 2; ++j) {
                    for (int i = currentX - 1; i < currentX + 2; ++i) {
                        if (isInRange(i, j) && openedCells[j][i] == 0) {
                            openedCells[j][i] = 1;
                            Cell nextCell = new Cell(i, j, nearbyMinesCountMatrix[j][i]);

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