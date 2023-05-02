package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.infraestructure.repository.ProductDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HisClinicServiceImpl implements HisClinicService{
    private final ProductDAO productDAO;
    @Override
    public Product save(Product product) {
        return productDAO.saveAndFlush(product);
    }
}
