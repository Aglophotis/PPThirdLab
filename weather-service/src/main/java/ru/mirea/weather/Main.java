package ru.mirea.weather;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ru.mirea.data.*;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskExecutorImpl taskExecutor = new TaskExecutorImpl();
        LoggerImpl logger = new LoggerImpl();
        TaskGenerator taskGenerator = new TaskGeneratorImpl();
        BlockingQueue<TaskImpl> qIn = new LinkedBlockingQueue<>();
        ArrayDeque<TaskImpl> qOut = new ArrayDeque<>();

        Generator generator = new Generator(taskGenerator, 100, 100, qIn);
        Worker worker = new Worker(taskExecutor, 300, qIn, qOut);
        Printer printer = new Printer(logger,100, qOut);

        Thread threadGenerator = new Thread(generator);
        Thread threadExecutor1 = new Thread(worker);
        Thread threadExecutor2 = new Thread(worker);
        Thread threadExecutor3 = new Thread(worker);
        Thread threadLogger = new Thread(printer);

        threadGenerator.start();
        threadExecutor1.start();
        threadExecutor2.start();
        threadExecutor3.start();
        threadLogger.start();

        threadGenerator.join();
        while (!qIn.isEmpty()){
            Thread.sleep(1000);
        }
        worker.close();
        while (!qOut.isEmpty()){
            Thread.sleep(1000);
        }
        printer.close();
    }
}