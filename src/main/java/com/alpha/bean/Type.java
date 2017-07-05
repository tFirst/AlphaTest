package com.alpha.bean;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type")
    private Long id;
    private String title;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<Product> product = new HashSet<>();

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
        return "Type {" + "id = " + id + ", title = " + title + '}';
    }
}
