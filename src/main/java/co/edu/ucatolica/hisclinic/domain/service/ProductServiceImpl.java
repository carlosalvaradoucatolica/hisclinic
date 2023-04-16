package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.infraestructure.repository.ProductDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;
    @Override
    public Product save(Product product) {
        return productDAO.saveAndFlush(product);
    }
    @Override
    public List<Product> list() {
        return productDAO.findAll();
    }
}
