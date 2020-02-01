package com.sih.division;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class HospitalActivity extends AppCompatActivity {
    private ListView lv;
    SQLiteHelper sQLiteHelper;
    DiseaseCustomAdapter adapter;
    EditText inputSearch;
    HospitalModel map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        lv = (ListView) findViewById(R.id.list_view_disease);
        inputSearch = (EditText) findViewById(R.id.inputSearchDisease);
        sQLiteHelper = new SQLiteHelper(HospitalActivity.this);
        map = (HospitalModel) getIntent().getSerializableExtra("Map");
        Toast.makeText(HospitalActivity.this,map.getHid()+"", Toast.LENGTH_SHORT).show();
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                //adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });
    }
    private void getAllRecordsAndSetTOAdapter() {
        ArrayList<DiseaseModel> diseases = sQLiteHelper.getAllRecordsDisease(map);
        adapter = new DiseaseCustomAdapter(this,diseases ,sQLiteHelper);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllRecordsAndSetTOAdapter();
    }
    public void add_disease(View V) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(HospitalActivity.this);
        builder2.setTitle("Add Disease");
        builder2.setCancelable(true);
        View viewInflated = LayoutInflater.from(HospitalActivity.this).inflate(R.layout.new_disease,null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.new_dis);
        builder2.setView(viewInflated);
        builder2.setNegativeButton(
                "Add",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        DiseaseModel newDisease = new DiseaseModel();
                        String text = input.getText().toString();
                        newDisease.setHid(map.getHid());
                        newDisease.setDname(text);
                        newDisease.setPatcount("0");
                        sQLiteHelper.insertRecordDisease(newDisease);
                        getAllRecordsAndSetTOAdapter();
                    }
                });
        AlertDialog alertDialog = builder2.create();
        alertDialog.show();
    }
}
