package co.edu.ucatolica.hisclinic.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles_app_user"
            , joinColumns = @JoinColumn(name = "roles_id"))
    @MapKeyJoinColumn(name = "app_user_id")
    List<AppUser> appUsers;

}
