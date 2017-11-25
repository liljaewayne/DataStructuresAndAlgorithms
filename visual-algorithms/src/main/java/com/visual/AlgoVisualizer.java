package com.visual;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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


            // 添加监听器！实现 键盘/鼠标 交互
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());

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
     * 监听器， 用来监听鼠标响应事件
     */
    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            System.out.println("origin point (contains menu bar): " + event.getPoint());

            // 处理menu bar的高度影响的y轴坐标
            event.translatePoint(
                    0,
                    -(frame.getBounds().height - frame.getCanvasHeight())
            );

            System.out.println("translate point: " + event.getPoint());

            for (Circle circle : circles) {
                if (circle.contain(event.getPoint())) {
                    circle.filled = !circle.filled;
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
