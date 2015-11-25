package com.socks.rxandroiddemo.http;

import com.socks.okhttp.plus.OkHttpProxy;
import com.socks.okhttp.plus.callback.OkCallback;
import com.socks.okhttp.plus.parser.OkJsonParser;
import com.socks.rxandroiddemo.model.User;

import java.util.ArrayList;

import rx.Observable;

/**
 * 真正的网络请求类，可以生成Observable<User>对象，如果你的API是REST风格，推荐使用Retrofit。
 * <p>
 * 因为Retrofit使用非常简单，资料很多，这里不再赘述，本Demo使用OkHttp作为网络请求的实现，如果你看懂了，那么其他任何Http框架就都可以使用了
 * <p>
 * Created by zhaokaiqiang on 15/11/25.
 */
public class HttpService implements TestWebsite {

    public static Observable<User> getUser() {

        return Observable.create((Observable.OnSubscribe<User>) subscriber ->
                OkHttpProxy.get().url(URL_USER).execute(new OkCallback<User>(new OkJsonParser<User>() {
                }) {
                    @Override
                    public void onSuccess(int code, User user) {
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        subscriber.onError(e);
                    }
                }));
    }


    public static Observable<ArrayList<User>> getUsers() {

        return Observable.create((Observable.OnSubscribe<ArrayList<User>>) subscriber ->
                OkHttpProxy.get().url(URL_USERS).execute(new OkCallback<ArrayList<User>>(new OkJsonParser<ArrayList<User>>() {
                }) {
                    @Override
                    public void onSuccess(int code, ArrayList<User> users) {
                        subscriber.onNext(users);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        subscriber.onError(e);
                    }
                }));
    }

}
