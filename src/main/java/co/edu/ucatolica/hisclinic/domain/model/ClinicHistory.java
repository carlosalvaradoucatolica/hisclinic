package co.edu.ucatolica.hisclinic.domain.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "clinic_history")
public class ClinicHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    private int identification;
    @NotNull
    private String name;
    @NotNull
    private String last_name;
    private LocalDate date_of_birth;
    @NotNull
    private String city;
    @NotNull
    private String type;
    @NotNull
    private String comment;
    @ManyToOne
    private AppUser appUser;
}
