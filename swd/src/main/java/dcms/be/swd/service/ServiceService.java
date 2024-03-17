
package dcms.be.swd.service;


import dcms.be.swd.entity.Service;
import dcms.be.swd.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * This service provides methods for retrieval of services, adding, editing and deleting service<p>
 *
 * @author LocDP
 */
@org.springframework.stereotype.Service
public class ServiceService {
    static final int PAGE_SIZE = 10;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * <p>This function retrieves all services that match defined criteria from the database.<p>
     *
     * @param pageNo The page number to be retrieved
     * @param name  The name of the service to be retrieved
     * @return object containing the list of services and the total number of pages
     */
    public Page<Service> findAllNotDeletedServiceByNamePaginated(int pageNo, String name) {
        var paging = PageRequest.of(pageNo, PAGE_SIZE);

        return serviceRepository.findAllByNameContainingIgnoreCaseAndDeletedFalse(name, paging);
    }



    /**
     * <p>This function add a service to the database.<p>
     *
     * @param service The service to be added
     * @return The added service
     */
    public Service add(Service service) {
        return serviceRepository.save(service);
    }

    /**
     * <p>This function edit a service in the database.<p>
     *
     * @param service The service to be edited
     * @return The edited service
     */
    public Service edit(Service service) {
        return serviceRepository.save(service);
    }

    /**
     * <p>This function retrieves a service by its ID.<p>
     *
     * @param id The ID of the service to be retrieved
     * @return The service or null
     */
    public Service getServiceById(Integer id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Object> deleteService(Integer id) {
        try {
            Service service = serviceRepository.findById(id).get();
            if (service != null) {
                Service oldService = serviceRepository.findById(id).get();
                oldService.setDeleted(true);
                serviceRepository.save(oldService);
                return ResponseEntity.ok().body("Service is deleted successfully");
            } else {
                return new ResponseEntity<>("Not found service", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

