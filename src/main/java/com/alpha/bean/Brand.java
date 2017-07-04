package com.alpha.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_brand")
    private long id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Product> product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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