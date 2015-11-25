package com.socks.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.socks.library.KLog;
import com.socks.rxandroiddemo.http.HttpManager;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private TextView tv_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_response = (TextView) findViewById(R.id.tv_response);
    }

    public void create(View v) {
        HttpManager.create().subscribe(user -> {
            tv_response.setText(user.toString());
        }, e -> {
            tv_response.setText(e.getMessage());
        });
    }

    public void map(View v) {
        HttpManager.map().subscribe(user -> {
            tv_response.setText(user.toString());
        }, e -> {
            tv_response.setText(e.getMessage());
        });
    }

    public void flatMap(View v) {
        clear();
        HttpManager.flatMap()
                .subscribe(user -> {
                    tv_response.setText(tv_response.getText() + "\n" + user.toString());
                }, e -> {
                    tv_response.setText(e.getMessage());
                });
    }

    public void filter(View view) {
        clear();
        HttpManager.flatMap()
                .filter(user -> user.getAge() <= 30)
                .subscribe(user -> {
                    tv_response.setText(tv_response.getText() + "\n" + user.toString());
                }, e -> {
                    tv_response.setText(e.getMessage());
                });
    }

    public void take(View view) {
        clear();
        HttpManager.flatMap()
                .take(3)
                .subscribe(user -> {
                    tv_response.setText(tv_response.getText() + "\n" + user.toString());
                }, e -> {
                    tv_response.setText(e.getMessage());
                });
    }

    public void doOnNext(View view) {
        clear();
        Subscription subscription = HttpManager.flatMap()
                .doOnNext(user -> KLog.d(user))
                .subscribe(user -> {
                    tv_response.setText(tv_response.getText() + "\n" + user.toString());
                }, e -> {
                    tv_response.setText(e.getMessage());
                });

        tv_response.postDelayed(() -> subscription.unsubscribe(), 1000);

    }

    private void clear() {
        tv_response.setText("");
    }
}