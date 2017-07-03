package com.alpha.bean;

import javax.persistence.*;

@Entity
@Table(name = "Features")
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Features")
    @JoinColumn(name = "id")
    private Long id;
    @Column
    private String feature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
