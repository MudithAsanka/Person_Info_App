package dev.mudithasanka.personinfoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    MyDatabaseHelper(@Nullable Context context) {
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

    void addPerson(String division, String hno, String name, String nic, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DIVISION, division);
        cv.put(COLUMN_HNO, hno);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NIC, nic);
        cv.put(COLUMN_GENDER, gender);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //Read Data for Recycleview -start
    Cursor readAllData(String search_text, String filter_division){
        //System.out.println(search_text+filter_division);
        if (filter_division.equals("All") && search_text.equals("")){
            //String query = "SELECT * FROM " + TABLE_NAME;

            String query = "SELECT * FROM person ";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor curser = null;
            if(db != null){
                curser = db.rawQuery(query, null);
            }
            return curser;

        }else if(filter_division.equals("All") && !(search_text.equals(""))){
            //String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DIVISION + filter_division;
            //System.out.println("All and not null");
            String query = "SELECT * FROM person where person_name LIKE '%"+search_text+"%' OR person_nic LIKE '%"+search_text+"%'";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor curser = null;
            if(db != null){
                curser = db.rawQuery(query, null);
            }
            return curser;
        }else {
            //System.out.println("ELSE");
            //System.out.println("All and not null");
            String query = "SELECT * FROM person where person_division='"+filter_division+"' AND (person_name LIKE '%"+search_text+"%' OR person_nic LIKE '%"+search_text+"%')";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor curser = null;
            if(db != null){
                curser = db.rawQuery(query, null);
            }
            return curser;
        }

    }

    void updateData(String row_id, String division, String hno, String name, String nic, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DIVISION, division);
        cv.put(COLUMN_HNO, hno);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_NIC, nic);
        cv.put(COLUMN_GENDER, gender);

        long result = db.update(TABLE_NAME, cv, "person_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "person_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Delete Failed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
