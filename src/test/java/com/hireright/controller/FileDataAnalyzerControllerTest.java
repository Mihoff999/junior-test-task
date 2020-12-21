package com.hireright.controller;

import com.hireright.model.File;
import com.hireright.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

class FileDataAnalyzerControllerTest {

    @Mock
    private PrintStream printStreamMock;

    @Mock
    private ReadFileContentService readFileContentServiceMock;

    @Mock
    private StopWordService stopWordServiceMock;

    @Mock
    private CountCharactersService countCharactersServiceMock;

    @Mock
    private CountCapitalCharactersService countCapitalCharactersServiceMock;

    @Mock
    private ArgumentSearchService argumentSearchServiceMock;

    private List<String> argumentsAsList;

    private List<String> foundFileNames;

    private List<String> foundStopWords;

    private FileDataAnalyzerController fileDataAnalyzerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        fileDataAnalyzerController = new FileDataAnalyzerController(readFileContentServiceMock, stopWordServiceMock,
                countCharactersServiceMock, countCapitalCharactersServiceMock, argumentSearchServiceMock) {
            @Override
            void printMessage() {
                printStreamMock.println();
            }
        };

        argumentsAsList = new ArrayList<>();
        foundFileNames = new ArrayList<>();
        foundFileNames.add("test.txt");
        foundStopWords = new ArrayList<>();

        when(argumentSearchServiceMock.hasFileNameCommand(argumentsAsList)).thenReturn(true);
        when(argumentSearchServiceMock.getAllFileNames(argumentsAsList)).thenReturn(foundFileNames);
        when(argumentSearchServiceMock.hasStopWordsCommand(argumentsAsList)).thenReturn(true);
        when(argumentSearchServiceMock.getStopWords(argumentsAsList)).thenReturn(foundStopWords);
    }

    @Test
    void whenFileNameCommandIsNotThere_thenErrorMessageWillBeWritten() {
        when(argumentSearchServiceMock.hasFileNameCommand(argumentsAsList)).thenReturn(false);

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verify(printStreamMock).println();
    }

    @Test
    void whenFileNameCommandIsThere_thenErrorMessageWillNotBeCalled() {
        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verifyZeroInteractions(printStreamMock);
    }

    @Test
    void givenZeroFileNames_whenFileNameCommandIsThere_thenNoFileWillBeRead() {
        foundFileNames.remove(0);
        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verifyZeroInteractions(readFileContentServiceMock);
    }

    @Test
    void givenOneFileName_whenFileNameCommandIsThere_thenFileWillBeRead() {

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verify(readFileContentServiceMock).fillFileWithReadContent(any(File.class), eq(Paths.get("test.txt")));
    }

    @Test
    void givenTwoFileNames_whenFileNameCommandIsThere_thenFileWillBeReadTwoTimes() {
        foundFileNames.add("second.txt");

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verify(readFileContentServiceMock).fillFileWithReadContent(any(File.class), eq(Paths.get("test.txt")));
        verify(readFileContentServiceMock).fillFileWithReadContent(any(File.class), eq(Paths.get("second.txt")));
    }

    @Test
    void whenStopWordsCommandIsNotThere_thenStopWordsServiceWillNotBeCalled() {
        when(argumentSearchServiceMock.hasStopWordsCommand(argumentsAsList)).thenReturn(false);

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verifyZeroInteractions(stopWordServiceMock);
    }

    @Test
    void whenStopWordsCommandIsThere_thenStopWordsServiceWillBeCalled() {
        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verify(stopWordServiceMock).excludeGivenWordsFromText(any(File.class), eq(foundStopWords));
    }

    @Test
    void whenCountCharactersCommandIsThere_thenCountCharactersServiceWillBeCalled() {
        when(argumentSearchServiceMock.hasCountCharacterCommand(argumentsAsList)).thenReturn(true);

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verify(countCharactersServiceMock).countCharacters(any(File.class));
    }

    @Test
    void whenCountCapitalCharactersCommandIsThere_thenCountCapitalCharactersServiceWillBeCalled() {
        when(argumentSearchServiceMock.hasCountCapitalCharacterCommand(argumentsAsList)).thenReturn(true);

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verify(countCapitalCharactersServiceMock).countCapitalCharacters(any(File.class));
    }

    @Test
    void whenCountCharactersCommandIsNotThere_thenCountCharactersServiceWillNotBeCalled() {
        when(argumentSearchServiceMock.hasCountCharacterCommand(argumentsAsList)).thenReturn(false);

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verifyZeroInteractions(countCharactersServiceMock);
    }

    @Test
    void whenCountCapitalCharactersCommandIsNotThere_thenCountCapitalCharactersServiceWillNotBeCalled() {
        when(argumentSearchServiceMock.hasCountCapitalCharacterCommand(argumentsAsList)).thenReturn(false);

        fileDataAnalyzerController.startFileAnalyzing(argumentsAsList);

        verifyZeroInteractions(countCapitalCharactersServiceMock);
    }

}