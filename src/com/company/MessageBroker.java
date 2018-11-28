package com.company;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageBroker {
    private AtomicInteger count = new AtomicInteger();
    private Map<String, BlockingQueue<String>> queueMap = new ConcurrentHashMap<>();

    public void registerQueue(String queue) {
        queueMap.putIfAbsent(queue, new ArrayBlockingQueue<>(1, true));
    }

    public void sendMessage(String queue, String message) {
        queueMap.get(queue).add(message);
        this.count.incrementAndGet();
    }

    public String receiveMessage(String queue) {
        return this.queueMap.get(queue).poll();
    }

    public int getCount() {
        return this.count.get();
    }
}
