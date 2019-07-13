package com.example.studentregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.layout.simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText name,register,dob,user_name,password;
    RadioButton male,female;
    CheckBox droid,java,cpp,python;
    Spinner semester;
    Button submit;
    String s_name,s_uname,s_password,s_register,s_dob,gender,course,sem;

    SharedPreferences shared;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared=getSharedPreferences("db",MODE_PRIVATE);
        editor=shared.edit();


        name = findViewById(R.id.name);
        register = findViewById(R.id.register);
        dob = findViewById(R.id.dob);
        user_name = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        droid = findViewById(R.id.android);
        java = findViewById(R.id.java);
        cpp = findViewById(R.id.cpp);
        python = findViewById(R.id.python);
        semester = findViewById(R.id.semester);
        submit = findViewById(R.id.submit);

        male.setOnCheckedChangeListener(this);
        male.setChecked(true);
        female.setOnCheckedChangeListener(this);
        droid.setOnCheckedChangeListener(this);
        droid.setChecked(true);
        java.setOnCheckedChangeListener(this);
        python.setOnCheckedChangeListener(this);
        cpp.setOnCheckedChangeListener(this);

        String[] ssemester=getResources().getStringArray(R.array.semester);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this, simple_spinner_dropdown_item,ssemester);
        semester.setAdapter(arrayAdapter);

        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sem= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Stores user_data
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s_name = name.getText().toString();
                s_uname = user_name.getText().toString();
                s_password = password.getText().toString();
                s_register = register.getText().toString();
                s_dob = dob.getText().toString();
               // Toast.makeText(MainActivity.this, "Name: "+s_name+"\ngender: "+gender+"\nReg.no: "+s_register+"\nsemester: "+sem+"\nD.O.B: "+s_dob+"\ncourse: "+course+"\nusername: "+s_uname+"\nPassword: "+s_password, Toast.LENGTH_SHORT).show();

                if(s_name.equalsIgnoreCase(""))
                {
                    name.setError("Mandatory Field");
                }
                else if(s_register.equalsIgnoreCase(""))
                {
                    register.setError("Mandatory Field");
                }
                else if(s_dob.equalsIgnoreCase(""))
                {
                    dob.setError("Mandatory Field");
                }
                else if(s_uname.equalsIgnoreCase(""))
                {
                    user_name.setError("Mandatory Field");
                }
                else if(s_password.equalsIgnoreCase(""))
                {
                    password.setError("Mandatory Field");
                }

                else
                {
                    editor.putString("dbname",s_name);
                    editor.putString("dbuname",s_uname);
                    editor.putString("dbpassword",s_password);
                    editor.putString("dbregister",s_register);
                    editor.putString("dbdob",s_dob);
                    editor.putString("dbgender",gender);
                    editor.putString("dbcourse",course);
                    editor.putString("dbsem",sem);
                    editor.apply();
                    Intent intent=new Intent(MainActivity.this,loginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            if(buttonView.getId()==R.id.male)
            {
                female.setChecked(false);
                gender="male";
            }
            if(buttonView.getId()==R.id.female)
            {

                male.setChecked(false);
                gender="female";
            }

            if(buttonView.getId()==R.id.android)
            {
                java.setChecked(false);
                cpp.setChecked(false);
                python.setChecked(false);
                course="android";
            }
            else if(buttonView.getId()==R.id.java)
            {
                droid.setChecked(false);
                cpp.setChecked(false);
                python.setChecked(false);
                course="java";
            }
            else if(buttonView.getId()==R.id.python)
            {
                java.setChecked(false);
                cpp.setChecked(false);
                droid.setChecked(false);
                course="python";
            }
            else
            {
                java.setChecked(false);
                python.setChecked(false);
                droid.setChecked(false);
                course="cpp";
            }
        }
    }
}
