package com.insurance.comment.model;

import com.insurance.comment.model.enumeration.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT")
@Data
public class Product extends BaseEntity {
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @Column(name = "description")
    private String description;

    private Boolean enable;

    private Boolean visitable;

    private Boolean registerComment;

    private Boolean registerVote;
}
