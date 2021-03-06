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
public class SearchController {

    @Autowired
    private PetsService petsService;

    //TODO этот метод же есть 1 в 1 в другом контроллере
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

    public String petListFilter(@PathVariable(value = "pageNum") int pageNum,
                                @Param("field") String field,
                                @Param("sortDir") String sortDir,
                                @Param("keyword") String keyword,
                                Model model) {

        Page<Pet> page = petsService.findAllByKeywordSort(pageNum, keyword, field, sortDir);
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

        if(keyword.equals("Котенок"))
            return "kittensListPage";
        else
            return "puppiesListPage";
    }


    //TODO этот тоже
    @GetMapping("/petslist/search")
    public String searchPage(@Param("keyword") String keyword, Model model) {
        return petListSearch(1, "id", "asc", keyword, model);
    }

    //TODO в целом я не вижу смысла разделять это на 2 контроллера, эти методы можно было оставить в первом контроллере
    @GetMapping("/kittens")
    public String getKittens(Model model) {
        return petListFilter(1, "id", "asc", "Котенок", model);
    }

    @GetMapping("/puppies")
    public String getPuppies(Model model) {
        return petListFilter(1, "id", "asc", "Щенок", model);
    }

}
