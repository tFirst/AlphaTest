package com.alpha.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Features")
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_feature")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY)
    private Set<ProductFeatures> productFeatures = new HashSet<>();

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
}
