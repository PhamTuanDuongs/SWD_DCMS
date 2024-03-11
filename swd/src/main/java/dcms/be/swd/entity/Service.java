package dcms.be.swd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Service {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 126)
    private String name;

    @Column(name = "\"description\"", length = 1000)
    private String description;

    @Column
    private Boolean deleted;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "service")
    private Set<Examination> serviceExaminations;

}
