package com.insurance.comment.repository;

import com.insurance.comment.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}