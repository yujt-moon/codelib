package com.moon.ui;

import com.moon.concurrency.gui.SwingUtilities;

/**
 * 桌面程序入口
 * @author yujiangtao
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToolUI toolUI = new ToolUI();
                toolUI.launchFrame();
            }
        });
    }
}
