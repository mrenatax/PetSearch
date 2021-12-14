package com.nc.petSearch.entity;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name; // Порода

    @Column(name = "typeOfPet")
    private String typeOfPet; // Собака/кошка

    @Column(name = "description")
    private String description;

    @Column(name = "gender")
    private String gender;

    @Column(name = "size")//TODO взрослого животного?
    private int size;

    @Column(name = "age")//TODO в бд хранить др, а отображать возраст в месяцах
    private int age; // возраст в месяцах

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    public Pet setName(String petName) {
        this.name = petName;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setTypeOfPet(String typeOfPet) {
        this.typeOfPet = typeOfPet;
        return this;
    }

    public String getTypeOfPet() {
        return typeOfPet;
    }

    public Pet setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pet setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Pet setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Pet setSize(int size) {
        this.size = size;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Pet setAge(int age) {
        this.age = age;
        return this;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeOfPet='" + typeOfPet + '\'' +
                ", description='" + description + '\'' +
                ", gender='" + gender + '\'' +
                ", size=" + size +
                ", age=" + age +
                '}';
    }
}