package ru.academits.biluta.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph<T> {
    private final T[] nodes;
    private final int[][] connectivityMatrix;

    public Graph(T[] nodes, int[][] connectivityMatrix) {
        if (connectivityMatrix.length != connectivityMatrix[0].length) {
            throw new IllegalArgumentException(String.format("Connectivity matrix has different width %d and height %d",
                    connectivityMatrix[0].length, connectivityMatrix.length));
        }

        if (nodes.length != connectivityMatrix.length) {
            throw new IllegalArgumentException(String.format("Nodes count %d is not equal to connectivity matrix dimension %d.",
                    nodes.length, connectivityMatrix.length));
        }

        this.nodes = nodes;
        this.connectivityMatrix = connectivityMatrix;
    }

    public void traverseBreadthFirst(Consumer<T> handler) {
        Queue<T> nodesQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        boolean[] isVisited = new boolean[nodes.length];

        int branchCounter = 1;

        for (int i = 0; i < nodes.length; ++i) {
            if (isVisited[i]) {
                continue;
            }

            System.out.printf("Branch %d: ", branchCounter);

            nodesQueue.add(nodes[i]);
            indexQueue.add(i);
            isVisited[i] = true;

            while (!nodesQueue.isEmpty()) {
                T node = nodesQueue.remove();

                // Do some work with a node from the queue
                handler.accept(node);

                Integer nodeIndex = indexQueue.remove();

                // put all unvisited children of the node to the queue by index
                for (int j = 0; j < isVisited.length; ++j) {
                    if (connectivityMatrix[nodeIndex][j] != 0 && !isVisited[j]) {
                        nodesQueue.add(nodes[j]);
                        indexQueue.add(j);
                        isVisited[j] = true;
                    }
                }
            }

            ++branchCounter;
            System.out.println();
        }
    }

    public void traverseDepthFirst(Consumer<T> handler) {
        LinkedList<T> nodesStack = new LinkedList<>();
        LinkedList<Integer> indexStack = new LinkedList<>();
        boolean[] isVisited = new boolean[nodes.length];

        int branchCounter = 1;

        for (int i = 0; i < nodes.length; ++i) {
            if (isVisited[i]) {
                continue;
            }

            System.out.printf("Branch %d: ", branchCounter);

            nodesStack.addLast(nodes[i]);
            indexStack.addLast(i);
            isVisited[i] = true;

            while (!nodesStack.isEmpty()) {
                T node = nodesStack.pollLast();

                // Do some work with a node from the stack
                handler.accept(node);

                Integer nodeIndex = indexStack.pollLast();

                // put all unvisited children of the node to the stack in reverse order by index
                for (int j = isVisited.length - 1; j >= 0; --j) {
                    if (connectivityMatrix[nodeIndex][j] != 0 && !isVisited[j]) {
                        nodesStack.addLast(nodes[j]);
                        indexStack.addLast(j);
                        isVisited[j] = true;
                    }
                }
            }

            ++branchCounter;
            System.out.println();
        }
    }
}