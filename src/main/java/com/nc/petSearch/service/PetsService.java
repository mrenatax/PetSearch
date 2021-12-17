package com.nc.petSearch.service;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.repository.PetsDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
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

    public Page<Pet> findAll(int pageNum, String field, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum-1, 12, sortDir.equals("asc") ? Sort.by(field).ascending()
                : Sort.by(field).descending());
        return petsDBRepo.findAll(pageable);

    }

    public List<Pet> findAllForUser() {
        List<Pet> result = new ArrayList<Pet>();
        petsDBRepo.findAll().forEach(result::add);
        return result;
    }

    public Pet findById(int id) {
        return petsDBRepo.findById(id).orElse(null);
    }

    public Page<Pet> findAllByKeyword(int pageNum,String keyword, String field, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, 12, sortDir.equals("asc") ? Sort.by(field).ascending()
                : Sort.by(field).descending());

        if (keyword != null) {
            return petsDBRepo.findAllByKeyword(keyword, pageable);
        }
        return petsDBRepo.findAll(pageable);
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

    /**
     * Поиск питомцев, описание которых содержит указанную фразу
     *
     * @param phrase
     * @return List<Pet>
     */
    public List<Pet> findByDescriptionContainingIgnoreCase(String phrase) {
        return petsDBRepo.findByDescriptionContainingIgnoreCase(phrase);
    }

    /**
     * Поиск домашних животных по возрасту (фильтрация по заданному возрасту)
     *
     * @param age возраст в месяцах
     * @return List<Pet>
     */
    public List<Pet> findAllByAge(int age) {
        return petsDBRepo.findAllByAge(age);
    }

    /**
     * Сортировака по возрасту в порядке возрастания
     *
     * @return List<Pet>
     */
    public List<Pet> sortByAgeAscending() {
        return petsDBRepo.sortByAgeAscending();
    }

    /**
     * Сортировака по возрасту в порядке убывания
     *
     * @return List<Pet>
     */
    public List<Pet> sortByAgeDescending() {
        return petsDBRepo.sortByAgeDescending();
    }
}