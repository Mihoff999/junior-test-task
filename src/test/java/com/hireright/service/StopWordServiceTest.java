package com.hireright.service;

import com.hireright.model.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StopWordServiceTest {

    private File file;

    private List<String> stopWords;

    private StopWordService stopWordService;

    @BeforeEach
    void setUp() {
        stopWords = new ArrayList<>();
        stopWordService = new StopWordService();

        String fileContent = "the the. New Yark Apple apple a. a, a: a theme";
        file = new File();
        file.setContent(fileContent);
    }

    @Test
    void whenStopWordsAreEmpty_thenFileContentStaysTheSame() {
        String expectedFileContent = "the the. New Yark Apple apple a. a, a: a theme";
        stopWordService.excludeGivenWordsFromText(file, stopWords);

        assertThat(file.getContent()).isEqualTo(expectedFileContent);
    }

    @Test
    void givenStopWordA_whenStopWordIsThere_thenFileContentWillBeChanged() {
        stopWords.add("a");
        String expectedFileContent = "the the. New Yark Apple apple . , : theme";

        stopWordService.excludeGivenWordsFromText(file, stopWords);

        assertThat(file.getContent()).isEqualTo(expectedFileContent);
    }

    @Test
    void givenStopWordAAndThe_whenStopWordsAreThere_thenFileContentWillBeChanged() {
        stopWords.add("a");
        stopWords.add("the");
        String expectedFileContent = "  . New Yark Apple apple . , : theme";

        stopWordService.excludeGivenWordsFromText(file, stopWords);

        assertThat(file.getContent()).isEqualTo(expectedFileContent);
    }
}