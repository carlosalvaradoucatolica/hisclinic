package co.edu.ucatolica.hisclinic.application.usecases.store;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.domain.service.ProductService;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class GetProductUseCaseImpl implements GetProductUseCase{

    private final ProductService productService;
    @Override
    public ResponseEntity<ResponseDTO> getProducts() {
        List<Product> products = productService.list();
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("products",products))
                        .message("Listado de productos vigentes.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
                , HttpStatus.OK
        );
    }
}
