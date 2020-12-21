package com.hireright.service;

import com.hireright.model.File;
import com.hireright.service.CountCharactersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountCharactersServiceTest {

    private File file;

    private CountCharactersService countCharactersService;

    @BeforeEach
    void setUp() {
        countCharactersService = new CountCharactersService();

        String fileContent = "Here Is File Content"; //17 chars without spaces
        file = new File();
        file.setContent(fileContent);
    }

    @Test
    void whenCountCharInFileMethodIsUsed_thenItCountsCharsInFile() {
        int expectedNumberOfChar = 17;
        int numberOfCharsInFile = countCharactersService.countCharacters(file);

        assertThat(numberOfCharsInFile).isEqualTo(expectedNumberOfChar);
    }
}