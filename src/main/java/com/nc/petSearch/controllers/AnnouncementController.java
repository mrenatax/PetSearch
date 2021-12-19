package com.nc.petSearch.controllers;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class AnnouncementController {

    @Autowired
    PetsService petsService;

    @GetMapping("/admin/add-announcement")
    public String addAnnouncement(Model model) {
        return "addingPage";
    }

    @GetMapping("/admin/my-announcements")
    public String myAnnouncements(Model model) {
        List<Pet> pets = petsService.findAllForUser();
        model.addAttribute("pets", pets);
        return "myAnnouncementsPage";
    }


    @PostMapping("/admin/add-announcement")
    public String addAnnouncementPost(@RequestParam("animalType") String animalType,
                                      @RequestParam String breed,
                                      @RequestParam("gender") String gender,
                                      @RequestParam String dateOfBirth,
                                      @RequestParam String description,
                                      @RequestParam("photoOfPet") MultipartFile photoOfPet,
                                      Model model) {
        String img = "/img/pets/dog1.jpeg";
        Pet pet = new Pet(breed,animalType,description,gender,img);
        petsService.createPet(pet);
        //TODO pet.setBirthDate(LocalDate.parse(dateOfBirth)).setAge(LocalDate.now());
        return "redirect:/admin/my-announcements";
    }

    @GetMapping("/admin/pet/{id}/edit")
    public String editAnnouncement(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "editingPage";
    }


    @PostMapping("/admin/pet/{id}/edit")
    public String editAnnouncementPost(@PathVariable(value = "id") int id,
                                        @RequestParam("animalType") String animalType,
                                        @RequestParam String breed,
                                        @RequestParam("gender") String gender,
                                        @RequestParam String dateOfBirth,
                                        @RequestParam String description,
                                        @RequestParam("photoOfPet") MultipartFile photoOfPet,
                                         Model model) {

        String img = "/img/pets/dog1.jpeg";
        Pet pet = petsService.findById(id);
        pet.setTypeOfPet(animalType).setName(breed).setGender(gender).setDescription(description);
        petsService.createPet(pet);

        return "redirect:/admin/my-announcements";
    }

    @PostMapping("/admin/pet/{id}/remove")
    public String removeAnnouncementPost(@PathVariable(value = "id") int id,
                                       Model model) {

        Pet pet = petsService.findById(id);
        petsService.removePet(pet);

        return "redirect:/admin/my-announcements";
    }

}
