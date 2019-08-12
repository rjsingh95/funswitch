package com.singh.ranjeet.myapplication;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


public class adapter extends RecyclerView
        .Adapter<adapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<model> list;
    Activity main;


    private static MyClickListener myClickListener;
    private Context context;
    private WebView mWebview;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
            {


        private final View mview;
        private final TextView by;

        private final TextView time2;
                private  ImageView checked;

                private  RadioButton radio;



        public DataObjectHolder(View itemView) {
            super(itemView);


            mview = itemView;

            by = (TextView) itemView.findViewById(R.id.details);
            time2 = (TextView) itemView.findViewById(R.id.time);
            radio = (RadioButton) itemView.findViewById(R.id.radio);
            checked = (ImageView) itemView.findViewById(R.id.checked);


        }


    }


    public adapter(Activity activity, Context context, ArrayList<model> list) {

        this.main = activity;
        this.context = context;
        this.list = list;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


        holder.by.setText(list.get(position).getDescription());
        if(list.get(position).getStatus().equals("PENDING")){
            holder.radio.setVisibility(View.VISIBLE);
            holder.checked.setVisibility(View.GONE);
        }
        if(list.get(position).getStatus().equals("COMPLETED")){
            holder.radio.setVisibility(View.GONE);
            holder.checked.setVisibility(View.VISIBLE);
        }

        String l = String.valueOf(list.get(position).getScheduledDate());

        String m=l.substring(4,6);
        Log.d("month",m);
        String  mon = null;
        if(m.equals("01"))
            mon="JAN";
        if(m.equals("02"))
            mon="FEB";
        if(m.equals("03"))
            mon="MAR";
        if(m.equals("04"))
            mon="APR";
        if(m.equals("05"))
            mon="MAY";
        if(m.equals("06"))
            mon="JUN";
        if(m.equals("07"))
            mon="JUL";
        if(m.equals("08"))
            mon="AUG";
        if(m.equals("09"))
            mon="SEP";
        if(m.equals("10"))
            mon="OCT";
        if(m.equals("11"))
            mon="NOV";
        if(m.equals("12"))
            mon="DEC";

        String n=l.substring(7,9);

    holder.    time2.setText(mon +"  "+n);


    }

    public void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }


}
