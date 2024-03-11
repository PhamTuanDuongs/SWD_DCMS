package dcms.be.swd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Feedback {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer doctorPoint;

    @Column(length = 500)
    private String comment;

    @OneToMany(mappedBy = "feedback")
    private Set<Appointment> feedbackAppointments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_point_id", nullable = false)
    private Appointment appPoint;

}
