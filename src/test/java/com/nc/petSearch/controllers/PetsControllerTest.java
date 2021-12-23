package com.nc.petSearch.controllers;


import com.nc.petSearch.entity.Pet;
import com.nc.petSearch.service.PetsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PetListController.class)
@ExtendWith(SpringExtension.class)
public class PetsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PetsService petsService;

    private Pet getPet() {
        return Pet.builder()
                .id(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE))
                .name(RandomStringUtils.randomAlphabetic(10))
                .typeOfPet(RandomStringUtils.randomAlphabetic(10))
                .description(RandomStringUtils.randomAlphabetic(15))
                .gender("female")
                .size(ThreadLocalRandom.current().nextInt(1, 40))
                .age(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE))
                .avatar("/avatar.png")
                .pictureForDescription("bigPicture")
                .minorPictureForDescription("smallPicture")
                .build();
    }

    @Test
    public void petPageTest() throws Exception {
        //given
        Pet pet = getPet();
        given(petsService.findById(pet.getId())).willReturn(pet);

        //when
        ResultActions resultActions = mockMvc.perform(get("/pet/{id}", pet.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(view().name("petPage"));
    }

    @Test
    public void petListPageTest() throws Exception {
        //given
        Pet pet = getPet();
        given(petsService.findById(pet.getId())).willReturn(pet);

        //when
        ResultActions resultActions = mockMvc.perform(get("/pet/{id}", pet.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(view().name("petPage"));
    }
}
