package com.cemp.bci.users.repository;

import com.cemp.bci.users.entity.InputEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<InputEntity, String> {
    InputEntity findByEmail(String email);

}
