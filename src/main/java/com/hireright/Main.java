package com.hireright;

import com.hireright.controller.ExternalDataAnalyzerController;
import com.hireright.model.File;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ExternalDataAnalyzerController externalDataAnalyzerController = new ExternalDataAnalyzerController();

        File file = new File();
        List<String> arguments = Arrays.asList(args);

        externalDataAnalyzerController.startExternalFileAnalyzing(file, arguments);
    }
}
