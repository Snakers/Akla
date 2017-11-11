package com.shatterplay.snakers.akla;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by snakers on 10/21/2017.
 */

public class RecyclerMap extends RecyclerView.Adapter<RecyclerMap.Vh> {
    private LinearLayout linearLayout;
    private final Context context;
    private final ArrayList<Map> maps;

    RecyclerMap(Context context, ArrayList<Map> maps) {
        this.context = context;
    this.maps=maps;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_datajson, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(Vh holder, int position) {
        Map map = maps.get(position);

holder.onBindData(map);


    }

    @Override
    public int getItemCount() {
        return maps.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        private final TextView name;

        public Vh(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.ResMapName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layoutid);

        }
void onBindData(final Map map){
    name.setText(map.getName());
    linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,MapActivity.class);
   intent.putExtra("name",map.getName());

            intent.putExtra("lat",map.getLat());
            intent.putExtra("rate",map.getRate());
            intent.putExtra("lng",map.getLng());
            context.startActivity(intent);
        }
    });
}
    }
}
