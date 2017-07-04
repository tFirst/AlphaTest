package com.alpha.bean;

import javax.persistence.*;

@Entity
@Table(name = "Brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String brand;

    @OneToMany(mappedBy = "brand")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return brand;
    }

    public void setTitle(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Brand {" + "id=" + id + ", brand=" + brand + '}';
    }
}