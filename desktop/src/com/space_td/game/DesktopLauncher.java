package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.space_td.game.GameMain;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        DebugIU.runDbUI();
		data.init();
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Space TD | v0.3 UI update | "); //TODO: пофиксить загрузчик сплешей что бы вставить data.splashes[Utils.randInt(0, data.splashes.length-1)] сюда
        config.setResizable(false);
        new Lwjgl3Application(new GameMain(), config);
//        Runnable rb = new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    if (data.splashes != null) {
//						config.setTitle();
//                    }
//                }
//            }
//        }


    }
}
