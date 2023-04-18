package co.edu.ucatolica.hisclinic.infraestructure.dto.request;

import co.edu.ucatolica.hisclinic.domain.model.Product;
import co.edu.ucatolica.hisclinic.domain.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
    private Integer productId;

    public Purchase valid(){

        if(
            productId != null
            && productId > 0
        ) {
            return new Purchase(
                    new Product(productId)
            );
        } else {
            return null;
        }
    }
}
