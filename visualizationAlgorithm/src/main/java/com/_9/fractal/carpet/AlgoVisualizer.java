package com._9.fractal.carpet;

import com._9.fractal.rectangle.FractalData;
import com.template.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int depth) {

        data = new FractalData(depth);
        int sceneWidth = (int) Math.pow(3, depth);
        int sceneHeight = (int) Math.pow(3, depth);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        setData(data.depth);
    }

    private void setData(int depth) {
        data.depth = depth;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public void addAlgoKeyListener() {
        frame.addKeyListener(new AlgoKeyListener());
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            //System.out.println("Key released:" + event);
            if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9') {
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {

        int depth = 6;

        AlgoVisualizer vis = new AlgoVisualizer(depth);
    }
}