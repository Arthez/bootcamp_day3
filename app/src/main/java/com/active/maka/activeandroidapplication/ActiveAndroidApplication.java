package com.active.maka.activeandroidapplication;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.stetho.Stetho;

public class ActiveAndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDatabase();
        initStetho();
    }

    private void initDatabase() {
        ActiveAndroid.initialize(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
