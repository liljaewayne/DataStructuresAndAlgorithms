package com.visual;

public class Main {

    /*public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("DefaultFrame");
        });
    }*/

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        int N = 10;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);

    }


}
