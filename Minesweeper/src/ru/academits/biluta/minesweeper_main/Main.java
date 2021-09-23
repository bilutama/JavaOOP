package ru.academits.biluta.minesweeper_main;

import ru.academits.biluta.minesweeper.Level;
import ru.academits.biluta.minesweeper.Minesweeper;
import ru.academits.biluta.minesweeper_gui.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Minesweeper minesweeper = new Minesweeper(Level.EASY);

            View view = new View(Level.EASY);
        });
    }
}
