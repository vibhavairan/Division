package com.sih.division;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserMainActivity extends AppCompatActivity {

    UserModel m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        m = (UserModel) getIntent().getSerializableExtra("Map");
    }
    public void blog(View V) {
        Intent intent = new Intent(UserMainActivity.this,UserActivity.class);
        intent.putExtra("Map", m);
        startActivity(intent);
    }
    public void map(View V) {}
    public void profile(View V) {}
    public void contactUs(View V) {}
}
