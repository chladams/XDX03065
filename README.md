# 本地版的百度百科

####此爬虫是宽度队列爬虫，从一个网页为基础开始宽度遍历

基本功能设计思路：

1.com.Crawl         线程池维护数据的抓取，解析

2.com.DAO           数据持久化，将抓取的数据存储到Mysql

3.com.proxyPool     维护IP代理池

4.com.TaskControl   任务的调度，管理

5.com.GUI           图形用户界面交互

