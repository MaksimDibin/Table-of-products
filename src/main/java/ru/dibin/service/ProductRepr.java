package ru.dibin.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dibin.persist.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ProductRepr {

    Long id;

    @NotBlank
    String name;

    String description;

    @Min(0)
    @NotNull
    BigDecimal price;

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
