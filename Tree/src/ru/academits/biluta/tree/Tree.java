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

        TreeNode<T> node = root;

        while (true) {
            // OPTION 1 - value less than the current node
            if (value.compareTo(node.getData()) < 0) {
                if (node.getLeft() == null) {
                    node.addLeft(value);
                    ++size;
                    return;
                } else {
                    node = node.getLeft();
                    continue;
                }
            }

            // OPTION 2 - value not less than the current node
            if (node.getRight() == null) {
                node.addRight(value);
                ++size;
                return;
            } else {
                node = node.getRight();
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

        // Relationship flag between a node and its parent
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

        // Delete algorithm depends on children count
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

        // Find the leftmost node and its parent in the right subtree
        while (leftmostNode.getLeft() != null) {
            leftmostParent = leftmostNode;
            leftmostNode = leftmostNode.getLeft();
        }

        //Check if the leftmost node has right child
        if (leftmostNode.getRight() == null) {
            // Check if right subtree has one node, i.e. if the leftmost node is the _right_ child of its parent
            // and delete it from the tree
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

        // Link children
        leftmostNode.setLeft(node.getLeft());
        leftmostNode.setRight(node.getRight());

        // Delete the root, i.e. replace it with the leftmost node
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

    public void traverseBreadthFirst() {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        TreeNode<T> node = root;
        queue.add(node);

        while (!queue.isEmpty()) {
            node = queue.poll();

            // Do some work with a node from the queue
            System.out.print(node.getData());
            System.out.print(" ");

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseDepthFirst() {
        Stack<TreeNode<T>> stack = new Stack<>();

        TreeNode<T> node = root;
        stack.add(node);

        while (!stack.isEmpty()) {
            node = stack.pop();

            // Do some work with a node from the stack
            System.out.print(node.getData());
            System.out.print(" ");

            if (node.getRight() != null) {
                stack.add(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.add(node.getLeft());
            }
        }
    }

    public void traverseDepthFirstRecursively(TreeNode<T> subtreeRoot) {
        System.out.print(subtreeRoot.getData());
        System.out.print(" ");

        if (subtreeRoot.getLeft() != null) {
            traverseDepthFirstRecursively(subtreeRoot.getLeft());
        }

        if (subtreeRoot.getRight() != null) {
            traverseDepthFirstRecursively(subtreeRoot.getRight());
        }
    }
}