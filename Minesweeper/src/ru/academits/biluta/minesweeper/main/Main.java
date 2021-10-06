package ru.academits.biluta.minesweeper.main;

import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.gui.View;
import ru.academits.biluta.minesweeper.logic.Minesweeper;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View(Level.EASY, new Minesweeper(Level.EASY));
        });
    }
}
