package dcms.be.swd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;


@Entity
@Getter
@Setter
public class Account {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private LocalDate verifiedAt;

    @Column(length = 62)
    private String password;

    @Column(length = 62)
    private String resetPasswordToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",  referencedColumnName = "id",  nullable = false)
    @JsonManagedReference
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_id",  referencedColumnName = "id",  nullable = false, unique = true)
    @JsonBackReference
    private User user;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
}
