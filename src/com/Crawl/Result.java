package com.Crawl;

import java.util.ArrayList;

/**
 * Created by lewis on 2016/12/9.
 *
 * 爬取的结果类
 */
public class Result {
    private String title;
    private String context;
    private ArrayList<String> urlLink ;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public ArrayList<String> getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(ArrayList<String> urlLink) {
        this.urlLink = urlLink;
    }
}
