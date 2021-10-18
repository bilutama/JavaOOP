package ru.academits.biluta.minesweeper.gui;

import ru.academits.biluta.minesweeper.logic.Game;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public interface View {
    void setResetGameButton(MouseAdapter resetGameMouseAdapter, ActionListener resetGameListener);

    void showPopupMenu(MouseEvent e);

    void initializeGui(Game minesweeper);
}