package com.example.contact_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgot extends AppCompatActivity {

    TextView username;
    EditText email,pass, cpass;
    Button forgot;
    DBHelper DB;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        username=(TextView)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.npass);
        cpass=(EditText)findViewById(R.id.cpass);
        forgot=(Button)findViewById(R.id.forgot1);
        DB=new DBHelper(this);
        Intent intent =getIntent();
        username.setText(intent.getStringExtra("username"));
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repass=cpass.getText().toString();
                if (password.equals(repass)) {
                    Boolean checkpasswordUpdate = DB.checkpasswordUpdate(user, password);
                    if (checkpasswordUpdate == true) {
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                        Toast.makeText(forgot.this, "Passsword Update Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(forgot.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(forgot.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}