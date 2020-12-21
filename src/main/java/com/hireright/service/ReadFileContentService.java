package com.hireright.service;

import com.hireright.model.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFileContentService {

    // -F file name method
    public void fillFileWithReadContent(File file, Path pathToFile) {
        Path fileName = pathToFile.getFileName();
        try {
            file.setContent(readAllLinesFromFile(pathToFile)
                    .stream()
                    .map(String::toString)
                    .collect(Collectors.joining()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printFileNameAndContent(file, fileName);
    }

    void printFileNameAndContent(File file, Path fileName){
        System.out.println("\nFile Name: " + fileName);
        System.out.println("----------------------");
        System.out.println("File Content:");
        System.out.println(file.getContent());
    }

    List<String> readAllLinesFromFile(Path pathToFile) throws IOException {
            return Files.readAllLines(pathToFile);
    }
}
