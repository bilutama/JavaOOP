package ru.academits.biluta.minesweeper.view;

import ru.academits.biluta.minesweeper.logic.Game;
import ru.academits.biluta.minesweeper.logic.Minesweeper;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public interface View {
    void setResetGameButton(MouseAdapter resetGameMouseAdapter, ActionListener resetGameListener);

    void showPopupMenu(MouseEvent e);

    void initializeGame(Game minesweeper);
}