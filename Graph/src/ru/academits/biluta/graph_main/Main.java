package ru.academits.biluta.graph_main;

import ru.academits.biluta.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Integer[] graphElements = {1, 2, 3, 4, 5, 6, 7};
        int[][] connections = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0}
        };

        Graph<Integer> graph = new Graph<>(graphElements, connections);
        graph.breadthTraversal();
    }
}