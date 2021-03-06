package com.alpha.bean;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String title;
    private long count;
    private long price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductFeatures> productFeatures = new HashSet<>();

    public Product() {

    }

    public Product(Long id, Type type, Brand brand, String title, int count, int price) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.title = title;
        this.count = count;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Set<ProductFeatures> getProductFeatures() {
        return productFeatures;
    }

    public void setProductFeatures(Set<ProductFeatures> productFeatures) {
        this.productFeatures = productFeatures;
    }
}
