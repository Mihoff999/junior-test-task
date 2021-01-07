package com.hireright.model;

public class File {

    private String Content;

    private int numberOfCapitalLettersInFile;

    private int numberOfCharactersInFile;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getNumberOfCapitalLettersInFile() {
        return numberOfCapitalLettersInFile;
    }

    public void setNumberOfCapitalLettersInFile(int numberOfCapitalLettersInFile) {
        this.numberOfCapitalLettersInFile = numberOfCapitalLettersInFile;
    }

    public int getNumberOfCharactersInFile() {
        return numberOfCharactersInFile;
    }

    public void setNumberOfCharactersInFile(int numberOfCharactersInFile) {
        this.numberOfCharactersInFile = numberOfCharactersInFile;
    }
}
