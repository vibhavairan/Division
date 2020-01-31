package com.sih.division;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioLoginGroup;
    String LoginValidation="User";
    String UserPass = "1234",HospitalPass = "1234",GuestPass = "1234";
    int UserId = 101 ,HospitalId = 102,GuestId = 103;
    EditText pass , id;
    String pass_s="" , id_s="";
    SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioLoginGroup = (RadioGroup) findViewById(R.id.radio);
        id = (EditText) findViewById(R.id.id);
        pass = (EditText) findViewById(R.id.pass);
        radioLoginGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radiouser:
                        LoginValidation="User";
                        break;
                    case R.id.radiohospital:
                        LoginValidation="Hospital";
                        break;
                    case R.id.radioguest:
                        LoginValidation="Guest";
                        break;
                }
            }
        });
    }

    public void addListenerOnButton(View V)
    {
        id_s = id.getText().toString();
        pass_s = pass.getText().toString();
        if(LoginValidation.equals("User") && id_s.equals(UserId+"")&& pass_s.equals(UserPass))
        {
            Toast.makeText(MainActivity.this, LoginValidation, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        }
        else
        if(LoginValidation.equals("Hospital"))
        {
            sqLiteHelper = new SQLiteHelper(MainActivity.this);
            try
            {
               // boolean z = sqLiteHelper.passcheckStudent(id_s,pass_s);
                //if(z)
                //{
                    Toast.makeText(MainActivity.this,"Hospital", Toast.LENGTH_SHORT).show();
                    /*StudentModel s = new StudentModel();
                    s.setsID(id_s);*/
                    HospitalModel h = new HospitalModel();
                    h.setHid(id_s);
                    Intent intent = new Intent(MainActivity.this,HospitalActivity.class);
                    intent.putExtra("Map", h);
                    startActivity(intent);
               // }
               // else
                  //  Toast.makeText(MainActivity.this,"You are not registered", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(MainActivity.this,"You are not registered", Toast.LENGTH_SHORT).show();
            }
        }
        else if(LoginValidation.equals("Guest"))
        {
            sqLiteHelper = new SQLiteHelper(MainActivity.this);
            try
            {
                //boolean z = sqLiteHelper.passcheckTeacher(id_s,pass_s);
                //if(z)
                //{
                    Toast.makeText(MainActivity.this,"Guest", Toast.LENGTH_SHORT).show();
                    //TeacherModel m = new TeacherModel();
                    //m.setTeacherID(id_s);
                    Intent intent = new Intent(MainActivity.this,GuestActivity.class);
                    //intent.putExtra("Map", m);
                    startActivity(intent);
                //}
                //else
                   // Toast.makeText(LoginActivity.this,"You are not registered", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(MainActivity.this,"You are not registered", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(MainActivity.this,"Invalid Input", Toast.LENGTH_SHORT).show();
    }
    public void addListenerOnButtonSignup(View V)
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);
        builder.setMessage("");
        builder.setTitle("Sign up as");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "User",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(MainActivity.this,UserSignup.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton(
                "Hospital",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(MainActivity.this,HospitalSignup.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
