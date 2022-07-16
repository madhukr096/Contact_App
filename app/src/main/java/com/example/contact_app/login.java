package com.example.contact_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText email1,password1;
    TextView signup1,forgot;
    Button signin1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email1=(EditText)findViewById(R.id.email1);
        password1=(EditText)findViewById(R.id.password1);
        signup1=(TextView)findViewById(R.id.signup1);
        forgot=(TextView)findViewById(R.id.forgot);
        signin1=(Button)findViewById(R.id.signin1);
        DB=new DBHelper(this);

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=email1.getText().toString();
                String pass=password1.getText().toString();
                if (user.equals("")|| pass.equals(""))
                    Toast.makeText(login.this, "Please enter all the fileds", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass =DB.checkemailpassword(user,pass);
                    if (checkuserpass==true)
                    {
                        Toast.makeText(login.this, "Signin Successfully ", Toast.LENGTH_SHORT).show();
                        Intent signinintent =new Intent(getApplicationContext(),contact.class);
                        startActivity(signinintent);
                    }else{
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotintent=new Intent(login.this,password_activity.class);
                startActivity(forgotintent);
            }
        });

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup1intent=new Intent(login.this,MainActivity.class);
                startActivity(signup1intent);
            }
        });

    }
}