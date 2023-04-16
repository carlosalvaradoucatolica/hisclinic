package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public List<Product> list();
}
