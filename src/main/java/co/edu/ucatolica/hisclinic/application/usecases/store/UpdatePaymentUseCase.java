package co.edu.ucatolica.hisclinic.application.usecases.store;

import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UpdatePaymentUseCase {
    public ResponseEntity<ResponseDTO> updatePayment(Map<?,?> mercadoPagoDTO) throws JsonProcessingException;
}
