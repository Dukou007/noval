package com.service;


import java.io.IOException;
import java.util.List;


import com.entity.BooksChapter;
import com.vo.BooksChapterVO;



public interface BooksChapterService  extends IService<BooksChapter, Integer> {

    List<BooksChapterVO> findByBooksId(Integer booksId) throws IOException;
    
    /*void initBooksChapter(Integer booksId) throws  Exception;*/
    
    BooksChapter findPreviousByBooksIdAndId(Integer booksId,Integer id);
    
    BooksChapter findNextByBooksIdAndId(Integer booksId,Integer id);
}
