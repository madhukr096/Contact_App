package com.example.contact_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,password,secret;
    TextView signin;
    Button signup;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        secret=(EditText)findViewById(R.id.secret);
        signin=(TextView)findViewById(R.id.signin);
        signup=(Button)findViewById(R.id.signin1);
        DB= new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id = email.getText().toString();
                String pass = password.getText().toString();
                String sec = secret.getText().toString();
                if (email_id.equals("") || pass.equals("") || sec.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(sec)) {
                        Boolean checkuser = DB.checkemail(email_id);
                        if (checkuser == false) {
                            Boolean insert = DB.insertuserdata(email_id, pass, sec);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent signupintent = new Intent(getApplicationContext(), contact.class);
                                startActivity(signupintent);

                            } else {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
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
                Intent signupintent=new Intent(MainActivity.this,login.class);
                startActivity(signupintent);
            }
        });
    }
}