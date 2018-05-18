package ru.mirea.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class TaskGeneratorImpl implements TaskGenerator<TaskImpl> {
    private int counter = 0;
    private ArrayList<String> arr = new ArrayList<>();
    private Random rnd = new Random(System.currentTimeMillis());

    public TaskGeneratorImpl(){
        createArrOfCity();
    }

    @Override
    public void generateTask(int quantity, BlockingQueue<TaskImpl> qIn){
        synchronized (qIn) {
            for (int i = 0; i < quantity; i++) {
                Date currentDate = new Date();
                TaskImpl tmpTaskImpl = new TaskImpl(counter++, currentDate, randomCity());
                qIn.add(tmpTaskImpl);
            }
        }
    }

    private String randomCity(){
        return arr.get(rnd.nextInt(arr.size()));
    }

    private void createArrOfCity(){
        arr.add("Moscow");
        arr.add("Novosibirsk");
        arr.add("Irkutsk");
        arr.add("Cheboksary");
        arr.add("St.Petersburg");
        arr.add("Latvia");
    }

    public ArrayList<String> getArr() {
        return arr;
    }
}
