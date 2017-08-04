package com.miaochegu.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.miaochegu.GettingStartedApp;

/**
 * 存储各种数据到sharepreference的的类，存储方法以save开头，取数据方法以load字段开头
 *
 * @author user
 *
 */
public class SharePCach {
    private static final String SHARENAME = "APPcaoping";

    /**
     * 删除缓存中的数据
     *
     * @param key
     * @return
     */
    public static boolean removeShareCach(String key) {
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    /**
     * 往sharepreference里面存储字符串数据
     * 
     * @param key
     *            存储的键名
     * @param content
     *            存储的内容
     * @return
     */
    public static boolean saveStringCach(String key, String content) {
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, content);
        return editor.commit();
    }

    /**
     * 从sharepreference里面取出字符串数据
     * 
     * @param key
     *            取数据键名
     * @return
     */
    public static String loadStringCach(String key) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    /**
     * 从sharepreference里面取出字符串数据
     *
     * @param key
     *            取数据键名
     * @return
     */
    public static String loadStringCach(String key, String defaultValue) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 往sharepreference里面存储整型数据
     * 
     * @param key
     *            存储的键名
     * @param content
     *            存储的内容
     * @return
     */
    public static boolean saveIntCach(String key, int content) {
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(key, content);
        return editor.commit();
    }

    /**
     * 从sharepreference里面取出整型数据
     * 
     * @param key
     *            取数据键名
     * @return
     */
    public static int loadIntCach(String key) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * 从sharepreference里面取出整型数据
     *
     * @param key
     *            取数据键名
     * @return
     */
    public static int loadIntCach(String key, int defaultValue) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 往sharepreference里面存储Boolean数据
     * 
     * @param key
     *            存储的键名
     * @param content
     *            存储的内容
     * @return
     */
    public static boolean saveBooleanCach(String key, Boolean content) {
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(key, content);
        return editor.commit();
    }

    /**
     * 从sharepreference里面取出Boolean数据
     * 
     * @param key
     *            取数据键名
     * @return
     */
    public static Boolean loadBooleanCach(String key) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 从sharepreference里面取出Boolean数据
     *
     * @param key
     *            取数据键名
     * @return
     */
    public static Boolean loadBooleanCach(String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 往sharepreference里面存储float数据
     * 
     * @param key
     *            存储的键名
     * @param content
     *            存储的内容
     * @return
     */
    public static boolean saveFloatCach(String key, Float content) {
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putFloat(key, content);
        return editor.commit();
    }

    /**
     * 从sharepreference里面取出float数据
     * 
     * @param key
     *            取数据键名
     * @return
     */
    public static Float loadFloatCach(String key) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }

    public static Float loadFloatCach(String key, float def) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, def);
    }

    /**
     * 往sharepreference里面存储Long数据
     * 
     * @param key
     *            存储的键名
     * @param content
     *            存储的内容
     * @return
     */
    public static boolean saveLongCach(String key, Long content) {
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putLong(key, content);
        return editor.commit();
    }

    /**
     * 从sharepreference里面取出Long数据
     * 
     * @param key
     *            取数据键名
     * @return
     */
    public static Long loadLongCach(String key) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * 从sharepreference里面取出Long数据
     *
     * @param key
     *            取数据键名
     * @return
     */
    public static Long loadLongCach(String key, long def) {
        SharedPreferences sharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getLong(key, def);
    }

    private static String Bean2String(LocalStorageData data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private static LocalStorageData String2Bean(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, LocalStorageData.class);
    }

    public static class LocalStorageData {
        public String time;
        public String data;
    }

    public static LocalStorageData get(String key) {
        String dataString = SharePCach.loadStringCach(key);
        LocalStorageData localStorageData = new LocalStorageData();
        if (TextUtils.isEmpty(dataString)) {
            localStorageData.time = "";
            localStorageData.data = "";
        } else {
            localStorageData = String2Bean(dataString);
        }
        return localStorageData;
    }

    public static boolean saveStringCachWithDate(String key, String value) {
        LocalStorageData localStorageData = new LocalStorageData();
        localStorageData.data = value;
        localStorageData.time = System.currentTimeMillis()+"";
        String data = Bean2String(localStorageData);
        SharedPreferences mySharedPreferences = GettingStartedApp.getInstance()
                .getSharedPreferences(SHARENAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, data);
        return editor.commit();
    }


    public static boolean isEnglishBook = false;
    public static boolean isEnglishBook(){
        return isEnglishBook;
    }
}
