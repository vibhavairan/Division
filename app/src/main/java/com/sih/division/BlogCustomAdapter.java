package com.sih.division;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class BlogCustomAdapter extends BaseAdapter {
    public Context mContext;
    public List<BlogModel> mBlogList = null;
    private SQLiteHelper sqLiteHelper;

    public BlogCustomAdapter(Context mContext, List<BlogModel> mList, SQLiteHelper sqLiteHelper) {
        this.mContext = mContext;
        this.mBlogList = mList;
        this.sqLiteHelper = sqLiteHelper;
    }
    @Override
    public int getCount() {
        return mBlogList.size();
    }

    @Override public Object getItem(int position) { return mBlogList.get(position); }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(R.layout.blog_list_item, null);
        }
        final BlogModel p = (BlogModel) getItem(position);
        if (p != null) {
            TextView tt1;
            TextView tt2;
            TextView tt3;
            ImageView iv;
            tt1 = (TextView) v.findViewById(R.id.nameUser);
            tt2 = (TextView) v.findViewById(R.id.blogTitle);
            tt3 = (TextView) v.findViewById(R.id.blog);
            iv = v.findViewById(R.id.imageUser);
            Button but;
            but = v.findViewById(R.id.readMore);
            if (iv != null) {
                byte[] data = sqLiteHelper.getStudentPhoto(p);
                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                iv.setImageBitmap(bmp);
            }
            if (tt1 != null) {
                tt1.setText(sqLiteHelper.getUserName(p));
            }
            if (tt2 != null) {
                tt2.setText(p.getTitle());
            }
            if (tt3 != null) {
                tt3.setText(p.getAbs());
            }
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return v;
    }
}
