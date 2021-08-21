package com.insurance.comment.repository.filter;

import com.insurance.comment.dto.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductFilterDto extends FilterDto {
    private String code;
    private String title;
    private ProductType type;
    private Boolean enable;
    private Boolean visitable;
    private Boolean registerComment;
    private Boolean registerVote;
    private Date fromCreationDate;
    private Date toCreationDate;
}
