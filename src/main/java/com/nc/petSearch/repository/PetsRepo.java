package com.nc.petSearch.repository;

import com.nc.petSearch.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetsRepo extends PagingAndSortingRepository<Pet, Integer> {
    //TODO почему часть методов возвращает просто список, а часть уже классы с пагинацией?
    List<Pet> findAllByName(String name);

    List<Pet> findAllByTypeOfPet(String typeOfPet);

    List<Pet> findAllByGender(String gender);

    List<Pet> findAllBySize(int size);

    @Query(value="SELECT * FROM pet WHERE UPPER(CONCAT(name,' ',type_of_pet,' ',age,' ',gender,' ', description, 'котята')) LIKE %?1%", nativeQuery = true)
    Page<Pet> findAllByKeyword(String keyword, Pageable pageable);

    //TODO все эти методы можно было сделать и без нэтив кверей, так же если они у вас совсем не используются, нужно удалить
    @Query(value = "select * from pet order by size", nativeQuery = true)
    List<Pet> sortBySizeAscending();

    @Query(value = "select * from pet order by size desc", nativeQuery = true)
    List<Pet> sortBySizeDescending();

    List<Pet> findAllByBirthDate(LocalDate birthDate);

    @Query(value = "select * from pet order by birth_date", nativeQuery = true)
    List<Pet> sortByBirthDateAscending();

    @Query(value = "select * from pet order by birth_date desc", nativeQuery = true)
    List<Pet> sortByBirthDateDescending();

    @Query(value = "select*from pet order by name", nativeQuery = true)
    List<Pet> sortByNameAlphabetically();

    List<Pet> findByDescriptionContainingIgnoreCase(String phrase);
}