package co.edu.ucatolica.hisclinic.infraestructure.integration.mercadopago;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Service
@PropertySource("classpath:application.yml")
public class MercadoPagoServiceImpl implements MercadoPagoService{
    private static String accessToken;
    private static String url;
    private static String endpointCreatePayment;
    @Autowired
    public MercadoPagoServiceImpl(
            @Value("${mercadopago.access-token}") String accessToken,
            @Value("${mercadopago.url}") String url,
            @Value("${mercadopago.endpoint-create-payment}") String endpointCreatePayment
    ){
        MercadoPagoServiceImpl.url = url;
        MercadoPagoServiceImpl.accessToken = accessToken;
        MercadoPagoServiceImpl.endpointCreatePayment = endpointCreatePayment;
    }

    @Override
    public Map<?, ?> createPaymentLink(Product product) throws JsonProcessingException {

        String uri = url + endpointCreatePayment;
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        Map<?,?> toBody = Map.of("items",products);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(toBody);

        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        try {
            Map<?,?> response = restTemplate.postForObject(uri, httpEntity, Map.class);
            return Map.of("statusCode", 201,"message","Data retrieved successfull", "data",Map.of("id",response.get("id"),"url",response.get("init_point")));
        } catch (HttpClientErrorException e) {
            return Map.of("statusCode", e.getStatusCode().value(),"message", Objects.requireNonNull(e.getMessage()), "data","");
        }
    }
}
