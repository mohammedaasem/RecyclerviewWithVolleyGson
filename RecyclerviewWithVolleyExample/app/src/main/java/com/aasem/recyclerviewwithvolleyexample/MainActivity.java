package com.aasem.recyclerviewwithvolleyexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aasem.recyclerviewwithvolleyexample.Adapter.FeatureAdapter;
import com.aasem.recyclerviewwithvolleyexample.Adapter.ListAdapter;
import com.aasem.recyclerviewwithvolleyexample.Data.FeatureData;
import com.aasem.recyclerviewwithvolleyexample.Data.ListData;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvList, rvFeature;
    List<ListData> listData;
    List<FeatureData> featureData;
    FeatureAdapter featureAdapter;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = findViewById(R.id.rv_list);
        rvFeature = findViewById(R.id.rv_feature);
        listData = new ArrayList<>();
        featureData = new ArrayList<>();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvFeature.setLayoutManager(new GridLayoutManager(this, 3));
        getListData();
        getFeatureData();
    }

    @Override
    public void onResume() {
        super.onResume();
       getListData();
       getFeatureData();
    }

    private void getListData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listData.clear();
                        try {
                            GsonBuilder builder = new GsonBuilder();
                            Gson mGson = builder.create();
                            listData = Arrays.asList(mGson.fromJson(response, ListData[].class));
                            listAdapter = new ListAdapter(MainActivity.this, listData);
                            rvList.setAdapter(listAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void getFeatureData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.FEATURE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        featureData.clear();
                        try {
                            GsonBuilder builder = new GsonBuilder();
                            Gson mGson = builder.create();
                            featureData = Arrays.asList(mGson.fromJson(response, FeatureData[].class));
                            featureAdapter = new FeatureAdapter(MainActivity.this, featureData);
                            rvFeature.setAdapter(featureAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}
