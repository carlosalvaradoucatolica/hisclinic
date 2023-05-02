package co.edu.ucatolica.hisclinic.domain.service;

import co.edu.ucatolica.hisclinic.domain.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseServiceTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PurchaseService purchaseService;

    @Test
    void save(){
        AppUser appUser = new AppUser("carlosalvaradom@icloud.com","Carlos","Alvarado",false,"123123",null);
        Product product = new Product("Servicios Hisclinic 1 Mes","Licencia 1 Mes de vigencia x 1 usuario de acceso para visualizar, crear y editar historias clinicas.",1,"COP",60000,1);
        Purchase purchase = new Purchase(appUser,product, LocalDateTime.now(),LocalDateTime.now(),"ExampleReferenceMP",false,"pending");
        Purchase purchasePersisted = purchaseService.save(purchase);
        assertInstanceOf(purchasePersisted.getClass(),Purchase.class);
    };
    @Test
    void getByExternalReference(){
        Purchase purchase = purchaseService.getByExternalReference("ExampleReferenceMP");
        assertInstanceOf(purchase.getClass(),Purchase.class);
    };
}
