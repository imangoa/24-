package com.example.a24game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InitActivity extends AppCompatActivity {
    String mode;
    String level;
    LinearLayout custom;
    EditText customs;
    int duration = 0;
    int flag =0;
    final  static int[] durations ={60,180,300,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        custom = findViewById(R.id.customs);
        custom.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mode = bundle.getString("mode");
        level = bundle.getString("level");
        TextView textView =findViewById(R.id.title_mode);
        textView.setText(mode);
        LinearLayout linearLayout =findViewById(R.id.control_on);
        customs =findViewById(R.id.customss);
        if (mode.equals("闯关模式"))
        {
            linearLayout.setVisibility(View.INVISIBLE);
        }
        Log.i(mode, "onCreate: model");
        Log.i(level, "onCreate: level");

        Button button = (Button)findViewById(R.id.init_start);
        Spinner spinner =findViewById(R.id.timing);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =findViewById(R.id.player_name);
                String Player_name=editText.getText().toString();
                if (Player_name.length()<1)
                {
                    Toast.makeText(InitActivity.this,"请输入玩家名",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (mode.equals("闯关模式")) {
                        Intent intent = new Intent(InitActivity.this, RecruitActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("level", level);
                        bundle.putCharSequence("player",Player_name);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(InitActivity.this, TimingActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("level", level);
                        bundle.putCharSequence("player",Player_name);
                        if (flag==1)
                        {
                            String val =customs.getText().toString();
                            if (val.length()<1)
                            {
                                Toast.makeText(InitActivity.this,"请输入自定义时间",Toast.LENGTH_SHORT).show();

                            }
                            duration = Integer.parseInt(val);
                        }
                        bundle.putCharSequence("timer",duration+"");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
//                Log.i("闯关模式", "onClick: ");
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                duration=durations[position];
                if (position==3)
                {
                    custom.setVisibility(View.VISIBLE);
                    flag= 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}