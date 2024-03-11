package dcms.be.swd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 62)
    private String name;

    @Column
    private LocalDate dob;

    @Column(nullable = false)
    private String address;

    @Column(length = 30)
    private String phoneNo;

    @Column
    private Boolean gender;

    @Column(length = 30)
    private String nationalId;

    @Column
    private String avatar;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> doctorAppointments;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> patientAppointments;

    @OneToMany(mappedBy = "user")
    private Set<MedStaffInfo> userMedStaffInfoes;

    @OneToMany(mappedBy = "user")
    private Set<StaffShift> userStaffShifts;

}
