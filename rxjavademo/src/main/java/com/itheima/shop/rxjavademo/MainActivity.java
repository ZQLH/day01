package com.itheima.shop.rxjavademo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private String tag = "MainActivity";
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.image);
//        base();
//        just();
//        from();
        loadImage();
        concat();
        merge();


    }


    private void base() {
        //创建观察者Observer  /  Subscriber
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.i(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.i(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(tag, "Error!");
            }
        };


        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.i(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.i(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(tag, "Error!");
            }
        };


        //创建被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });


        //订阅，建立订阅关系
        observable.subscribe(observer);
    }

    private void just() {
        //创建被观察者，同时发布事件(传递一个字符串)，由被观察者决定什么时候触发事件
        Observable<String> observable = Observable.just("Hello");
        //创建观察者，决定事件触发时，执行的行为.
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                // 订阅者处理事件：打印字符串
                Log.i(tag, "=========from subscribe=====" + s);
            }
        };
        //建立订阅关系
        observable.subscribe(observer);

    }

    private void from() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        //创建被观察者，同时发布事件(传递一个字符串)，由被观察者决定什么时候触发事件
        Observable<String> observable = Observable.from(list);
        //创建观察者，决定事件触发时，执行的行为.
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                // 订阅者处理事件：打印字符串
                Log.i(tag, "=========from subscribe=====" + s);
            }
        };
        //建立订阅关系
        observable.subscribe(observer);

    }


    private void loadImage() {
        Observable
                // 传入要展示的图片的ID
                .just(R.mipmap.ic_launcher)
                // 指定下一个方法将在IO线程执行
                .subscribeOn(Schedulers.io())
                // 加载图片，由于是耗时的，所以应该运行在子线程
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer integer) {
                        Log.e(tag, "=====map thread======= " + Thread.currentThread().getName());
                        return getResources().getDrawable(integer);
                    }
                })
                // 指定订阅者将在主线程接收事件
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {
                        Log.e(tag, "=====subscribe thread======= " + Thread.currentThread().getName());
                        imageview.setImageDrawable(drawable);
                    }
                });

    }

    public void concat() {
        Observable<String> observable1 = Observable.just("Hello");
        Observable<String> observable2 = Observable.just("World");
        Observable<String> observable3 = Observable.just("rxjava");
        Observable.concat(observable1, observable2, observable3).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void merge() {
        Observable<String> observable1 = Observable.just("Hello");
        Observable<String> observable2 = Observable.just("World");
        Observable<String> observable3 = Observable.just("rxjava");
        Observable.merge(observable1, observable2, observable3).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
