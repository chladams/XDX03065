package com.Crawl;

import com.DAO.DataCRUD;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

import static java.lang.System.out;

/**
 * Created by lewis on 2016/12/9.
 *
 * 爬虫的主要调度类
 */
public class ManagerCrawler {
    public static BloomFilter unique = new BloomFilter();                                    //为超链接去重
    public static ExecutorService crawlPool = Executors.newFixedThreadPool(50);   //线程池维护页面的抓取
    public static Queue<Result> results= new LinkedList<>();                                //结果队列
    public static Result queueTop = new Result();                                            //队首
    public static Crawler crawler = null;                                                   //爬虫类
    public static int count = 0;


    public ManagerCrawler initial(String url){                                                        //由一个url初始化爬虫
        results.add(HtmlParserTool.getResult(url));
        unique.add(url);
        return this;
    }



    public void start(){

        while(results.size()>0 && count<=10){
            queueTop = results.poll();                                              //取出队头，并且pop
            DataCRUD.persistent(queueTop);                                          //持久化到数据库
            ArrayList< FutureTask<Result> > tasks = new ArrayList<>();               //为每个链接分出一个线程去运行

            for(String link:queueTop.getUrlLink()) {                                //扩展队头
                unique.add(link);                                                   //添加到过滤器，去重
                crawler = new Crawler(link);                                       //实例化一个爬虫类
                tasks.add(new FutureTask<>(crawler));                               //加入到任务数组中
            }

            for(FutureTask<Result> task:tasks){                                     //线程池执行数组的线程
                crawlPool.submit(task);
                count++;
                if(count>10){
                    break ;
                }
            }

            for(FutureTask<Result> task:tasks) {
                try {
                    out.println(task.get(1, TimeUnit.SECONDS).getTitle());  //获取线程的结果,设置每个线程的超时时间为1s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

        }
        crawlPool.shutdown();                                                   //完成所有的线程，关闭线程池

    }

    public static void main(String []args){
        long st = System.currentTimeMillis();

        new ManagerCrawler().initial("http://baike.baidu.com/item/清华").start();

        out.print(System.currentTimeMillis()-st);
    }

}
