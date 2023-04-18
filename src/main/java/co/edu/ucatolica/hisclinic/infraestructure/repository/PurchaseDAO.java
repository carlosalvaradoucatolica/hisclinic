package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDAO extends JpaRepository<Purchase,Long> {
    public Purchase findPurchaseByPaymentProcessorReference(String paymentProcessorReference);

}
