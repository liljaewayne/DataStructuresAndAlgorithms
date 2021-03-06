package com.visual;

import javax.swing.*;
import java.awt.*;

/**
 * 视图
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

//        setSize(canvasWidth, canvasHeight);

        // frame不能绘制, 只能在画布上绘制
        AlgoCanvas canvas = new AlgoCanvas();

//        canvas.setPreferredSize/*首选的*/(new Dimension/*面积*/(canvasWidth, canvasHeight));

        setContentPane(canvas);

        // 在确保系统也不会修改我们窗口的大小后进行pack， 这样就解决了windows下窗口坐标不对应的问题
        setResizable(false);
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

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);// 是否支持双缓存
            // 这也是默认的JPanel的构造函数
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 屏幕坐标系, 左上角为原点, y轴向下
//            g.drawOval/*椭圆形*/(50, 50, 300, 300);

            // 使用g2d代替g
            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿, 框架已经提供
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 双缓存, 在JPanel的构造函数中就默认支持

            // 具体绘制
//            Ellipse2D cycle = new Ellipse2D.Double(50, 50, 300, 300);

//            AlgoVisHelper.setStrokeWidth(g2D, 5);// 设置笔画宽度为10
//            AlgoVisHelper.setColor(g2D, Color.BLUE);// 设置颜色
//            AlgoVisHelper.fillCircle(g2D, canvasWidth / 2, canvasHeight / 2, 200);// 实心圆
//            AlgoVisHelper.setColor(g2D, Color.RED);
//            AlgoVisHelper.strokeCycle(g2D, canvasWidth / 2, canvasHeight / 2, 200);// 空心圆


            AlgoVisHelper.setStrokeWidth(g2d, 1);
            AlgoVisHelper.setColor(g2d, Color.RED);

            for (Circle circle : circles) {
                if (circle.filled) {
                    AlgoVisHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
                } else {
                    AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                }
            }
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

    private Circle[] circles;

    public void render(Circle[] circles) {
        this.circles = circles;

        // JPanel的方法, 将JFrame中所有的控件刷新一遍
        repaint();
    }


}
