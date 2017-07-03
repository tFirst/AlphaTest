package com.alpha.bean;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany
    @JoinColumn(name = "id")
    private Long id;
    @Column
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Set<Long> typeId = new HashSet<>();
    @Column
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Set<Long> brandId = new HashSet<>();
    @Column
    private String title;
    @Column
    private Long count;
    @Column
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getTypeId() {
        return typeId;
    }

    public void setTypeId(Set<Long> typeId) {
        this.typeId = typeId;
    }

    public Set<Long> getBrandId() {
        return brandId;
    }

    public void setBrandId(Set<Long> brandId) {
        this.brandId = brandId;
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
