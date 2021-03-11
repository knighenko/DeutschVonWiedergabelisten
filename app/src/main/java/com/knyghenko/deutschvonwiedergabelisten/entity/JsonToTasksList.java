package com.knyghenko.deutschvonwiedergabelisten.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonToTasksList {
    public static List<Task> getListLessons(String jsonString) {
        List<Task> taskList = new ArrayList<Task>();

        String regEx = "\\{[^{}]*\\}";
        Pattern logEntry = Pattern.compile(regEx);
        Matcher matchPattern = logEntry.matcher(jsonString.replaceAll("\\s+"," "));

        while (matchPattern.find()) {
            ObjectMapper mapper = new ObjectMapper();
            Task task = null;
            try {
                task = mapper.readValue(matchPattern.group(), Task.class);
                taskList.add(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(task);
        }
        return taskList;
    }
}
