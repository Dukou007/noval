package com.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.entity.Books;
import com.novel.NovelQula;
import com.respository.BooksRepository;
import com.service.BooksService;
import com.vo.BooksVO;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository repository;

	@Override
	public List<Books> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Books> saveAll(List<Books> list) {
		return repository.saveAll(list);
	}

	@Override
	public void save(Books entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public Books getOneById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public Page<Books> findAllByPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<BooksVO> search(String name) {
		List<BooksVO> volist=new ArrayList<BooksVO>();
		List<Books> bookList = repository.findByNameContaining(name);
		if(bookList==null||bookList.size()==0) {
			try {
				volist = NovelQula.getNovelUrl(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (Books book : bookList) {
			BooksVO vo=new BooksVO(book);
			volist.add(vo);
		}
		return volist;
	}

	@Override
	public List<Books> findByBookState(Integer bookState) {
		// TODO Auto-generated method stub
		return repository.findByBookState(bookState);
	}

	

	

	

	

}
