package org.example.models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    public boolean running = false;

    public Queue() {
        this.clients = new LinkedBlockingDeque<>();
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addClient(Client newClient) {
        this.clients.add(newClient);
        this.waitingPeriod.set(this.waitingPeriod.get() + newClient.getServiceTime());
    }

    @Override
    public void run() {
        int currentServiceTime = 0;
        running = true;
        while (running) {
            if (clients != null) {
                try {
                    Client currentClient = clients.peek();
                    if (currentClient != null) {
                        while (currentClient.getServiceTime() > 0) {
                            currentServiceTime = currentClient.getServiceTime();
                            Thread.sleep(1500);
                            if (waitingPeriod.get() > 0) {
                                waitingPeriod.decrementAndGet();
                            }
                            currentClient.setServiceTime(currentServiceTime - 1);
                            clients.peek().setServiceTime(currentClient.getServiceTime());
                            if (clients.peek().getServiceTime() == 0)
                                clients.take();
                        }
                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        String queueOfClients = "";
        if (clients != null) {
            for (Client client : clients) {
                queueOfClients = queueOfClients.concat("(" + client.getId() + "," + client.getArrivalTime() + "," +
                        client.getServiceTime() + ")" + "; ");
            }
        }
        return queueOfClients;
    }

    public void stopThread() {
        running = false;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }
}
