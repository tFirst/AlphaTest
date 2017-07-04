package com.alpha.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product_Features")
public class ProductFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "id_product")
    private Set<Product> product = new HashSet<>();
    @ManyToOne(targetEntity = Features.class)
    @JoinColumn(name = "id_feature")
    private Set<Features> feature = new HashSet<>();
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Set<Features> getFeature() {
        return feature;
    }

    public void setFeature(Set<Features> feature) {
        this.feature = feature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
