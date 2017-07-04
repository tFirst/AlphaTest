package com.alpha.bean;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private long id;

    @ManyToOne(targetEntity = Type.class)
    @JoinColumn(name = "type_id")
    private Set<Type> type = new HashSet<Type>();
    @ManyToOne(targetEntity = Brand.class)
    @JoinColumn(name = "brand_id")
    private Set<Brand> brand = new HashSet<Brand>();

    private String title;
    private long count;
    private long price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductFeatures> productFeatures;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
