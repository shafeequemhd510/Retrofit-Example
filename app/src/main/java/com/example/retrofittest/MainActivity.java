package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<Owner> users = new ArrayList<Owner>();
        recyclerView = findViewById(R.id.rv);
        Button btn_netWorkRequest=findViewById(R.id.button);
        btn_netWorkRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GitHubClient service = retrofit.create(GitHubClient.class);

                Call<List<Owner>> callUser = service.reposForUser("ptsdeen2007");

                callUser.enqueue(new Callback<List<Owner>>() {
                    @Override
                    public void onResponse(Call<List<Owner>> call, Response<List<Owner>> response) {
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(new UserAdapter(response.body()));

                    }

                    @Override
                    public void onFailure(Call<List<Owner>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




    }
}
