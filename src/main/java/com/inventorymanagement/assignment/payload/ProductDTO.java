package com.inventorymanagement.assignment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private Long product_id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private Long category;
}
