package com.example.android_kimjongmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // UI
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // API
    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;
    private Call<JokeListVO> mCallMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView 설정
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // get API
        setRetrofitInit();
        callMovieList();

        // SwipeRefreshLayout 설정
        mSwipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout_background);
        // SwipeRefreshLayout을 당겼을때 동작
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRetrofitInit();
                callMovieList();

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // Retrofit 설정
    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com/jokes/random/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
    }

    // Retrofit 가져오기
    private void callMovieList() {
        mCallMovieList = mRetrofitAPI.getJoke();
        mCallMovieList.enqueue(new Callback<JokeListVO>() {
            @Override
            public void onResponse(Call<JokeListVO> call, Response<JokeListVO> response) {
                JokeListVO result = response.body();
                ArrayList<JokeListVO.Joke> jokeDataList = new ArrayList<JokeListVO.Joke>();

                for(JokeListVO.Joke jokeData :result.getValue())
                {
                    jokeDataList.add(jokeData);
                }

                //RecyclerView 값 전달
                mAdapter = new MyAdapter(jokeDataList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<JokeListVO> call, Throwable t) {
                t.printStackTrace();
                Log.d("     onFailure  : ", t.getMessage());
            }
        });
    }
}
