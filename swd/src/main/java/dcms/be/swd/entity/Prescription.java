package dcms.be.swd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Prescription {

    @Id
    @Column(nullable = false, updatable = false, length = 500)
    private String details;

    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "app_point_id", referencedColumnName = "id",  nullable = false, unique = true)
    @JsonBackReference
    private Appointment appPoint;

}
