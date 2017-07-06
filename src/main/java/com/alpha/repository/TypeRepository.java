package com.alpha.repository;

import com.alpha.bean.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {
    public Set<Type> findAll();
}
