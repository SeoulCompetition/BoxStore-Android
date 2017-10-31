package com.b05studio.boxstore.service.network;

import com.b05studio.boxstore.model.BoxstoreUser;
import com.b05studio.boxstore.model.Keyword;
import com.b05studio.boxstore.model.Product;
import com.b05studio.boxstore.service.response.BoxtorePostResponse;
import com.b05studio.boxstore.service.response.CategoryGetResponse;
import com.b05studio.boxstore.service.response.KeywordGetResponse;
import com.b05studio.boxstore.service.response.RankStationGetResponse;
import com.b05studio.boxstore.service.response.StuffGetResponse;
import com.b05studio.boxstore.service.response.UserGetResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @GET("/categories/{keyword}")
    Call<CategoryGetResponse> getCategoryInfo(@Path(value="keyword", encoded = true) String keyword);

    @Headers("Content-type: application/json; charset=utf-8")
    @POST("/stuffs")
    Call<BoxtorePostResponse> uplodeProduct(@Body Product product);

    @Multipart
    @POST("/stuffs/images/{stuffId}")
    Call<BoxtorePostResponse> uplodeProductImages(@Part List<MultipartBody.Part> files, @Path("stuffId") String stuffId);

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("/station/popular")
    Call<RankStationGetResponse> getStaionRankList();

    // 역 이름으로 최근 상품 검색
    @GET("/stuffs/lately/{stationName}")
    Call<StuffGetResponse> getStuffListByStationName(@Path(value="stationName", encoded = true) String stationName);

    @GET("/stuffs/search/{keyword}")
    Call<StuffGetResponse> getStuffListByKeywordName(@Path(value="keyword", encoded = true) String keyword);

    @GET("/stuffs/list/{categoryName}")
    Call<StuffGetResponse> getStuffListByCategoryName(@Path(value="categoryName", encoded = true) String categoryName);

    // 최근 등록된 상품 열람
    @GET("/stuffs")
    Call<StuffGetResponse> getLatelyProductList();

//     키워드등록
     @POST("/users/keywords")
     Call<BoxtorePostResponse> postKeyword(@Body Keyword keyword);

    // 키워드가져오기
    @GET("/users/{uid}/keywords")
    Call<KeywordGetResponse> getKeywordList(@Path(value="uid", encoded = true) String uid);

    // 경찰청 사기꾼검색
    @GET("/cheat/{uid}")
    Call<BoxtorePostResponse> checkCriminal(@Path(value="uid", encoded = true) String uid);

    // 지도가져오기



//    @Headers("Content-type: application/json; charset=utf-8")
//    @GET("/categories")


}
