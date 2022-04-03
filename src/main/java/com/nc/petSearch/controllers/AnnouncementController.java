package com.nc.petSearch.controllers;

import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import com.nc.petSearch.util.ImageUploadUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AnnouncementController {

    @Autowired
    PetsService petsService;

    @GetMapping("/add-announcement")
    public String addAnnouncement(Model model) {
        return "addingPage";
    }

    @GetMapping("/admin/my-announcements")
    public String myAnnouncements(Model model) {
        List<Pet> pets = petsService.findAllForUser();
        model.addAttribute("pets", pets);
        return "myAnnouncementsPage";
    }


    //TODO нельзя было использовать @RequestBody?
    @PostMapping("/add-announcement")
    public String addAnnouncementPost(@RequestParam("animalType") String animalType,
                                      @RequestParam String breed,
                                      @RequestParam("gender") String gender,
                                      @RequestParam String dateOfBirth,
                                      @RequestParam String description,
                                      @RequestParam("avatar") MultipartFile avatar,
                                      @RequestParam("descPic") MultipartFile descPic,
                                      @RequestParam("minorDescPic") MultipartFile minorDescPic,
                                      Model model) {
        //TODO вся бизнес логика должна быть на уровне сервиса
        Pet pet = new Pet(breed, animalType, description, gender, dateOfBirth);
        pet.setAge(LocalDate.now());
        petsService.createPet(pet);

        String avatarName = StringUtils.cleanPath(avatar.getOriginalFilename());
        String descPicName = StringUtils.cleanPath(descPic.getOriginalFilename());
        String minorDescPicName = StringUtils.cleanPath(minorDescPic.getOriginalFilename());

        pet.setAvatar(avatarName);
        pet.setPictureForDescription(descPicName);
        pet.setMinorPictureForDescription(minorDescPicName);

        petsService.createPet(pet);
        //TODO два запроса на создание? в чем смысл сохранить сущность с частью параметров,
        // а потом сохранить вторую часть вторым запросом?)

        try {
            ImageUploadUtil.savePicture(pet.getPhotoPath(), avatarName, avatar);
            ImageUploadUtil.savePicture(pet.getPhotoPath(), descPicName, descPic);
            ImageUploadUtil.savePicture(pet.getPhotoPath(), minorDescPicName, minorDescPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/petslist";
    }

    @GetMapping("/admin/pet/{id}/edit")
    public String editAnnouncement(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "editingPage";
    }


    //TODO нельзя было использовать @RequestBody?
    @PostMapping("/admin/pet/{id}/edit")
    public String editAnnouncementPost(@PathVariable(value = "id") int id,
                                       @RequestParam("animalType") String animalType,
                                       @RequestParam String breed,
                                       @RequestParam("gender") String gender,
                                       @RequestParam String dateOfBirth,
                                       @RequestParam String description,
                                       @RequestParam("avatar") MultipartFile avatar,
                                       @RequestParam("descPic") MultipartFile descPic,
                                       @RequestParam("minorDescPic") MultipartFile minorDescPic,
                                       Model model) {

        Pet pet = petsService.findById(id);
        pet.setTypeOfPet(animalType).setName(breed).setGender(gender).setDescription(description).setBirthDate(LocalDate.parse(dateOfBirth)).setAge(LocalDate.now());

        //TODO вся бизнес логика должна быть на уровне сервиса

        if (!avatar.isEmpty()) {
            String avatarName = StringUtils.cleanPath(avatar.getOriginalFilename());
            pet.setAvatar(avatarName);
            try {
                ImageUploadUtil.savePicture(pet.getPhotoPath(), avatarName, avatar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!descPic.isEmpty()) {
            String descPicName = StringUtils.cleanPath(descPic.getOriginalFilename());
            pet.setPictureForDescription(descPicName);
            try {
                ImageUploadUtil.savePicture(pet.getPhotoPath(), descPicName, descPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!minorDescPic.isEmpty()) {
            String minorDescPicName = StringUtils.cleanPath(minorDescPic.getOriginalFilename());
            pet.setMinorPictureForDescription(minorDescPicName);
            try {
                ImageUploadUtil.savePicture(pet.getPhotoPath(), minorDescPicName, minorDescPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //TODO запрос на редактирование, тут колл на создание
        petsService.createPet(pet);

        return "redirect:/admin/my-announcements";
    }

    @PostMapping("/admin/pet/{id}/remove")
    public String removeAnnouncementPost(@PathVariable(value = "id") int id,
                                         Model model) {

        //TODO вся эта логика должна лежать в слое сервисов
        //контроллер должен отвечать только за отдачу результата
        Pet pet = petsService.findById(id);
        try {
            FileUtils.deleteDirectory(new File(pet.getPhotoPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO а здесь будет IllegalArgumentException, т.к. у вас petsService.findById(id) может вернуть null
        petsService.removePet(pet);

        return "redirect:/admin/my-announcements";
    }

    @GetMapping("/admin/pet/{id}")
    public String adminPetPage(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "adminPetPage";
    }

}
