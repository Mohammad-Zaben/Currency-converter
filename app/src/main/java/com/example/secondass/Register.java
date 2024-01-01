package com.example.secondass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText pass;
    private EditText confpass;
    private Button register;
    private ArrayList<UserInform> userInforms;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        confpass = findViewById(R.id.confpass);

        setupSharedPrefs();

        //  getArrayLIst();
    }

    public void bt_OnClick(View view) {
        Gson gson = new Gson();

        String user = username.getText().toString();
        String Pass = pass.getText().toString();
        String confPass = confpass.getText().toString();

        if (!user.equals("")) {
            if (Pass.equals(confPass)) {

                UserInform userInform = new UserInform(user, Pass);
                String toGson = gson.toJson(userInform);
                editor.putString("UserInformation", toGson);
                editor.commit();
                Toast.makeText(this, "done", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "The password did not match the confirmation password", Toast.LENGTH_LONG).show();

            }
        }else {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_LONG).show();

        }
    }

    public void getArrayLIst() {
        Gson gson = new Gson();
        String data = prefs.getString("UserInf", "");

        if (data.equals("")) {
            userInforms = new ArrayList<>();
            String infromationString = gson.toJson(userInforms);
            editor.putString("UserInf", infromationString);
            editor.commit();
        } else {
            Type listType = new TypeToken<ArrayList<UserInform>>() {
            }.getType();
            userInforms = gson.fromJson(data, listType);
        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}