package com.nc.petSearch.controllers;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PetsService petsService;

    @GetMapping("/petslist/search")
    public String petListSearch(@Param("keyword") String keyword, Model model) {
        List<Pet> pets = petsService.findAllByKeyword(keyword);
        model.addAttribute("pets", pets);
        model.addAttribute("keyword", keyword);
        return "petListPage";
    }


}
