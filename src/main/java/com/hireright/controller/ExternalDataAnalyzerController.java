package com.hireright.controller;

import com.hireright.model.File;
import com.hireright.service.*;

import java.util.List;

public class ExternalDataAnalyzerController {

    ReadFileContentService readFileContentService = new ReadFileContentService();
    StopWordService stopWordService = new StopWordService();
    CountCharactersService countCharactersService = new CountCharactersService();
    CountCapitalCharactersService countCapitalCharactersService = new CountCapitalCharactersService();
    ArgumentSearchService argumentSearchService = new ArgumentSearchService();
    FileDataAnalyzerController fileDataAnalyzerController = new FileDataAnalyzerController(readFileContentService,
            stopWordService, countCharactersService, countCapitalCharactersService, argumentSearchService);

    public File startExternalFileAnalyzing(File externalFile, List<String> arguments) {
        fileDataAnalyzerController.startFileAnalyzing(externalFile, arguments);
        return externalFile;
    }
}
