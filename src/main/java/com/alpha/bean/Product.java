package com.alpha.bean;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Type.class)
    @JoinColumn(name = "type")
    private Set<Type> type = new HashSet<>();
    @ManyToOne(targetEntity = Brand.class)
    @JoinColumn(name = "brand")
    private Set<Brand> brand = new HashSet<>();

    private String title;
    private Long count;
    private Long price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Type> getType() {
        return type;
    }

    public void setType(Set<Type> type) {
        this.type = type;
    }

    public Set<Brand> getBrand() {
        return brand;
    }

    public void setBrand(Set<Brand> brand) {
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
}
