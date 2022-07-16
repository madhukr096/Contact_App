package com.example.contact_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contact extends AppCompatActivity {
    EditText name,phone,email2;
    Button save, my_contact;
    Database DB1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email2=(EditText)findViewById(R.id.email2);
        save=(Button)findViewById(R.id.save);
        my_contact=(Button)findViewById(R.id.my_contact);
        DB1=new Database(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                String nm = name.getText().toString();
                String ph = phone.getText().toString();
                String em = email2.getText().toString();
                Boolean checkinserdata = DB1.insertuserdata(nm, ph, em);
                if (nm.equals("") || ph.equals("") || em.equals(""))
                    Toast.makeText(contact.this, "Please enter all the fileds", Toast.LENGTH_SHORT).show();
                else {
                    if (checkinserdata == true) {
                        Toast.makeText(contact.this, "Contact added", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(contact.this, "Contact not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        my_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB1.viewdata();
                if(res.getCount()==0){
                    Toast.makeText(contact.this, "No data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : "+res.getString(0)+"\n");
                    buffer.append("Phone : "+res.getString(1)+"\n");
                    buffer.append("Email: "+res.getString(2)+"\n\n");
                }
                AlertDialog .Builder builder =new AlertDialog.Builder(contact.this);
                builder.setCancelable(true);
                builder.setTitle("User Entriers");
                builder. setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}