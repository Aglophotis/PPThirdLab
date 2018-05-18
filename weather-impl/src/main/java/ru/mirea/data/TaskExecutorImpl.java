package ru.mirea.data;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class TaskExecutorImpl implements TaskExecutor<TaskImpl>{
    private Map<String, String> hashMap = new HashMap<>();

    private void createHashMapQueue() {
        hashMap.put("Moscow", "+12");
        hashMap.put("Novosibirsk", "-5");
        hashMap.put("Irkutsk", "0");
        hashMap.put("Cheboksary", "+20");
        hashMap.put("St.Petersburg", "+5");
        hashMap.put("Latvia", "-1");
    }

    public TaskExecutorImpl(){
        createHashMapQueue();
    }

    @Override
    public void executeTask(BlockingQueue<TaskImpl> qIn, ArrayDeque<TaskImpl> qOut) {
        TaskImpl tmpTaskImpl;

        if (!qIn.isEmpty()) {
            synchronized (qIn) {
                if (!qIn.isEmpty()) {
                    tmpTaskImpl = qIn.poll();
                    tmpTaskImpl.setWeather(hashMap.get(tmpTaskImpl.getCity()));
                    synchronized (qOut) {
                        qOut.offer(tmpTaskImpl);
                    }
                }
            }
        }
    }

    @Override
    public Map<String, String> getHashMap() {
        return hashMap;
    }
}
