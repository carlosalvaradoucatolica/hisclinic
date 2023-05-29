package co.edu.ucatolica.hisclinic.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auditoria")

public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user;
    private String event;
    private LocalDateTime date;
}
