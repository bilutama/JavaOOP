package ru.academits.biluta.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph<T> {
    private final T[] graphNodes;
    private final int[][] connectivityMatrix;

    public Graph(T[] graphNodes, int[][] connectivityMatrix) {
        if (connectivityMatrix.length != connectivityMatrix[0].length) {
            throw new IllegalArgumentException(String.format("Connectivity matrix has different width %d and height %d",
                    connectivityMatrix[0].length, connectivityMatrix.length));
        }

        if (graphNodes.length != connectivityMatrix.length) {
            throw new IllegalArgumentException(String.format("Nodes count %d not equals to connectivity matrix dimension %d.",
                    graphNodes.length, connectivityMatrix.length));
        }

        this.graphNodes = graphNodes;
        this.connectivityMatrix = connectivityMatrix;
    }

    public void breadthTraversal() {
        Queue<T> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[graphNodes.length];

        for (int i = 0; i < graphNodes.length; ++i) {
            if (!isVisited[i]) {
                queue.add(graphNodes[i]);
                isVisited[i] = true;

                while (!queue.isEmpty()) {
                    T node = queue.poll();

                    // Do some work with a node from the queue
                    System.out.print(node);
                    System.out.print(" ");

                    for (int j = 0; j < isVisited.length; ++j) {
                        if (connectivityMatrix[j][i] != 0 && !isVisited[j]) {
                            queue.add(graphNodes[j]);
                            isVisited[j] = true;
                        }
                    }

                    ++i;
                }
            }
        }
    }

    public void depthTraversal() {
        Stack<T> stack = new Stack<>();
        boolean[] isVisited = new boolean[graphNodes.length];

        for (int i = 0; i < graphNodes.length; ++i) {
            if (!isVisited[i]) {
                stack.add(graphNodes[i]);
                isVisited[i] = true;

                while (!stack.isEmpty()) {
                    T node = stack.pop();

                    // Do some work with a node from the queue
                    System.out.print(node);
                    System.out.print(" ");

                    for (int j = isVisited.length - 1; j >= 0; --j) {
                        if (connectivityMatrix[j][i] != 0 && !isVisited[j]) {
                            stack.add(graphNodes[j]);
                            isVisited[j] = true;
                        }
                    }

                    ++i;
                }
            }
        }
    }
}
