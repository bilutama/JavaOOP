package ru.academits.biluta.tree;

public class Tree<T> {
    private int size;
    public TreeNode<T> head;

    public Tree() {
    }

    public Tree(T data) {
        head = new TreeNode<>(data);
        size = 1;
    }

    public boolean add(T data) {

        return true;
    }

    public boolean find(T data){
        return false;
    }

    public boolean removeFirst(T data) {
        return false;
    }

    public int size() {
        return size;
    }
}
