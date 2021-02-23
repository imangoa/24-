package com.example.a24game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GradeActivity extends AppCompatActivity {

    String time;
    String grade;
    TextView times;
    TextView grades;
    TextView tplyaer;
    String level;
    String mode;
    int tgrade;
    float effect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this,"ciec.db",null,2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        time = bundle.getString("time");
        grade = bundle.getString("grade");
        level=bundle.getString("level");
        mode =bundle.getString("mode");
        String player=bundle.getString("player");
        tplyaer=findViewById(R.id.player);
        times=findViewById(R.id.times);
        times.setText(time);
        grades=findViewById(R.id.grades);
        grades.setText(grade);
        tgrade=Integer.parseInt(grade);
        tplyaer.setText(player);
        System.out.println(time);
        int ttime=Integer.parseInt(time);
        effect=tgrade/(float)ttime;
        String result = String.format("%.4f",effect);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();  //获得写入模式的数据库
        ContentValues values = new ContentValues();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        //db.execSQL("select _id from information where _id = "+ id +";");
            System.out.println(result);
            values.put("name",player);
            values.put("correct_num",tgrade);
            values.put("use_time",time);
            values.put("effect",result);
            values.put("time",simpleDateFormat.format(date));
            values.put("level",level);
            values.put("mode",mode);
            db.insert("aa",null,values);
    }
    public void restart(View view) {
        Intent intent = new Intent(GradeActivity.this,MainActivity.class);
        startActivity(intent);
    }

}