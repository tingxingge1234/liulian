package com.liulian.chatuidemo;

import android.app.Application;
import android.content.Context;

import com.liulian.chatuidemo.bean.LoginUser;
import com.liulian.chatuidemo.data.RequestManager;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class LiuLian extends Application{
    public static LiuLian instance;
    public static Context applicationContext;
    LoginUser loginUser;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = this;
        RequestManager.init(applicationContext);

    }
    public static LiuLian getInstance() {
        return instance;
    }

}
