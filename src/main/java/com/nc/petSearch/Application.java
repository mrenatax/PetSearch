package com.nc.petSearch;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsDB_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nc.petSearch.service"})
public class Application {
    @Autowired
    private PetsDB_Service petsDB_service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods() {
        Pet pet = new Pet();
        pet.setPetName("Tom");
        pet.setText("It's cat");
        Pet pet1 = new Pet();
        pet1.setPetName("Jerry");
        pet1.setText("It's mouse");
        petsDB_service.createPetsDB(pet);
        petsDB_service.createPetsDB(pet1);
        System.out.println("After adding");
        petsDB_service.findAll().forEach(it -> System.out.println(it));
    }
}