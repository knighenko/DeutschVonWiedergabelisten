package com.knyghenko.deutschvonwiedergabelisten.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.knyghenko.deutschvonwiedergabelisten.R;
import com.knyghenko.deutschvonwiedergabelisten.model.ConnectServer;
import com.knyghenko.deutschvonwiedergabelisten.model.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity {
    private String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SaveSharedPreference.getUserName(getApplicationContext()).equals("")) {
            setContentView(R.layout.activity_login);
        } else {

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("e_mail", SaveSharedPreference.getUserName(getApplicationContext()));
            startActivity(intent);
        }
    }

    public void btnEnter(View view) {
        String e_mail;
        String password;
        e_mail = ((EditText) findViewById(R.id.edit_text_e_mail)).getText().toString();
        password = ((EditText) findViewById(R.id.edit_text_password)).getText().toString();
        String response = ConnectServer.connectToServerSearch("1:" + e_mail + ":" + password);
        if (response.equals("false")) {
            Toast toast = Toast.makeText(this, "Неправильно введён логин или пароль!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            SaveSharedPreference.setUserName(getApplicationContext(), e_mail);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("e_mail", e_mail);
            startActivity(intent);
        }
    }


    public void createUser(View view) {
        Intent intent = new Intent(getBaseContext(), CreateUserActivity.class);
        startActivity(intent);
    }
}