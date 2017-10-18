package com.b05studio.boxstore.service.network;

import com.b05studio.boxstore.model.BoxstoreUser;
import com.b05studio.boxstore.service.response.BoxtorePostResponse;
import com.b05studio.boxstore.service.response.CategoryGetResponse;
import com.b05studio.boxstore.service.response.UserGetResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by young on 2017-10-04.
 */

public interface BoxStoreHttpService {

    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/users")
    Call<BoxtorePostResponse> registerUser(@Body BoxstoreUser boxstoreUser);

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/users/{uid}")
    Call<UserGetResponse> getUserData(@Path("uid") String uid);
    //Call<BoxstoreUser> getUserData(@Query(value="uid", encoded = true) String uid);

    @Headers("Content-type: application/json; charset=utf-8")
    @FormUrlEncoded
    @GET("/categories/{keyword}")
    Call<CategoryGetResponse> getCategoryInfo(@Path(value="keyword", encoded = true) String keyword);


//    @Headers("Content-type: application/json; charset=utf-8")
//    @GET("/categories")


}
