package com.sina.roomrx;

import android.app.Application;

import com.sina.roomrx.di.ApplicationComponent;
import com.sina.roomrx.di.ApplicationModule;
import com.sina.roomrx.di.DaggerApplicationComponent;

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}