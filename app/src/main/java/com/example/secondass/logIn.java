package com.example.secondass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class logIn extends AppCompatActivity {

    private TextView doAccount;
    private EditText userName;
    private EditText Pass;

    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";

    private CheckBox chk;
    private boolean flag = false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Button LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setupSharedPrefs();

        userName = findViewById(R.id.username);
        Pass = findViewById(R.id.pass);
        LogIn = findViewById(R.id.button);
        doAccount = findViewById(R.id.createAcc);
        doAccount.setPaintFlags(doAccount.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        chk = findViewById(R.id.checkBox);

        checkPrefs();
      /*  Gson gson = new Gson();
        UserInform userInform = new UserInform("admin","admin");
        String toGson=gson.toJson(userInform);
        editor.putString("UserInformation",toGson);
        editor.commit();*/

    }

    public void bt_OnClick(View view) {
        String username = userName.getText().toString();
        String password = Pass.getText().toString();
        Gson gson = new Gson();

        String fromGson = prefs.getString("UserInformation", "");
        if (!fromGson.equals("")) {

            UserInform userInform = gson.fromJson(fromGson, UserInform.class);

            if (!username.equals("") && !password.equals("")) {
                if (username.equals(userInform.getUserName()) && password.equals(userInform.getPassword())) {
                    Intent intent = new Intent(logIn.this, TowButtonActivity.class);
                    startActivity(intent);

                    if (chk.isChecked()) {
                        editor.putString(NAME, username);
                        editor.putString(PASS, password);
                        editor.putBoolean(FLAG, true);
                        editor.commit();
                    }
                } else {
                    Toast.makeText(this, "The user name or password is invalid", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Wrong Entry", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "you Don't have account", Toast.LENGTH_LONG).show();
        }
    }

    public void txt_OnClick(View view) {

        Intent intent = new Intent(logIn.this, Register.class);
        startActivity(intent);
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if (flag) {
            String name = prefs.getString(NAME, "");
            String password = prefs.getString(PASS, "");
            userName.setText(name);
            Pass.setText(password);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}