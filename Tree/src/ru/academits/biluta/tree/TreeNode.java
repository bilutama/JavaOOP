package ru.academits.biluta.tree;

class TreeNode<T> {
    private final T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void addLeft(T data) {
        left = new TreeNode<>(data);
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void addRight(T data) {
        right = new TreeNode<>(data);
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}