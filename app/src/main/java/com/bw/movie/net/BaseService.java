package com.bw.movie.net;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：BaseService
 */
public interface BaseService {

    @GET
    @Headers({
            "ak:0110010010000",
            "Content-Type:application/x-www-form-urlencoded"
    })
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @POST
    @Headers({
            "ak:0110010010000",
            "Content-Type:application/x-www-form-urlencoded"
    })
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map);

    @Multipart
    @POST("/file/upload")
    Observable<ResponseBody> upload(@Part MultipartBody.Part part, @Query("uid") String uid);

    //get 把querymap 改成headermap注解  headget
    @GET
    Observable<ResponseBody> HeadGet(@Url String url, @HeaderMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    @Headers({
            "ak:0110010010000",
            "Content-Type:application/x-www-form-urlencoded"
    })
    Observable<ResponseBody> HeadPost(@HeaderMap Map<String, String> hmap, @Url String url, @FieldMap Map<String, String> fmap);

    @GET
    Observable<ResponseBody> HeadOrQueryGet(@Url String url, @HeaderMap Map<String, String> hap ,@QueryMap Map<String, String> qmap);

   @Headers({
           "ak:0110010010000",
            "Content-Type:application/x-www-form-urlencoded"
   })
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postRegister(@Url String url, @FieldMap Map<String, String> map);

}