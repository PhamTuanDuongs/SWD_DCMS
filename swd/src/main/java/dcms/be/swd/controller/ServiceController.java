package dcms.be.swd.controller;

import dcms.be.swd.dto.service.PageServiceResponseDTO;
import dcms.be.swd.entity.Service;
import dcms.be.swd.repository.ServiceRepository;
import dcms.be.swd.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    static final String SERVICE_NOT_FOUND_MESSAGE = "Service not found";
    private final ServiceService serviceService;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    /**
     * This function returns a list of all services that have not been deleted yet.<p>
     * @param pageNo The pageNo parameter represents the page number of the paginated list of services<p>
     * @param name The name parameter represents the name of the service.<p>
     * @return This method returns a PageServiceResponseDTO object that contains a list of services, the total number of pages, and the current page number.<p>
     */
    @GetMapping
    @RequestMapping("/all")
    public PageServiceResponseDTO findAllNotDeleted(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "") String name
    ) {
        var page = serviceService.findAllNotDeletedServiceByNamePaginated(pageNo - 1, name);

        if (!page.hasContent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, SERVICE_NOT_FOUND_MESSAGE
            );
        } else {
            return PageServiceResponseDTO
                    .builder()
                    .totalPages(page.getTotalPages())
                    .currentPage(page.getNumber() + 1)
                    .services(page.getContent())
                    .build();
        }
    }

    /**
     * This function call the serviceService to add a new service to the database.<p>
     *
     * @param service The service parameter is an instance of the Service class, which contains the information of the service to be added.<p>
     * @return This method returns a Service object that contains the information of the service that was added.<p>
     */
    @PostMapping
    @RequestMapping("/add")
    public Service add(@RequestBody Service service) {
        return serviceService.add(service);
    }

    /**
     * This function call the serviceService to get a service by its id.<p>
     *
     * @param id The id parameter represents the unique identifier of the service that needs to be retrieved.<p>
     * @return This method returns a Service object that contains the information of the service that was retrieved.<p>
     */
    @GetMapping(value = "/{id}")
    public Service get(@PathVariable("id") Integer id) {
        return serviceService.getServiceById(id);
    }

    /**
     * This function call the serviceService to edit a service in the database.<p>
     *
     * @param service The service parameter is an instance of the Service class, which contains the information of the service to be edited.<p>
     * @return This method returns a Service object that contains the information of the service that was edited.<p>
     */
    @PostMapping
    @RequestMapping("/edit")
    public Service edit(@RequestBody Service service) {
        System.out.println("edit" + service);
        return serviceService.edit(service);
    }

    /**
     * This function deletes a service by setting its "deleted" flag to true and returns an appropriate response based on whether the service was found or not.<p>
     *
     * @param id The id parameter is a Long type variable that represents the unique identifier of the service that needs to be deleted. It is passed as a path variable in the URL.<p>
     * @return This method returns a ResponseEntity object that contains a String message indicating whether the service was deleted successfully or not, along with an HTTP status code.<p>
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable("id") Integer id) {
        return serviceService.deleteService(id);
    }
}
