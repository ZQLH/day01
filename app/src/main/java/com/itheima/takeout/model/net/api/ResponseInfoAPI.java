package com.itheima.takeout.model.net.api;



import com.itheima.takeout.model.net.bean.ResponseInfo;
import com.itheima.takeout.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Teacher on 2016/9/2.
 */
public interface ResponseInfoAPI {

    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(@Query("username") String username, @Query("password") String password);

    Call<ResponseInfo> getHomeInfo();
}
