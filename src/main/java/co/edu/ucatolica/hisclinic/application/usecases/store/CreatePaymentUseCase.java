package co.edu.ucatolica.hisclinic.application.usecases.store;

import co.edu.ucatolica.hisclinic.infraestructure.dto.request.PurchaseDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface CreatePaymentUseCase {
    public ResponseEntity<ResponseDTO> createPayment(PurchaseDTO purchaseDTO) throws JsonProcessingException;
}
