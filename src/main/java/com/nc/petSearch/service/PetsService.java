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

    /**
     * Поиск домашних животных по заданной породе (фильтрация)
     *
     * @param name порода питомца
     * @return List<Pet>
     */
    public List<Pet> findAllByName(String name) {
        return petsDBRepo.findAllByName(name);
    }

    /**
     * Поиск домашних животных по заданному типу
     *
     * @param typeOfPet тип питомца (dog, cat и т.д.) (фильтрация по заданному типу)
     * @return List<Pet>
     */
    public List<Pet> findAllByTypeOfPet(String typeOfPet) {
        return petsDBRepo.findAllByTypeOfPet(typeOfPet);
    }

    /**
     * Поиск домашних животных по полу (фильтрация по заданному полу)
     *
     * @param gender пол питомца
     * @return List<Pet>
     */
    public List<Pet> findAllByGender(String gender) {
        return petsDBRepo.findAllByGender(gender);
    }

    /**
     * Поиск домашних животных по размеру (фильтрация по заданному размеру)
     *
     * @param size размер в холке
     * @return List<Pet>
     */
    public List<Pet> findAllBySize(int size) {
        return petsDBRepo.findAllBySize(size);
    }

    /**
     * Сортировака по размеру в порядке возрастания
     *
     * @return List<Pet>
     */
    public List<Pet> sortBySizeAscending() {
        return petsDBRepo.sortBySizeAscending();
    }

    /**
     * Сортировака по размеру в порядке убывания
     *
     * @return List<Pet>
     */
    public List<Pet> sortBySizeDescending() {
        return petsDBRepo.sortBySizeDescending();
    }

    /**
     * Сортировка по породе в алфавитном порядке
     *
     * @return List<Pet>
     */
    public List<Pet> sortByNameAlphabetically() {
        return petsDBRepo.sortByNameAlphabetically();
    }
}
