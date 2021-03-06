package com.example.sidharthapriya.competition_prep1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelperBookmarks extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String GK_TABLE_NAME = "BOOKMARKS";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String SUBJECT = "subject";
    public static final String QUESTION_ID = "question_id";
   /* public static final String OPTION1 = "option1";
    public static final String OPTION2 = "option2";
    public static final String OPTION3 = "option3";
    */
    private HashMap hp;

    public DBHelperBookmarks(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // Tables
        //Allready Created in
        // DBHelperCreateTables

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub,
        //db.execSQL("DROP TABLE IF EXISTS gk");

        onCreate(db);

    }

    public boolean insertBookmark (String subject, int question_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", subject);
        contentValues.put("question_id", question_id);
       /* contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        */
        db.insert("BOOKMARKS", null, contentValues);
        return true;
    }

    public Cursor getData(String subject) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from BOOKMARKS where subject="+subject+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, GK_TABLE_NAME);
        return numRows;
    }

    public boolean updateTable (String subject, int question_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", subject);
        contentValues.put("question_id", question_id);
       /* contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);*/
        //db.update("BOOKMARKS", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from gk", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            //array_list.add(res.getString(res.getColumnIndex(QUESTION)));
            res.moveToNext();
        }
        return array_list;
    }
}