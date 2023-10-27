package org.example.logic;

import org.example.models.Queue;
import org.example.models.Client;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public void addClient(List<Queue> queues, Client client) {
        int minWaitingTime = Integer.MAX_VALUE, waitingTime = 0, currentTime = 0;
        boolean ok = false;
        for (int i = 0; i < queues.size(); i++) {
            waitingTime = queues.get(i).getWaitingPeriod().get();
            currentTime = waitingTime + client.getServiceTime();
            if (currentTime < minWaitingTime) {
                minWaitingTime = currentTime;
            }
        }
        for (int i = 0; i < queues.size(); i++) {
            waitingTime = queues.get(i).getWaitingPeriod().get();
            currentTime = waitingTime + client.getServiceTime();
            if (currentTime == minWaitingTime && !ok) {
                queues.get(i).addClient(client);
                client.setTotalTimeSpentInTheQueue(client.getTotalTimeSpentInTheQueue() +
                        queues.get(i).getWaitingPeriod().get());
                ok = true;
            }
        }
    }
}
