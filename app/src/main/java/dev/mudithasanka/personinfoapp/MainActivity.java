package dev.mudithasanka.personinfoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText search_input;
    Button search_button;
    Spinner filterDivisionSpinner;
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> person_id, person_hno, person_division, person_name, person_nic, person_gender;
    CustomAdapter customAdapter;
    String searchInputText = "";
    String selectCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_input = findViewById(R.id.search_input);
        search_button = findViewById(R.id.search_button);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), selectCity, Toast.LENGTH_SHORT).show();
                searchInputText = search_input.getText().toString().toLowerCase().trim();
                String fDivision = filterDivisionSpinner.getSelectedItem().toString();
                selectCity=fDivision;
                storeDataInArrays(searchInputText, selectCity);
            }
        });

        //Dropdown division list -start
        filterDivisionSpinner = (Spinner) findViewById(R.id.spinnerDivisionFilter);

        ArrayAdapter<String> filterDivisionAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filterDivisions));
        filterDivisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterDivisionSpinner.setAdapter(filterDivisionAdapter);
        //Dropdown division list -end

        filterDivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String fDivision = filterDivisionSpinner.getSelectedItem().toString();
                selectCity=fDivision;
                //Toast.makeText(getApplicationContext(), fDivision, Toast.LENGTH_LONG).show();
                storeDataInArrays("", fDivision);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


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

        storeDataInArrays("","All");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //Store Data in Arrays -start
    void storeDataInArrays(String inputText, String fdD){

        person_id.clear();
        person_division.clear();
        person_gender.clear();

        person_hno.clear();
        person_name.clear();
        person_nic.clear();

        Cursor cursor = myDB.readAllData(inputText, fdD);
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
            recyclerView.setAdapter(null);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }else {
            while (cursor.moveToNext()){

                person_id.add(cursor.getString(0));
                person_division.add(cursor.getString(1));
                person_hno.add(cursor.getString(2));
                person_name.add(cursor.getString(3));
                person_nic.add(cursor.getString(4));
                person_gender.add(cursor.getString(5));
            }



            customAdapter = new CustomAdapter(MainActivity.this, this, person_id, person_division, person_hno,
                    person_name, person_nic, person_gender);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            
        }

    }
    //Store Data in Arrays -end
}