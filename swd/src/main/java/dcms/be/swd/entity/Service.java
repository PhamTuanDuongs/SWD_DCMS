package dcms.be.swd.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`service`")
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
}

