package it.polito.ezqueue.dto;

public class Ticket{
    private Integer number;
    private Service service;

    public Ticket(Integer number, Service service) {
        this.number = number;
        this.service = service;
    }

    public Ticket(){
        this.number=0;
        this.service=null;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}