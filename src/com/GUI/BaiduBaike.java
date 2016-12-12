package com.GUI;

import com.Crawl.ManagerCrawler;

import javax.swing.*;

/**
 * Created by lewis on 2016/12/9.
 *
 * 作为数据显示的页面
 */
public class BaiduBaike extends JFrame{
    public ManagerCrawler managerCrawler = new ManagerCrawler();

    public void launch(String url){
        managerCrawler.initial(url).start();
    }



}
