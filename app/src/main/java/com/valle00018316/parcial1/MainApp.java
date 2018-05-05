package com.valle00018316.parcial1;

import android.app.Application;
import android.content.Context;

import com.valle00018316.parcial1.Helper.LocaleHelper;

public class MainApp extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
