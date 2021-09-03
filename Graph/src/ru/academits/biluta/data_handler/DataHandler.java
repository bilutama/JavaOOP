package ru.academits.biluta.data_handler;

import java.util.function.Consumer;

public class DataHandler<T> implements Consumer<T> {
    @Override
    public void accept(T data) {
        // some work implementation
        System.out.print(data);
        System.out.print(" ");
    }
}