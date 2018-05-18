package ru.mirea.weather;

import java.util.concurrent.BlockingQueue;

import ru.mirea.data.TaskImpl;
import ru.mirea.data.TaskGenerator;

class Generator implements Runnable {

    private TaskGenerator taskGenerator;
    private int taskQuantity;
    private int sleepTime;
    private boolean isThreadActive = true;
    private BlockingQueue<TaskImpl> qIn;

    public Generator(TaskGenerator taskGenerator, int taskQuantity, int sleepTime, BlockingQueue<TaskImpl> qIn) {
        this.taskGenerator = taskGenerator;
        this.taskQuantity = taskQuantity;
        this.sleepTime = sleepTime;
        this.qIn = qIn;
    }

    @Override
    public void run() {
        int i = 0;
        while (isThreadActive) {
            if (i < taskQuantity) {
                try {
                    taskGenerator.generateTask(1, qIn);
                    i++;
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                close();
            }
        }
    }

    public void close(){
        isThreadActive = false;
    }
}