package com.alpha.bean;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_brand")
    private long id;
    private String title;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private Set<Product> product = new HashSet<Product>();

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