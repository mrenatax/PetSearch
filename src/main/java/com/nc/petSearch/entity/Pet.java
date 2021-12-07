package com.nc.petSearch.entity;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "petName")
    private String petName;//TODO: в целом pet в названии уже излишне, т.к. name относится к классу Pet
    @Column(name = "description")
    private String description;//TODO: для читаемости лучше бы добавить по строчке между переменными

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
