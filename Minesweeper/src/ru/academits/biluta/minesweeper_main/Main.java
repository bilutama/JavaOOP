package ru.academits.biluta.minesweeper_main;

import ru.academits.biluta.minesweeper.Complexity;
import ru.academits.biluta.minesweeper.Minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper(Complexity.EASY);
        minesweeper.initializeGame();

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println(minesweeper.getAdjacentMinesCount(x, y));
    }
}
