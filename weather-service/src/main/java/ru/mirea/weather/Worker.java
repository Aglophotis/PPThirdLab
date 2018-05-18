package ru.mirea.weather;

import ru.mirea.data.TaskExecutorImpl;
import ru.mirea.data.TaskImpl;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;


class Worker implements Runnable {
    private TaskExecutorImpl taskExecutor;
    private int sleepTime;
    private boolean isThreadActive = true;
    BlockingQueue<TaskImpl> qIn;
    ArrayDeque<TaskImpl> qOut;

    public Worker(TaskExecutorImpl taskExecutor, int sleepTime, BlockingQueue<TaskImpl> qIn, ArrayDeque<TaskImpl> qOut) {
        this.taskExecutor = taskExecutor;
        this.sleepTime = sleepTime;
        this.qIn = qIn;
        this.qOut = qOut;
    }

    @Override
    public void run() {
        while (isThreadActive) {
            try {
                Thread.sleep(sleepTime);
                taskExecutor.executeTask(qIn, qOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        isThreadActive = false;
    }
}