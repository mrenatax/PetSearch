package com.nc.petSearch.service;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.repository.PetsDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {

    @Autowired
    private final PetsDBRepo petsDBRepo;

    public PetsService(PetsDBRepo petsDBRepo) {
        this.petsDBRepo = petsDBRepo;
    }

    public void createPet(Pet pet) {
        petsDBRepo.save(pet);
    }

    public void removePet(Pet pet) {
        petsDBRepo.delete(pet);
    }

    public List<Pet> findAll() {
        return petsDBRepo.findAll();
    }

    public Pet findById(int id) {
        return petsDBRepo.findById(id).orElse(null);
    }

    public List<Pet> findAllByName(String name) {
        return petsDBRepo.findAllByName(name);
    }

}
