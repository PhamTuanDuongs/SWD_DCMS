package dcms.be.swd.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class MedStaffInfo {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 225)
    private String quanlification;

    @Column(length = 1000)
    private String experience;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",  nullable = false, unique = true)
    private User user;

}
