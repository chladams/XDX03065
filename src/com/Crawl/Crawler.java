package com.Crawl;

import java.util.concurrent.Callable;

/**
 * Created by lewis on 2016/12/11.
 *          爬虫类
 */
public class Crawler implements Callable<Result>{
    private String url = null;

    public Crawler(String url) {
        this.url = url;
    }

    @Override
    public Result call() throws Exception {
        return HtmlParserTool.getResult(url);
    }

    public String getLinks() {
        return url;
    }

    public void setLinks(String links) {
        this.url = links;
    }


}
