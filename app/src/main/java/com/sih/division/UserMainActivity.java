package com.sih.division;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserMainActivity extends AppCompatActivity {
    UserModel m;
    SQLiteHelper sqLiteHelper;
    ImageView map,blog,pro,cont;
    /**int hid[] = new int[1000];
    double lat[] = new double[1000];
    double lng[] = new double[1000];
    int patcount[] = new int[1000];
    public List<HeatModel> mHeatList = null;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        m = (UserModel) getIntent().getSerializableExtra("Map");
        sqLiteHelper = new SQLiteHelper(UserMainActivity.this);
        map = findViewById(R.id.mapbut);
        blog = findViewById(R.id.blogbut);
        pro = findViewById(R.id.profbut);
        cont = findViewById(R.id.conbut);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainActivity.this,UserActivity.class);
                intent.putExtra("Map", m);
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 /*mHeatList = sqLiteHelper.getAllHeatRecordsCorona();
        for(int i = 0; i < mHeatList.size(); i++) {
            hid[i] = Integer.parseInt(mHeatList.get(i).getHid());
            lat[i] = Double.parseDouble(mHeatList.get(i).getLat());
            lng[i] = Double.parseDouble(mHeatList.get(i).getLng());
            patcount[i] = Integer.parseInt(mHeatList.get(i).getPat());
            String s = mHeatList.size()+"";
            Toast.makeText(UserMainActivity.this, s, Toast.LENGTH_LONG).show();*/

            }
        });
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = sqLiteHelper.userDetails(m);
                AlertDialog.Builder builder2 = new AlertDialog.Builder(UserMainActivity.this);
                builder2.setTitle("Profile");
                builder2.setCancelable(true);
                View viewInflated = LayoutInflater.from(UserMainActivity.this).inflate(R.layout.profile,null);
                TextView namee = (TextView) viewInflated.findViewById(R.id.namee);
                TextView numberr = (TextView) viewInflated.findViewById(R.id.numberr);
                TextView emaill = (TextView) viewInflated.findViewById(R.id.emaill);
                TextView genderr = (TextView) viewInflated.findViewById(R.id.genderr);
                TextView dobb = (TextView) viewInflated.findViewById(R.id.dobb);
                TextView addd = (TextView) viewInflated.findViewById(R.id.addd);
                TextView distt = (TextView) viewInflated.findViewById(R.id.districtt);
                ImageView img = viewInflated.findViewById(R.id.imgUser);
                namee.setText(m.getUname());
                numberr.setText(m.getCnumber());
                emaill.setText(m.getEmail());
                genderr.setText(m.getGender());
                dobb.setText(m.getDob());
                addd.setText(m.getPermadd());
                distt.setText(m.getUname());
                byte[] data = m.getPhoto();
                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                img.setImageBitmap(bmp);
                builder2.setView(viewInflated);
                builder2.setNegativeButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder2.create();
                alertDialog.show();
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
