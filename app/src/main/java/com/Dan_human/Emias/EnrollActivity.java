package com.Dan_human.Emias;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class EnrollActivity extends AppCompatActivity {

    Button btnconfirm;
    CalendarView clndrenroll;
    Spinner spinnerType, spinnerDoc;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerDoc = (Spinner) findViewById(R.id.spinnerDoc);
        btnconfirm = (Button) findViewById(R.id.btnConfirmEnroll);
        clndrenroll = (CalendarView) findViewById(R.id.clndrenroll);
        DB= new DBHelper(this);

        clndrenroll.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                String selectedDate = new StringBuilder().append(mMonth + 1)
                        .append("-").append(mDay).append("-").append(mYear)
                        .append(" ").toString();
                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        FillSpinnerType();

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = spinnerType.getSelectedItem().toString();
                FillSpinnerDoc(type);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                btnconfirm.setEnabled(false);
            }
        });

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_surname = spinnerDoc.getSelectedItem().toString();
                String type = spinnerType.getSelectedItem().toString();
                String date;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                date = sdf.format(clndrenroll.getDate());
                Bundle arguments = getIntent().getExtras();
                String user = arguments.get("user").toString();
                DB.insertDataEnroll(name_surname,type, date, user);
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    public void FillSpinnerType(){
        String[] types = {"district doctor","urologist","ophthalmologist","otorhinolaryngologist", "surgeon"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        spinnerType.setAdapter(adapter);
    }

    public void FillSpinnerDoc(String type){
        Cursor doctype = DB.getDoctorType(type);
        ArrayList<String> docs = new ArrayList<String>();
        if(doctype!=null&& doctype.moveToFirst()){
            do{
                String doc  = doctype.getString(doctype.getColumnIndexOrThrow ("name_surname"));
                docs.add(doc);
            }while(doctype.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, docs);
        spinnerDoc.setAdapter(adapter);
    }
}