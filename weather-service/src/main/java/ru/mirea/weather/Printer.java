package ru.mirea.weather;

import ru.mirea.data.LoggerImpl;
import ru.mirea.data.TaskImpl;

import java.util.ArrayDeque;

class Printer implements Runnable {

    private LoggerImpl logger;
    private ArrayDeque<TaskImpl> qOut;
    private int sleepTime;
    private boolean isThreadActive = true;

    public Printer(LoggerImpl logger, int sleepTime, ArrayDeque<TaskImpl> qOut) {
        this.logger = logger;
        this.qOut = qOut;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (isThreadActive) {
            try {
                Thread.sleep(sleepTime);
                logger.printExecuteTask(qOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        isThreadActive = false;
    }
}
