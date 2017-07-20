package com.itheima.mvp.model.net;

import com.itheima.mvp.model.ResponseInfo;
import com.itheima.mvp.util.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Teacher on 2016/9/2.
 */
public interface ResponseInfoAPI {

    //login?username=xxx
    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(@Query("username") String username, @Query("password") String password, @Query("type") String type);
}
