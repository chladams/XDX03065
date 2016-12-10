package com.Crawl;

import com.DAO.DataCRUD;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lewis on 2016/12/9.
 *
 * 爬虫的主要调度类
 */
public class ManngerClawler {
    public BloomFilter unique = new BloomFilter();                                    //为超链接去重
    public ExecutorService crwalPool = Executors.newFixedThreadPool(10);   //线程池维护页面的抓取
    public Queue<Result> results= new LinkedList<Result>();                           //结果队列
    public Result queueTop = new Result();                                            //队首
    public Crawler crawler = new Crawler();                                           //爬虫类

    public void initial(String url){                                                //由一个url初始化爬虫
        results .add(HtmlParserTool.getResult(url));
        unique.add(url);
        start();
    }

    public void start(){
        while(results.size()>0){
            queueTop = results.poll();                                              //取出队头，并且pop
            DataCRUD.persistent(queueTop);                                          //持久化到数据库

            for(String link:queueTop.getUrlLink()){
                unique.add(link);                                                   //添加到过滤器，去重

            }

        }
    }

}
