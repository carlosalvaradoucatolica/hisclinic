package co.edu.ucatolica.hisclinic.infraestructure.port.webhook;

import co.edu.ucatolica.hisclinic.application.usecases.store.UpdatePaymentUseCase;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(path = "/mercadopago")
@AllArgsConstructor
public class MercadoPagoWebHook {

    private final UpdatePaymentUseCase updatePaymentUseCase;
    @PostMapping("/hook")
    public ResponseEntity<ResponseDTO> updatedPaymentHook(@RequestBody Map<?,?> dto) throws JsonProcessingException {

        return updatePaymentUseCase.updatePayment(dto);
    }
}
