package co.edu.ucatolica.hisclinic.domain.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "payment_processor_reference")
    private String paymentProcessorReference;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "payment_processor_state")
    private String paymentProcessorState;

    public Purchase(AppUser appUser, Product product, LocalDateTime createdAt, LocalDateTime expiresAt, String paymentProcessorReference, Boolean paid, String paymentProcessorState) {
        this.appUser = appUser;
        this.product = product;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.paymentProcessorReference = paymentProcessorReference;
        this.paid = paid;
        this.paymentProcessorState = paymentProcessorState;
    }

    public Purchase(Product product) {
        this.product = product;
    }
}
