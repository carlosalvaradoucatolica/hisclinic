package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Purchase;
import co.edu.ucatolica.hisclinic.infraestructure.repository.TrazabilidadDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TrazabilidadServiceImpl {

    private final TrazabilidadDAO trazabilidadDAO;
    //@Override
    //public Purchase trazabilidad(Purchase purchase) { return purchaseDAO.save(purchase); }
}
