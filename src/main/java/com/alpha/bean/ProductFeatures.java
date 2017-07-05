package com.alpha.bean;


import javax.persistence.*;

@Entity
@Table(name = "Product_Features")
public class ProductFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Features feature;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Features getFeature() {
        return feature;
    }

    public void setFeature(Features feature) {
        this.feature = feature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
