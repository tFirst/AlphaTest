package com.alpha.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Product_Features")
public class ProductFeatures {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Set<Long> productId = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Set<Long> featureId = new HashSet<>();
    @Column
    private String value;

    public Set<Long> getProductId() {
        return productId;
    }

    public void setProductId(Set<Long> productId) {
        this.productId = productId;
    }

    public Set<Long> getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Set<Long> featureId) {
        this.featureId = featureId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
