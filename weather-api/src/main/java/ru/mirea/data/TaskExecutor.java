package ru.mirea.data;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.BlockingQueue;


public interface TaskExecutor<T> {
    void executeTask(BlockingQueue<T> qIn, ArrayDeque<T> qOut);
    Map<String, String> getHashMap();
}
