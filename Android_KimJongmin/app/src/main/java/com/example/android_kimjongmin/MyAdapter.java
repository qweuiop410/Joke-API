package com.example.android_kimjongmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<JokeListVO.Joke> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_ID;
        private TextView textView_Categories;
        private TextView textView_Content;

        public MyViewHolder(View v) {
            super(v);

            textView_ID = v.findViewById(R.id.TextView_jokeID);
            textView_Categories = v.findViewById(R.id.TextView_jokeCategories);
            textView_Content = v.findViewById(R.id.TextView_jokeContent);
        }
    }

    public MyAdapter(ArrayList<JokeListVO.Joke> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JokeListVO.Joke jokeData = mDataset.get(position);

        holder.textView_ID.setText(jokeData.getId());
        holder.textView_Content.setText(jokeData.getJoke());
        //카테고리가 없으면 "none"으로 저장
        if(jokeData.getCategories().size() < 1)
            holder.textView_Categories.setText("none");
        else //카테고리가 있으면 첫번째 카테고리로 저장
            holder.textView_Categories.setText(jokeData.getCategories().get(0));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}