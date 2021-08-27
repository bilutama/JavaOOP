package ru.academits.biluta.tree;

public class TreeNode<T extends Comparable<T>> {
    private final T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

//    public void setData(T data) {
//        this.data = data;
//    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void addLeft(T data) {
        this.left = new TreeNode<>(data);
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void addRight(T data) {
        this.right = new TreeNode<>(data);
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}