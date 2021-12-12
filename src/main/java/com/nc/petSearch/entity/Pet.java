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
    private String petName;//TODO: в целом pet в названии уже излишне, т.к. name относится к классу Pet

    @Column(name = "description")
    private String description;//TODO: для читаемости лучше бы добавить по строчке между переменными
}
