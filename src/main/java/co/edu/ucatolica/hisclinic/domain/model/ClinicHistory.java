package co.edu.ucatolica.hisclinic.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "clinic_history")
public class ClinicHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String name;
    private String last_name;
    private LocalDate date_of_birth;
    private String city;
    private String type;
    private String comment;
    @ManyToOne
    private AppUser appUser;
}
