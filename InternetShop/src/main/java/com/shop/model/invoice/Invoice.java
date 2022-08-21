package com.shop.model.invoice;

import com.shop.model.Customer;
import com.shop.model.product.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private List<Product> productList;
    private Customer customer;
    private InvoiceType type;
    private LocalDateTime creatingTime;

    @Override
    public String toString() {
        return "Customer{" +
                "productList=" + productList +
                ", customer=" + customer +
                ", type=" + type +
                ", creatingTime=" + creatingTime +
                '}';
    }
}
