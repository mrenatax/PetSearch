package com.nc.petSearch.controllers;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


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
        return petListPageSort(1, null, "id", "asc", model);
    }

    @GetMapping("/petslist/{pageNum}")
    public String petListPageSort(@PathVariable(value = "pageNum") int pageNum,
                                  @Param("keyword") String keyword,
                                  @Param("field") String field,
                                  @Param("sortDir") String sortDir,
                                  Model model) {
        Page<Pet> page = petsService.findAllByKeyword(pageNum, null, field, sortDir);
        int totalPages;
        if (page.getTotalPages() != 0)
            totalPages = page.getTotalPages();
        else
            totalPages = 1;

        List<Pet> pets = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pets", pets);
        model.addAttribute("field", field);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);

        return "petListPage";
    }

    @GetMapping("/pet/{id}")
    public String petPage(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "petPage";
    }

}
