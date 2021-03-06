package com.knyghenko.deutschvonwiedergabelisten.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.adapter.LessonAdapter;
import com.knyghenko.deutschvonwiedergabelisten.entity.Lesson;

import java.util.List;

public class TasksList extends AppCompatActivity {
    private RecyclerView recyclerViewTasks;
   // private List<Lesson> lessonsList;
  //  private LessonAdapter lessonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        Toolbar toolbar = findViewById(R.id.toolbar_task_list);
        toolbar.setTitle( getIntent().getStringExtra("lessonTitle")+" Задания.");
        setSupportActionBar(toolbar);

    }
}