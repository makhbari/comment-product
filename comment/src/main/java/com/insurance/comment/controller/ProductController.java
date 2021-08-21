package com.insurance.comment.controller;

import com.insurance.comment.dto.ReviewProductRequestDto;
import com.insurance.comment.repository.filter.ProductFilterDto;
import com.insurance.comment.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("دریافت اطلاعات محصولات")
    @PostMapping("/review")
    public ResponseEntity review(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                 @RequestParam(value = "sortBy", defaultValue = "createdDate", required = false) String sortBy,
                                 @RequestParam(value = "sortDirection", defaultValue = "desc", required = false) String sortDirection,
                                 @NotNull(message = "invalid request") @Valid @RequestBody ReviewProductRequestDto dto) {
        return productService.review(getProductFilterDto(dto, page, size, sortBy, sortDirection));
    }

    private ProductFilterDto getProductFilterDto(ReviewProductRequestDto dto, int page, int size, String sortBy, String sortDirection) {
        ProductFilterDto filterDto = new ProductFilterDto();
        filterDto.setPageNumber(page);
        filterDto.setPageSize(size);
        filterDto.setSortBy(sortBy);
        filterDto.setSortDirection(sortDirection);
        filterDto.setCode(dto.getCode());
        filterDto.setTitle(dto.getTitle());
        filterDto.setType(dto.getType());
        filterDto.setEnable(dto.getEnable());
        filterDto.setVisitable(dto.getVisitable());
        filterDto.setRegisterVote(dto.getRegisterVote());
        filterDto.setRegisterComment(dto.getRegisterComment());
        filterDto.setFromCreationDate(dto.getFromCreationDate());
        filterDto.setToCreationDate(dto.getToCreationDate());
        return filterDto;
    }
}
