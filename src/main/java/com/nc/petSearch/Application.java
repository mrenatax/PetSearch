package com.nc.petSearch;

import com.nc.petSearch.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private PetsService petsService;//TODO: camelCase

    @Autowired
    public void setApplication (PetsService petsService) {
        this.petsService = petsService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

   /* @EventListener(ApplicationReadyEvent.class)//TODO: hope its just for test))
    private void writeToDB() {
        Pet pet = new Pet();
        pet.setPetName("Pet1");
        pet.setText("--Description of Pet1--");
        Pet pet1 = new Pet();
        pet1.setPetName("Pet2");
        pet1.setText("--Description of Pet1--");
        petsDB_service.createPetsDB(pet);
        petsDB_service.createPetsDB(pet1);
        *//*System.out.println("After adding");
        petsDB_service.findAll().forEach(it -> System.out.println(it));*//*
    }*/
}
