package com.hireright;

import com.hireright.controller.FileDataAnalyzerController;
import com.hireright.service.*;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        ReadFileContentService readFileContentService = new ReadFileContentService();
        StopWordService stopWordService = new StopWordService();
        CountCharactersService countCharactersService = new CountCharactersService();
        CountCapitalCharactersService countCapitalCharactersService = new CountCapitalCharactersService();
        ArgumentSearchService argumentSearchService = new ArgumentSearchService();
        FileDataAnalyzerController fileDataAnalyzerController = new FileDataAnalyzerController(readFileContentService,
                stopWordService, countCharactersService, countCapitalCharactersService, argumentSearchService);

        fileDataAnalyzerController.startFileAnalyzing(Arrays.asList(args));
    }
}
