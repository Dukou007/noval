package com.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.entity.Books;
import com.entity.BooksChapter;
import com.novel.NovelQula;
import com.respository.BooksChapterRepository;
import com.respository.BooksRepository;
import com.service.BooksChapterService;
import com.vo.BooksChapterVO;

@Service
public class BooksChapterImpl implements BooksChapterService {

	@Autowired
	private BooksChapterRepository repository;
	@Autowired
	private BooksRepository booksRepository;

	@Override
	public List<BooksChapter> findAll() {
		return repository.findAll();
	}

	@Override
	public List<BooksChapter> saveAll(List<BooksChapter> list) {
		return repository.saveAll(list);
	}

	@Override
	public void save(BooksChapter entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public BooksChapter getOneById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public Page<BooksChapter> findAllByPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<BooksChapterVO> findByBooksId(Integer booksId) throws IOException {
		List<BooksChapterVO> listVO=new ArrayList<>();
		List<BooksChapter> list = repository.findByBooksId(booksId);
		for (BooksChapter booksChapter : list) {
			listVO.add(new BooksChapterVO(booksChapter));
		}
		return listVO;
	}

	@Override
	public BooksChapter findPreviousByBooksIdAndId(Integer booksId, Integer id) {
		// TODO Auto-generated method stub
		return repository.findPreviousByBooksIdAndId(booksId, id);
	}

	@Override
	public BooksChapter findNextByBooksIdAndId(Integer booksId, Integer id) {
		// TODO Auto-generated method stub
		return repository.findNextByBooksIdAndId(booksId, id);
	}

	/*@Override
	public void initBooksChapter(Integer booksId) throws Exception {
		// 根据小说的URL初始化章节目录
		List<BooksChapter> novelIndex = null;
		Books books = booksRepository.findById(booksId).get();
		if (books.getUrl() != null && !books.getUrl().equals("")) {

			// 获取目录
			novelIndex = NovelQula.getNovelIndex(books.getUrl());
		}
		for (BooksChapter booksChapter : novelIndex) {
			booksChapter.setBooks(books);
		}
		repository.saveAll(novelIndex);
	}*/

}
