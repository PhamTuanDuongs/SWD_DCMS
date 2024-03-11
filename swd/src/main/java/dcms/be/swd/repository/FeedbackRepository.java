package dcms.be.swd.repository;

import dcms.be.swd.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
