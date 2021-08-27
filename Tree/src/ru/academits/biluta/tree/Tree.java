package ru.academits.biluta.tree;

public class Tree<T extends Comparable<T>> {
    private int size;
    public TreeNode<T> head;

    public Tree(T data) {
        head = new TreeNode<>(data);
        size = 1;
    }

    public void add(T value) {
        if (size == 0) {
            head = new TreeNode<>(value);
        }

        TreeNode<T> currentNode = head;

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

    public TreeNode<T> find(T value) {
        if (size == 0) {
            return null;
        }

        TreeNode<T> node = head;

        while (!node.getData().equals(value)) {
            if (value.compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node == null) {
                return null;
            }
        }

        return node;
    }

    public boolean remove(T value) {
        if (size == 0) {
            return false;
        }

        // deleting head
        if (value.equals(head.getData())) {
            //TODO: delete head
            return false;
        }

        TreeNode<T> node = head;
        TreeNode<T> nodeParent = null;
        boolean isNodeLeftChild = false;

        // finding a node
        while (!value.equals(node.getData())) {
            nodeParent = node;

            if (value.compareTo(node.getData()) < 0) {
                node = node.getLeft();
                isNodeLeftChild = true;
            } else {
                node = node.getRight();
                isNodeLeftChild = false;
            }

            if (node == null) {
                return false;
            }
        }

        if (node.getLeft() == null) {
            if (node.getRight() == null) {
                // node has no leaves
                if (isNodeLeftChild) {
                    nodeParent.setLeft(null);
                } else {
                    nodeParent.setRight(null);
                }

                return true;
            }

            // node has right leaf
            if (isNodeLeftChild) {
                nodeParent.setLeft(node.getRight());
            } else {
                nodeParent.setRight(node.getRight());
            }

            return true;
        }

        // has left leaf
        if (node.getRight() == null) {
            // has left leaf
            if (isNodeLeftChild) {
                nodeParent.setLeft(node.getLeft());
            } else {
                nodeParent.setRight(node.getLeft());
            }
        }

        // has both leaves
        // TODO: delete when has both leaves

        return true;
    }

    public int size() {
        return size;
    }
}