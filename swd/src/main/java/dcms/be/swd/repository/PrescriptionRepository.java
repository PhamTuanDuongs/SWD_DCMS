package dcms.be.swd.repository;

import dcms.be.swd.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
}
