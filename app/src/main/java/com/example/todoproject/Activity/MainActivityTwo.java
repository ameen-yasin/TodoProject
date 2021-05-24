package com.example.todoproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoproject.R;

public class MainActivityTwo extends AppCompatActivity {

    private TextView txtViewTASK;
    private TextView txtViewTASKDATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        getSupportActionBar().hide();

        Intent intent = getIntent();

//        String idValue = intent.getStringExtra("ID");
//        int id = Integer.parseInt(idValue);
//        String statusValue = intent.getStringExtra("STATUS");
//        int status = Integer.parseInt(statusValue);

        String name = intent.getStringExtra("NAME");
        String date = intent.getStringExtra("DATE");
        setUpViews();

        txtViewTASK.setText(name);
        txtViewTASKDATE.setText(date);
    }

    private void setUpViews() {
        txtViewTASK = findViewById(R.id.txtViewTASK);
        txtViewTASKDATE = findViewById(R.id.txtViewTASKDATE);
    }


    public void btn_editTask(View view) {
        Intent intent = new Intent(this, EditTask.class);
        String name = txtViewTASK.getText().toString();
        intent.putExtra("NAME",name);
        startActivity(intent);

    }
}