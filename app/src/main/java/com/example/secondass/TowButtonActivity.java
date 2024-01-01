package com.example.secondass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TowButtonActivity extends AppCompatActivity {

    private Button Exchange;
    private Button News;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow_button);
        Exchange=findViewById(R.id.Exchange);
        News=findViewById(R.id.News);
    }

    public void News_onClick(View view){
        Intent intent=new Intent(this,NewsActivity.class);
        startActivity(intent);
    }

    public void Exchange_onClick(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}