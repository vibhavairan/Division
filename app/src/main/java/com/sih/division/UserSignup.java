package com.sih.division;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserSignup extends AppCompatActivity {
    EditText uName, DOB, City, Number, Mail, Address, Pass;
    byte[] image;
    Button b;
    ImageView img;
    RadioGroup Gender;
    boolean z;
    String gender = "Male";
    SQLiteHelper sQLiteHelper;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        sQLiteHelper = new SQLiteHelper(UserSignup.this);
        uName = (EditText)findViewById(R.id.username);
        Pass = (EditText)findViewById(R.id.upass);
        DOB = (EditText)findViewById(R.id.dob_user);
        City = (EditText)findViewById(R.id.city);
        Number = (EditText)findViewById(R.id.no_user);
        Mail = (EditText)findViewById(R.id.mail_user);
        Address = (EditText)findViewById(R.id.user_add);
        Gender = (RadioGroup) findViewById(R.id.gender_user);
        b = findViewById(R.id.user_signup);
        Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioMale:
                        gender="Male";
                        break;

                    case R.id.radioFemale:
                        gender="Female";
                        break;
                }
            }
        });
        img = findViewById(R.id.photo_user);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else
                    {
                        pickimageFromGallery();
                    }

                }
                else
                {

                }
            }
        });
    }
    private void pickimageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case PERMISSION_CODE: {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    pickimageFromGallery();
                else
                    Toast.makeText(this, "Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            img.setImageURI(data.getData());
        }

    }

    public void button(View V) {
        String cid="temp";
        String pass = Pass.getText().toString();
        String uname = uName.getText().toString();
        String dob = DOB.getText().toString();
        String number = Number.getText().toString();
        String city = City.getText().toString();
        String mail = Mail.getText().toString();
        String address = Address.getText().toString();
      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());*/
        UserModel newUser = new UserModel();
        if((!pass.equals("")) && (!uname.equals("")) && (!dob.equals("")) && (!city.equals("")) && (!number.equals(""))  && (!mail.equals("")) && (!address.equals("")))
        {
            /*if(!z)
                newStudent.setsID(studenttemp.getsID());*/
            newUser.setUpass(pass);
            newUser.setUname(uname);
            newUser.setUid(cid);
            newUser.setPermadd(address);
            newUser.setDob(dob);
            newUser.setGender(gender);
            newUser.setEmail(mail);
            newUser.setCnumber(number);
            newUser.setDist(city);
            Drawable d = img.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            image = stream.toByteArray();
            newUser.setPhoto(image);
           // if(z)
           // {
                sQLiteHelper.insertRecordUser(newUser);
           //     sQLiteHelper.insertRecordAttendanceLater(newStudent);
          //  }
          //  else
          //      sQLiteHelper.updateRecordStudent(newStudent);
            Toast.makeText(UserSignup.this,"Success", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(UserSignup.this,"All fields are compulsory", Toast.LENGTH_SHORT).show();

    }

}
