package com.hireright.service;

import com.hireright.model.File;

public class CountCapitalCharactersService {

    // -L count words which start with a Capital letter
    public File countCapitalCharacters(File file) {
        int countCapitalLetters = 0;
        for (int i = 0; i < file.getContent().length(); i++) {
            if (Character.isUpperCase(file.getContent().charAt(i)))
                countCapitalLetters++;
        }
        System.out.println("\nNumber of capital letters in the file " + countCapitalLetters);
        file.setNumberOfCapitalLettersInFile(countCapitalLetters);
        return file;
    }
}
