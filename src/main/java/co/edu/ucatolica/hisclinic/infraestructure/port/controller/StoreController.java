package co.edu.ucatolica.hisclinic.infraestructure.port.controller;

import co.edu.ucatolica.hisclinic.application.usecases.store.CreatePaymentUseCase;
import co.edu.ucatolica.hisclinic.application.usecases.store.GetProductUseCaseImpl;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.PurchaseDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/store")
@AllArgsConstructor
public class StoreController {
    private final GetProductUseCaseImpl getProductUseCase;
    private final CreatePaymentUseCase createPaymentUseCase;
    @GetMapping("/getProducts")
    public ResponseEntity<ResponseDTO> getProducts(){
        return getProductUseCase.getProducts();
    }
    @PostMapping("/createPayment")
    public ResponseEntity<ResponseDTO> createPayment(@RequestBody PurchaseDTO purchaseDTO) throws JsonProcessingException { return createPaymentUseCase.createPayment(purchaseDTO); }
}
