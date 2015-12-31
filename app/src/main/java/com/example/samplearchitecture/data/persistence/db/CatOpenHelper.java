package com.example.samplearchitecture.data.persistence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sean on 12/29/2015.
 */
public class CatOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cat.db";
    private static final int VERSION = 1;

    private static final String CREATE_ITEM = ""
            + "CREATE TABLE " + CatItem.TABLE + "("
            + CatItem.ID + " TEXT NOT NULL PRIMARY KEY,"
            + CatItem.IMAGE_URL + " TEXT NOT NULL,"
            + CatItem.SOURCE_URL + " TEXT NOT NULL"
            + ")";

    public CatOpenHelper(Context context) {
        super(context, DB_NAME, null /* factory */, VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
