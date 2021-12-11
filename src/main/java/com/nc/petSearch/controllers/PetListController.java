package com.nc.petSearch.controllers;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Controller
public class PetListController {

    @Autowired
    private PetsService petsService;

    @GetMapping("/")
    public String home(Model model) {
        return "notReadyPage";
    }

    @GetMapping("/petslist")
    public String petListPage(Model model) {
        List<Pet> pets = petsService.findAll();
        model.addAttribute("pets", pets);
        return "petListPage";
    }

    @GetMapping("/breeders")
    public String breedersPage(Model model) {
        return "notReadyPage";
    }

    @GetMapping("/nurseries")
    public String nurseriesPage(Model model) {
        return "notReadyPage";
    }

    @GetMapping("pet/{id}")
    public String petPage(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "petPage";
    }





}
