package com.nc.petSearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.function.Supplier;

@Entity
@Builder
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name; // Порода

    @Column(name = "typeOfPet")
    private String typeOfPet; // Собака/кошка

    @Column(name = "description", columnDefinition="text")
    private String description;

    @Column(name = "gender")
    private String gender;

    @Column(name = "size")//TODO взрослого животного?
    private int size;

    @Column(name = "age")//TODO в бд хранить др, а отображать возраст в месяцах
    private int age; // возраст в месяцах

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "pictureForDescription")
    private String pictureForDescription;

    @Column(name = "minorPictureForDescription")
    private String minorPictureForDescription;


    public Pet() {
    }

    public Pet(String name, String typeOfPet, String description, String gender, String avatar) {
        this.name = name;
        this.typeOfPet = typeOfPet;
        this.description = description;
        this.gender = gender;
        this.avatar = avatar;
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

    /**
     * @param avatar название изображения (например dog1.jpeg), расположенного в ресурсах ./src/main/resources/static/img/pets/
     * @return
     */
    public Pet setAvatar(String avatar) {
        this.avatar = "./src/main/resources/static/img/pets/" + avatar;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public Pet setPictureForDescription(String pic) {
        this.pictureForDescription = "./src/main/resources/static/img/pets/" + pic;
        return this;
    }

    public String getPictureForDescription() {
        return pictureForDescription;
    }

    public Pet setMinorPictureForDescription(String pic) {
        this.minorPictureForDescription = "./src/main/resources/static/img/pets/" + pic;
        return this;
    }

    public String getMinorPictureForDescription() {
        return minorPictureForDescription;
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