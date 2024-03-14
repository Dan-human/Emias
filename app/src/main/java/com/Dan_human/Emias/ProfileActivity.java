package com.Dan_human.Emias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button btnenroll, btnexit, btncrtdoc, btnshowenrolls;
    TextView textwelcome;
    Switch switchadm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnexit=(Button) findViewById(R.id.btnexit);
        btncrtdoc=(Button) findViewById(R.id.btncreatedoc);
        switchadm=(Switch) findViewById(R.id.switchadmin);
        btnenroll=(Button) findViewById(R.id.btnenroll);
        textwelcome=(TextView) findViewById(R.id.textwelcome);
        btnshowenrolls = (Button) findViewById(R.id.btnshowenrolls);

        btncrtdoc.setEnabled(false);

        Bundle arguments = getIntent().getExtras();
        String user = arguments.get("user").toString();
        textwelcome.setText("Hello, " + user);

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btncrtdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateDocActivity.class);
                startActivity(intent);
            }
        });

        btnenroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnrollActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        btnshowenrolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle arguments = getIntent().getExtras();
                String user = arguments.get("user").toString();
                Intent intent = new Intent(getApplicationContext(), ShowEnrollsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        switchadm.setOnCheckedChangeListener((compoundButton, b) -> {
            if (switchadm.isChecked()){
                btncrtdoc.setEnabled(true);
            } else{
                btncrtdoc.setEnabled(false);
            }
        });
    }
}