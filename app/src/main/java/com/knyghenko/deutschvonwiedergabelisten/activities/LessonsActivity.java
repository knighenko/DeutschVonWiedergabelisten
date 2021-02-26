package com.knyghenko.deutschvonwiedergabelisten.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.adapter.LessonAdapter;
import com.knyghenko.deutschvonwiedergabelisten.entity.JsonToList;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;
import com.knyghenko.deutschvonwiedergabelisten.model.SaveSharedPreference;

import java.util.List;

public class LessonsActivity extends AppCompatActivity {
    private RecyclerView lessons;
    private List<String> lessonsList;
    private LessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        Toolbar toolbar = findViewById(R.id.toolbar_lessons);
        setSupportActionBar(toolbar);
        lessons = findViewById(R.id.lessons_recycler_view);
        lessons.setHasFixedSize(true);
        lessons.setLayoutManager(new LinearLayoutManager(this));
        JsonToList.getListLessons(getIntent().getStringExtra("urlsLessons"));
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
            String urlsYouTube = ConnectServer.connectToServerSearch("2:" + "lessons");
            System.out.println(urlsYouTube);
            Intent intent = new Intent(this, LessonsActivity.class);
            intent.putExtra("urlsYouTube", urlsYouTube);
            startActivity(intent);
        } else if (item.getItemId() == R.id.exit) {
            Intent intent = new Intent(this, LoginActivity.class);
            SaveSharedPreference.clearUserName(getApplicationContext());
            startActivity(intent);
        }
        return true;
    }
}