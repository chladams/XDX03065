package com.Crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by lewis on 2016/12/9.
 *
 * Html解析，主要使用Jsoup解析html为DOM对象
 */
public class HtmlParserTool {

    public static ArrayList<String> Parser(String html){
        html = DownLoadFile.downloadHtml(html);
        Document dom =  Jsoup.parse(html);            //public static Document parse(String html, String baseUri)
        Elements paras = dom.getElementsByClass("para");        //class选择器,获取div为para的内容

        StringBuffer sb = new StringBuffer();                   //作为中间变量保存字符串

        for(Element link : paras){
            sb.append(link);
        }

        dom = Jsoup.parse(sb.toString());                        //再次解析出字符串
        paras = dom.select("a[href]");                 //选择标签为a，属性含有href的元素

        ArrayList<String> links = new ArrayList<>();

        for(Element link:paras){
            links.add("http://baike.baidu.com"+link.attr("href"));  //抽取出超链接
        }
        return links;
    }

    public static void main(String []args){
        ArrayList<String> links = Parser("http://baike.baidu.com/item/清华大学");
        for(String link:links){
            System.out.println(link);
        }
    }
}
