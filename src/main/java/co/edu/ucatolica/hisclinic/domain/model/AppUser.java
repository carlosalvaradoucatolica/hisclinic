package co.edu.ucatolica.hisclinic.domain.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(unique = true, name = "email")
    private String email;
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

    @ManyToMany
    @JoinTable(name = "appuser_role", joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public AppUser(String email, String firstName, String lastName, Boolean enabled, String password, Collection<Role> roles) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
    }

}
