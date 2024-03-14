package dcms.be.swd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonManagedReference
    @Cascade(CascadeType.MERGE)
    private User user;

}
