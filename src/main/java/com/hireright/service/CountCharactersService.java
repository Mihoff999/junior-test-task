package com.hireright.service;

import com.hireright.model.File;

public class CountCharactersService {

    // -C count method
    public File countCharacters(File file) {
        int countChar = 0;
        for (int i = 0; i < file.getContent().length(); i++) {
            if (file.getContent().charAt(i) != ' ')
                countChar++;
        }
        System.out.println("\nNumber of characters in the file " + countChar);
        file.setNumberOfCharactersInFile(countChar);
        return file;
    }
}
