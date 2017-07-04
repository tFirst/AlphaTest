package com.alpha.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Features")
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_feature")
    private long id;

    private String feature;

    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY)
    private List<ProductFeatures> productFeatures;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
