package ru.academits.biluta.minesweeper.logic;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MinesweeperTimer {
    //private static final long TIME_SHIFT_MILLIS = 10_800_000;

    long startDate;
    Timer timer;

    public MinesweeperTimer() {
        startDate = System.currentTimeMillis();

        ActionListener actionListener = e -> {
            Date date = new Date();
            //DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            //String time = timeFormat.format(date.getTime() - startDate - TIME_SHIFT_MILLIS);
            System.out.println(date.getTime() - startDate);
        };

        timer = new Timer(1000, actionListener);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
