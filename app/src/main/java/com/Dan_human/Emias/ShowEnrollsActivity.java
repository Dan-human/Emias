package com.Dan_human.Emias;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowEnrollsActivity extends AppCompatActivity {


    ListView listenrollsns, listenrollstype, listenrollsdate;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_enrolls);

        DB= new DBHelper(this);

        listenrollsns = (ListView) findViewById(R.id.listenrollsns);
        listenrollstype = (ListView) findViewById(R.id.listenrollstype);
        listenrollsdate = (ListView) findViewById(R.id.listenrollsdate);

        Bundle arguments = getIntent().getExtras();
        String user = arguments.get("user").toString();

        ShowEnrolls(user);
    }

    public void ShowEnrolls(String user) {
        Cursor uenrollsns = DB.getUserEnrolls(user, "name_surname");
        ArrayList<String> enrollsns = new ArrayList<String>();
        if (uenrollsns != null && uenrollsns.moveToFirst()) {
            do {
                String enroll = uenrollsns.getString(uenrollsns.getColumnIndexOrThrow("name_surname"));
                enrollsns.add(enroll);

            } while (uenrollsns.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, enrollsns);
        listenrollsns.setAdapter(adapter);


        Cursor uenrollstype = DB.getUserEnrolls(user, "type");
        ArrayList<String> enrollstype = new ArrayList<String>();
        if (uenrollstype != null && uenrollstype.moveToFirst()) {
            do {
                String enroll1 = uenrollstype.getString(uenrollstype.getColumnIndexOrThrow("type"));
                enrollstype.add(enroll1);
            } while (uenrollstype.moveToNext());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, enrollstype);
        listenrollstype.setAdapter(adapter1);
        


        Cursor uenrollsdate = DB.getUserEnrolls(user, "date");
        ArrayList<String> enrollsdate = new ArrayList<String>();
        if (uenrollsdate != null && uenrollsdate.moveToFirst()) {
            do {
                String enroll2 = uenrollsdate.getString(uenrollsdate.getColumnIndexOrThrow("date"));
                enrollsdate.add(enroll2);
            } while (uenrollsdate.moveToNext());
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, enrollsdate);
        listenrollsdate.setAdapter(adapter2);
    }
}