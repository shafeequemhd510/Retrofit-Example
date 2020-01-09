package com.example.retrofittest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<Owner>> reposForUser(
            @Path("user") String user
    );
}