package com.example.a24game;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper { //带全部参数的构造函数，name为数据库名称
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //建表
        db.execSQL("CREATE TABLE aa(\n" +
                "             id INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "             name TEXT NOT NULL,\n" +
                "             correct_num INTEGER NOT NULL,\n"+
                "             use_time TEXT NOT NULL,\n"+
                "             effect REAL NOT NULL,\n"+
                "             time TEXT NOT NULL,\n" +
                "             level TEXT NOT NULL,\n"+
                "              mode TEXT NOT NULL\n"+
                "             )");

//        db.execSQL("INSERT into aa(id,name,correct_num,use_time,effect,time) VALUES (1,\"张三\",22,\"33s\",15.6,\"2021:2:2\");");
/*        db.execSQL("INSERT into information(id,name,sex,class) VALUES (202002,\"王乐\",\"男\",\"嵌入式1班\");");
        db.execSQL("INSERT into information(id,name,sex,class) VALUES (202003,\"刘小慧\",\"女\",\"网编1班\");");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//简单demo，就不写这个了

    }
    //只有conCreate()和onUpgrade是抽象方法，所以重写，

}