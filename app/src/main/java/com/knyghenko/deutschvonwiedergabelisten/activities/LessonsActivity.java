package com.knyghenko.deutschvonwiedergabelisten.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.adapter.LessonAdapter;
import com.knyghenko.deutschvonwiedergabelisten.entity.JsonToLessonsList;
import com.knyghenko.deutschvonwiedergabelisten.entity.Lesson;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;

import com.knyghenko.deutschvonwiedergabelisten.model.SaveSharedPreference;

import java.util.List;

public class LessonsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewLessons;
    private List<Lesson> lessonsList;
    private LessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        Toolbar toolbar = findViewById(R.id.toolbar_lessons);
        setSupportActionBar(toolbar);


        recyclerViewLessons = findViewById(R.id.lessons_recycler_view);
        recyclerViewLessons.setHasFixedSize(true);
        recyclerViewLessons.setLayoutManager(new LinearLayoutManager(this));
        lessonsList = JsonToLessonsList.getListLessons(getIntent().getStringExtra("lessons"));
        LessonAdapter.OnLessonClickListener onLessonClickListener = new LessonAdapter.OnLessonClickListener() {
            @Override
            public void onAdvClick(Lesson lesson) {
                //     ConnectToDownloadServer.connectToServerSearch(fileOutputStream, lesson.getServerUrl());
            }
        };
        lessonAdapter = new LessonAdapter(onLessonClickListener, getIntent().getStringExtra("e_mail"));
        lessonAdapter.setListLessons(lessonsList);
        recyclerViewLessons.setAdapter(lessonAdapter);

    }


    /**
     * Method create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Метод реагирует на нажатие кнопки меню, в данном случае кнопки Последние обьявления
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.lessons) {
            String lessons = ConnectServer.connectToServerSearch("2:" + "lessons");
            Intent intent = new Intent(this, LessonsActivity.class);
            intent.putExtra("lessons", lessons);
            intent.putExtra("e_mail", getIntent().getStringExtra("e_mail"));
            startActivity(intent);
        } else if (item.getItemId() == R.id.exit) {
            Intent intent = new Intent(this, LoginActivity.class);
            SaveSharedPreference.clearUserName(getApplicationContext());
            startActivity(intent);
        }
        return true;
    }


}