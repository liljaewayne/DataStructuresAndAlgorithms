package com.visual;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

//        setSize(canvasWidth, canvasHeight);

        // frame不能绘制, 只能在画布上绘制
        AlgoPanel canvas = new AlgoPanel();

//        canvas.setPreferredSize/*首选的*/(new Dimension/*面积*/(canvasWidth, canvasHeight));

        setContentPane(canvas);
        pack/*压缩,拧紧*/();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }


    private class AlgoPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 屏幕坐标系, 左上角为原点, y轴向下
            g.drawOval/*椭圆形*/(50, 50, 300, 300);
        }

        /**
         * 覆盖这个方法是因为, 在创建frame时候, swing框架会自动的调用这个方法获取画布的大小
         * 这样做的话, 在上面的调用时就不用显式声明了
         *
         * @see AlgoFrame(String, int, int) 中的注释, 对canvas的操作
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
