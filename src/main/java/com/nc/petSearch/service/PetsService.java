package com.nc.petSearch.service;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.repository.PetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {//TODO: DB в названии точно лишнее, если в репозитории еще можно было это допустить, то сервис к бд отношения не имеет, так же обращаю внимание на код стайл

    private final PetsRepo petsDBRepo;

    @Autowired
    public PetsService(PetsRepo petsRepo) {
        this.petsDBRepo = petsRepo;
    }

    public void createPet(Pet pet) {//TODO: название метода странно, лучше подходит просто createPet, то же самое касается и метода remove
        petsDBRepo.save(pet);
    }

    public void removePet(Pet pet) {
        petsDBRepo.delete(pet);
    }

    public List<Pet> findAll() {
        return petsDBRepo.findAll();
    }

    public Pet findById(int Id) {//TODO: метод из репозитория вернет Pet по его id, почему тут какой-то userId?)
        return petsDBRepo.findById(Id).orElse(null);
    }

    public List<Pet> findAllByName(String name) {
        return petsDBRepo.findAllByPetName(name);
    }

}
