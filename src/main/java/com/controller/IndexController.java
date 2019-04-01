package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Books;
import com.entity.BooksChapter;
import com.entity.Demo;
import com.novel.NovelQula;
import com.service.BooksChapterService;
import com.service.BooksService;
import com.service.DemoService;
import com.util.JsonUtils;
import com.vo.BooksVO;
import com.vo.BooksChapterVO;
import com.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sunxusen on 2018/7/22.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private BooksService bookService;
    @Autowired
    private BooksChapterService booksChapterService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletResponse response,Model model) {
    	List<Books> findAll = bookService.findAll();
        List<BooksVO> listVO=new ArrayList<>();
    	for (Books books : findAll) {
    		listVO.add(new BooksVO(books));																		
		}
    	model.addAttribute("list", listVO);
        return "novel/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(String name, Model model) {
    	name=name.trim();
        List<BooksVO> novelIndex = null;
        try {
            // 搜索结果
            novelIndex=bookService.search(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("list", novelIndex);
        model.addAttribute("name", name);
        return "novel/search";
    }
    @RequestMapping(value = "findByBooksId", method = RequestMethod.GET)
    public String findByBooksId(@RequestParam Integer booksId, Model model) {

        List<BooksChapterVO> novelIndex = null;
        try {
        	novelIndex=booksChapterService.findByBooksId(booksId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("list", novelIndex);
        return "novel/novelindex";
    }
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO findAll() {

        List<BooksVO> listVO=new ArrayList<>();
		try {
			List<Books> findAll = bookService.findAll();
	    	for (Books books : findAll) {
	    		listVO.add(new BooksVO(books));																		
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVO(false,2001,"查询失败"+e.getLocalizedMessage()) ;
		}
        return new ResultVO(true,2000,"查询成功",listVO) ;
    }
    @RequestMapping(value = "findSearch", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO search(@RequestParam String name) {
    	name=name.trim();
        List<BooksVO> search=null;
		try {
			search = bookService.search(name);
			if(search==null||search.size()==0) {
				search =NovelQula.getNovelUrl(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultVO(false,2001,"查询失败"+name+e.getLocalizedMessage()) ;
		}
        return new ResultVO(true,2000,"查询成功",search) ;
    }
    @RequestMapping(value = "reader", method = RequestMethod.GET)
    public String reader(Integer id, Model model) {
        BooksChapter oneById = booksChapterService.getOneById(id);
        BooksChapter next = booksChapterService.findNextByBooksIdAndId(oneById.getBooks().getId(), id);
        BooksChapter previous =booksChapterService.findPreviousByBooksIdAndId(oneById.getBooks().getId(), id);
        try {
            String text = NovelQula.getText(oneById.getUrl());
            model.addAttribute("text", text);
            model.addAttribute("next", next==null?0:next.getId());
            model.addAttribute("previous", previous==null?0:previous.getId());
            model.addAttribute("name", oneById.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "novel/reader";
    }
    @RequestMapping(value = "booksChapter", method = RequestMethod.GET)
    public String booksChapter(String url,Integer id,String classify, Model model) {
    	List<BooksChapterVO> list=null;
        try {
            if(id!=null&&id!=0) {
            	list = booksChapterService.findByBooksId(id);
            }else {
            	List<BooksChapter> novelIndex = NovelQula.getNovelIndex(url, classify);
            	if(novelIndex.size()>0)
            	bookService.save(novelIndex.get(0).getBooks());
            	booksChapterService.saveAll(novelIndex);
            	list=new ArrayList<>();
            	for (BooksChapter booksChapter : novelIndex) {
            		list.add(new BooksChapterVO(booksChapter));
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("list", list);
        return "novel/booksChapter";
    }
    


    // ***
    // ***
    // ***

//    @RequestMapping(value = "index", method = RequestMethod.GET)
//    public void index(HttpServletResponse response) {
//
////        write(response, "index");
//    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public void get(Integer id, HttpServletResponse response) {

        Demo one = demoService.getOne(id);
        write(response, "get: " + JsonUtils.toJSON(one));
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public void update(Integer id, HttpServletResponse response) {
        Demo demo = new Demo();
        demo.setId(id);
        demo.setName("000-新名字:" + id);
        demoService.update(demo);

        write(response, "update: " + id);
    }


}
