package dev.mudithasanka.personinfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPerson extends AppCompatActivity {

    Spinner spinnerAddPerson;
    EditText hno_input, name_input, nic_input;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        //Dropdown division list -start
        Spinner addPersonSpinner = (Spinner) findViewById(R.id.spinnerAddPerson);

        ArrayAdapter<String> addPersonAdapter = new ArrayAdapter<String>(AddPerson.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.divisions));
        addPersonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addPersonSpinner.setAdapter(addPersonAdapter);
        //Dropdown division list -end

        //spinnerAddPerson = findViewById(R.id.spinnerAddPerson);
        hno_input = findViewById(R.id.hno_input);
        name_input = findViewById(R.id.name_input);
        nic_input = findViewById(R.id.nic_input);
        radioGroup = findViewById(R.id.radioGroup);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if (radioId != -1) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddPerson.this);
                    myDB.addPerson(addPersonSpinner.getSelectedItem().toString().trim(),
                            hno_input.getText().toString().trim(),
                            name_input.getText().toString().trim(),
                            nic_input.getText().toString().trim(),
                            radioButton.getText().toString().trim());
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Select Gender";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    public void checkbutton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Gender : "+radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}