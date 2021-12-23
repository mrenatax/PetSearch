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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PetsService petsService;

    @GetMapping("/petslist/search/{pageNum}")
    public String petListSearch(@PathVariable(value = "pageNum") int pageNum,
                                @Param("field") String field,
                                @Param("sortDir") String sortDir,
                                @Param("keyword") String keyword,
                                Model model) {

        Page<Pet> page = petsService.findAllByKeyword(pageNum, keyword, field, sortDir);
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

    @GetMapping("/petslist/search")
    public String searchPage(@Param("keyword") String keyword, Model model) {
        return petListSearch(1, "id", "asc", keyword, model);
    }

    @GetMapping("/kittens")
    public String getKittens(Model model) {
        return petListSearch(1, "id", "asc", "Котенок", model);
    }

    @GetMapping("/puppies")
    public String getPuppies(Model model) {
        return petListSearch(1, "id", "asc", "Щенок", model);
    }

}
