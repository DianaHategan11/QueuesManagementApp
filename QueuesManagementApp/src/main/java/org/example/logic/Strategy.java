package org.example.logic;

import org.example.models.Queue;
import org.example.models.Client;

import java.util.List;

public interface Strategy {
    public void addClient(List<Queue> queues, Client client);
}
