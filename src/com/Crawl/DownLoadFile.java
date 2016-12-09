package com.Crawl;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

/**
 * Created by lewis on 2016/12/9.
 *
 * 主要提供文件的下载，html源文件，图片等文件。
 */
public class DownLoadFile {

    public static CloseableHttpClient httpClient = HttpClients.custom().build();

    public static String downloadHtml(String url) {

        CloseableHttpResponse response;
        BufferedReader br=null;
        HttpGet httpGet = new HttpGet(url);

        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStreamReader isr = new InputStreamReader(entity.getContent(),"utf-8");

            StringBuilder stringBuilder =new StringBuilder();
            br =new BufferedReader(isr);
            String line;
            while((line=br.readLine())!=null){
                stringBuilder.append(line+'\n');
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
