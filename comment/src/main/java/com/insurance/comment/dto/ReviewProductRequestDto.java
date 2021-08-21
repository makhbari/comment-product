package com.insurance.comment.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ReviewProductRequestDto {
    private String code;
    private String title;
    private ProductType type;
    private Boolean enable;
    private Boolean visitable;
    private Boolean registerComment;
    private Boolean registerVote;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromCreationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toCreationDate;
}
