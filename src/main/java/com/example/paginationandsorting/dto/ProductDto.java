package com.example.paginationandsorting.dto;

import com.example.paginationandsorting.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private Long id;
    private String brand_name;
    private String description;
    private String name;
    private Double offer_price;
    private LocalDateTime offer_valid_until;
    private Double original_price;

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this);
    }
}
