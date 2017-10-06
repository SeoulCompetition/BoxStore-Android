package com.b05studio.boxstore.service.network;

import com.b05studio.boxstore.model.BoxstoreUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by young on 2017-10-04.
 */

public interface BoxStoreHttpService {

    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/users")
    Call<String> registerUser(@Body BoxstoreUser boxstoreUser);

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/users")
    Call<BoxstoreUser> getUserData(@Query(value="uid", encoded = true) String uid);

}
