package org.example.logic;

import org.example.gui.SimulationFrame;
import org.example.models.Client;
import org.example.models.Queue;

import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SimulationManager implements Runnable {
    public int maxSimulationTime;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int minServiceTime;
    public int maxServiceTime;
    public int nbOfClients;
    public int nbOfQueues;
    public SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private StartSimulation startSimulation;
    private List<Client> clients;
    private double averageWaitingTime;
    private double averageServiceTime;
    private int peakHour;

    public SimulationManager(int nbOfClients, int nbOfQueues, int maxSimulationTime, int minArrivalTime, int maxArrivalTime,
                             int minServiceTime, int maxServiceTime, SelectionPolicy selectionPolicy,
                             SimulationFrame frame, StartSimulation startSimulation) {
        this.nbOfClients = nbOfClients;
        this.nbOfQueues = nbOfQueues;
        this.maxSimulationTime = maxSimulationTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.scheduler = new Scheduler(nbOfQueues, nbOfClients);
        this.scheduler.changeStrategy(selectionPolicy);
        this.frame = frame;
        this.startSimulation = startSimulation;
        this.clients = new ArrayList<>();
        this.averageWaitingTime = 0.0;
        this.averageServiceTime = 0.0;
        this.peakHour = 0;
        this.generateRandomClients();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public SimulationFrame getFrame() {
        return frame;
    }

    public void setFrame(SimulationFrame frame) {
        this.frame = frame;
    }

    public List<Client> getTasks() {
        return clients;
    }

    public void setTasks(List<Client> clients) {
        this.clients = clients;
    }

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(double averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public double getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(double averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    public void generateRandomClients() {
        int id = 0, aTime = 0, sTime = 0;
        Random rand = new Random();
        for (int i = 0; i < scheduler.getMaxClientsPerQueue(); i++) {
            id = i;
            aTime = rand.nextInt(maxArrivalTime) + minArrivalTime;
            sTime = rand.nextInt(maxServiceTime) + minServiceTime;
            Client newClient = new Client(id, aTime, sTime);
            clients.add(newClient);
            this.averageServiceTime += newClient.getServiceTime();
        }
        this.averageServiceTime /= nbOfClients;
        this.averageServiceTime = Math.round(this.averageServiceTime * 10000.0) / 10000.0;
        Collections.sort(clients);
    }

    @Override
    public void run() {
        try {
            FileWriter fileWriter = new FileWriter("logFile.txt");
            if (this.frame.getNField() == 4 && this.frame.getQField() == 2 && this.frame.getMaxSimulationField() == 60 &&
                    this.frame.getMinArrivalTimeField() == 2 && this.frame.getMaxArrivalTimeField() == 30 &&
                    this.frame.getMinServiceTimeField() == 2 && this.frame.getMaxServiceTimeField() == 4) {
                fileWriter = new FileWriter("Test1.txt");
            } else if (this.frame.getNField() == 50 && this.frame.getQField() == 5 && this.frame.getMaxSimulationField() == 60 &&
                    this.frame.getMinArrivalTimeField() == 2 && this.frame.getMaxArrivalTimeField() == 40 &&
                    this.frame.getMinServiceTimeField() == 1 && this.frame.getMaxServiceTimeField() == 7) {
                fileWriter = new FileWriter("Test2.txt");
            } else if (this.frame.getNField() == 1000 && this.frame.getQField() == 20 && this.frame.getMaxSimulationField() == 200 &&
                    this.frame.getMinArrivalTimeField() == 10 && this.frame.getMaxArrivalTimeField() == 100 &&
                    this.frame.getMinServiceTimeField() == 3 && this.frame.getMaxServiceTimeField() == 9) {
                fileWriter = new FileWriter("Test3.txt");
            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currentTime = 0, queueNb = 0, nullQueues = 0;
            int totalNbOfClientsInTheQueues = 0, maxNbOfClients = 0;
            boolean stop = false;
            String logText;
            while (currentTime < maxSimulationTime && !stop) {
                totalNbOfClientsInTheQueues = 0;
                queueNb = 0;
                nullQueues = 0;
                stop = false;
                if (clients.isEmpty()) {
                    for (Queue queue : scheduler.getQueues()) {
                        if (queue.getClients().isEmpty()) {
                            nullQueues++;
                        }
                    }
                    if (nullQueues == nbOfQueues) {
                        stop = true;
                    }
                }
                Iterator<Client> iterator = clients.iterator();
                while (iterator.hasNext()) {
                    Client client = iterator.next();
                    if (client.getArrivalTime() == currentTime) {
                        scheduler.dispatchClient(client);
                        this.averageWaitingTime += client.getTotalTimeSpentInTheQueue();
                        iterator.remove();
                    }
                }
                logText = "";
                bufferedWriter.write("Time " + currentTime + "\n");
                logText = logText.concat("Time " + currentTime + "\n");
                for (Queue queue : scheduler.getQueues()) {
                    if (!queue.getClients().isEmpty()) {
                        totalNbOfClientsInTheQueues += queue.getClients().size();
                    }
                }
                if (totalNbOfClientsInTheQueues > maxNbOfClients) {
                    peakHour = currentTime;
                    maxNbOfClients = totalNbOfClientsInTheQueues;
                }
                currentTime++;
                bufferedWriter.write("Waiting clients: ");
                logText = logText.concat("Waiting clients: ");
                for (Client client : clients) {
                    bufferedWriter.write(client.toString());
                    logText = logText.concat(client.toString());
                }
                bufferedWriter.write("\n");
                logText = logText.concat("\n");
                for (Queue queue : scheduler.getQueues()) {
                    if (queue.getClients().size() == 0) {
                        bufferedWriter.write("Queue " + (queueNb + 1) + ": " + "closed");
                        logText = logText.concat("Queue " + (queueNb + 1) + ": " + "closed");
                    } else {
                        bufferedWriter.write("Queue " + (queueNb + 1) + ": " + queue.toString());
                        logText = logText.concat("Queue " + (queueNb + 1) + ": " + queue.toString());
                    }
                    queueNb++;
                    bufferedWriter.write("\n");
                    logText = logText.concat("\n");
                    this.frame.setTextArea(logText);
                }
                Thread.sleep(1500);
            }
            this.stop();
            this.averageWaitingTime /= nbOfClients;
            this.averageWaitingTime = Math.round(this.averageWaitingTime * 10000.0) / 10000.0;
            bufferedWriter.write("Average waiting time: " + averageWaitingTime + "\n");
            this.frame.setWaitingField(this.averageWaitingTime);
            bufferedWriter.write("Average service time: " + averageServiceTime + "\n");
            this.frame.setServiceField(this.averageServiceTime);
            bufferedWriter.write("Peak hour: " + peakHour + "\n");
            this.frame.setHourField(this.peakHour);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {
        for (Queue queue : scheduler.getQueues()) {
            queue.running = false;
        }
    }
}
