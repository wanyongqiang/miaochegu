package com.miaochegu;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BinaryHB on 16/9/13.
 */
public class GettingStartedApp extends Application {

    public static Context context;  //全局的Context

    public String tempStr = "";

    public boolean isLogin = false;
    /**
     * 单例对象实例
     */
    private static GettingStartedApp instance = null;

    public static String PICPATH = Environment.getExternalStorageDirectory()
            + "/caoping/pic/";
    public static String FILEPATH = Environment.getExternalStorageDirectory()
            + "/caoping/cache/";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AVOSCloud.initialize(this, "q0nq7FjwkIbpppCaUbu7oj6h-gzGzoHsz", "kthi55WJGBnw07yypGqGPove");
        AVOSCloud.setDebugLogEnabled(true);
        AVAnalytics.enableCrashReport(this, true);

    }

    public static GettingStartedApp getInstance() {
        if (instance == null) {
            instance = new GettingStartedApp();
        }
        return instance;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getTempStr() {
        return tempStr;
    }

    public void setTempStr(String tempStr) {
        this.tempStr = tempStr;
    }

    /**
     * 图片名称
     *
     * @return
     */
    public static String imageName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        String time = format.format(date);
        String imageName = "IMG_" + time + ".jpg";
        return imageName;
    }
}
