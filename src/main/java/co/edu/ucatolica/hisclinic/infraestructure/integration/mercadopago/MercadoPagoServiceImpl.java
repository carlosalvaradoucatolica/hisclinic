package co.edu.ucatolica.hisclinic.infraestructure.integration.mercadopago;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Service
@PropertySource("classpath:application.yml")
public class MercadoPagoServiceImpl implements MercadoPagoService{
    private static String accessToken;
    private static String url;
    private static String endpointCreatePayment;
    private static String endpointGetPaymentMetadata;

    @Autowired
    public MercadoPagoServiceImpl(
            @Value("${mercadopago.access-token}") String accessToken,
            @Value("${mercadopago.url}") String url,
            @Value("${mercadopago.endpoint-create-payment}") String endpointCreatePayment,
            @Value("${mercadopago.endpoint-get-payment-metadata}") String endpointGetPaymentMetadata
    ){
        MercadoPagoServiceImpl.url = url;
        MercadoPagoServiceImpl.accessToken = accessToken;
        MercadoPagoServiceImpl.endpointCreatePayment = endpointCreatePayment;
        MercadoPagoServiceImpl.endpointGetPaymentMetadata = endpointGetPaymentMetadata;
    }

    @Override
    public Map<?, ?> createPaymentLink(Product product, String externalReference) throws JsonProcessingException {

        String uri = url + endpointCreatePayment;
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        Map<?,?> toBody = Map.of("items",products,"external_reference",externalReference);
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

    @Override
    public Map<?, ?> getPaymentMetadata(String paymentId) {
        String uri = url + endpointGetPaymentMetadata + paymentId;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        RequestEntity<Object> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(uri));

        ResponseEntity<Map> responseEntity = restTemplate.exchange(requestEntity, Map.class);
        return responseEntity.getBody();


    }
}
