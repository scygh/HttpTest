package com.moredian.entrance.httptest;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 10:30
 */
public class HttpURLConnectionUtils {

    private static final String TAG = "HttpURLConnectionUtils";

    public static String getHttpURLConnnection(String url) {
        URL mUrl = null;
        HttpURLConnection connection = null;
        try {
            mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();//连接
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return is2String(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "getHttpURLConnnection error";
    }

    public static String postHttpURLConnnection(String url) {
        URL mUrl = null;
        HttpURLConnection connection = null;
        try {
            mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("POST");
            //connection.setRequestProperty("token", "");//添加header
            connection.setDoInput(true);//接受输入流
            connection.setDoOutput(true);//传递参数时需要开启
            connection.setUseCaches(false);//不使用缓存
            connection.connect();//连接
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return is2String(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "postHttpURLConnnection error";
    }

    /**
     * descirption: post 传递键值对参数
     */
    public static String postkeyvalueHttpURLConnnection(String url) {
        URL mUrl = null;
        HttpURLConnection connection = null;
        try {
            mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("POST");
            //connection.setRequestProperty("token", "");//添加header
            connection.setDoInput(true);//接受输入流
            connection.setDoOutput(true);//传递参数时需要开启
            connection.setUseCaches(false);//不使用缓存
            connection.connect();//连接

            String body = "userName=zhangsan&password=123456";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return is2String(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "postHttpURLConnnection error";
    }

    /**
     * descirption: post 传递json
     */
    public static String postjsonHttpURLConnnection(String url) {
        URL mUrl = null;
        HttpURLConnection connection = null;
        try {
            mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("POST");
            //connection.setRequestProperty("token", "");//添加header
            connection.setDoInput(true);//接受输入流
            connection.setDoOutput(true);//传递参数时需要开启
            connection.setUseCaches(false);//不使用缓存
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//设置参数类型是json格式
            connection.connect();//连接

            String body = "{userName:zhangsan,password:123456}";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return is2String(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "postHttpURLConnnection error";
    }

    /**
     * descirption: post 上传文件
     */
    public static String postFileHttpURLConnnection(String url) {
        URL mUrl = null;
        HttpURLConnection connection = null;
        try {
            mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("POST");
            //connection.setRequestProperty("token", "");//添加header
            connection.setDoInput(true);//接受输入流
            connection.setDoOutput(true);//传递参数时需要开启
            connection.setUseCaches(false);//不使用缓存
            connection.setRequestProperty("Content-Type", "file/*");//设置参数类型是file格式
            connection.connect();//连接

            OutputStream outputStream = connection.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream("file");
            int length = -1;
            byte[] b = new byte[1024];
            while ((length = fileInputStream.read(b)) != -1) {
                outputStream.write(b, 0, length);
            }
            outputStream.close();
            fileInputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return is2String(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "postFileHttpURLConnnection error";
    }

    /**
     * descirption: post 下载文件
     */
    public static void getFileHttpURLConnnection(String url) {
        URL mUrl = null;
        HttpURLConnection connection = null;
        try {
            mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();//连接

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                File dir = new File("fileDir");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, "filename");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buf = new byte[1024 * 8];
                int len = -1;
                while ((len = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, len);
                }
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * descirption: 把inputStream转成字符串
    */
    public static String is2String(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] car = new byte[1024];
            int length;
            while ((length = inputStream.read(car)) != -1) {
                byteArrayOutputStream.write(car, 0, length);
            }
            return byteArrayOutputStream.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "is2String error";
    }
}
