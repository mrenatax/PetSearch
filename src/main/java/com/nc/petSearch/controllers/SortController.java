package com.nc.petSearch.controllers;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SortController {

    @Autowired
    private PetsService petsService;

    @GetMapping("/breed-sort")
    public String breedSort(Model model) {
        List<Pet> pets = petsService.sortByNameAlphabetically();
        model.addAttribute("pets", pets);
        return "petListPage";
    }

    @GetMapping("/age-sort-asc")
    public String ageSortAsc(Model model) {
        List<Pet> pets = petsService.sortByAgeAscending();
        model.addAttribute("pets", pets);
        return "petListPage";
    }

    @GetMapping("/age-sort-desc")
    public String ageSortDesc(Model model) {
        List<Pet> pets = petsService.sortByAgeDescending();
        model.addAttribute("pets", pets);
        return "petListPage";
    }

    @GetMapping("/size-sort-asc")
    public String sizeSortAsc(Model model) {
        List<Pet> pets = petsService.sortBySizeAscending();
        model.addAttribute("pets", pets);
        return "petListPage";
    }

    @GetMapping("/size-sort-desc")
    public String sizeSortDesc(Model model) {
        List<Pet> pets = petsService.sortBySizeDescending();
        model.addAttribute("pets", pets);
        return "petListPage";
    }

}
