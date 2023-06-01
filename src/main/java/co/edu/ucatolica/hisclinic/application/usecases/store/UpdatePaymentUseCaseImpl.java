package co.edu.ucatolica.hisclinic.application.usecases.store;

import co.edu.ucatolica.hisclinic.domain.model.Hook;
import co.edu.ucatolica.hisclinic.domain.model.Purchase;
import co.edu.ucatolica.hisclinic.domain.service.PurchaseService;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import co.edu.ucatolica.hisclinic.infraestructure.integration.mercadopago.MercadoPagoService;
import co.edu.ucatolica.hisclinic.infraestructure.repository.HookDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class UpdatePaymentUseCaseImpl implements UpdatePaymentUseCase{
    private final MercadoPagoService mercadoPagoService;
    private  final PurchaseService purchaseService;
    private  final HookDAO hookDAO;
    @Override
    public ResponseEntity<ResponseDTO> updatePayment(Map<?, ?> mercadoPagoDTO) throws JsonProcessingException {

        Hook hook = new Hook();
        hook.setJson(mercadoPagoDTO.toString());
        hookDAO.save(hook);

        Map<?,?> data = (Map<?, ?>) mercadoPagoDTO.get("data");
        String paymentId = (String) data.get("id");

        Map<?,?> paymentMetadata = mercadoPagoService.getPaymentMetadata(paymentId);
        Long externalReference = Long.valueOf(paymentMetadata.get("external_reference").toString());
        String status = paymentMetadata.get("status").toString();


        Purchase purchase = purchaseService.findById(externalReference);
        purchase.setPaid(status == "approved");
        purchase.setPaymentProcessorState(status);
        purchaseService.save(purchase);

        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Ok.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
                , HttpStatus.OK
        );
    }
}
