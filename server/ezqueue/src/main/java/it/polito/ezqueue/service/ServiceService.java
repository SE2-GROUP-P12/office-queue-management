package it.polito.ezqueue.service;

import it.polito.ezqueue.dto.ServiceDto;
import java.util.List;

@Service
public class ServiceService
{
    ServiceDto getServiceById(String serviceId);
    Boolean saveService(ServiceDto service);
    Boolean deleteService(String serviceId);
    List<ServiceDto> getAllServices();
    Float getServiceTime(String serviceId);
}