package com.itheima.mvp.presenter;

import com.itheima.mvp.MainActivity;
import com.itheima.mvp.UserLoginNet;
import com.itheima.mvp.model.ResponseInfo;
import com.itheima.mvp.model.User;
import com.itheima.mvp.model.net.ResponseInfoAPI;
import com.itheima.mvp.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 与登陆相关的业务处理
 */
public class MainActivityPresenter {
    private MainActivity activity;

    public MainActivityPresenter(MainActivity activity) {
        this.activity = activity;
    }


    public void login(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constant.BASEURL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ResponseInfoAPI responseInfoAPI = retrofit.create(ResponseInfoAPI.class);
        Call<ResponseInfo> call = responseInfoAPI.login(username, password, "1");

        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                ResponseInfo body = response.body();
                if("0".equals(body.getCode())) {
                    // 登陆成功
                    activity.success();
                }

            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                //登陆失败
                activity.failed();
            }
        });

    }



//    public void a(){
//        //rxjava  rxandroid
//        Observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())//观察者所在的线程
//                .subscribe(new Observer<ResponseInfo>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseInfo responseInfo) {
//                        if ("0".equals(responseInfo.getCode())) {
////                    // 登陆成功
//                            activity.success();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //登陆失败
//                        activity.failed();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }


    public void login1(String username, String password) {

        final User user = new User();
        user.username = username;
        user.password = password;
        new Thread() {
            @Override
            public void run() {
                UserLoginNet net = new UserLoginNet();

                if (net.sendUserLoginInfo(user)) {
                    // 登陆成功
                    activity.success();
                } else {
                    //登陆失败
                    activity.failed();
                }

            }
        }.start();
    }
}
