# 本地版的百度百科

基本功能设计思路：

1.com.Crawl         数据的抓取，解析

2.com.DAO           数据持久化，将抓取的数据存储到Mysql

3.com.proxyPool     维护IP代理池

4.com.TaskControl   任务的调度，管理

