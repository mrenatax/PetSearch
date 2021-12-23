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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdController {

    @Autowired
    PetsService petsService;

    @GetMapping("/admin/add-ad")
    public String addAd(Model model) {
        return "addingPage";
    }

    @GetMapping("/admin/my-ads")
    public String myAds(Model model) {
        List<Pet> pets = petsService.findAllForUser();
        model.addAttribute("pets", pets);
        return "myAdsPage";
    }


    @PostMapping("/admin/add-ad")
    public String addAdPost(@RequestParam("animalType") String animalType,
                                      @RequestParam String breed,
                                      @RequestParam("gender") String gender,
                                      @RequestParam String dateOfBirth,
                                      @RequestParam String description,
                                      @RequestParam("avatar") MultipartFile avatar,
                                      @RequestParam("descPic") MultipartFile descPic,
                                      @RequestParam("minorDescPic") MultipartFile minorDescPic,
                                      Model model) {

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

        try {
            ImageUploadUtil.savePicture(pet.getPhotoPath(), avatarName, avatar);
            ImageUploadUtil.savePicture(pet.getPhotoPath(), descPicName, descPic);
            ImageUploadUtil.savePicture(pet.getPhotoPath(), minorDescPicName, minorDescPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/admin/my-ads";
    }

    @GetMapping("/admin/pet/{id}/edit")
    public String editAd(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "editingPage";
    }


    @PostMapping("/admin/pet/{id}/edit")
    public String editAdPost(@PathVariable(value = "id") int id,
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

        petsService.createPet(pet);

        return "redirect:/admin/my-ads";
    }

    @PostMapping("/admin/pet/{id}/remove")
    public String removeAdPost(@PathVariable(value = "id") int id,
                                         Model model) {

        Pet pet = petsService.findById(id);
        try {
            FileUtils.deleteDirectory(new File(pet.getPhotoPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        petsService.removePet(pet);

        return "redirect:/admin/my-ads";
    }

    @GetMapping("/admin/pet/{id}")
    public String adminPetPage(@PathVariable(value = "id") int id, Model model) {
        Pet pet = petsService.findById(id);
        model.addAttribute("pet", pet);
        return "adminPetPage";
    }

}
