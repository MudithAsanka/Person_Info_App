package dev.mudithasanka.personinfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Spinner addPersonSpinner = (Spinner) findViewById(R.id.spinnerAddPerson);

        ArrayAdapter<String> addPersonAdapter = new ArrayAdapter<String>(AddPerson.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.divisions));
        addPersonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addPersonSpinner.setAdapter(addPersonAdapter);
    }
}