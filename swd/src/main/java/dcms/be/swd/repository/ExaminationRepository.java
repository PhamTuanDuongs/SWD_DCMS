package dcms.be.swd.repository;

import dcms.be.swd.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExaminationRepository extends JpaRepository<Examination, Long> {
}
