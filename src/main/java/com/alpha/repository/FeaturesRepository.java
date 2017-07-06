package com.alpha.repository;

import com.alpha.bean.Features;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface FeaturesRepository extends CrudRepository<Features, Long> {
    public Set<Features> findAll();
}
