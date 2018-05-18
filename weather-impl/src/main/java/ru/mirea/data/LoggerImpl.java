package ru.mirea.data;

import java.util.ArrayDeque;

public class LoggerImpl implements Logger<TaskImpl> {

    @Override
    public void printExecuteTask(ArrayDeque<TaskImpl> qTaskImpl) {
        TaskImpl taskImpl;
        synchronized (qTaskImpl) {
            taskImpl = qTaskImpl.poll();
        }
        if (taskImpl != null) {
            System.out.println(taskImpl.getID() + " " + taskImpl.getDate() + " " + taskImpl.getCity() + " " + taskImpl.getWeather());
        }
    }
}
