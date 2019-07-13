package com.example.studentregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText nameId,passwordId;
    String name,password,nameInput,passwordInput;
    Button login;

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        shared=getSharedPreferences("db",MODE_PRIVATE);
        nameId=findViewById(R.id.user_name1);
        passwordId=findViewById(R.id.password_1);
        login=findViewById(R.id.login);
        name=shared.getString("dbuname"," ");
        password=shared.getString("dbpassword"," ");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameInput=nameId.getText().toString();
                passwordInput=passwordId.getText().toString();
                if(nameInput.equalsIgnoreCase(name)&&passwordInput.equalsIgnoreCase(password))
                {
                    Toast.makeText(loginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(loginActivity.this,Profile.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(loginActivity.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
