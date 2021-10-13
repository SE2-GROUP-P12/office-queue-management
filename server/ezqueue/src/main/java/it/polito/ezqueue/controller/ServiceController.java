package it.polito.ezqueue.controller;

@RestController
@RequestMapping(path = "") //must wait for API
public class ServiceController {

    @Autowired
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService)
    {
        this.serviceService=serviceService;
    }

    @GetMapping(path="...")
    public List<Service> getAllServices(){
        return serviceService.getAllServices();
    }

    @GetMapping
    public ServiceDto getServiceById(String serviceId)
    {
        return serviceService.getServiceById(serviceId);
    }

    @GetMapping
    public Float getServiceTime(String serviceId)
    {
        return serviceService.getServiceTime(serviceId);
    }

    @PostMapping
    public Boolean saveService(ServiceDto service)
    {
        return serviceService.saveService(service);
    }

    @DeleteMapping
    public Boolean deleteService(String serviceId)
    {
        return serviceService.deleteService(serviceId);
    }

}