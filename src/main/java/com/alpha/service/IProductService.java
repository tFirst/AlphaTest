package com.alpha.service;


import com.alpha.bean.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductService extends CrudRepository<Product, Long> {
    public List<Product> findAll();
}
