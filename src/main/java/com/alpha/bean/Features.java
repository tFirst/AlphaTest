package com.alpha.bean;

import javax.persistence.*;

@Entity
@Table(name = "Features")
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String feature;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feature_id")
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
