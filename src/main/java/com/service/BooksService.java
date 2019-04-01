package com.service;


import java.io.IOException;
import java.util.List;

import com.entity.Books;
import com.vo.BooksVO;



public interface BooksService  extends IService<Books, Integer> {

    List<BooksVO> search(String name) throws IOException;
    
    List<Books> findByBookState(Integer bookState);
}
