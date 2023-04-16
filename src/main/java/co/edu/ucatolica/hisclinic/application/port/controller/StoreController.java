package co.edu.ucatolica.hisclinic.application.port.controller;

import co.edu.ucatolica.hisclinic.application.usecases.store.GetProductUseCaseImpl;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/store")
@AllArgsConstructor
public class StoreController {
    private final GetProductUseCaseImpl getProductUseCase;
    @GetMapping("/getProducts")
    public ResponseEntity<ResponseDTO> getProducts(){
        return getProductUseCase.getProducts();
    }

}
