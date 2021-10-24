package ru.academits.biluta.minesweeper.controller;

import ru.academits.biluta.minesweeper.logic.Game;
import ru.academits.biluta.minesweeper.logic.Level;
import ru.academits.biluta.minesweeper.logic.MinesweeperGame;
import ru.academits.biluta.minesweeper.gui.View;
import ru.academits.biluta.minesweeper.logic.record_table.HighScoreRecord;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Controller extends MouseAdapter {
    private Game minesweeper;
    private final View view;

    public Controller(Game minesweeper, View view) {
        this.minesweeper = minesweeper;
        this.view = view;
        this.view.setHomeButton(this, new PopupMenuHandler(), new HighScoresTableHandler());
        new GameTimeListener();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            minesweeper = new MinesweeperGame(minesweeper.getLevel());
            view.initializeGui(minesweeper);
            view.setGameTime(0L);
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            view.showPopupMenu(e);
        }
    }

    class PopupMenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minesweeper = new MinesweeperGame(Level.valueOf(e.getActionCommand()));
            view.initializeGui(minesweeper);
            view.setGameTime(0L);
        }
    }

    class HighScoresTableHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] headers = {"#", "NAME", "BEST TIME"};

            LinkedList<HighScoreRecord> records = minesweeper.getHighScoresTable().getHighScoreRecords();
            int recordsCount = records.size();
            String[][] rows = new String[recordsCount][3];

            int i = 0;

            for (HighScoreRecord record : records) {
                rows[i][0] = Integer.toString(i + 1);
                rows[i][1] = record.getNickName();
                rows[i][2] = String.format("%.1f", (double) record.getGameTime() / 1000);
                ++i;
            }

            JTable highScoreTable = new JTable(rows, headers);
            highScoreTable.setEnabled(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(highScoreTable), "HIGH SCORES", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class GameTimeListener implements ActionListener {
        Timer timer;

        public GameTimeListener() {
            timer = new Timer(1000, this);
            timer.setInitialDelay(0);
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setGameTime(minesweeper.getGameTime());
        }
    }
}