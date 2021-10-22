package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

public class test {
    Timer timer;

    public test(){
        ActionListener actionListener = e -> {
            Date date = new Date();
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String time = timeFormat.format(date);
            System.out.println(time);
        };

        timer = new Timer(1000, actionListener);
        timer.setInitialDelay(0);
        timer.start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            new test();
        });
    }
}
