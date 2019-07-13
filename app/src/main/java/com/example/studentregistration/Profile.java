package com.example.studentregistration;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView username,dob,reg,sem,course,name,gender;

    SharedPreferences shared;
    String sname,sdob,sreg,ssem,scourse,suser,sgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        shared=getSharedPreferences("db",MODE_PRIVATE);
        sname=shared.getString("dbname"," ");
        sreg=shared.getString("dbregister"," ");
        sgender=shared.getString("dbgender"," ");
        sdob=shared.getString("dbdob"," ");
        ssem=shared.getString("dbsem"," ");
        scourse=shared.getString("dbcourse"," ");
        suser=shared.getString("dbuname"," ");

        name=findViewById(R.id.namep);
        dob=findViewById(R.id.dobp);
        reg=findViewById(R.id.regp);
        sem=findViewById(R.id.semp);
        course=findViewById(R.id.coursep);
        gender=findViewById(R.id.genderp);
        username=findViewById(R.id.unamep);

        name.setText(sname);
        dob.setText(sdob);
        reg.setText(sreg);
        sem.setText(ssem);
        course.setText(scourse);
        gender.setText(sgender);
        username.setText(suser);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure to exit?");
        //set listener for dialog button

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*finish();
                System.exit(0);*/
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
