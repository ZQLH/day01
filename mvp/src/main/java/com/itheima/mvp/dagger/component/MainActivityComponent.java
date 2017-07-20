package com.itheima.mvp.dagger.component;

import com.itheima.mvp.MainActivity;
import com.itheima.mvp.dagger.module.MainActivityModule;

import dagger.Component;

/**
 * 链接工具
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void in(MainActivity activity);
}
