package com.nc.petSearch.repository;

import com.nc.petSearch.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetsDBRepo extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByName(String name);
}