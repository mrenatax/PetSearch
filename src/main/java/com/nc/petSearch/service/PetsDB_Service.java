package com.nc.petSearch.service;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.repository.PetsDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsDB_Service {

    @Autowired
    private final PetsDBRepo petsDBRepo;

    public PetsDB_Service(PetsDBRepo petsDBRepo) {
        this.petsDBRepo = petsDBRepo;
    }

    public void createPetsDB(Pet pet) {
        petsDBRepo.save(pet);
    }

    public void removePetsDB(Pet pet) {
        petsDBRepo.delete(pet);
    }

    public List<Pet> findAll() {
        return petsDBRepo.findAll();
    }

    public Pet findById(int userId) {
        return petsDBRepo.findById(userId).orElse(null);
    }

    public List<Pet> findAllByName(String name) {
        return petsDBRepo.findAllByPetName(name);
    }

}