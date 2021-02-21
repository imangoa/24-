package com.example.a24game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private boolean music_flag =false;
    private String gameLevel;
    private String gameMode;
    static final String[] glevels ={"初级","高级"};
    static final String[] gModes = {"闯关模式","计时模式"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        取消bar栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.start);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.music_bg); //创建mediapaly对象，装载要播放的音频
//        mediaPlayer.start();
        ImageView music_button=(ImageView)findViewById(R.id.music_button);
        Button game_start  = (Button)findViewById(R.id.game_start);
        Button rank_list = (Button)findViewById(R.id.rank_list);
        Spinner game_levels =(Spinner)findViewById(R.id.game_level);
        Spinner game_models =(Spinner)findViewById(R.id.game_model);
        rank_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RankActivity.class);
                startActivity(intent);
            }
        });
        music_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_flag=!music_flag;
                if (music_flag) {
                    mediaPlayer.start();
                    music_button.setImageResource(R.drawable.music_on);
                    Log.i("music_flag", "onClick: 播放音乐");
                }
                else{
                    mediaPlayer.pause();
                    music_button.setImageResource(R.drawable.musci_off);
                    Log.i("music_flag", "onClick: 关闭音乐");

                }
            }
        });   //音乐播放按钮
        game_levels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameLevel=glevels[position];
                Log.i(gameLevel, "onItemSelected: ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        game_models.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameMode=gModes[position];
                Log.i(gameMode, "onItemSelected: ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        game_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InitActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("mode",gameMode);
                bundle.putCharSequence("level",gameLevel);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}