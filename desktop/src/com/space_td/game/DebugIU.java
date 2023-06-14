package com.space_td.game;

import javax.swing.*;

public class DebugIU {
    public static String data = "nothing to show";

    public static void main(String[] args) {


    }

    public static void runDbUI() {
        MyThread thread = new MyThread();
        thread.start();
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        JFrame frame = new JFrame("Space TD Debug UI");
        JLabel label = new JLabel();

        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(label);


        Timer timer = new Timer(100, e -> {
            DebugIU.data = GameMain.debugData;
            label.setText(DebugIU.data);
        });
        timer.start();

        frame.setVisible(true);
    }
}

