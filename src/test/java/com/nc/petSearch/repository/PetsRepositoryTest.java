package com.nc.petSearch.repository;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsServiceTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("test")

public class PetsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PetsRepo petsRepo;

    private Pet getPet() {
        return Pet.builder()
                .id(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE))
                .name(RandomStringUtils.randomAlphabetic(10))
                .typeOfPet(RandomStringUtils.randomAlphabetic(10))
                .description(RandomStringUtils.randomAlphabetic(15))
                .gender("female")
                .size(ThreadLocalRandom.current().nextInt(1, 40))
                .birthDate(LocalDate.now())
                .avatar("/avatar.png")
                .pictureForDescription("bigPicture")
                .minorPictureForDescription("smallPicture")
                .build();
    }

    @Test
    public void findAllByNameTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        pets.add(pet);

        String nameLike = pets.get(0).getName();

        List<Pet> expectedPets = pets.stream()
                .filter(onePet -> onePet.getName().contains(nameLike))
                .collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.findAllByName(nameLike);

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void findAllByTypeOfPetTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        pets.add(pet);

        String typeLike = pets.get(0).getTypeOfPet();

        List<Pet> expectedPets = pets.stream()
                .filter(onePet -> onePet.getTypeOfPet().contains(typeLike))
                .collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.findAllByTypeOfPet(typeLike);

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void findAllByGenderTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        pets.add(pet);

        String genderLike = pets.get(0).getGender();

        List<Pet> expectedPets = pets.stream()
                .filter(onePet -> onePet.getGender().contains(genderLike))
                .collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.findAllByGender(genderLike);

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void findAllBySizeTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        pets.add(pet);

        int sizeLike = pets.get(0).getSize();

        List<Pet> expectedPets = pets.stream()
                .filter(onePet -> onePet.getSize() == sizeLike)
                .collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.findAllBySize(sizeLike);

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void findAllByAgeTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        pets.add(pet);

        LocalDate ageLike = pets.get(0).getBirthDate();

        List<Pet> expectedPets = pets.stream()
                .filter(onePet -> onePet.getBirthDate().equals(ageLike))
                .collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.findAllByBirthDate(ageLike);

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void sortByAgeAscendingTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        Pet pet1 = entityManager.merge(getPet());
        pets.add(pet);
        pets.add(pet1);

        List<Pet> expectedPets = pets.stream()
                .sorted(Comparator.comparing(Pet::getAge)).collect(Collectors.toList());
        //expectedPets.sort(Comparator.comparing(Pet::getAge).reversed());

        //when
        List<Pet> petsFromRepo = petsRepo.sortByBirthDateAscending();

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void sortByAgeDescendingTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        Pet pet1 = entityManager.merge(getPet());
        pets.add(pet);
        pets.add(pet1);

        List<Pet> expectedPets = pets.stream()
                .sorted(Comparator.comparing(Pet::getAge).reversed()).collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.sortByBirthDateDescending();

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void sortBySizeDescendingTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        Pet pet1 = entityManager.merge(getPet());
        pets.add(pet);
        pets.add(pet1);

        List<Pet> expectedPets = pets.stream()
                .sorted(Comparator.comparing(Pet::getSize).reversed()).collect(Collectors.toList());
        //expectedPets.sort(Comparator.comparing(Pet::getSize).reversed());

        //when
        List<Pet> petsFromRepo = petsRepo.sortBySizeDescending();

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void sortBySizeAscendingTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        Pet pet1 = entityManager.merge(getPet());
        pets.add(pet);
        pets.add(pet1);

        List<Pet> expectedPets = pets.stream()
                .sorted(Comparator.comparing(Pet::getSize)).collect(Collectors.toList());
        //expectedPets.sort(Comparator.comparing(Pet::getSize).reversed());

        //when
        List<Pet> petsFromRepo = petsRepo.sortBySizeAscending();

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void sortByNameAlphabeticallyTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        Pet pet1 = entityManager.merge(getPet());
        pets.add(pet);
        pets.add(pet1);

        List<Pet> expectedPets = pets.stream()
                .sorted(Comparator.comparing(Pet::getName)).collect(Collectors.toList());
        //expectedPets.sort(Comparator.comparing(Pet::getSize).reversed());

        //when
        List<Pet> petsFromRepo = petsRepo.sortByNameAlphabetically();

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

    @Test
    public void findByDescriptionContainingIgnoreCaseTest() {
        //given
        List<Pet> pets = new ArrayList<>();
        Pet pet = entityManager.merge(getPet());
        pets.add(pet);

        String descriptionLike = pets.get(0).getDescription();

        List<Pet> expectedPets = pets.stream()
                .filter(onePet -> onePet.getDescription().contains(descriptionLike))
                .collect(Collectors.toList());

        //when
        List<Pet> petsFromRepo = petsRepo.findByDescriptionContainingIgnoreCase(descriptionLike.toUpperCase());

        //then
        assertEquals(expectedPets, petsFromRepo);
    }

}
