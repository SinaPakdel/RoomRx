package com.sina.roomrx.di;

import com.sina.roomrx.ui.MainActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}