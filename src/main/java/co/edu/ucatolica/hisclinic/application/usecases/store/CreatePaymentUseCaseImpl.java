package co.edu.ucatolica.hisclinic.application.usecases.store;

import co.edu.ucatolica.hisclinic.domain.model.AppUser;
import co.edu.ucatolica.hisclinic.domain.model.Auditoria;
import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.domain.model.Purchase;
import co.edu.ucatolica.hisclinic.domain.service.*;
import co.edu.ucatolica.hisclinic.infraestructure.dto.request.PurchaseDTO;
import co.edu.ucatolica.hisclinic.infraestructure.dto.response.ResponseDTO;
import co.edu.ucatolica.hisclinic.infraestructure.integration.mercadopago.MercadoPagoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@PropertySource("classpath:application.yml")
public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {
    private final AppUserService appUserService;
    private final PurchaseService purchaseService;
    private final ProductService productService;
    private final MercadoPagoService mercadoPagoService;
    @Autowired
    private TrasabilidadServiceBDStrategy trasabilidadServiceBDStrategy;
    @Autowired
    private TrasabilidadServiceFileStrategy trasabilidadServiceFileStrategy;
    private String paymentStateDefault;
    private Integer purchaseExpiresHoursDefault;

    private String auditoriaDefault;

    @Autowired
    public CreatePaymentUseCaseImpl(
            @Value("${mercadopago.payment-state-default}") String paymentStateDefault,
            @Value("${mercadopago.purchase-expires-time-hours-default}") Integer purchaseExpiresHoursDefault,
            @Value("${auditoria.default}") String auditoriaDefault,
            AppUserService appUserService,
            PurchaseService purchaseService,
            ProductService productService,
            MercadoPagoService mercadoPagoService
    ){
        this.appUserService = appUserService;
        this.purchaseService = purchaseService;
        this.mercadoPagoService = mercadoPagoService;
        this.productService = productService;
        this.paymentStateDefault = paymentStateDefault;
        this.purchaseExpiresHoursDefault = purchaseExpiresHoursDefault;
        this.auditoriaDefault = auditoriaDefault;
    }

    @Override
    public ResponseEntity<ResponseDTO> createPayment(PurchaseDTO purchaseDTO) throws JsonProcessingException {

        //TODO: Pendiente eliminar, se tiene que obtener el AppUser por medio de JWT, de momento obtenemos intencionalmente con una cuenta de pruebas.
        AppUser appUser = appUserService.getByEmail("carlosalvaradom@icloud.com");
        //Hacemos validacion del formato de la peticion
        Purchase purchase = purchaseDTO.valid();
        if (purchase == null) {
            //TODO: esta es la implementación del parcial
            Auditoria auditoria = new Auditoria();
            auditoria.setJson(purchaseDTO.toString());
            auditoria.setCreatedAt(LocalDateTime.now());
            auditoria.setAppUser(appUser);
            auditoria.setStatusCodeResponse(HttpStatus.BAD_REQUEST.value());
            if(auditoriaDefault.equals("db")){
                trasabilidadServiceBDStrategy.guardarAuditoria(auditoria);
            } else {
                trasabilidadServiceFileStrategy.guardarAuditoria(auditoria);
            }

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Los datos no contienen un formato correcto, por favor valide formato del producto a pagar e intente nuevamente.")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }

        //Buscamos el producto por id
        Optional<Product> product = productService.getProduct(purchase.getProduct().getId());

        //Si el producto no existe retornamos error
        if(product.isEmpty()){

            //TODO: esta es la implementación del parcial
            Auditoria auditoria = new Auditoria();
            auditoria.setJson(purchaseDTO.toString());
            auditoria.setCreatedAt(LocalDateTime.now());
            auditoria.setAppUser(appUser);
            auditoria.setStatusCodeResponse(HttpStatus.BAD_REQUEST.value());
            if(auditoriaDefault.equals("db")){
                trasabilidadServiceBDStrategy.guardarAuditoria(auditoria);
            } else {
                trasabilidadServiceFileStrategy.guardarAuditoria(auditoria);
            }

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("El codigo de producto no existe o no se encuentra vigente, por favor consulte los productos disponibles y vuelva a intentarlo.")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }

        //Realizamos la solicitud a la pasarela de pagos para poder obtener el link de pago y la referencia de pago externa.
        Map<?,?> response = mercadoPagoService.createPaymentLink(product.get());
        if((Integer) response.get("statusCode") != 201){

            //TODO: esta es la implementación del parcial
            Auditoria auditoria = new Auditoria();
            auditoria.setJson(purchaseDTO.toString());
            auditoria.setCreatedAt(LocalDateTime.now());
            auditoria.setAppUser(appUser);
            auditoria.setStatusCodeResponse(HttpStatus.CONFLICT.value());
            if(auditoriaDefault.equals("db")){
                trasabilidadServiceBDStrategy.guardarAuditoria(auditoria);
            } else {
                trasabilidadServiceFileStrategy.guardarAuditoria(auditoria);
            }

            return new ResponseEntity<>(
                    ResponseDTO.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Error de la pasarela de pagos, por favor intenta de nuevo mas tarde. " + response.get("message"))
                            .status(HttpStatus.CONFLICT)
                            .statusCode(HttpStatus.CONFLICT.value())
                            .build()
                    , HttpStatus.CONFLICT
            );
        }

        //Construimos el objeto tipo Purchase con la referencia externa y demas pagos, y lo retornamos conforma a US
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setPaid(false);
        purchase.setAppUser(appUser);
        purchase.setProduct(product.get());
        purchase.setPaymentProcessorState(paymentStateDefault);
        purchase.setExpiresAt(LocalDateTime.now().plusHours(purchaseExpiresHoursDefault));
        purchase.setPaymentProcessorReference((String) ((Map<?,?>) response.get("data")).get("id"));
        purchase = purchaseService.save(purchase);

        //TODO: esta es la implementación del parcial
        Auditoria auditoria = new Auditoria();
        auditoria.setJson(purchaseDTO.toString());
        auditoria.setCreatedAt(LocalDateTime.now());
        auditoria.setAppUser(appUser);
        auditoria.setStatusCodeResponse(HttpStatus.CREATED.value());
        if(auditoriaDefault.equals("db")){
            trasabilidadServiceBDStrategy.guardarAuditoria(auditoria);
        } else {
            trasabilidadServiceFileStrategy.guardarAuditoria(auditoria);
        }
        //Retornamos la respuesta con el link de pago y otros datos.
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Link de pago generado con exito, tienes 24 horas para realizar el pago.")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(Map.of(
                                "hisclinicReference",purchase.getId(),
                                "paymentLink", (String) ((Map<?,?>) response.get("data")).get("url"),
                                "pasarelaReference",purchase.getPaymentProcessorReference(),
                                "linkExpiresAt",purchase.getExpiresAt()
                        ))
                        .build()
                , HttpStatus.CREATED
        );
    }
}
