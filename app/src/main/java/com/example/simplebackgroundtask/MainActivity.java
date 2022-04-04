package com.example.simplebackgroundtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView mRecyclerView;
//    private UserListAdapter mAdapter;
//    ArrayList<User> mWordList = new ArrayList<>();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mRecyclerView = findViewById(R.id.recyclerview);
//        mAdapter = new UserListAdapter(this, mWordList);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ApiClient.getAPI().getAllUsers().enqueue(new Callback<ArrayList<User>>() {
//            @Override
//            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
//                mWordList.addAll(response.body());
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
//
//            }
//        });
//    }

    private RecyclerView recyclerView;
    private UserListAdapter userListAdapter;
    private ArrayList<User> userList = new ArrayList<>();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        recyclerView = findViewById(R.id.recyclerview);
        userListAdapter = new UserListAdapter(this, userList);
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiClient.getAPI().getAllUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userList.addAll(response.body());
                userListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserDetail(view, 0);
            }
        });
    }

    public static void showUserDetail(View view, int userId) {
        Context context = view.getContext();
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }
}

