package com.lgcns.Docking.letter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class LetterControllerTest {

    private final MockMvc mvc;

    public LetterControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

}