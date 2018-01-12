package com.zb.project.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chengxs on 2016/10/27.
 */
public class RequestUtil {
    private static final Logger log = LoggerFactory.getLogger("RequestUtil");
    private static final String ENCODING_UTF_8 = "utf-8";

    public static String doHttpRequest(String url, String params) {
        HttpClient client = null;
        HttpPost httpPost = null;
        try {
            client = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(params, ENCODING_UTF_8);
            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), ENCODING_UTF_8);
            log.info("http result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        } finally {
            //关闭连接 ,释放资源
            if (httpPost != null) {
                try {
                    httpPost.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
            }
            if (client != null) {
                try {
                    client.getConnectionManager().shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
            }
        }
    }


    public static String doHttpPost(String urlStr, String params) {
        String backInfo = null;
        HttpURLConnection httpConn = null;
        try {
            String pathUrl = urlStr;
            // 建立连接
            URL url = new URL(pathUrl);
            httpConn = (HttpURLConnection) url.openConnection();
            // //设置连接属性
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(false);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法
            //String requestString = "客服端要以以流方式发送到服务端的数据...";
            String requestString = params;
            // 设置请求属性
            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            byte[] requestStringBytes = requestString.getBytes("utf-8");
            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpConn.setRequestProperty("Content-Type", "application/octet-stream");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.close();
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
                // 当正确响应时处理数据
                StringBuffer sb = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                // 处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
                backInfo = sb.toString();
            }
        } catch (Exception ex) {
            log.error("HTTP POST请求返回错误：", ex);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return backInfo;
    }
}
