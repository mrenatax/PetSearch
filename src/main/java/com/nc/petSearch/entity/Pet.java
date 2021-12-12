package com.nc.petSearch.entity;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Pet() {
    }

    public Pet(String description) {
        this.description = description;

    }

    public Pet setText(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public Pet setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String petName) {
        this.name = petName;
        return this;
    }

    @Override
    public String toString() {
        return "PetsDB{" +
                "id=" + id +
                ", petName='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}