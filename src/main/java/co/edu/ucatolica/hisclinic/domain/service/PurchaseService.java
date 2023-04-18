package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Purchase;

import java.util.List;

public interface PurchaseService {
    public Purchase save(Purchase purchase);
    public Purchase getByExternalReference(String externalReference);
}
