package co.edu.ucatolica.hisclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authentication")
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer codigo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "authentication_app_user"
            , joinColumns = @JoinColumn(name = "authentication_id"))
    @MapKeyJoinColumn(name = "app_user_id")
    List<AppUser> appUsers;

}
