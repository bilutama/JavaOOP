package ru.academits.biluta.graph_main;

import ru.academits.biluta.graph.Graph;

public class Main {
    public static void main(String[] args) {
        String[] nodes = {"A", "B", "E", "D", "C", "F", "G", "H", "J", "X", "Y", "Z", "K"};
        int[][] connections = {
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Graph<String> graph = new Graph<>(nodes, connections);

        System.out.println("Graph breadth traversal:");
        graph.traverseBreadthFirst();
        System.out.println();

        System.out.println("Graph depth traversal:");
        graph.traverseDepthFirst();
        System.out.println();

        /* BST as a graph
               8
             /   \
            5     9
           / \     \
          4  6     11
              \    / \
               7  10  15
        */
        Integer[] nodesInt = {8, 5, 9, 4, 6, 11, 7, 10, 15};
        int[][] connectionsInt = {
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0}
        };

        Graph<Integer> graphInt = new Graph<>(nodesInt, connectionsInt);

        System.out.println("Graph breadth traversal:");
        graphInt.traverseBreadthFirst();
        System.out.println();

        System.out.println("Graph depth traversal:");
        graphInt.traverseDepthFirst();
        System.out.println();
    }
}