package ru.mirea.weather;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ru.mirea.data.TaskExecutorImpl;
import ru.mirea.data.TaskImpl;
import ru.mirea.data.TaskGenerator;
import ru.mirea.data.TaskGeneratorImpl;

import static org.junit.Assert.*;

public class TestBlockTest {

    private BlockingQueue<TaskImpl> createTestTask(){
        BlockingQueue<TaskImpl> qIn = new LinkedBlockingQueue<>();
        TaskImpl tmpTaskImpl1 = new TaskImpl();
        Date currentDate = new Date();
        tmpTaskImpl1.setID(0);
        tmpTaskImpl1.setCity("Moscow");
        tmpTaskImpl1.setDate(currentDate);
        qIn.add(tmpTaskImpl1);

        TaskImpl tmpTaskImpl2 = new TaskImpl();
        tmpTaskImpl2.setID(1);
        tmpTaskImpl2.setCity("Novosibirsk");
        tmpTaskImpl2.setDate(currentDate);
        qIn.add(tmpTaskImpl2);

        TaskImpl tmpTaskImpl3 = new TaskImpl();
        tmpTaskImpl3.setID(2);
        tmpTaskImpl3.setCity("Irkutsk");
        tmpTaskImpl3.setDate(currentDate);
        qIn.add(tmpTaskImpl3);

        TaskImpl tmpTaskImpl4 = new TaskImpl();
        tmpTaskImpl4.setID(3);
        tmpTaskImpl4.setCity("Cheboksary");
        tmpTaskImpl4.setDate(currentDate);
        qIn.add(tmpTaskImpl4);

        TaskImpl tmpTaskImpl5 = new TaskImpl();
        tmpTaskImpl5.setID(4);
        tmpTaskImpl5.setCity("St.Petersburg");
        tmpTaskImpl5.setDate(currentDate);
        qIn.add(tmpTaskImpl5);

        TaskImpl tmpTaskImpl6 = new TaskImpl();
        tmpTaskImpl6.setID(5);
        tmpTaskImpl6.setCity("Latvia");
        tmpTaskImpl6.setDate(currentDate);
        qIn.add(tmpTaskImpl6);

        return qIn;
    }

    @org.junit.Test
    public void testWriteTask(){
        BlockingQueue<TaskImpl> qIn = createTestTask();
        for (TaskImpl item: qIn) {
            if (item.getID() == 0)
                assertEquals("Moscow", item.getCity());

            if (item.getID() == 1)
                assertEquals("Novosibirsk", item.getCity());

            if (item.getID() == 2)
                assertEquals("Irkutsk", item.getCity());

            if (item.getID() == 3)
                assertEquals("Cheboksary", item.getCity());

            if (item.getID() == 4)
                assertEquals("St.Petersburg", item.getCity());

            if (item.getID() == 5)
                assertEquals("Latvia", item.getCity());
        }
    }

    private ArrayList<String> createTestArr() {
        TaskGenerator taskGenerator = new TaskGeneratorImpl();
        ArrayList<String> arr = taskGenerator.getArr();
        arr.add("Moscow");
        arr.add("Novosibirsk");
        arr.add("Irkutsk");
        arr.add("Cheboksary");
        arr.add("St.Petersburg");
        arr.add("Latvia");
        return arr;
    }

    @org.junit.Test
    public void testWriteArr() {
        ArrayList<String> tmp = createTestArr();
        assertEquals("Moscow", tmp.get(0));
        assertEquals("Novosibirsk", tmp.get(1));
        assertEquals("Irkutsk", tmp.get(2));
        assertEquals("Cheboksary", tmp.get(3));
        assertEquals("St.Petersburg", tmp.get(4));
        assertEquals("Latvia", tmp.get(5));
    }

    private Map<String, String> createTestHashMap() {
        TaskExecutorImpl taskExecutor = new TaskExecutorImpl();
        Map<String, String> hashMap = taskExecutor.getHashMap();
        hashMap.put("Moscow", "+12");
        hashMap.put("Novosibirsk", "-5");
        hashMap.put("Irkutsk", "0");
        hashMap.put("Cheboksary", "+20");
        hashMap.put("St.Petersburg", "+5");
        hashMap.put("Latvia", "-1");

        return hashMap;
    }

    @org.junit.Test
    public void testWriteHashMap(){
        Map<String, String> hashMap = createTestHashMap();
        assertEquals("+12", hashMap.get("Moscow"));
        assertEquals("-5", hashMap.get("Novosibirsk"));
        assertEquals("0", hashMap.get("Irkutsk"));
        assertEquals("+20", hashMap.get("Cheboksary"));
        assertEquals("+5", hashMap.get("St.Petersburg"));
        assertEquals("-1", hashMap.get("Latvia"));
    }

    private ArrayDeque<TaskImpl> createExecuteTask() {
        BlockingQueue<TaskImpl> qIn = createTestTask();
        ArrayDeque<TaskImpl> qOut = new ArrayDeque<>();
        TaskImpl tmpTaskImpl;
        tmpTaskImpl = qIn.poll();
        if ("Moscow".equals(tmpTaskImpl.getCity()))
            tmpTaskImpl.setWeather("+12");
        qOut.offer(tmpTaskImpl);

        tmpTaskImpl = qIn.poll();
        if ("Novosibirsk".equals(tmpTaskImpl.getCity()))
            tmpTaskImpl.setWeather("-5");
        qOut.offer(tmpTaskImpl);

        tmpTaskImpl = qIn.poll();
        if ("Irkutsk".equals(tmpTaskImpl.getCity()))
            tmpTaskImpl.setWeather("0");
        qOut.offer(tmpTaskImpl);

        tmpTaskImpl = qIn.poll();
        if ("Cheboksary".equals(tmpTaskImpl.getCity()))
            tmpTaskImpl.setWeather("+20");
        qOut.offer(tmpTaskImpl);

        tmpTaskImpl = qIn.poll();
        if ("St.Petersburg".equals(tmpTaskImpl.getCity()))
            tmpTaskImpl.setWeather("+5");
        qOut.offer(tmpTaskImpl);

        tmpTaskImpl = qIn.poll();
        if ("Latvia".equals(tmpTaskImpl.getCity()))
            tmpTaskImpl.setWeather("-1");
        qOut.offer(tmpTaskImpl);

        return qOut;
    }

    @org.junit.Test
    public void testExecuteTask(){
        ArrayDeque<TaskImpl> qOut = createExecuteTask();
        for (TaskImpl item: qOut) {
            if ("Moscow".equals(item.getCity()))
                assertEquals("+12", item.getWeather());

            if ("Novosibirsk".equals(item.getCity()))
                assertEquals("-5", item.getWeather());

            if ("Irkutsk".equals(item.getCity()))
                assertEquals("0", item.getWeather());

            if ("Cheboksary".equals(item.getCity()))
                assertEquals("+20", item.getWeather());

            if ("St.Petersburg".equals(item.getCity()))
                assertEquals("+5", item.getWeather());

            if ("Latvia".equals(item.getCity()))
                assertEquals("-1", item.getWeather());
        }
    }

    @org.junit.Test
    public void testGenerateArr() throws Exception {
        TaskGenerator taskGenerator = new TaskGeneratorImpl();
        ArrayList<String> tmp = taskGenerator.getArr();
        assertEquals("Moscow", tmp.get(0));
        assertEquals("Novosibirsk", tmp.get(1));
        assertEquals("Irkutsk", tmp.get(2));
        assertEquals("Cheboksary", tmp.get(3));
        assertEquals("St.Petersburg", tmp.get(4));
        assertEquals("Latvia", tmp.get(5));
    }

    @org.junit.Test
    public void testGenerateTask() throws Exception {
        TaskGenerator taskGenerator = new TaskGeneratorImpl();
        int counter = 0;
        BlockingQueue<TaskImpl> qIn = new LinkedBlockingQueue<>();
        taskGenerator.generateTask(50, qIn);
        for (TaskImpl item: qIn) {
            boolean isTruthCity = false;
            ArrayList<String> arr = taskGenerator.getArr();
            for (String city: arr) {
                if (item.getCity().equals(city))
                    isTruthCity = true;
            }
            if (item.getID() != counter)
                isTruthCity = false;
            counter++;
            if (item.getDate() == null)
                isTruthCity = false;
            if (item.getWeather() != null)
                isTruthCity = false;
            assertTrue(isTruthCity);
        }
    }

    @org.junit.Test
    public void testGenerateHashMap(){
        TaskExecutorImpl taskExecutor = new TaskExecutorImpl();
        Map<String, String> hashMap = taskExecutor.getHashMap();
        assertEquals("+12", hashMap.get("Moscow"));
        assertEquals("-5", hashMap.get("Novosibirsk"));
        assertEquals("0", hashMap.get("Irkutsk"));
        assertEquals("+20", hashMap.get("Cheboksary"));
        assertEquals("+5", hashMap.get("St.Petersburg"));
        assertEquals("-1", hashMap.get("Latvia"));
    }

    @org.junit.Test
    public void testGenerateExecuteTask() throws Exception {
        TaskExecutorImpl taskExecutor = new TaskExecutorImpl();
        TaskGenerator taskGenerator = new TaskGeneratorImpl();
        BlockingQueue<TaskImpl> qIn = new LinkedBlockingQueue<>();
        ArrayDeque<TaskImpl> qOut = new ArrayDeque<>();
        taskGenerator.generateTask(50, qIn);
        while (!qIn.isEmpty())
            taskExecutor.executeTask(qIn, qOut);
        for (TaskImpl item: qOut) {
            if ("Moscow".equals(item.getCity()))
                assertEquals("+12", item.getWeather());

            if ("Novosibirsk".equals(item.getCity()))
                assertEquals("-5", item.getWeather());

            if ("Irkutsk".equals(item.getCity()))
                assertEquals("0", item.getWeather());

            if ("Cheboksary".equals(item.getCity()))
                assertEquals("+20", item.getWeather());

            if ("St.Petersburg".equals(item.getCity()))
                assertEquals("+5", item.getWeather());

            if ("Latvia".equals(item.getCity()))
                assertEquals("-1", item.getWeather());
        }
    }

    @org.junit.Test
    public void testThread() throws Exception {
        TaskExecutorImpl taskExecutor = new TaskExecutorImpl();
        BlockingQueue<TaskImpl> qIn = createTestTask();
        ArrayDeque<TaskImpl> qOut = new ArrayDeque<>();
        Map<String, String> hashMap = taskExecutor.getHashMap();
        hashMap.put("Moscow", "+12");
        hashMap.put("Novosibirsk", "-5");
        hashMap.put("Irkutsk", "0");
        hashMap.put("Cheboksary", "+20");
        hashMap.put("St.Petersburg", "+5");
        hashMap.put("Latvia", "-1");
        Worker worker = new Worker(taskExecutor, 200, qIn, qOut);
        Thread threadExecutor1 = new Thread(worker);
        Thread threadExecutor2 = new Thread(worker);
        threadExecutor1.start();
        threadExecutor2.start();
        while (!qIn.isEmpty())
            Thread.sleep(10);
        worker.close();
        threadExecutor1.join();
        threadExecutor2.join();
        for (TaskImpl item: qOut) {
            if ("Moscow".equals(item.getCity()))
                assertEquals("+12", item.getWeather());

            if ("Novosibirsk".equals(item.getCity()))
                assertEquals("-5", item.getWeather());

            if ("Irkutsk".equals(item.getCity()))
                assertEquals("0", item.getWeather());

            if ("Cheboksary".equals(item.getCity()))
                assertEquals("+20", item.getWeather());

            if ("St.Petersburg".equals(item.getCity()))
                assertEquals("+5", item.getWeather());

            if ("Latvia".equals(item.getCity()))
                assertEquals("-1", item.getWeather());
        }
    }
}