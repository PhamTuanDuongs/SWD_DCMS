package dcms.be.swd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Appointment {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column
    private Integer status;

    @Column(length = 1000)
    private String conclusion;

    @Column
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "doctor_id",  referencedColumnName = "id",  nullable = false)
    @JsonBackReference
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id",  referencedColumnName = "id",  nullable = false)
    @JsonBackReference
    private User patient;

    @OneToMany(mappedBy = "appointment")
    @JsonManagedReference
    private Set<Examination> appointmentExaminations;

    @OneToMany(mappedBy = "appPoint")
    @JsonManagedReference
    private Set<Feedback> appPointFeedbacks;

    @OneToOne(mappedBy = "appPoint")
    @JsonManagedReference
    private Prescription appPointPrescriptions;

}
