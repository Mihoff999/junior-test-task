package com.hireright.service;

import com.hireright.model.File;

import java.util.List;

public class StopWordService {

    // -S words which will not be counted
    public void excludeGivenWordsFromText(File file, List<String> stopWords) {
        for (String stopWord : stopWords) {
            String replaceRegex = "\\s*\\b" + stopWord + "\\b\\s*";
            file.setContent(file.getContent().replaceAll(replaceRegex, " "));
        }
    }
}
