package com.space_td.game;
import javax.swing.*;

public class DebugIU {
    public static void main(String[] args) {


    }
    public static void runDbUI(){
        MyThread thread = new MyThread();
        thread.start();
    }
}
class MyThread extends Thread {

    @Override
    public void run() {
//        while(true) {
//            System.out.println("Сообщение из потока");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        JFrame frame = new JFrame("Space TD Debug UI"); // создание окна
        JLabel label = new JLabel(); // создание текстового поля

        frame.setSize(200, 200); // установка размеров окна
        frame.setLocationRelativeTo(null); // установка окна по центру экрана
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(label); // добавление текстового поля на окно

        // запуск обновления текста каждые 100 миллисекунд
        Timer timer = new Timer(100, e -> {
            label.setText("This text is constantly updating");
        });
        timer.start();

        frame.setVisible(true); // отображение окна
    }
}

