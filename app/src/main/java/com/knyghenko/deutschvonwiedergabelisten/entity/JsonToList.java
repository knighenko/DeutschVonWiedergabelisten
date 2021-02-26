package com.knyghenko.deutschvonwiedergabelisten.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonToList {
    public static List<String> getListLessons(String jsonString) {
        List<String> lessonsList = new ArrayList<String>();


        String regEx = "\\{[^{}]*\\}";

        Pattern logEntry = Pattern.compile(regEx);
        Matcher matchPattern = logEntry.matcher(jsonString.replaceAll("\\s+", " "));

        while (matchPattern.find()) {
         lessonsList.add( matchPattern.group().substring(1,matchPattern.group().length()-1));

        }

        return lessonsList;
    }
}
