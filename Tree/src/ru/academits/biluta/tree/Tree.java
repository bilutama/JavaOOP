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
            // new item less than current node
            if (currentNode.getData().compareTo(value) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                } else {
                    currentNode.addLeft(value);
                    currentNode.getLeft().setParent(currentNode);
                    ++size;
                    return;
                }
            }

            // new item not less than current node
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            } else {
                currentNode.addRight(value);
                currentNode.getRight().setParent(currentNode);
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
            if (node.getData().compareTo(value) > 0) {
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

    public boolean remove(T data) {
        TreeNode<T> node = find(data);

        //node is not found
        if (node == null) {
            return false;
        }

        // node is a head
        if (node == head) {
            //TODO: delete head
        }

        // node is not a head
        if (node.getRight() == null) {
            if (node.getLeft() == null) {
                // Node has no children
                node = null;
            } else {
                // Node has left child
                node.getParent().setLeft(node.getLeft());
            }
        } else {
            if (node.getLeft() == null) {
                // Node has right child
                node.getParent().setLeft(node.getRight());
            } else {
                // Node has both children
                //TODO: implement deletion with both children
            }
        }

        return false;
    }

    public int size() {
        return size;
    }
}