package com.alpha.bean;


import javax.persistence.*;

@Entity
@Table(name = "Types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
