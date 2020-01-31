package com.sih.division;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class DiseaseCustomAdapter extends BaseAdapter {
    public Context mContext;
    public List<DiseaseModel> mDiseaseList = null;
    private SQLiteHelper sqLiteHelper;
    ElegantNumberButton but;
    int count=0;


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
                final TextView tt1;
                tt1 = (TextView) v.findViewById(R.id.disease_name);
                but = v.findViewById(R.id.elegant);
                but.setNumber(p.getPatcount());
                if (tt1 != null) {
                    tt1.setText(p.getDname());
                }
               but.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                   @Override
                   public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                       String num = newValue+"";
                       p.setPatcount(num);
                       sqLiteHelper.updateVac(p);
                   }
               });
        }
        return v;
    }

   /*public Filter getFilter() {
        return null;

    }*/
}
