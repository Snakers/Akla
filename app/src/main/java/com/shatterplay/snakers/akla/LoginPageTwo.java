package com.shatterplay.snakers.akla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;


public class LoginPageTwo extends AppCompatActivity {
private EditText phone;
    private EditText address;
    private Users tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_two);
        final Bundle bundle = this.getIntent().getExtras();

        phone = (EditText)findViewById(R.id.phone2);
        address = (EditText)findViewById(R.id.adress2);
        Button confirm = (Button) findViewById(R.id.confirm);
   final      DatabaseForAkla databaseForAkla = new DatabaseForAkla(LoginPageTwo.this);
databaseForAkla.getReadableDatabase();
        ArrayList<Users> users =DatabaseForAkla.databaseForAkla().insertUsers();


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert bundle != null;
                String image = bundle.getString("image");
                    String name = bundle.getString("name");
                 String email = bundle.getString("email");
                 String id = bundle.getString("id");
                   long ids=Long.parseLong(id);
                String phoneparse = phone.getText().toString();

             String addressprarse = address.getText().toString();

                final   Date date = new Date();
                String datahours =String.valueOf(date.getHours());
                String dataseconds=String.valueOf(date.getSeconds());
                String dateMinutes =String.valueOf(date.getMinutes());
                String dateStr = datahours +":"+":"+ dateMinutes+":"+dataseconds;

                tables = new Users(name,phoneparse,addressprarse,email,image,dateStr);

                Log.v("shitty"," " + id + " " + addressprarse + " " +email + " " + phoneparse + " "+dateStr);
                databaseForAkla.getWritableDatabase();
                   databaseForAkla.insertUsers(tables);
                databaseForAkla.close();

databaseForAkla.close();
                Intent intent = new Intent(LoginPageTwo.this,MainActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putAll(bundle);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();

recreate();

    }

}
