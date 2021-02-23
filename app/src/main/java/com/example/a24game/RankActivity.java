package com.example.a24game;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RankActivity extends AppCompatActivity {

    private ListView ranks;
    ArrayList<String> stringArrayList = new ArrayList<String>();
    String level="初级";
    String mode="闯关";
    TextView bChuji;
    TextView bGaoji;
    TextView bChaugnGuan;
    TextView bJiShi;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ranks = (ListView) findViewById(R.id.Student_ALL);
        bChuji= findViewById(R.id.chuji);
        bGaoji= findViewById(R.id.gaoji);
        bChaugnGuan= findViewById(R.id.chuangguan);
        bJiShi= findViewById(R.id.jishi);

        show();

    }
    public void show()
    {
        stringArrayList.clear();
//        stringArrayList=null;
        final DatabaseHelper databaseHelper = new DatabaseHelper(this,"ciec.db",null,2);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("aa", new String[]{"id","name","correct_num","use_time","effect","time"}, "level=? and mode=?", new String[]{level,mode}, null, null, "effect desc");
        String textview_data = "";
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中
        int i=0;
//        String init=" "+"|"+"玩家名"+"|"+"正确个数"+"|"+"用时"+"|"+"效率"+"|"+"时间";
//        stringArrayList.add(init);
        while(cursor.moveToNext()){
            i++;
            String asd = cursor.getString(cursor.getColumnIndex("name"));
            String asd2 = cursor.getString(cursor.getColumnIndex("correct_num"));
            String zxc = cursor.getString(cursor.getColumnIndex("use_time"));
            String qaz = cursor.getString(cursor.getColumnIndex("effect"));
            String qaz2 = cursor.getString(cursor.getColumnIndex("time"));
            textview_data = i+ "|" + asd+"|"+asd2 +"|" + zxc +"|" + qaz+"|"+qaz2;
            stringArrayList.add(textview_data);
            if (i>=10)                    //限制排行榜前10
                break;
        }
        String [] stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
        data = stringArray;
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        ranks.setAdapter(mAdapter);
//        ranks.setAdapter(null);
    }
    public void click_chuji(View view)
    {
        ranks.setAdapter(null);
        bGaoji.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        bChuji.setBackgroundColor(Color.parseColor("#A6ADD5"));
        level="初级";
        show();
    }
    public void click_gaoji(View view)
    {
        bChuji.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        bGaoji.setBackgroundColor(Color.parseColor("#A6ADD5"));
        level="高级";
        show();
    }
    public void click_chuangguan(View view)
    {
        bJiShi.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        bChaugnGuan.setBackgroundColor(Color.parseColor("#A6ADD5"));
//        level="初级";
        mode="闯关";
        show();
    }
    public void click_jishi(View view)
    {
        bChaugnGuan.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        bJiShi.setBackgroundColor(Color.parseColor("#A6ADD5"));
        level="计时";
        show();
    }
    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.length;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_content,parent,false);
                holder = new ViewHolder();
                holder.id = (TextView) convertView.findViewById(R.id.tv1);
                holder.name = (TextView) convertView.findViewById(R.id.tv2);
                holder.corect_num = (TextView) convertView.findViewById(R.id.tv3);
                holder.time_use= (TextView) convertView.findViewById(R.id.tv4);
                holder.effect= (TextView) convertView.findViewById(R.id.tv5);
                holder.time = (TextView) convertView.findViewById(R.id.tv6);

                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            String[] strArr = data[position].split("\\|");
            holder.id.setText(strArr[0]);
            holder.name.setText(strArr[1]);
            holder.corect_num.setText(strArr[2]);
            holder.time_use.setText(strArr[3]);
            holder.effect.setText(strArr[4]);
            holder.time.setText(strArr[5]);
            return convertView;
        }
        class ViewHolder {
            TextView id;
            TextView name;
            TextView corect_num;
            TextView time_use;
            TextView effect;
            TextView time;
        }
    }
}