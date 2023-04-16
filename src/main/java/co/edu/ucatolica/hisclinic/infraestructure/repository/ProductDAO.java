package co.edu.ucatolica.hisclinic.infraestructure.repository;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {
    public List<Product> findAll();
}
