package com.miaochegu.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 文件缓存：资源缓存bean类，必须实现序列化
 */
public class FileCache {

    //获取默认存储路径无论有无挂载SD卡只选取他默认的路径
    public static String DEFAULT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static String YUNBIAN = "/miaochegu";//默认根目录名称

    public static String YUNBIAN_LOADING = "/loading";//登录信息缓存
    public static String YUNBIAN_SLIDE = "/slide";//幻灯片模板缓存
    public static String YUNBIAN_USER = "/user";//用户个人信息
    public static String YUNBIAN_PIC = "/InFoYunBianPic";

    /**
     * 设置根目录缓存路径，并返回
     */
    public static String setRootDirectory() {
        String infomedia = FileCache.setPath(FileCache.DEFAULT_PATH + YUNBIAN);//缓存根目录
        return infomedia; //默认返回根目录
    }

    /**
     * 设置文件缓存根目录
     *
     * @param patchName 文件缓存根目录
     */
    public static String setPath(String patchName) {
        File file = new File(patchName);
        if (file.exists() || file.isDirectory()) {
            return file.getPath();//如果文件存在，直接返回他的路径
        } else {
            boolean mkdir = file.mkdirs(); //不存在创建文件目录
        }
        return file.getPath();
    }

    /**
     * 缓存路径
     */
    public static void save(Object obj, String path, Context context) {
        try {
            File f = new File(path);
            if (!f.exists()) {//不存在创建
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);//创建输出流对象
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);//写入资源
            oos.flush();//关闭资源
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出缓存：单个目录下 指定的缓存取出
     */
    public static Object load(String path, Context context) {
        Object obj = null;
        File file = new File(path);
        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
                obj = ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 删除临时缓存
     */
    public static void remove(String path, Context context) {

        File file = new File(path);

        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                remove(childFiles[i].getPath(), context);
            }
            file.delete();
        }
    }

    public static File[] getpatch(String path) {

        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            return files;
        }
        return null;
    }


    public static void saveOBJ(Serializable obj, String fileName) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + YUNBIAN);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file, fileName);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }

    }

    public static Object readObjectFromFile(String name) {
        Object temp = null;
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + YUNBIAN + "/" + name);
        if (!file.exists()) {
            return null;
        }
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public static ArrayList getLiveList() {

        return (ArrayList) readObjectFromFile("livelist.data");
    }

    public static void saveLiveList(ArrayList datas) {
        saveOBJ(datas, "livelist.data");
    }

    public static void saveBroadcastTitleList(ArrayList datas) {
        saveOBJ(datas, "broadcastlist.data");
    }

    public static ArrayList readBroadcastTitleList() {
        return (ArrayList) readObjectFromFile("broadcastlist.data");
    }

}