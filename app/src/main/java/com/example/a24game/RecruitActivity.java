package com.example.a24game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class RecruitActivity extends AppCompatActivity  implements View.OnClickListener {

    String txt="";
    Game game;
    Button Bnum1;
    Button Bnum2;
    Button Bnum3;
    Button Bnum4;
    Button Bnum5;
    Button Bnum6;
    Button Bnum7;
    Button Bnum8;
    Button Bnum9;
    Button Bnum10;
    Button Submit;
    ImageView Inum1;
    ImageView Inum2;
    ImageView Inum3;
    ImageView Inum4;
    EditText text;
    TextView hint;
    String tLevel="";
    String tPlayer="";
    TextView grade;
    int mgrade=0;
    int hint_count=0;
    Chronometer ch;
    final static String[] Image_path={"a1","a2","a3","a4","a5","a6","a7","a8","a9","a10","a11","a12","a13"};
    int[] flags =new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        取消bar栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_recruit);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tLevel=bundle.getString("level");
        tPlayer=bundle.getString("player");
        init();

//        获取开始组件
        ch=(Chronometer)findViewById(R.id.chronometer);
        ch.setBase(SystemClock.elapsedRealtime());
        ch.start();

    }
    public void hint(View view)
    {
        TextView hint=findViewById(R.id.hint);
        if (tLevel.equals("高级"))
        {
            String s=game.data.get(0);
            StringBuilder out= new StringBuilder();
            char[] exp = s.toCharArray();
            for (int k=0;k<exp.length;k++)
            {

                if (Character.isDigit(exp[k]))
                {
                    int temp = Integer.parseInt(String.valueOf(exp[k]));
                    temp+=game.extra[0];
                    out.append(temp+"");
                }
                else {
                    out.append(exp[k]);
                }
            }
            text.setText(out);
            txt=game.data.get(0);
            hint_count++;
            hint.setText("提示："+hint_count);
            System.out.println(out);
        }
        else{
            hint_count++;
            txt=game.data.get(0);
            text.setText(game.data.get(0));
            hint.setText("提示："+hint_count);

        }
    }
    protected void init()
    {
        game =new Game();
        game.Level=tLevel;
        game.Games();
        game.compute();
        Bnum1 = (Button)findViewById(R.id.num1);
        Bnum2 = (Button)findViewById(R.id.num2);
        Bnum3 = (Button)findViewById(R.id.num3);
        Bnum4 = (Button)findViewById(R.id.num4);
        Bnum5 = (Button)findViewById(R.id.btn1);
        Bnum6 = (Button)findViewById(R.id.btn2);
        Bnum7 = (Button)findViewById(R.id.btn3);
        Bnum8 = (Button)findViewById(R.id.btn4);
        Bnum9 = (Button)findViewById(R.id.btn5);
        Bnum10 = (Button)findViewById(R.id.btn6);
        Submit = (Button)findViewById(R.id.submit);
        text = (EditText)findViewById(R.id.arithmetic);
        grade =(TextView)findViewById(R.id.answer_num);


        Bnum1.setText(String.valueOf(game.nums[0]));
        Bnum2.setText(String.valueOf(game.nums[1]));
        Bnum3.setText(String.valueOf(game.nums[2]));
        Bnum4.setText(String.valueOf(game.nums[3]));

        Bnum1.setOnClickListener(this);
        Bnum2.setOnClickListener(this);
        Bnum3.setOnClickListener(this);
        Bnum4.setOnClickListener(this);
        Bnum5.setOnClickListener(this);
        Bnum6.setOnClickListener(this);
        Bnum7.setOnClickListener(this);
        Bnum8.setOnClickListener(this);
        Bnum9.setOnClickListener(this);
        Bnum10.setOnClickListener(this);
        Submit.setOnClickListener(this);
        grade.setText(mgrade+"");

        Inum1 = (ImageView) findViewById(R.id.puke1);
        Inum2 = (ImageView) findViewById(R.id.puke2);
        Inum3 = (ImageView) findViewById(R.id.puke3);
        Inum4 = (ImageView) findViewById(R.id.puke4);

        Resources r = this.getResources();
        int id1 = r.getIdentifier(Image_path[game.nums[0]-1], "drawable", "com.example.a24game");
        int id2 = r.getIdentifier(Image_path[game.nums[1]-1], "drawable", "com.example.a24game");
        int id3 = r.getIdentifier(Image_path[game.nums[2]-1], "drawable", "com.example.a24game");
        int id4 = r.getIdentifier(Image_path[game.nums[3]-1], "drawable", "com.example.a24game");

        Inum1.setImageResource(id1);
        Inum2.setImageResource(id2);
        Inum3.setImageResource(id3);
        Inum4.setImageResource(id4);



    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        String s = text.getText().toString();
        if (v.getId() == R.id.num1) {
            if (flags[0]!=1)
            {
                int num1= Integer.parseInt(Bnum1.getText().toString());
                text.setText(s+String.valueOf(num1));
                num1-=game.extra[0];
                String sNum1=num1+"";
                txt+=sNum1;
                flags[0]=1;
            }
        }
        else if (v.getId() == R.id.num2) {
            if (flags[1] != 1) {
                int num2= Integer.parseInt(Bnum2.getText().toString());
                text.setText(s+String.valueOf(num2));
                num2-=game.extra[1];
                String sNum2=num2+"";
                txt+=sNum2;
                flags[1] = 1;
            }
        }
        else if (v.getId() == R.id.num3) {
            if (flags[2] != 1) {
                int num3= Integer.parseInt(Bnum3.getText().toString());
                text.setText(s+String.valueOf(num3));
                num3-=game.extra[2];
                String sNum3=num3+"";
                txt+=sNum3;
                flags[2] = 1;
            }
        }
        else if (v.getId() == R.id.num4) {
            if (flags[3] != 1) {
                int num4= Integer.parseInt(Bnum4.getText().toString());
                text.setText(s+String.valueOf(num4));
                num4-=game.extra[3];
                String sNum4=num4+"";
                txt+=sNum4;
                flags[3] = 1;
            }
        }
        else if (v.getId() == R.id.btn1) {
            text.setText(s + Bnum5.getText().toString());
            txt+=Bnum5.getText().toString();
        }
        else if (v.getId() == R.id.btn2) {
            text.setText(s + Bnum6.getText().toString());
            txt+=Bnum6.getText().toString();
        }
        else if (v.getId() == R.id.btn3) {
            text.setText(s + Bnum7.getText().toString());
            txt+=Bnum7.getText().toString();
        }
        else if (v.getId() == R.id.btn4) {
            text.setText(s + Bnum8.getText().toString());
            txt+=Bnum8.getText().toString();
        }
        else if (v.getId() == R.id.btn5) {
            text.setText(s + Bnum9.getText().toString());
            txt+=Bnum9.getText().toString();
        }
        else if (v.getId() == R.id.btn6) {
            text.setText(s + Bnum10.getText().toString());
            txt+=Bnum10.getText().toString();
        }
        else if (v.getId() == R.id.submit)
        {
//            System.out.println(txt);
            flags[0]=0;
            flags[1]=0;
            flags[2]=0;
            flags[3]=0;
            if (game.data.contains(txt))
            {
                txt="";
                text.setText("");
                Log.e("s", "onClick: sucess" );
                mgrade++;
                init();
            }
            else {
                txt="";
                text.setText("");
                ch.stop();
                int temp0 = Integer.parseInt(ch.getText().toString().split(":")[0]);

                int temp1 =Integer.parseInt(ch.getText().toString().split(":")[1]);
                int temp=temp0*60+temp1;
                int time=temp+30*hint_count;
                Bundle bundle = new Bundle();
                bundle.putCharSequence("time", time+"");
                bundle.putCharSequence("grade", grade.getText().toString());
                Intent intent = new Intent(RecruitActivity.this, GradeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}