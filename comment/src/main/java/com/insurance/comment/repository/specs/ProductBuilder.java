package com.insurance.comment.repository.specs;


import com.insurance.comment.model.Product;
import com.insurance.comment.model.enumeration.ProductType;
import com.insurance.comment.repository.filter.ProductFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductBuilder extends SpecificationsBuilder<ProductFilterDto, Product> {
    @Override
    public Specification<Product> build(ProductFilterDto filterDto) {
        if (StringUtils.hasLength(filterDto.getCode())) {
            with("code", "eq", filterDto.getCode());
        }
        if (StringUtils.hasLength(filterDto.getTitle())) {
            with("title", "~", filterDto.getTitle());
        }
        if (filterDto.getType() != null) {
            with("type", "eq", ProductType.valueOf(filterDto.getType().name()));
        }
        if (filterDto.getVisitable() != null) {
            with("visitable", "eq", filterDto.getVisitable());
        }
        if (filterDto.getEnable() != null) {
            with("enable", "eq", filterDto.getEnable());
        }
        if (filterDto.getRegisterComment() != null) {
            with("registerComment", "eq", filterDto.getRegisterComment());
        }
        if (filterDto.getRegisterVote() != null) {
            with("registerVote", "eq", filterDto.getRegisterVote());
        }
        if (filterDto.getFromCreationDate() != null) {
            with("createdDate", ">=", filterDto.getFromCreationDate());
        }
        if (filterDto.getToCreationDate() != null) {
            with("createdDate", "<=", filterDto.getToCreationDate());
        }

        return super.build(EntitySpecification::new);
    }
}
