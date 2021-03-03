package com.knyghenko.deutschvonwiedergabelisten.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonToLessonsList {
    public static List<Lesson> getListLessons(String jsonString) {
        List<Lesson> lessonsList = new ArrayList<Lesson>();

        String regEx = "\\{[^{}]*\\}";
        Pattern logEntry = Pattern.compile(regEx);
        Matcher matchPattern = logEntry.matcher(jsonString.replaceAll("\\s+"," "));

        while (matchPattern.find()) {
            ObjectMapper mapper = new ObjectMapper();
            Lesson lesson = null;
            try {
                lesson = mapper.readValue(matchPattern.group(), Lesson.class);
                lessonsList.add(lesson);
            } catch (IOException e) {
                e.printStackTrace();
            }
              System.out.println(lesson);
        }
        return lessonsList;
    }
}
