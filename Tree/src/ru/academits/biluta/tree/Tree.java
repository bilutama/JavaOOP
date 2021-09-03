package ru.academits.biluta.tree;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<T> {
    private int size;
    private TreeNode<T> root;
    private Comparator<T> comparator;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree() {
    }

    private int compareData(T data1, T data2) {
        if (data1 == null && data2 == null) {
            return 0;
        } else if (data1 == null) {
            return -1;
        } else if (data2 == null) {
            return 1;
        }

        if (comparator == null) {
            //noinspection unchecked
            return ((Comparable<T>) data1).compareTo(data2);
        }

        return comparator.compare(data1, data2);
    }

    public void addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
    }

    public void add(T data) {
        if (size == 0) {
            root = new TreeNode<>(data);
            ++size;
            return;
        }

        TreeNode<T> node = root;

        while (true) {
            // OPTION 1 - data less than the current node
            if (compareData(data, node.getData()) < 0) {
                if (node.getLeft() == null) {
                    node.addLeft(data);
                    ++size;
                    return;
                }

                node = node.getLeft();
                continue;

            }

            // OPTION 2 - data not less than the current node
            if (node.getRight() == null) {
                node.addRight(data);
                ++size;
                return;
            }

            node = node.getRight();
        }
    }

    public boolean find(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> node = root;
        int comparedData = compareData(data, node.getData());

        while (comparedData != 0) {
            if (comparedData < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node == null) {
                return false;
            }

            comparedData = compareData(data, node.getData());
        }

        return true;
    }

    public boolean removeAll(Collection<? extends T> collection) {
        int initialSize = size;

        for (T element : collection) {
            remove(element);
        }

        return size != initialSize;
    }

    public boolean remove(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> node = root;
        TreeNode<T> parentNode = null;

        // Relationship flag between a node and its parent
        boolean isLeftChild = false;

        // Find a node
        int comparedData = compareData(data, node.getData());

        while (comparedData != 0) {
            parentNode = node;

            if (comparedData < 0) {
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

            comparedData = compareData(data, node.getData());
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

    public void traverseBreadthFirst(Consumer<T> handler) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        TreeNode<T> node = root;
        queue.add(node);

        while (!queue.isEmpty()) {
            node = queue.poll();

            // Handle node from the queue
            handler.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseDepthFirst(Consumer<T> handler) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> stack = new LinkedList<>();

        TreeNode<T> node = root;
        stack.addFirst(node);

        while (!stack.isEmpty()) {
            node = stack.removeFirst();

            // Do some work with a node from the stack
            handler.accept(node.getData());

            if (node.getRight() != null) {
                stack.addFirst(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addFirst(node.getLeft());
            }
        }
    }

    public void traverseDepthFirstRecursively(Consumer<T> handler) {
        visitNodeRecursively(root, handler);
    }

    private void visitNodeRecursively(TreeNode<T> node, Consumer<T> handler) {
        handler.accept(node.getData());

        if (node.getLeft() != null) {
            visitNodeRecursively(node.getLeft(), handler);
        }

        if (node.getRight() != null) {
            visitNodeRecursively(node.getRight(), handler);
        }
    }
}