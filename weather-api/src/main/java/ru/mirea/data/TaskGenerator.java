package ru.mirea.data;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public interface TaskGenerator<T> {
    void generateTask(int quantity, BlockingQueue<T> qIn);
    ArrayList<String> getArr();
}
