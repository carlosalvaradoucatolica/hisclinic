package co.edu.ucatolica.hisclinic.infraestructure.integration.mercadopago;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Map;

public interface MercadoPagoService {
    public Map<?,?> createPaymentLink(Product product) throws JsonProcessingException;
}
