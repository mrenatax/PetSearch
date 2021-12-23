package com.nc.petSearch.service;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.repository.PetsRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetsServiceTest {
    @Mock
    private PetsRepo petsRepo;

    private PetsService petsService;

    private static Pet getPet() {
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

    public List<Pet> getPetList(int size) {
        return Stream.generate(PetsServiceTest::getPet)
                .limit(size)
                .collect(Collectors.toList());
    }

    @BeforeAll
    public void init() {
        petsService = new PetsService(petsRepo);
    }

    @Test
    public void findByIdTest() {
        //given
        Pet expectedPet = getPet();
        int id = expectedPet.getId();
        given(petsRepo.findById(id)).willReturn(Optional.of(expectedPet));

        //when
        Pet actualPet = petsService.findById(id);

        //then
        assertEquals(expectedPet, actualPet);
        verify(petsRepo).findById(id);
    }

    @Test
    public void notFindByIdTest() {
        //given
        int id = ThreadLocalRandom.current().nextInt();
        given(petsRepo.findById(id)).willReturn(Optional.empty());

        //when
        Pet actualPet = petsService.findById(id);

        //then
        assertNull(actualPet);
        verify(petsRepo).findById(id);
    }

    @Test
    public void deleteTest() {
        //given
        Pet pet = getPet();

        //when
        petsService.removePet(pet);

        //then
        verify(petsRepo).delete(pet);
    }

    @Test
    public void createTest() {
        //given
        Pet pet = getPet();

        //when
        petsService.createPet(pet);

        //then
        verify(petsRepo).save(pet);
    }

    @Test
    public void findAllByNameTest() {
        //given
        Pet expectedPet = getPet();
        List<Pet> expectedPetList = new ArrayList<>();
        expectedPetList.add(expectedPet);
        given(petsRepo.findAllByName(expectedPet.getName())).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.findAllByName(expectedPet.getName());

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).findAllByName(expectedPet.getName());
    }

    @Test
    public void findAllByTypeOfPetTest() {
        //given
        Pet expectedPet = getPet();
        List<Pet> expectedPetList = new ArrayList<>();
        expectedPetList.add(expectedPet);
        given(petsRepo.findAllByTypeOfPet(expectedPet.getTypeOfPet())).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.findAllByTypeOfPet(expectedPet.getTypeOfPet());

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).findAllByTypeOfPet(expectedPet.getTypeOfPet());
    }

    @Test
    public void findAllByGenderTest() {
        //given
        Pet expectedPet = getPet();
        List<Pet> expectedPetList = new ArrayList<>();
        expectedPetList.add(expectedPet);
        given(petsRepo.findAllByGender(expectedPet.getGender())).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.findAllByGender(expectedPet.getGender());

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).findAllByGender(expectedPet.getGender());
    }

    @Test
    public void findAllBySizeTest() {
        //given
        Pet expectedPet = getPet();
        List<Pet> expectedPetList = new ArrayList<>();
        expectedPetList.add(expectedPet);
        given(petsRepo.findAllBySize(expectedPet.getSize())).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.findAllBySize(expectedPet.getSize());

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).findAllBySize(expectedPet.getSize());
    }

    @Test
    public void findAllByAgeTest() {
        //given
        Pet expectedPet = getPet();
        expectedPet.setDescription(expectedPet.getDescription().toUpperCase());
        List<Pet> expectedPetList = new ArrayList<>();
        expectedPetList.add(expectedPet);
        given(petsRepo.findAllByBirthDate(expectedPet.getBirthDate())).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.findAllByBirthDate(expectedPet.getBirthDate());

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).findAllByBirthDate(expectedPet.getBirthDate());
    }

    @Test
    public void findByDescriptionContainingIgnoreCaseTest() {
        //given
        Pet expectedPet = getPet();
        List<Pet> expectedPetList = new ArrayList<>();
        expectedPetList.add(expectedPet);
        String phrase = expectedPet.getDescription().toLowerCase(Locale.ROOT);
        given(petsRepo.findByDescriptionContainingIgnoreCase(phrase)).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.findByDescriptionContainingIgnoreCase(phrase);

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).findByDescriptionContainingIgnoreCase(phrase);
    }

    @Test
    public void findAllTest() {
        //given
        int pageNum = ThreadLocalRandom.current().nextInt(1, 5);
        String field = RandomStringUtils.randomAlphabetic(5);
        String sortDir = "asc";

        //when
        petsService.findAll(pageNum, field, sortDir);

        //then
        verify(petsRepo).findAll(any(Pageable.class));
    }


    @Test
    public void findAllByKeywordNotNullTest() {
        //given
        String keyword = RandomStringUtils.randomAlphabetic(10);
        int pageNum = ThreadLocalRandom.current().nextInt(1, 5);
        String field = RandomStringUtils.randomAlphabetic(5);
        String sortDir = "asc";

        //when
        petsService.findAllByKeyword(pageNum, keyword, field, sortDir);

        //then
        verify(petsRepo).findAllByKeyword(anyString(), any(Pageable.class));
    }

    @Test
    public void sortByAgeAscendingTest() {
        //given
        List<Pet> expectedPetList = getPetList(10);
        given(petsRepo.sortByBirthDateAscending()).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.sortByBirthDateAscending();

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).sortByBirthDateAscending();
    }

    @Test
    public void sortByAgeDescendingTest() {
        //given
        List<Pet> expectedPetList = getPetList(10);
        given(petsRepo.sortByBirthDateDescending()).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.sortByBirthDateDescending();

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).sortByBirthDateDescending();
    }

    @Test
    public void sortBySizeAscendingTest() {
        //given
        List<Pet> expectedPetList = getPetList(10);
        given(petsRepo.sortBySizeAscending()).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.sortBySizeAscending();

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).sortBySizeAscending();
    }

    @Test
    public void sortBySizeDescendingTest() {
        //given
        List<Pet> expectedPetList = getPetList(10);
        given(petsRepo.sortBySizeDescending()).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.sortBySizeDescending();

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).sortBySizeDescending();
    }

    @Test
    public void sortByNameAlphabetically() {
        //given
        List<Pet> expectedPetList = getPetList(10);
        given(petsRepo.sortByNameAlphabetically()).willReturn(expectedPetList);

        //when
        List<Pet> actualPetList = petsService.sortByNameAlphabetically();

        //then
        assertEquals(expectedPetList, actualPetList);
        verify(petsRepo).sortByNameAlphabetically();
    }

}