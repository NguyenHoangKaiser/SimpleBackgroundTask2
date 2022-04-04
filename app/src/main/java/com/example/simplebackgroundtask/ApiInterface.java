package com.example.simplebackgroundtask;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("users")
    Call<ArrayList<User>> getAllUsers();

    @GET("users/{id}")
    Call<User> getUsersByID(@Path("id") int id);

    String token = "f308cb73468306aab720148dd4b0e590a25edbeacea593e0fcb37e7a3934ffe7";
    @POST("users?access-token=" + token)
    Call<User> addUser(@Body() User user);

    @PUT("users?access-token=" + token)
    Call<User> updateUser(@Body() User user);

    @DELETE("users/{id}?access-token=" + token)
    Call deleteUser(@Path("id") int id);

}


