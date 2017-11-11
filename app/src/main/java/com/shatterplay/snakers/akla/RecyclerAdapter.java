package com.shatterplay.snakers.akla;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by snakers on 10/16/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Vh> {
    private final ArrayList<Meals> meals;
    private final Context context;
    private int price;

    private int plus = 0;

    RecyclerAdapter(ArrayList<Meals> meals, Context context, ArrayList<Integer> lista) {
        this.meals = meals;
        this.context = context;
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(final Vh holder, final int position) {

        Meals meal = meals.get(position);
        holder.bindData(meal, position);


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final ImageView imageView;
        private final EditText editText;

        public Vh(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            editText = (EditText) itemView.findViewById(R.id.editText2);

        }

        public void bindData(final Meals meal, final int position) {
            name.setText(meal.getName());

            price.setText(String.valueOf(meal.getPrice()));

            Picasso.with(context).load(meal.getImageUrl()).into(imageView);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //  onEditTextChanged.onTextChanged(position);


                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        //  onEditTextChanged.onTextChanged(meal,s.toString());)

                        if (s.toString().isEmpty()) {
                            meal.setQuantity(0);
                            meal.setQuantitiyprice(0);
                            FragmentUser.totalPrice = 0;
                        } else {
                            meal.setQuantitiyprice(Integer.valueOf(meals.get(position).getPrice()));
                            meal.setQuantity(Integer.valueOf(s.toString()));
                        }


                    } catch (Exception e) {

                    }
                }
            });

        }


    }



}

