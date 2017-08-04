package com.miaochegu.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by wangxiaojian on 16/5/8.
 */
public class GsonUtils {
    private  static Gson mGson;

        public final  static  class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if (json.getAsString().equals("")){
                    return 0;
                }
            } catch (Exception ignore){
            }
            try {
                return json.getAsInt();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }





        @Override
        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    static {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .create();
    }


    /**
     * json转化为java实体对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return mGson.fromJson(json, clazz);
    }

    /**
     * json转化为java实体对象
     */
    public static <T> T fromJson(String json, Type type) {
        return mGson.fromJson(json, type);
    }


    /**
     * java 实体对象转化为json字符串
     */
    public synchronized static String toJson(Object entity) {
        return mGson.toJson(entity);
    }
}
