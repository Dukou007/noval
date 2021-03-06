package com.novel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.entity.Books;
import com.entity.BooksChapter;
import com.util.HttpsTrustManager;
import com.vo.BooksChapterVO;
import com.vo.BooksVO;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunxusen on 2018/7/30.
 */
public class NovelQula {


    public static String BASE_URL = "https://www.qu.la";
    static {
        HttpsTrustManager.trustAll();
    }
    /**
     * 获取章节内容
     * @param url
     * @return
     * @throws IOException
     */
    public static String getText(String url) throws IOException {
        url = BASE_URL + url;
        Document document = Jsoup.connect(url).get();
        Element content = document.getElementById("content");
        return content.html().replace("<script>chaptererror();</script>", "");
    }

    /**
     * 获取目录
     * @param url
     * @return
     * @throws IOException
     */
    public static List<BooksChapter> getNovelIndex(String url,String classify) throws IOException {
    	Books books=new Books();
        List<BooksChapter> list = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Element info = document.getElementById("info");
        books.setName(info.getElementsByTag("h1").text().trim());
        books.setAuthor(info.getElementsByTag("p").get(0).text().trim().split("：")[1]);
        books.setUrl(url);
        books.setIntro(document.getElementById("intro").text().trim());
        books.setCover(document.getElementById("fmimg").attr("src"));
        books.setLatestChapter(info.getElementsByTag("p").get(3).getElementsByTag("a").text());
        books.setClassify(classify);
        for (Element a : document.getElementsByTag("a")) {
            if (a.hasAttr("style") && "".equals(a.attr("style")) && a.attr("href").contains("/book/")) {
            	BooksChapter m = new BooksChapter();
                m.setUrl(a.attr("href"));
                m.setName( a.text());
                m.setBooks(books);
                list.add(m);
            }
        }
        return list;
    }

    /**
     * 获取搜索结果
     * @param keywords
     * @return
     * @throws IOException
     */
    public static List<BooksVO> getNovelUrl(String keywords) throws IOException {
        List<BooksVO> booksVOList=new ArrayList<>();
        
        String url = "https://sou.xanbhx.com/search?siteid=qula&q=" + URLEncoder.encode(keywords, "utf-8");
        Document document = Jsoup.connect(url).get();
        Elements li = document.getElementsByTag("li");
        int i=0;
        for (Element element : li) {
			if(i==0) {
				i++;
				continue;
			}
			BooksVO vo=new BooksVO();
			vo.setClassify(element.getElementsByClass("s1").text().trim().replaceAll("\\[|\\]", ""));
			vo.setName(element.getElementsByClass("s2").text().trim());
			vo.setUrl(element.getElementsByClass("s2").get(0).getElementsByTag("a").attr("href"));
			vo.setLatestChapter(element.getElementsByClass("s3").text().trim());
			vo.setAuthor(element.getElementsByClass("s4").text().trim());
			vo.setEditUser(element.getElementsByClass("s6").text().trim());
			vo.setBookState(element.getElementsByClass("s7").text().trim().equals("完结")?1:0);
			booksVOList.add(vo);
			
		}
        return booksVOList;
    }

}
