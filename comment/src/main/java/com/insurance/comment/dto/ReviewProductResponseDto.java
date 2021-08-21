package com.insurance.comment.dto;

import java.util.ArrayList;
import java.util.List;

public class ReviewProductResponseDto {
    private List<ProductDto> products;
    private Long count;

    public List<ProductDto> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
