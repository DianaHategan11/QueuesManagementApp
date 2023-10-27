package org.example.logic;

import org.example.models.Queue;
import org.example.models.Client;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addClient(List<Queue> queues, Client client) {
        int minSize = 10000;
        boolean ok = false;
        for (int i = 0; i < queues.size(); i++) {
            if (queues.get(i).getClients().size() <= minSize) {
                minSize = queues.get(i).getClients().size();
            } else if (queues.get(i).getClients().size() == minSize) {
                if (queues.get(i).getClients().peek().getServiceTime() == 1) {
                    minSize = queues.get(i).getClients().size() - 1;
                }
            }
        }
        for (int i = 0; i < queues.size(); i++) {
            if (queues.get(i).getClients().size() == minSize && !ok) {
                queues.get(i).addClient(client);
                client.setTotalTimeSpentInTheQueue(client.getTotalTimeSpentInTheQueue() +
                        queues.get(i).getWaitingPeriod().get());
                ok = true;
            }
        }
    }
}
