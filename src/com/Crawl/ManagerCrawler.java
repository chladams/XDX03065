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
    public static ExecutorService crawlPool = Executors.newFixedThreadPool(20);   //线程池维护页面的抓取
    public static Queue<Result> resultsQueue= new LinkedList<>();                                //结果队列
    public static Result queueTop = new Result();                                            //队首
    public static Crawler crawler = null;                                                   //爬虫类
    public static int count = 0;
    public static final int MAX_PAGE = 1000;

    public ManagerCrawler initial(String url){                                                        //由一个url初始化爬虫
        HtmlParserTool parserTool = new HtmlParserTool();
        resultsQueue.add(parserTool.getResult(url));
        unique.add(url);
        return this;
    }

    public void start(){

        while(resultsQueue.size()>0 && count<MAX_PAGE){
            queueTop = resultsQueue.poll();                                              //取出队头，并且pop
            DataCRUD.persistent(queueTop);                                              //持久化到数据库
            ArrayList< FutureTask<Result> > tasks = new ArrayList<>();                  //为每个链接分出一个线程去运行

            for(String link:queueTop.getUrlLink()) {                                    //扩展队头
                if(!unique.contains(link)) {                                            //若没有爬取过这个页面
                    unique.add(link);                                                   //添加到过滤器，去重
                    crawler = new Crawler(link);                                       //实例化一个爬虫类
                    tasks.add(new FutureTask<>(crawler));                               //加入到任务数组中
                }
            }

            for(FutureTask<Result> task:tasks){                                         //线程池执行数组的线程
                crawlPool.submit(task);

            }

            for(FutureTask<Result> task:tasks) {
                try {
                    Result taskResult = task.get(2, TimeUnit.SECONDS);      //获取线程的结果,设置每个线程的超时时间为1s
                    if(!taskResult.getTitle().equals("")){                            //过滤掉误爬的链接
                        //out.println(taskResult.getTitle());
                        resultsQueue.add(taskResult);
                        count++;
                    }
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
      //out.println(count);
    }

    public static void main(String []args){
        long st = System.currentTimeMillis();

        new ManagerCrawler().initial("http://baike.baidu.com/item/清华").start();

        out.print(System.currentTimeMillis()-st);
    }

}
