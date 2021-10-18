package it.polito.ezqueue.entity;

import java.util.ArrayList;
import java.util.List;

public class Serv {
    private String serviceId;
    private String serviceDescription;
    private Float serviceTime;
    private List<Integer> serviceQueue;
    private boolean active;

    public List<Integer> getServiceQueue() {
        return serviceQueue;
    }

    public Serv(String serviceId, Float serviceTime) {
        this.serviceId = serviceId;
        this.serviceTime = serviceTime;
        this.serviceQueue = new ArrayList<>();
        this.active = false;
    }

    public Serv(String serviceId, String serviceDescription, Float serviceTime) {
        this.serviceId = serviceId;
        this.serviceDescription = serviceDescription;
        this.serviceTime = serviceTime;
        this.serviceQueue = new ArrayList<>();
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getServId() {
        return serviceId;
    }

    public void setServId(String serviceID) {
        this.serviceId = serviceID;
    }

    public String getServiceDescription() { return this.serviceDescription; }

    public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }

    public Float getServTime() {
        return serviceTime;
    }

    public void setServTime(Float serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean addTicket(Integer num) {
        return this.serviceQueue.add(num);
    }

    public void removeTicket(Integer num) {
        this.serviceQueue.remove(num);
    }

    public int getQueueSize() {
        return this.serviceQueue.size();
    }

    @Override
    public String toString() {
        return "Serv{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceTime=" + serviceTime +
                ", serviceQueue=" + serviceQueue +
                '}';
    }
}