package com.sih.division;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class DiseaseCustomAdapter extends BaseAdapter {
    public Context mContext;
    public List<DiseaseModel> mDiseaseList = null;
    private SQLiteHelper sqLiteHelper;


    public DiseaseCustomAdapter(Context mContext, List<DiseaseModel> mList, SQLiteHelper sqLiteHelper) {
        this.mContext = mContext;
        this.mDiseaseList = mList;
        this.sqLiteHelper = sqLiteHelper;
    }


    @Override
    public int getCount() {
        return mDiseaseList.size();
    }

    @Override public Object getItem(int position) { return mDiseaseList.get(position); }

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
            v = vi.inflate(R.layout.disease_list_item, null);
        }
        final DiseaseModel p = (DiseaseModel) getItem(position);
        if (p != null) {
                final TextView tt1,tt2;
                ImageView iv1;
                String s = "Disease";
                int counter = 0;
                tt1 = (TextView) v.findViewById(R.id.disease_name);
                Button incre = v.findViewById(R.id.incre);
                Button decre = v.findViewById(R.id.decre);
                tt2 = (TextView) v.findViewById(R.id.count);
                if (tt1 != null) {
                    tt1.setText(p.getDname());
                }
                if (tt2 != null) {
                    String c = counter+"";
                    tt2.setText(c);
                }
               incre.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String c ="1";
                       try {
                           tt2.setText(c);
                       }
                       catch (NullPointerException e)
                       {
                           System.out.println("Exception");
                       }
                   }
               });
                decre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String c ="2";
                        try {
                            tt2.setText(c);
                        }
                        catch (NullPointerException e)
                        {
                            System.out.println("Exception");
                        }
                    }
                });
        }
        return v;
    }

   /*public Filter getFilter() {
        return null;
    }*/
}
