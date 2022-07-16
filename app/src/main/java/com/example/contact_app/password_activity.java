package com.example.contact_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class password_activity extends AppCompatActivity {

    EditText username;
    Button reset;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_activity);
        username=(EditText) findViewById(R.id.uname);
        reset=(Button)findViewById(R.id.reset);
        DB=new DBHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                Boolean checkuser=DB.checkemail(user);
                if(checkuser==true)
                {
                    Intent intent= new Intent(getApplicationContext(),forgot.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(password_activity.this, "User does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}