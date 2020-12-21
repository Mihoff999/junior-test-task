package com.hireright.controller;

import com.hireright.model.File;
import com.hireright.service.*;

import java.nio.file.Paths;
import java.util.List;

public class FileDataAnalyzerController {

    public static final String NO_FILE_COMMAND_GIVEN_MESSAGE = "\nPlease use command -F to see filename " +
                                                                "and ./filename.txt for file content";

    private ReadFileContentService readFileContentService;
    private StopWordService stopWordService;
    private CountCharactersService countCharactersService;
    private CountCapitalCharactersService countCapitalCharactersService;
    private ArgumentSearchService argumentSearchService;

    public FileDataAnalyzerController(ReadFileContentService readFileContentService, StopWordService stopWordService,
                                      CountCharactersService countCharactersService,
                                      CountCapitalCharactersService countCapitalCharactersService, ArgumentSearchService argumentSearchService) {
        this.readFileContentService = readFileContentService;
        this.stopWordService = stopWordService;
        this.countCharactersService = countCharactersService;
        this.countCapitalCharactersService = countCapitalCharactersService;
        this.argumentSearchService = argumentSearchService;
    }

    public void startFileAnalyzing(List<String> arguments) {
        if (argumentSearchService.hasFileNameCommand(arguments)) {
            for (String fileName : argumentSearchService.getAllFileNames(arguments)) {
                File file = new File();
                readFileContentService.fillFileWithReadContent(file, Paths.get(fileName));
                removeStopWordsIfCommandIsGiven(arguments, file);
                countAllCharactersIfCommandIsGiven(arguments, file);
                countAllCapitalCharactersIfCommandIsGiven(arguments, file);
            }
        } else {
            printMessage();
        }

    }

    private void removeStopWordsIfCommandIsGiven(List<String> argumentsAsList, File file) {
        if (argumentSearchService.hasStopWordsCommand(argumentsAsList)) {
            List<String> stopWords = argumentSearchService.getStopWords(argumentsAsList);
            stopWordService.excludeGivenWordsFromText(file, stopWords);
        }
    }

    private void countAllCharactersIfCommandIsGiven(List<String> argumentsAsList, File file) {
        if (argumentSearchService.hasCountCharacterCommand(argumentsAsList)) {
            countCharactersService.countCharacters(file);
        }
    }

    private void countAllCapitalCharactersIfCommandIsGiven(List<String> argumentsAsList, File file) {
        if (argumentSearchService.hasCountCapitalCharacterCommand(argumentsAsList)) {
            countCapitalCharactersService.countCapitalCharacters(file);
        }
    }

    void printMessage() {
        System.out.println(NO_FILE_COMMAND_GIVEN_MESSAGE);
    }


}
