package com.hireright.service;

import com.hireright.model.File;
import com.hireright.service.CountCapitalCharactersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountCapitalCharactersServiceTest {

    private File file;

    private CountCapitalCharactersService countCapitalCharactersService;

    @BeforeEach
    void setUp() {
        countCapitalCharactersService = new CountCapitalCharactersService();

        String fileContent = "Here Is File Content"; //4 chars are Capital Letters
        file = new File();
        file.setContent(fileContent);
    }

    @Test
    void whenCountCapitalLettersInTextMethodIsUsed_thenItCountsCapitalLettersInText() {
        int expectedNumberOfCapitalLetters = 4;
        int numberOfCapitalLetters = countCapitalCharactersService.countCapitalCharacters(file);

        assertThat(numberOfCapitalLetters).isEqualTo(expectedNumberOfCapitalLetters);
    }
}