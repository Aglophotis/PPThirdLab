package ru.mirea.data;

import java.util.ArrayDeque;

public interface Logger<T> {
    void printExecuteTask(ArrayDeque<T> qTaskImpl);
}
