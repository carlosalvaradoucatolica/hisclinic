package co.edu.ucatolica.hisclinic.application.usecases.store;

import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface GetProductUseCase {
    public ResponseEntity<ResponseDTO> getProducts();
}
