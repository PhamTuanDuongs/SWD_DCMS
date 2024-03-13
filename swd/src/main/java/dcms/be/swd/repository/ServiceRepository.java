package dcms.be.swd.repository;

import dcms.be.swd.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Page<Service> findAllByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable paging);

}
