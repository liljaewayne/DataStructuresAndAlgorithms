package com.visual.jframe;

import javax.swing.*;
import java.awt.*;

public class Hello {

    public static void main(String[] args) {

        // 建议将窗口的逻辑放到EventQueue中
        // 作用是将窗体的代码放到另一个线程中(事件分发线程)
        // 避免窗体事件和数据逻辑之间的线程冲突问题, 是官方推荐的写法
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Welcome");

            frame.setSize(600, 300);

            // 默认的×是将框架隐藏, 在这里将其设置为关闭
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 窗口改变大小, 其中的部件自适应不易控制, 这里将窗口不可调整大小
            frame.setResizable(false);

            frame.setVisible(true);
        });

    }

}
