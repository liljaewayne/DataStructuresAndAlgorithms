package com.visual;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AlgoVisHelper {
    private AlgoVisHelper() {
    }

    public static void setStrokeWidth(Graphics2D g2d, int strokeWidth) {
        g2d.setStroke(new BasicStroke(strokeWidth, /*线段的端点是圆的*/BasicStroke.CAP_ROUND, /*拐角的点是圆的*/BasicStroke.JOIN_ROUND));
    }

    /**
     * 在(x,y)点为切点的地方创建一个半径为r的`空心圆`
     *
     * @param g2d
     * @param x
     * @param y
     * @param r
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D cycle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(cycle);
    }

    /**
     * 在(x,y)点为切点的地方创建一个半径为r的`实心圆`
     *
     * @param g2d
     * @param x
     * @param y
     * @param r
     */
    public static void fillCycle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D cycle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(cycle);
    }

    public static void setColor(Graphics2D g2d, Color color) {
        /*
        java中的图形绘制是基于状态的(大多数图形库都是这样的), setColor就是状态变了, 接下来写的都是在这个状态的
         */
        g2d.setColor(color);
    }

    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }
}
