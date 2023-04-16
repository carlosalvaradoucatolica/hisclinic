package co.edu.ucatolica.hisclinic.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "quantity")
    private Integer quantity;
    @NotNull
    @Column(name = "currency_id")
    private String currencyId;
    @NotNull
    @Column(name = "unit_price")
    private Integer unitPrice;
    @JsonIgnore
    @NotNull
    @Column(name = "service_months")
    private Integer serviceMonths;

    public Product(String title, String description, Integer quantity, String currencyId, Integer unitPrice, Integer serviceMonths) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.currencyId = currencyId;
        this.unitPrice = unitPrice;
        this.serviceMonths = serviceMonths;
    }
}
