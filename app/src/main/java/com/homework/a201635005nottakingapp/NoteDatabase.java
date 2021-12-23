package com.homework.a201635005nottakingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSİON = 2;
    private static final  String DATABASE_NAME ="notedb";
    private static final  String DATABASE_TABLE ="notestable";

    // columns name for database table
    private static final String KEY_ID ="id";
    private static final String KEY_TITLE ="title";
    private static final String KEY_CONTENT ="content";
    private static final String KEY_DATE ="date";
    private static final String KEY_TIME ="time";


    NoteDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSİON);


    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE nametame(id INT PRIMARY KEY, title TEXT, content TEXT, date TEXT, time TEXT);
        String query = "CREATE TABLE"+ DATABASE_TABLE + "("+ KEY_ID+" INT PRIMARY KEY,"+
                KEY_TITLE + "TEXT,"+
                KEY_CONTENT + "TEXT,"+
                KEY_DATE + "TEXT,"+
                KEY_TIME + "TEXT," +")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
        onCreate(db);
    }
    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = ContentValues();
        c.(KEY_TIME,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());

        long ID = db.insert(DATABASE_TABLE,null,c);
        Log.d("Inserted", "ID -> "+ ID);
        return ID;

    }
   public Note getNote (long id);{
       // select * from databaseTable where id=1
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[[]{KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_TIME},KEY_ID+"=?",
                new String []{String.valueOf(id)},null,null,null);

    }
    getNotes ();
}
