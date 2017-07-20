package com.itheima.mvp.dagger.module;

import com.itheima.mvp.MainActivity;

import dagger.Module;

/**
 * 创建MainActivityPresenter实例的容器
 */
@Module()
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }
}
