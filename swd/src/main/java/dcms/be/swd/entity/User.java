package dcms.be.swd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Account userAccounts;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private Set<Appointment> doctorAppointments;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> patientAppointments;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private MedStaffInfo userMedStaffInfoes;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<StaffShift> userStaffShifts;

}
