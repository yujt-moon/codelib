package com.moon.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 简单的小城
 * @author yujiangtao
 * @since 1.0
 */
public class ToolUI {

    private JFrame frame = new JFrame();

    /**
     * 启动主界面
     */
    public void launchFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        addContentPane();
        frame.setLocation(600, 400);
        frame.setSize(700, 500);
        // 设置默认的关闭功能
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setVisible(true);
    }

    /**
     * 将内容面板加入到frame中
     */
    public void addContentPane() {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        jPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        JLabel label1 = new JLabel("URLEncode: ");
        // label1.setBounds(20, 20, 200, 100);
        jPanel.add(label1);
        JTextField textField1 = new JTextField("请输入要转换的url");
        // textField1.setBounds(140, 20, 200, 100);
        jPanel.add(textField1);
        frame.add(jPanel);
    }

    /**
     * 将菜单项添加到面板中
     */
    public void addMenu() {

    }
}
