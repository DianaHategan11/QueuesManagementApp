package org.example.logic;

import org.example.models.Client;
import org.example.models.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
    private List<Queue> queues;
    private int maxNoQueues;
    private int maxClientsPerQueue;
    private Strategy strategy;
    private int updatedQueue;

    public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
        this.maxNoQueues = maxNoQueues;
        this.maxClientsPerQueue = maxClientsPerQueue;
        this.queues = new ArrayList<>();
        for (int i = 0; i < maxNoQueues; i++) {
            Queue newQueue = new Queue();
            this.queues.add(i, newQueue);
            Thread newThread = new Thread(newQueue);
            newThread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            this.strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            this.strategy = new TimeStrategy();
        }
    }

    public void dispatchClient(Client client) {
        this.strategy.addClient(this.queues, client);
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public int getMaxNoQueues() {
        return maxNoQueues;
    }

    public void setMaxNoQueues(int maxNoQueues) {
        this.maxNoQueues = maxNoQueues;
    }

    public int getMaxClientsPerQueue() {
        return maxClientsPerQueue;
    }

    public void setMaxClientsPerQueue(int maxClientsPerQueue) {
        this.maxClientsPerQueue = maxClientsPerQueue;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int getUpdatedQueue() {
        return updatedQueue;
    }

    public void setUpdatedQueue(int updatedQueue) {
        this.updatedQueue = updatedQueue;
    }
}
