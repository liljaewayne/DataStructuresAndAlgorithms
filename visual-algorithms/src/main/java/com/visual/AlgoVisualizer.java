package com.visual;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 控制器
 */
public class AlgoVisualizer {

    private Circle[] circles;   // 数据
    private AlgoFrame frame;    // 视图

    private boolean animated = true;


    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            // 添加监听器！实现键盘交互
            frame.addKeyListener(new AlgoKeyListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);


            if (animated) {
                // 更新数据
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }

        }
    }

    /**
     * 监听器， 用来监听键盘响应事件， 让程序可交互
     */
    private class AlgoKeyListener extends KeyAdapter /* 适配器模式 */ {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                animated = !animated;
            }
        }
    }


    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);

    }
}
