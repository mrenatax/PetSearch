package com.nc.petSearch.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "size")
    private int size;

    @Column(name = "age")
    private int age; // возраст в месяцах

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "pictureForDescription")
    private String pictureForDescription;

    @Column(name = "minorPictureForDescription")
    private String minorPictureForDescription;

}
