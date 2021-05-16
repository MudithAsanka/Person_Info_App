package dev.mudithasanka.personinfoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdatePerson extends AppCompatActivity {

    Spinner spinnerUpdatePerson;
    EditText hno_input, name_input, nic_input;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button update_button, delete_button;

    String id, division, hno, name, nic, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);

        //Dropdown division list -start
        Spinner updatePersonSpinner = (Spinner) findViewById(R.id.spinnerUpdatePerson);

        ArrayAdapter<String> updatePersonAdapter = new ArrayAdapter<String>(UpdatePerson.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.divisions));
        updatePersonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updatePersonSpinner.setAdapter(updatePersonAdapter);
        //Dropdown division list -end

        //spinnerUpdatePerson = findViewById(R.id.spinnerUpdatePerson);
        hno_input = findViewById(R.id.hno_inputUpdate);
        name_input = findViewById(R.id.name_inputUpdate);
        nic_input = findViewById(R.id.nic_inputUpdate);
        radioGroup = findViewById(R.id.radioGroupUpdate);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if (radioId != -1) {

                    MyDatabaseHelper myDB = new MyDatabaseHelper(UpdatePerson.this);
                    //and only then we call this
                    myDB.updateData(id,
                            updatePersonSpinner.getSelectedItem().toString().trim(),
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

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confimDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("division") &&
                getIntent().hasExtra("hno") && getIntent().hasExtra("name") && getIntent().hasExtra("nic") &&
                getIntent().hasExtra("gender")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            division = getIntent().getStringExtra("division");
            hno = getIntent().getStringExtra("hno");
            name = getIntent().getStringExtra("name");
            nic = getIntent().getStringExtra("nic");
            gender = getIntent().getStringExtra("gender");

            //Setting Intent Data
            hno_input.setText(hno);
            name_input.setText(name);
            nic_input.setText(nic);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confimDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete Confirmation");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdatePerson.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}