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

public class DBHelperCreateTables extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "MyDBName.db";

   public static final String CONTACTS_COLUMN_ID = "id";
   public static final String QUESTION = "question";
   public static final String SOLUTION = "solution";
   public static final String OPTION1 = "option1";
   public static final String OPTION2 = "option2";
   public static final String OPTION3 = "option3";
   private HashMap hp;

   public DBHelperCreateTables(Context context) {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
	   db.execSQL(
		         "create table ELECTRONICS " +
		         "(id integer primary key, question text,solution text,option1 text, option2 text,option3 text)"
		      );
	   db.execSQL(
	 	         "create table GK " +
	 	         "(id integer primary key, question text,solution text,option1 text, option2 text,option3 text)"
	 	      );
       db.execSQL(
               "create table BOOKMARKS " +
                       "(id integer primary key, subject, question_id)"
       );
	  // SQLiteDatabase db1 = this.getWritableDatabase();

   }

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	 db.execSQL(
	         "create table ELECTRONICS " +
	         "(id integer primary key, question text,solution text,option1 text, option2 text,option3 text)"
	      );
   db.execSQL(
 	         "create table GK " +
 	         "(id integer primary key, question text,solution text,option1 text, option2 text,option3 text)"
 	      );
    db.execSQL(
            "create table BOOKMARKS " +
                    "(id integer primary key, subject, question_id)"
    );
}
public int getData(int id) {
    SQLiteDatabase db = this.getWritableDatabase();
    int x=0;
    //adfads
    ContentValues contentValues = new ContentValues();
    contentValues.put("question", "?");
    contentValues.put("solution", "x");
    contentValues.put("option1", "option1");
    contentValues.put("option2", "option2");
    contentValues.put("option3", "option3");
    String[] str1;
    str1=new String[] {String.valueOf(id)};
    db.update("electronics", contentValues, "id = ", str1);
    
    //asd
    

    int numRows = (int) DatabaseUtils.queryNumEntries(db, "ELECTRONICS");
    db.close();
    return numRows;

 }
}