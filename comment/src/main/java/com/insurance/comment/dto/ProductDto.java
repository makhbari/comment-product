package com.insurance.comment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private long id;
    private String code;
    private String title;
    private ProductType type;
    private String description;
    private Boolean enable;
    private Boolean visitable;
    private Boolean registerComment;
    private Boolean registerVote;
    private Date createdDate;
    private Date updatedDate;

    public ProductDto(long id, String code, String title, ProductType type, String description, Boolean enable, Boolean visitable,
                      Boolean registerComment, Boolean registerVote, Date createdDate, Date updatedDate) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.type = type;
        this.description = description;
        this.enable = enable;
        this.visitable = visitable;
        this.registerComment = registerComment;
        this.registerVote = registerVote;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
