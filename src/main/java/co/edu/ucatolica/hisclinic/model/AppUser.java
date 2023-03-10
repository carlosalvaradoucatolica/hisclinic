package co.edu.ucatolica.hisclinic.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "app_user")
//@NamedQuery(name = "AppUser.findByEmail",
    //query = "select a from AppUser a where LOWER(TRIM(a.username)) = LOWER(TRIM(?1))")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(unique = true, name = "username")
    private String username;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column( name = "last_name")
    private String lastName;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @NotNull
    @Column(name = "password")
    private String password;

    //TODO: hacer asignaciones con tablas de union para relaciones muchos a muchos, o manejar con enums ?
    //@OneToMany
    //@JoinColumn(name = "app_user_id")
    //private List<RolUnion> asignaciones;

    //Opcion 2:
    //@ManyToMany
    //@JoinTable(name = "appuser_roles", joinColumns = @JoinColumn(name = "appuser_id"),
    //        inverseJoinColumns = @JoinColumn(name = "role_id"))
    //private Collection<Role> roles;

}
