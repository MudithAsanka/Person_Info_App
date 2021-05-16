package dev.mudithasanka.personinfoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> person_id, person_hno, person_division, person_name, person_nic, person_gender;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        person_id = new ArrayList<>();
        person_division = new ArrayList<>();
        person_hno = new ArrayList<>();
        person_name = new ArrayList<>();
        person_nic = new ArrayList<>();
        person_gender = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, person_id, person_division, person_hno,
                person_name, person_nic, person_gender);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    //Store Data in Arrays -start
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                person_id.add(cursor.getString(0));
                person_division.add(cursor.getString(1));
                person_hno.add(cursor.getString(2));
                person_name.add(cursor.getString(3));
                person_nic.add(cursor.getString(4));
                person_gender.add(cursor.getString(5));
            }
        }
    }
    //Store Data in Arrays -end
}