package com.example.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class BaiHocAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<BaiHoc> baiHocList;

    public BaiHocAdapter(MainActivity context, int layout, List<BaiHoc> baiHocList) {
        this.context = context;
        this.layout = layout;
        this.baiHocList = baiHocList;
    }


    @Override
    public int getCount() {
        return baiHocList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tvTen;
        ImageView imgEdit,imgDlt;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView== null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.tvTen = (TextView) convertView.findViewById(R.id.tvTen);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.btnedit);
            holder.imgDlt = (ImageView) convertView.findViewById(R.id.btndelete);
            convertView.setTag(holder);





        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        BaiHoc baiHoc = baiHocList.get(position);
        holder.tvTen.setText(baiHoc.getTenComputer());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogUpdate(baiHoc.getTenComputer(),baiHoc.getId());



            }
        });
        holder.imgDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(baiHoc.getTenComputer(),baiHoc.getId());



            }
        });


        return convertView;
    }


}
