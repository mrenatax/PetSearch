package com.nc.petSearch.repository;

import com.nc.petSearch.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface PetsDBRepo extends PagingAndSortingRepository<Pet, Integer> {
    List<Pet> findAllByName(String name);

    List<Pet> findAllByTypeOfPet(String typeOfPet);

    List<Pet> findAllByGender(String gender);

    List<Pet> findAllBySize(int size);

    @Query(value="SELECT * FROM pet WHERE CONCAT(name,' ',type_of_pet,' кошка собака',age,' месяца ',gender,' ', description) LIKE %?1%", nativeQuery = true)
    Page<Pet> findAllByKeyword(String keyword, Pageable pageable);

    @Query(value = "select * from pet order by size", nativeQuery = true)
    List<Pet> sortBySizeAscending();

    @Query(value = "select * from pet order by size desc", nativeQuery = true)
    List<Pet> sortBySizeDescending();

    List<Pet> findAllByAge(int age);

    @Query(value = "select * from pet order by age", nativeQuery = true)
    List<Pet> sortByAgeAscending();

    @Query(value = "select * from pet order by age desc", nativeQuery = true)
    List<Pet> sortByAgeDescending();

    @Query(value = "select*from pet order by name", nativeQuery = true)
    List<Pet> sortByNameAlphabetically();

    List<Pet> findByDescriptionContainingIgnoreCase(String phrase);

}