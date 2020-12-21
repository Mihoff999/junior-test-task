package com.hireright.service;

import com.hireright.model.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReadFileContentServiceTest {

    @Mock
    private Path pathToFileMock;

    private File file;

    private ReadFileContentService readFileContentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        file = new File();

        readFileContentService = new ReadFileContentService(){
            @Override
            List<String> readAllLinesFromFile(Path pathToFile) throws IOException {
                ArrayList<String> contentOfFileAsList = new ArrayList<>();
                contentOfFileAsList.add("firstLine ");
                contentOfFileAsList.add("secondLine");
                return contentOfFileAsList;
            }
        };

        when(pathToFileMock.getFileName()).thenReturn(pathToFileMock);
    }


    @Test
    void whenFillingFileWithContent_thenItFillsWithAllLines() {
        String expectedFileContent = "firstLine secondLine";

        readFileContentService.fillFileWithReadContent(file, pathToFileMock);

        assertThat(file.getContent()).isEqualTo(expectedFileContent);

    }

}