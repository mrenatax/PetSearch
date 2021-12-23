package com.nc.petSearch.service;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.repository.PetsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class PetsService {

    @Autowired
    private final PetsRepo petsRepo;

    public PetsService(PetsRepo petsRepo) {
        this.petsRepo = petsRepo;
    }

    public void createPet(Pet pet) {
        petsRepo.save(pet);
    }

    public void removePet(Pet pet) {
        petsRepo.delete(pet);
    }

    public Page<Pet> findAll(int pageNum, String field, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 12, sortDir.equals("asc") ? Sort.by(field).ascending()
                : Sort.by(field).descending());
        return petsRepo.findAll(pageable);

    }

    public List<Pet> findAllForUser() {
        List<Pet> result = new ArrayList<>();
        petsRepo.findAll().forEach(result::add);
        //TODO из листа в лист перегоняем данные?)
        //Ну и
        return result;
    }

    public Pet findById(int id) {
        return petsRepo.findById(id).orElse(null);
    }

    public Page<Pet> findAllByKeyword(int pageNum, String keyword, String field, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, 12, sortDir.equals("asc") ? Sort.by(field).ascending()
                : Sort.by(field).descending());

        if (keyword != null) {
            return petsRepo.findAllByKeyword(keyword.toUpperCase(Locale.ROOT), pageable);
        }
        return petsRepo.findAll(pageable);
    }


    /**
     * Поиск домашних животных по заданной породе (фильтрация)
     *
     * @param name порода питомца
     * @return List<Pet>
     */
    public List<Pet> findAllByName(String name) {
        return petsRepo.findAllByName(name);
    }

    /**
     * Поиск домашних животных по заданному типу
     *
     * @param typeOfPet тип питомца (dog, cat и т.д.) (фильтрация по заданному типу)
     * @return List<Pet>
     */
    public List<Pet> findAllByTypeOfPet(String typeOfPet) {
        return petsRepo.findAllByTypeOfPet(typeOfPet);
    }

    /**
     * Поиск домашних животных по полу (фильтрация по заданному полу)
     *
     * @param gender пол питомца
     * @return List<Pet>
     */
    public List<Pet> findAllByGender(String gender) {
        return petsRepo.findAllByGender(gender);
    }

    /**
     * Поиск домашних животных по размеру (фильтрация по заданному размеру)
     *
     * @param size размер в холке
     * @return List<Pet>
     */
    public List<Pet> findAllBySize(int size) {
        return petsRepo.findAllBySize(size);
    }

    /**
     * Сортировака по размеру в порядке возрастания
     *
     * @return List<Pet>
     */
    public List<Pet> sortBySizeAscending() {
        return petsRepo.sortBySizeAscending();
    }

    /**
     * Сортировака по размеру в порядке убывания
     *
     * @return List<Pet>
     */
    public List<Pet> sortBySizeDescending() {
        return petsRepo.sortBySizeDescending();
    }

    /**
     * Сортировка по породе в алфавитном порядке
     *
     * @return List<Pet>
     */
    public List<Pet> sortByNameAlphabetically() {
        return petsRepo.sortByNameAlphabetically();
    }

    /**
     * Поиск питомцев, описание которых содержит указанную фразу
     *
     * @param phrase ключевое слово для поиска
     * @return List<Pet>
     */
    public List<Pet> findByDescriptionContainingIgnoreCase(String phrase) {
        return petsRepo.findByDescriptionContainingIgnoreCase(phrase);
    }

    /**
     * Поиск домашних животных по возрасту (фильтрация по заданному возрасту)
     *
     * @param birthDate возраст в месяцах
     * @return List<Pet>
     */
    public List<Pet> findAllByBirthDate(LocalDate birthDate) {
        return petsRepo.findAllByBirthDate(birthDate);
    }

    /**
     * Сортировака по возрасту в порядке возрастания
     *
     * @return List<Pet>
     */
    public List<Pet> sortByBirthDateAscending() {
        return petsRepo.sortByBirthDateAscending();
    }

    /**
     * Сортировака по возрасту в порядке убывания
     *
     * @return List<Pet>
     */
    public List<Pet> sortByBirtDateDescending() {
        return petsRepo.sortByBirthDateDescending();
    }
}
