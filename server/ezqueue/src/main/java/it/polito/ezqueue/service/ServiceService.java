package it.polito.ezqueue.service;

import it.polito.ezqueue.dto.ServiceDto;
import java.util.List;

public interface ServiceService
{
    ServiceDto getServiceById(String serviceId);
    ServiceDto saveService(ServiceDto service);
    Boolean deleteService(String serviceId);
    List<ServiceDto> getAllServices();
    Float getServiceTime(String serviceId);
}