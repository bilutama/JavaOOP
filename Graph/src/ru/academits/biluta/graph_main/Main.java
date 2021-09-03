package ru.academits.biluta.graph_main;

import ru.academits.biluta.data_handler.DataHandler;
import ru.academits.biluta.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        String[] nodes1 = {"A", "B", "E", "D", "C", "F", "G", "H", "J", "X", "Y", "Z", "K"};
        int[][] connectivityMatrix1 = {
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

        Graph<String> graph = new Graph<>(nodes1, connectivityMatrix1);
        Consumer<String> stringHandler = new DataHandler<>();

        System.out.println("Graph breadth traversal:");
        graph.traverseBreadthFirst(stringHandler);
        System.out.println();

        System.out.println("Graph depth traversal:");
        graph.traverseDepthFirst(stringHandler);
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
        Integer[] nodes2 = {8, 5, 9, 4, 6, 11, 7, 10, 15};
        int[][] connectivityMatrix2 = {
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

        Graph<Integer> graphInt = new Graph<>(nodes2, connectivityMatrix2);
        Consumer<Integer> intHandler = new DataHandler<>();

        System.out.println("Graph breadth traversal:");
        graphInt.traverseBreadthFirst(intHandler);
        System.out.println();

        System.out.println("Graph depth traversal:");
        graphInt.traverseDepthFirst(intHandler);
        System.out.println();

        Integer[] nodes3 = new Integer[0];
        int[][] connectivityMatrix3 = {};

        Graph<Integer> emptyGraph = new Graph<>(nodes3, connectivityMatrix3);
        emptyGraph.traverseBreadthFirst(intHandler);
        emptyGraph.traverseDepthFirst(intHandler);
    }
}