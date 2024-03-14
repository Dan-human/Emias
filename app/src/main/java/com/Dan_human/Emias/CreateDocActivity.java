package com.Dan_human.Emias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class CreateDocActivity extends AppCompatActivity {

    EditText name_surname, type;
    Button createdoctor, deletedoctor;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createdoc);

        DB=new DBHelper (this);

        name_surname=(EditText) findViewById(R.id.doctorname);
        type=(EditText) findViewById(R.id.dtype);
        createdoctor = (Button) findViewById(R.id.btndoctor);
        deletedoctor = (Button) findViewById(R.id.btndeletedoc);

        createdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namesur = name_surname.getText().toString();
                String doctype = type.getText().toString();
                DB.insertDataDoctor(namesur, doctype);
            }
        });

        deletedoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namesur = name_surname.getText().toString();
                DB.deleteDoctor(namesur);
            }
        });
    }
}