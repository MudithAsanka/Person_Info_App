package dev.mudithasanka.personinfoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Personinfoapp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "person";
    private static final String COLUMN_ID = "person_id";
    private static final String COLUMN_DIVISION = "person_division";
    private static final String COLUMN_HNO = "person_hno";
    private static final String COLUMN_NAME = "person_name";
    private static final String COLUMN_NIC = "person_nic";
    private static final String COLUMN_GENDER = "person_gender";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DIVISION + " TEXT, " +
                COLUMN_HNO + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NIC + " TEXT, " +
                COLUMN_GENDER + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
