package ru.academits.biluta.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph<T> {
    private final T[] nodes;
    private final int[][] connectivityMatrix;

    public Graph(T[] nodes, int[][] connectivityMatrix) {
        if (connectivityMatrix.length > 0) {
            for (int[] matrixRow : connectivityMatrix) {
                if (matrixRow.length != connectivityMatrix.length) {
                    throw new IllegalArgumentException(String.format("At least one row in the connectivity matrix has length %d that differs from matrix height %d",
                            matrixRow.length, connectivityMatrix.length));
                }
            }
        }

        if (nodes.length != connectivityMatrix.length) {
            throw new IllegalArgumentException(String.format("Nodes count %d is not equal to connectivity matrix dimension %d.",
                    nodes.length, connectivityMatrix.length));
        }

        this.nodes = nodes;
        this.connectivityMatrix = connectivityMatrix;
    }

    public void traverseBreadthFirst(Consumer<T> handler) {
        if (nodes.length == 0) {
            return;
        }

        Queue<Integer> indexQueue = new LinkedList<>();
        boolean[] isVisited = new boolean[nodes.length];

        for (int i = 0; i < nodes.length; ++i) {
            if (isVisited[i]) {
                continue;
            }

            indexQueue.add(i);
            isVisited[i] = true;

            while (!indexQueue.isEmpty()) {
                Integer nodeIndex = indexQueue.remove();
                T node = nodes[nodeIndex];

                // Handle a node from a queue
                handler.accept(node);

                // Put all indices of unvisited children to the queue
                for (int j = 0; j < isVisited.length; ++j) {
                    if (connectivityMatrix[nodeIndex][j] != 0 && !isVisited[j]) {
                        indexQueue.add(j);
                        isVisited[j] = true;
                    }
                }
            }
        }
    }

    public void traverseDepthFirst(Consumer<T> handler) {
        if (nodes.length == 0) {
            return;
        }

        Deque<Integer> indexStack = new LinkedList<>();
        boolean[] isVisited = new boolean[nodes.length];

        for (int i = 0; i < nodes.length; ++i) {
            if (isVisited[i]) {
                continue;
            }

            indexStack.addFirst(i);
            isVisited[i] = true;

            while (!indexStack.isEmpty()) {
                Integer nodeIndex = indexStack.removeFirst();
                T node = nodes[nodeIndex];

                // Handle a node from the stack
                handler.accept(node);

                // Put indices of all unvisited children to the stack in reverse order
                for (int j = isVisited.length - 1; j >= 0; --j) {
                    if (connectivityMatrix[nodeIndex][j] != 0 && !isVisited[j]) {
                        indexStack.addFirst(j);
                        isVisited[j] = true;
                    }
                }
            }
        }
    }

    public void traverseDepthFirstRecursively(Consumer<T> handler) {
        if (nodes.length == 0) {
            return;
        }

        boolean[] isVisited = new boolean[nodes.length];

        for (int i = 0; i < nodes.length; ++i) {
            if (isVisited[i]) {
                continue;
            }

            visitNodeRecursively(i, isVisited, handler);
        }
    }

    private void visitNodeRecursively(int nodeIndex, boolean[] isVisited, Consumer<T> handler) {
        isVisited[nodeIndex] = true;
        handler.accept(nodes[nodeIndex]);

        for (int i = 0; i < isVisited.length; ++i) {
            if (connectivityMatrix[nodeIndex][i] != 0 && !isVisited[i]) {
                visitNodeRecursively(i, isVisited, handler);
            }
        }
    }
}