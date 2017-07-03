package com.alpha.bean;

import javax.persistence.*;

@Entity
@Table(name = "Brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Brand {" + "id=" + id + ", title=" + title + '}';
    }
}