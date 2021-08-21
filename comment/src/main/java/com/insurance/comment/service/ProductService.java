package com.insurance.comment.service;

import com.insurance.comment.dto.ProductDto;
import com.insurance.comment.dto.ProductType;
import com.insurance.comment.dto.ReviewProductResponseDto;
import com.insurance.comment.model.Product;
import com.insurance.comment.repository.ProductRepository;
import com.insurance.comment.repository.filter.ProductFilterDto;
import com.insurance.comment.repository.specs.ProductBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity review(ProductFilterDto dto) {
        ProductBuilder builder = new ProductBuilder();
        Specification<Product> specs = builder.build(dto);
        PageRequest pageRequest = PageRequest.of(
                dto.getPageNumber() - 1,
                dto.getPageSize(),
                Sort.by(dto.findSortDirection(), "createdDate"));
        Page<Product> productPage = productRepository.findAll(specs, pageRequest);

        ReviewProductResponseDto responseDto = new ReviewProductResponseDto();
        if (!CollectionUtils.isEmpty(productPage.getContent())) {
            productPage.getContent().forEach(product -> responseDto.getProducts().add(new ProductDto(product.getId(), product.getCode(), product.getTitle(),
                    ProductType.valueOf(product.getType().name()), product.getDescription(),
                    product.getEnable(), product.getVisitable(), product.getRegisterComment(), product.getRegisterVote(),
                    product.getCreatedDate(), product.getUpdatedDate())));
        }
        responseDto.setCount(productPage.getTotalElements());
        return ResponseEntity.ok(responseDto);
    }
}
