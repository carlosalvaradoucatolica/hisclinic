package co.edu.ucatolica.hisclinic.domain.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @NotNull
    @Column(name = "json")
    private String json;

    @NotNull
    @Column(name = "status_code_response")
    private Integer statusCodeResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "app_user_id")
    private AppUser appUser;

}