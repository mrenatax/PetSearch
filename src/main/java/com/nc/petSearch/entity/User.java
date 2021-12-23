package com.nc.petSearch.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

//TODO почему вообще здесь используете ломбок, а в другом классе нет?

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor//TODO @Data уже содержит в себе @RequiredArgsConstructor, зачем нужны эти?
public class User {

    //TODO почему у животного id - это Integer, а у юзера это Long, и GenerationType.IDENTITY вместо AUTO?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany
    private Set<Role> roles;
}
