package com.nc.petSearch.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name; // Порода

    @Column(name = "typeOfPet")
    private String typeOfPet; // Собака/кошка

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "gender")
    private String gender;

    @Column(name = "size")
    private int size;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "pictureForDescription")
    private String pictureForDescription;

    @Column(name = "minorPictureForDescription")
    private String minorPictureForDescription;

    @Column(name = "birthDate")
    private LocalDate birthDate; // дата рождения

    private long age; // возраст в месяцах


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

    /**
     * @param avatar название изображения (например dog1.jpeg), располоэенного в ресурсах ./src/main/resources/static/img/pets/
     * @return Pet
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

    public Pet setAge(LocalDate currentDate) {
        this.age = ChronoUnit.MONTHS.between(birthDate, currentDate);
        return this;
    }

    public long getAge() {
        return age;
    }

    public Pet setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeOfPet='" + typeOfPet + '\'' +
                ", description='" + description + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", pictureForDescription='" + pictureForDescription + '\'' +
                ", minorPictureForDescription='" + minorPictureForDescription + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                '}';
    }
}