package com.alpha.repository;


import com.alpha.bean.ProductFeatures;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductFeaturesRepository extends CrudRepository<ProductFeatures, Long> {
    public Collection<ProductFeatures> findAll();
}
