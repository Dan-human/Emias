package com.Dan_human.Emias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, password, repassword;
    Button signup, signin;
    DBHelper DB;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        signin=(Button) findViewById(R.id.btnsignin);
        signup=(Button) findViewById(R.id.btnsignup);
        DB=new DBHelper (this);

        firebaseAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String emailstr = email.getText().toString();
                String passstr = password.getText().toString();
                String repass = repassword.getText().toString();
                if(emailstr.equals("")||passstr.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fiels", Toast.LENGTH_SHORT).show();
                else{
                    if(passstr.equals(repass)){
                        Boolean checkuser = DB.checkusername(emailstr);
                        if(checkuser==false){
                            if(passstr.length()>6){
                                firebaseAuth.createUserWithEmailAndPassword(emailstr, passstr);
                                Boolean insert = DB.insertDataUser(emailstr, passstr);
                                if (insert){
                                    Toast.makeText(MainActivity.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                    intent.putExtra("user", emailstr);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Password must contain more than 6 characters", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}