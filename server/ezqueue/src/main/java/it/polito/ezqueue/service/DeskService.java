package it.polito.ezqueue.service;

import it.polito.ezqueue.entity.Desk;
import it.polito.ezqueue.entity.Service;

@Service
public class DeskService
{
    DeskDto getDeskById(Integer deskId);
    DeskDto saveDesk(DeskDto desk);
    Boolean deleteDesk(Integer deskId);
    List<DeskDto> getAllDesks();
    Boolean isDeskOpen(Integer deskId);
    Boolean openDesk(Integer deskId);
    Boolean closeDesk(Integer deskId);
    Boolean isServed(String serviceId);
    Boolean addDeskService(ServiceDto service);
}