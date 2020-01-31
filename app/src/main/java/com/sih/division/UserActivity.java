package com.sih.division;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class UserActivity extends AppCompatActivity {
    private ListView lv;
    SQLiteHelper sQLiteHelper;
    BlogCustomAdapter adapter;
    EditText inputSearch;
    UserModel map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        lv = (ListView) findViewById(R.id.list_view_blog);
        inputSearch = (EditText) findViewById(R.id.inputSearchBlog);
        sQLiteHelper = new SQLiteHelper(UserActivity.this);
        map = (UserModel) getIntent().getSerializableExtra("Map");
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
        ArrayList<BlogModel> Blogs = sQLiteHelper.getAllRecordsBlogs();
        adapter = new BlogCustomAdapter(this,Blogs ,sQLiteHelper);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllRecordsAndSetTOAdapter();
    }
    public void add_blog(View V) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(UserActivity.this);
        builder2.setTitle("Add Blog");
        builder2.setCancelable(true);
        View viewInflated = LayoutInflater.from(UserActivity.this).inflate(R.layout.new_blog,null);
        final EditText title = (EditText) viewInflated.findViewById(R.id.new_title);
        final EditText abs = (EditText) viewInflated.findViewById(R.id.new_blog_ab);
        final EditText cont = (EditText) viewInflated.findViewById(R.id.new_blog_co);
        builder2.setView(viewInflated);
        builder2.setNegativeButton(
                "Add",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        BlogModel newBlog = new BlogModel();
                        newBlog.setTitle(title.getText().toString());
                        newBlog.setAbs(abs.getText().toString());
                        newBlog.setCont(cont.getText().toString());
                        newBlog.setUid(map.getUid());
                        sQLiteHelper.insertRecordBlog(newBlog);
                    }
                });
        AlertDialog alertDialog = builder2.create();
        alertDialog.show();
    }
}
