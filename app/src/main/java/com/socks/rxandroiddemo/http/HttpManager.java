package com.socks.rxandroiddemo.http;


import com.socks.rxandroiddemo.model.User;

import rx.Observable;

/**
 * 用于演示各种RxJava的操作符
 * Created by zhaokaiqiang on 15/11/25.
 */
public class HttpManager {

    public static Observable<User> create() {
        return HttpService.getUser();
    }

    public static Observable<User> map() {
        return HttpService.getUser().map(user -> {
            user.setName(user.getName() + "_map");
            return user;
        });
    }

    public static Observable<User> flatMap() {
        return HttpService.getUsers().flatMap(users -> Observable.from(users))
                .map(user -> {
                    user.setName(user.getName() + "_flatMap");
                    return user;
                });
    }

}
