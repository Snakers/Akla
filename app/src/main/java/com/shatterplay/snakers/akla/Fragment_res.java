package com.shatterplay.snakers.akla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by snakers on 10/16/2017.
 */

public class Fragment_res extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Map>mapList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.fragament_res,container,false);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        recyclerView =(RecyclerView)view.findViewById(R.id.idAklaMeals);
        mapList= new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        String URL = "https://api.myjson.com/bins/frxyh";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1= jsonArray.getJSONObject(i);
                        String name = jsonObject1.getString("name");
    double rate = jsonObject1.getDouble("rate");
                        double lat = jsonObject1.getDouble("lat");
                        double lng = jsonObject1.getDouble("lng");
mapList.add(new Map(name,rate,lat,lng));
                    }
                    RecyclerMap recyclerAdapter = new RecyclerMap(getActivity(),mapList);
                    recyclerView.setAdapter(recyclerAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

        return view;

    }
}
