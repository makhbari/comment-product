package com.insurance.comment.repository;

import com.insurance.comment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}