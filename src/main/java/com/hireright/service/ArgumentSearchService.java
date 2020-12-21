package com.hireright.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArgumentSearchService {

    public static final String FILE_NAMES_COMMAND = "-F";
    public static final String STOP_WORDS_COMMAND = "-S";
    public static final String COUNT_CHARS_COMMAND = "-C";
    public static final String COUNT_CAPITAL_CHARS_COMMAND = "-L";

    public List<String> getAllFileNames(List<String> argumentsAsList) {
        return argumentsAsList.stream()
                .filter(s -> s.contains(".txt"))
                .collect(Collectors.toList());
    }

    public List<String> getStopWords(List<String> argumentsAsList) {
        for (String argument : argumentsAsList) {
            if (argument.contains("-S=")) {
                String stopWords = argument.replace("-S=", "");
                return Arrays.stream(stopWords.split(",")).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

    public boolean hasFileNameCommand(List<String> argumentsAsList) {
        return hasCommand(argumentsAsList, FILE_NAMES_COMMAND);
    }

    public boolean hasStopWordsCommand(List<String> argumentsAsList) {
        return hasCommand(argumentsAsList, STOP_WORDS_COMMAND);
    }

    public boolean hasCountCharacterCommand(List<String> argumentsAsList) {
        return hasCommand(argumentsAsList, COUNT_CHARS_COMMAND);
    }

    public boolean hasCountCapitalCharacterCommand(List<String> argumentsAsList) {
        return hasCommand(argumentsAsList, COUNT_CAPITAL_CHARS_COMMAND);
    }

    private boolean hasCommand(List<String> argumentsAsList, String commandName) {
        return argumentsAsList.stream().anyMatch(s -> s.contains(commandName));
    }

}
