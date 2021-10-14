package it.polito.ezqueue.entity;

import java.util.ArrayList;

public class Service {
    private String serviceId;
    private Float serviceTime;
    private ArrayList<Integer> serviceQueue;

    public Service(String serviceId, Float serviceTime) {
        this.serviceId = serviceId;
        this.serviceTime = serviceTime;
        this.serviceQueue= new ArrayList<Integer>();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceID) {
        this.serviceId = serviceID;
    }

    public Float getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Float serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean addTicket (Integer num)
    {return this.serviceQueue.add(num); }

    public boolean removeTicket (Integer num)
    { return this.serviceQueue.remove(num); }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceTime=" + serviceTime +
                ", serviceQueue=" + serviceQueue +
                '}';
    }
}