package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.domain.model.Purchase;
import co.edu.ucatolica.hisclinic.infraestructure.repository.PurchaseDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseDAO purchaseDAO;
    @Override
    public Purchase save(Purchase purchase) { return purchaseDAO.save(purchase); }
    @Override
    public Purchase getByExternalReference(String externalReference) {return purchaseDAO.findPurchaseByPaymentProcessorReference(externalReference);}

    @Override
    public Purchase findById(Long Id) {
        return purchaseDAO.findById(Id).get();
    }
}
