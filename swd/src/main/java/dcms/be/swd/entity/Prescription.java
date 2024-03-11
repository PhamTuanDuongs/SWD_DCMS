package dcms.be.swd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Prescription {

    @Id
    @Column(nullable = false, updatable = false, length = 500)
    private Long id;

    @Column(nullable = false)
    private String details;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_point_id", nullable = false)
    private Appointment appPoint;

}
