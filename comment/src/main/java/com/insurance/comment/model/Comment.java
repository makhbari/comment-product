package com.insurance.comment.model;

import com.insurance.comment.model.enumeration.CommentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMENT", uniqueConstraints = {@UniqueConstraint(name = "uk_comment_1", columnNames = {"USER_ID", "PRODUCT_ID"})})
@Data
public class Comment extends BaseEntity {
    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(targetEntity = Product.class, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "comment")
    private String comment;

    @Column(name = "vote")
    private String vote;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CommentStatus status;
}
