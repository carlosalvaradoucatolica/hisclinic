package co.edu.ucatolica.hisclinic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Roles name;

    @Column(name = "path")
    private String path;

    @Column(name = "icon_name")
    private String icon_name;

    public Role(Roles name, String path, String icon_name) {
        this.name = name;
        this.path = path;
        this.icon_name = icon_name;
    }
}
