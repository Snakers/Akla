package com.shatterplay.snakers.akla;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class FragmentUser extends Fragment {
    private static final String URL_ = "https://api.myjson.com/bins/6ufvt";
    private RecyclerView recyclerView;
    static int totalPrice;

    private ArrayList<Meals> meal;
    private RecyclerAdapter recyclerAdapter;
private ArrayList<Integer>lisa;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        Button button = (Button) view.findViewById(R.id.idorder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        lisa=new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.AklaMeal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        meal = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String name = jsonObject1.getString("name");
                        String url = jsonObject1.getString("img_url");
                        int price = jsonObject1.getInt("price");

                        meal.add(new Meals(name, price, url));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerAdapter = new RecyclerAdapter(meal, getActivity(),lisa);


                recyclerView.setAdapter(recyclerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        queue.add(stringRequest);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int sum;
                final int totally=0;
totalPrice=0;
                int jj;
                for (int j = 0; j < meal.size(); j++) {
                   jj=meal.get(j).getQuantitiyprice();
                   if(jj!=0){
                      sum= meal.get(j).getQuantity()*jj;
                      totalPrice+= sum;
                   }

                    Log.v("deeee", String.valueOf(totalPrice));


                }


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Your ORder Details");
                builder.setMessage(String.valueOf("Total : " + totalPrice));
                builder.setIcon(R.drawable.akla);
builder.setCancelable(false);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });

                builder.show();

            }
        });

        return view;

    }


}


