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

    //TODO: сеттеры я бы предложил переделать, чтобы их можно было объединять в цепочки вызовов
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

    public String getName() {
        return name;
    }

    public void setName(String petName) {
        this.name = petName;
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
