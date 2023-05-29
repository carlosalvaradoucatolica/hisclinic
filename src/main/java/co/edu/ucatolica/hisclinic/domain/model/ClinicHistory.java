package co.edu.ucatolica.hisclinic.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.aspectj.bridge.IMessage;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
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
    @NotNull
    private String city;
    @NotNull
    private String type;
    @NotNull
    private String comment;
    @ManyToOne
    private AppUser appUser;
}
