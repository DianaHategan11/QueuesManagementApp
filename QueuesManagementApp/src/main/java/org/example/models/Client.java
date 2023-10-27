package org.example.models;

public class Client implements Comparable<Client> {
    private int id;
    private int arrivalTime;
    private int serviceTime;
    private int totalTimeSpentInTheQueue;

    public Client(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.totalTimeSpentInTheQueue = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getTotalTimeSpentInTheQueue() {
        return totalTimeSpentInTheQueue;
    }

    public void setTotalTimeSpentInTheQueue(int totalTimeSpentInTheQueue) {
        this.totalTimeSpentInTheQueue = totalTimeSpentInTheQueue;
    }

    @Override
    public int compareTo(Client client) {
        if (this.arrivalTime < client.arrivalTime) {
            return -1;
        } else if (this.arrivalTime == client.arrivalTime) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "(" + this.getId() + "," + this.getArrivalTime() + "," +
                this.getServiceTime() + ")" + "; ";
    }
}
