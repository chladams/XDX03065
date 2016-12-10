package com.Crawl;

import java.util.concurrent.Callable;

/**
 * Created by lewis on 2016/12/11.
 *          爬虫类
 */
public class Crawler implements Callable{
    private String links = null;

    @Override
    public Object call() throws Exception {
        return HtmlParserTool.getResult(links);
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }


}
