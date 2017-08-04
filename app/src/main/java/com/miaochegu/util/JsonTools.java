package com.miaochegu.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 专门解析json字符串的工具类，提供了四个方�?
 *
 * @author user
 */
public class JsonTools {


    public static String toJson(Map<String, String> param) {
        String result = "";
        if (param != null) {
            JSONObject jsonObject = new JSONObject();
            Set<String> keyset = param.keySet();
            for (Iterator<String> iterator = keyset.iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                String value = param.get(key);
                try {
                    jsonObject.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            result = jsonObject.toString();
        }
        return result;

    }

    /**
     * 把一个json字符串解析成List<Map<String, String>>
     *
     * @param string
     * @return
     */
    public static List<Map<String, String>> toList(String string) {
        if (string != null) {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Map<String, String> map = new HashMap<String, String>();
                    Iterator<?> iterator = jsonObject.keys();
                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();
                        map.put(key.trim(), jsonObject.getString(key).trim());
                    }
                    list.add(map);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
        return null;

    }

    public static List<String> toListString(String json) throws JSONException {
        if (json != null) {
            List<String> list = new ArrayList<String>();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                String string = jsonArray.getString(i);

                list.add(string);
            }

            return list;
        }
        return null;

    }

    /**
     * 把一个一层json字符串解析成map
     *
     * @param string
     * @return
     * @throws JSONException
     */
    public static Map<String, String> toMap(String string) throws JSONException {
        if (string != null && !string.equals("")) {
            Map<String, String> map = new TreeMap<String, String>();
            JSONObject jsonObject = new JSONObject(string);
            Iterator<?> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                map.put(key.trim(), jsonObject.getString(key).trim());
            }
            return map;
        }
        return null;

    }

    /**
     * 如果json字符串有两层，则使用此方法直接可以解析json字符串为javabean，切记类的属性名于返回的json字符串的键名要完全一�?
     *
     * @param jsonString
     * @param cls
     * @param listOrKey
     * @return
     * @throws JSONException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> toListBean(String jsonString, Class<T> cls,
                                         String listOrKey) throws JSONException, InstantiationException, IllegalAccessException {
        jsonString = jsonString.replace("{}","null");
        JSONObject jsonObject;
        List<T> list;
        jsonObject = new JSONObject(jsonString);
        list = new ArrayList<T>();
        JSONArray jsonArray = (JSONArray) jsonObject.get(listOrKey);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonO = jsonArray.getJSONObject(i);
            Field[] fields = cls.getDeclaredFields();
            T bean = cls.newInstance();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                if (jsonO.has(fields[j].getName())) {
                    fields[j].set(bean,
                            jsonO.getString(fields[j].getName()));
                }
            }
            list.add(bean);
        }

        return list;
    }

    public static <T> List<T> toListBeanNoKey(String jsonString, Class<T> cls) throws JSONException, InstantiationException, IllegalAccessException {
        List<T> list;
        list = new ArrayList<T>();
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonO = jsonArray.getJSONObject(i);
//				Field[] fields = cls.getDeclaredFields();
            T bean = GsonUtils.fromJson(jsonO.toString(), cls);
//				for (int j = 0; j < fields.length; j++) {
//					fields[j].setAccessible(true);
//					if (jsonO.has(fields[j].getName())) {
//						System.out.println("-----fields[j].getType()-----"+fields[j].getType());
//						if (fields[j].getType().equals("java.lang.String")){
//							fields[j].set(bean,
//									jsonO.getString(fields[j].getName()));
//						}else if (fields[j].getType().equals("java.lang.Integer")){
//							fields[j].set(bean,
//									jsonO.getInt(fields[j].getName()));
//						}else if (fields[j].getType().equals("java.lang.Long")){
//							fields[j].set(bean,
//									jsonO.getLong(fields[j].getName()));
//						}
//
//					}
//				}
            list.add(bean);
        }

        return list;
    }

    /**
     * 如果json字符串有�?层，则使用此方法直接可以解析json字符串为javabean，切记类的属性名于返回的json字符串的键名要完全一�?
     *
     * @param jsonString
     * @param cls
     * @return
     * @throws JSONException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T toSingleBean(String jsonString, Class<T> cls) throws JSONException, InstantiationException, IllegalAccessException {
        if (jsonString == null || jsonString.equals("")) {
            return null;
        }
        JSONObject jsonObject;
        T t = null;
        jsonObject = new JSONObject(jsonString);
        t = cls.newInstance();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (jsonObject.has(fields[i].getName())) {
                fields[i].set(t, jsonObject.getString(fields[i].getName()));
            }
        }
        return t;
    }

    public static <T> T toSingleBean(String jsonString, Class<T> cls, String key)
            throws JSONException, InstantiationException, IllegalAccessException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        Map<String, String> map = toMap(jsonString);
        if (map != null && map.containsKey(key)) {
            return toSingleBean(map.get(key), cls);
        }
        return null;
    }

}
