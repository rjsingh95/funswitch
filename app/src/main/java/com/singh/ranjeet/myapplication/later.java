package com.singh.ranjeet.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class later extends Fragment {

    private RecyclerView listView;
    ArrayList<model> list=new ArrayList<>();

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView listView2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.later, container, false);

        listView = (RecyclerView) view. findViewById(R.id.listview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(mLayoutManager);




        new AsyncFetch().execute();
        return view;

    }


    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://www.mocky.io/v2/582695f5100000560464ca40.json");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
      Log.d("check",result);
            pdLoading.dismiss();
            List<model> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);


                    for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);



  long date = Long.parseLong(json_data.getString("scheduledDate"));


    String status = json_data.getString("status");

    Log.d("date1", String.valueOf(date));
    String description = json_data.getString("description");
    int id = json_data.getInt("id");
    model mod = new model(id,date,description,status);
    list.add(mod);









                }
                Collections.sort(list, new Comparator< model >() {
                    @Override public int compare(model p1, model p2) {
                        return (int) (p1.getScheduledDate()- p2.getScheduledDate());
                    }
                });

                adapter adapter=new adapter(getActivity()
                        ,getActivity(),list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



              //  mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);
              //  mAdapter = new AdapterFish(Today.this, data);
                //mRVFishPrice.setAdapter(mAdapter);
                //mRVFishPrice.setLayoutManager(new LinearLayoutManager(Today.this));

            } catch (JSONException e) {
            }

        }

    }
}