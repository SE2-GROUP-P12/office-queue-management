package it.polito.ezqueue.entity;

public class Service {
    private String serviceId;
    private Float serviceTime;

    public Service(String serviceId, Float serviceTime) {
        this.serviceId = serviceId;
        this.serviceTime = serviceTime;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceTime=" + serviceTime +
                '}';
    }
}