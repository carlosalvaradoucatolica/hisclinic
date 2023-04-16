package co.edu.ucatolica.hisclinic.domain.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "app_user_id")
    private AppUser appUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "expires_at")
    private Date expiresAt;
    @Column(name = "payment_processor_reference")
    private String paymentProcessorReference;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "payment_processor_state")
    private String paymentProcessorState;
}
