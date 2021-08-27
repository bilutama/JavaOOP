package ru.academits.biluta.tree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree<T extends Comparable<T>> {
    private int size;
    private TreeNode<T> root;

    public Tree(T data) {
        root = new TreeNode<>(data);
        size = 1;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void addAll(Collection<T> collection) {
        for (T element : collection) {
            add(element);
        }
    }

    public void add(T value) {
        if (size == 0) {
            root = new TreeNode<>(value);
        }

        TreeNode<T> currentNode = root;

        while (true) {
            // value less than the current node
            if (value.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                } else {
                    currentNode.addLeft(value);
                    ++size;
                    return;
                }
            }

            // value not less than the current node
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            } else {
                currentNode.addRight(value);
                ++size;
                return;
            }
        }
    }

    public boolean find(T value) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> node = root;

        while (!node.getData().equals(value)) {
            if (value.compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node == null) {
                return false;
            }
        }

        return true;
    }

    public boolean removeAll(Collection<T> collection) {
        int initialSize = size;

        for (T element : collection) {
            remove(element);
        }

        return size != initialSize;
    }

    public boolean remove(T value) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> node = root;
        TreeNode<T> parentNode = null;
        // Relationship flag between node and its parentNode
        boolean isLeftChild = false;


        // Find a node
        while (!value.equals(node.getData())) {
            parentNode = node;

            if (value.compareTo(node.getData()) < 0) {
                node = node.getLeft();
                isLeftChild = true;
            } else {
                node = node.getRight();
                isLeftChild = false;
            }

            // node is not found
            if (node == null) {
                return false;
            }
        }

        // Selecting deleting algorithm depending on children count
        // OPTION 1 - node has no children
        if (node.getLeft() == null && node.getRight() == null) {
            // deleting the root
            if (parentNode == null) {
                root = null;
                --size;
                return true;
            }

            if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }

            --size;
            return true;
        }

        // OPTION 2 - node has only right child
        if (node.getLeft() == null) {
            // deleting the root
            if (parentNode == null) {
                root = node.getRight();
                --size;
                return true;
            }

            if (isLeftChild) {
                parentNode.setLeft(node.getRight());
            } else {
                parentNode.setRight(node.getRight());
            }

            --size;
            return true;
        }

        // OPTION 3 - node has only left child
        if (node.getRight() == null) {
            // deleting the root
            if (parentNode == null) {
                root = node.getLeft();
                --size;
                return true;
            }

            if (isLeftChild) {
                parentNode.setLeft(node.getLeft());
            } else {
                parentNode.setRight(node.getLeft());
            }

            --size;
            return true;
        }

        // OPTION 4 - node has both children
        TreeNode<T> leftmostParent = node;
        TreeNode<T> leftmostNode = node.getRight();

        // Find the leftmost node in the right subtree
        while (leftmostNode.getLeft() != null) {
            leftmostParent = leftmostNode;
            leftmostNode = leftmostNode.getLeft();
        }

        if (leftmostNode.getRight() == null) {
            // Check if right subtree has one node, i.e. the leftmost node is the right child of its parent
            if (node.getRight() == leftmostNode) {
                leftmostParent.setRight(null);
            } else {
                leftmostParent.setLeft(null);
            }
        } else {
            // If the leftmost node has the right child then link node's parent and successor
            leftmostParent.setLeft(leftmostNode.getRight());
            leftmostNode.setRight(null);
        }

        // Relink node's children to the new node
        leftmostNode.setLeft(node.getLeft());
        leftmostNode.setRight(node.getRight());

        // Delete the root, i.e. replace root with the leftmost node
        if (parentNode == null) {
            root = leftmostNode;
            --size;
            return true;
        }

        // Relink parent if node is not the root
        if (isLeftChild) {
            parentNode.setLeft(leftmostNode);
        } else {
            parentNode.setRight(leftmostNode);
        }

        --size;
        return true;
    }

    public int size() {
        return size;
    }

    public void breadthTraversal() {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        TreeNode<T> currentNode = root;
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.poll();

            // Do some work with a node from queue
            System.out.print(currentNode.getData());
            System.out.print(" ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthTraversal() {
        Stack<TreeNode<T>> stack = new Stack<>();

        TreeNode<T> currentNode = root;
        stack.add(currentNode);

        while (!stack.isEmpty()) {
            currentNode = stack.pop();

            // Do some work with a node from stack
            System.out.print(currentNode.getData());
            System.out.print(" ");

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void depthTraversalRecursively(TreeNode<T> subtreeRoot) {
        System.out.print(subtreeRoot.getData());
        System.out.print(" ");

        if (subtreeRoot.getLeft() != null) {
            depthTraversalRecursively(subtreeRoot.getLeft());
        }

        if (subtreeRoot.getRight() != null) {
            depthTraversalRecursively(subtreeRoot.getRight());
        }
    }
}