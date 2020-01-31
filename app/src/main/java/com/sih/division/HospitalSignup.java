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

public class HospitalSignup extends AppCompatActivity {
    EditText hName, City, Pass, hNo, Vcount, hAddress;
    byte[] image;
    Button b;
    ImageView img;
    SQLiteHelper sQLiteHelper;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_signup);
        sQLiteHelper = new SQLiteHelper(HospitalSignup.this);
        hName = (EditText)findViewById(R.id.hname);
        Pass = (EditText)findViewById(R.id.hpass);
        hNo = (EditText)findViewById(R.id.hno);
        City = (EditText)findViewById(R.id.hcity);
        Vcount = (EditText)findViewById(R.id.vac);
        hAddress = (EditText)findViewById(R.id.hadd);
        b = findViewById(R.id.user_signup);
        img = findViewById(R.id.photo_hospital);
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
    public void h_button(View V) {
        String hid="temp";
        String hpass = Pass.getText().toString();
        String hname = hName.getText().toString();
        String hnumber = hNo.getText().toString();
        String city = City.getText().toString();
        String vacount = Vcount.getText().toString();
        String address = hAddress.getText().toString();
      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());*/
        HospitalModel newHospital = new HospitalModel();
        if((!hpass.equals("")) && (!hname.equals("")) && (!hnumber.equals("")) && (!city.equals("")) && (!vacount.equals(""))  &&  (!address.equals("")))
        {
            /*if(!z)
                newStudent.setsID(studenttemp.getsID());*/
            newHospital.setHpass(hpass);
            newHospital.setHname(hname);
            newHospital.setNumber(hnumber);
            newHospital.setVaccount(vacount);
            newHospital.setDist(city);
            newHospital.setAddress(address);
            Drawable d = img.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            image = stream.toByteArray();
            newHospital.setPhoto(image);
            // if(z)
            // {

            sQLiteHelper.insertRecordHospital(newHospital);
            //     sQLiteHelper.insertRecordAttendanceLater(newStudent);
            //  }
            //  else
            //      sQLiteHelper.updateRecordStudent(newStudent);
            Toast.makeText(HospitalSignup.this,"Success", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(HospitalSignup.this,"All fields are compulsory", Toast.LENGTH_SHORT).show();

    }

}
