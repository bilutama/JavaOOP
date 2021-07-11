package ru.academits.biluta.list;

public class List<T> {
    ListItem<T> head;

    public List(T data){
        head = new ListItem<T>(data);
        head.setNext(null);
    }


}