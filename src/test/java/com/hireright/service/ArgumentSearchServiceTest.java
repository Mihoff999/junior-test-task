package com.hireright.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ArgumentSearchServiceTest {

    private ArgumentSearchService argumentSearchService;

    private List<String> argumentsAsList;

    private List<String> expectedStopWordsAsList;

    private List<String> expectedFilesAsList;

    @BeforeEach
    void setUp() {
        argumentSearchService = new ArgumentSearchService();
        argumentsAsList = new ArrayList<>();
        expectedStopWordsAsList = new ArrayList<>();
        expectedFilesAsList = new ArrayList<>();

    }

    @Test
    void givenFileNameCommand_whenFileNameCommandIsThere_thenHasCommandMethodReturnsTrue() {
        argumentsAsList.add("-F");

        assertThat(argumentSearchService.hasFileNameCommand(argumentsAsList)).isTrue();
    }

    @Test
    void givenStopWordsCommand_whenStopWordsCommandIsThere_thenHasCommandMethodReturnsTrue() {
        argumentsAsList.add("-S");

        assertThat(argumentSearchService.hasStopWordsCommand(argumentsAsList)).isTrue();
    }

    @Test
    void givenCountCharacterCommand_whenCountCharacterCommandIsThere_thenHasCommandMethodReturnsTrue() {
        argumentsAsList.add("-C");

        assertThat(argumentSearchService.hasCountCharacterCommand(argumentsAsList)).isTrue();
    }
    @Test
    void givenCountCapitalCharacterCommand_whenCountCapitalCharacterCommandIsThere_thenHasCommandMethodReturnsTrue() {
        argumentsAsList.add("-L");

        assertThat(argumentSearchService.hasCountCapitalCharacterCommand(argumentsAsList)).isTrue();
    }

    @Test
    void whenZeroCommandCommandIsThere_thenReturnsEmptyArrayList() {

        assertThat(argumentSearchService.getStopWords(argumentsAsList)).isEqualTo(argumentsAsList);
    }

    @Test
    void givenStopWordsAndThe_whenGetStopWordsMethodIsUsed_thenOnlyStopWordsReturn() {
        argumentsAsList.add("-S=a,the");
        expectedStopWordsAsList.add("a");
        expectedStopWordsAsList.add("the");

        List<String> foundStopWordArguments = argumentSearchService.getStopWords(argumentsAsList);
        assertThat(foundStopWordArguments).isEqualTo(expectedStopWordsAsList);
    }

    @Test
    void givenFileCommandAndFileNameCommand_whenGetAllFileNamesMethodIsUsed_thenOnlyFileNamesReturn() {
        argumentsAsList.add("./file1.txt");
        argumentsAsList.add("-F");
        expectedFilesAsList.add("./file1.txt");

        List<String> foundFilesFromArguments = argumentSearchService.getAllFileNames(argumentsAsList);
        assertThat(foundFilesFromArguments).isEqualTo(expectedFilesAsList);
    }

}