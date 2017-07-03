package com.alpha.bean;


import javax.persistence.*;

@Entity
@Table(name = "Types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type_id")
    private Long id;
    private String type;

    public Type() {}

    public Type(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type {" + "id=" + id + ", type=" + type + '}';
    }
}
