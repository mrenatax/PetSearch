package com.nc.petSearch.entity;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "petName")
    private String petName;
    @Column(name = "description")
    private String description;


    public Pet() {
    }

    public Pet(String description) {
        this.description = description;

    }

    public void setText(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Override
    public String toString() {
        return "PetsDB{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}