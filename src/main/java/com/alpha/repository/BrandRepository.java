package com.alpha.repository;


import com.alpha.bean.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    public Set<Brand> findAll();
}
