package com.example.a24game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {

    String time;
    String grade;
    TextView times;
    TextView grades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        time = bundle.getString("time");
        grade = bundle.getString("grade");
        times=findViewById(R.id.times);
        times.setText(time);
        grades=findViewById(R.id.grades);
        grades.setText(grade);
    }
    public void restart(View view) {
        Intent intent = new Intent(GradeActivity.this,MainActivity.class);
        startActivity(intent);
    }

}