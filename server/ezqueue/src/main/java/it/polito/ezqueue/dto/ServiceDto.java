package it.polito.ezqueue.dto;

@Entity
public class Service {
    private String serviceID;
    private Float serviceTime;

    public Service(String serviceID, Float serviceTime) {
        this.serviceID = serviceID;
        this.serviceTime = serviceTime;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
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
                "serviceID='" + serviceID + '\'' +
                ", serviceTime=" + serviceTime +
                '}';
    }
}