package com.knyghenko.deutschvonwiedergabelisten.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;
import com.knyghenko.deutschvonwiedergabelisten.model.SaveSharedPreference;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        e_mail = getIntent().getStringExtra("e_mail");

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
            intent.putExtra("e_mail", e_mail);
            startActivity(intent);
        } else if (item.getItemId() == R.id.exit) {
            Intent intent = new Intent(this, LoginActivity.class);
            SaveSharedPreference.clearUserName(getApplicationContext());
            startActivity(intent);
        }
        return true;
    }

}