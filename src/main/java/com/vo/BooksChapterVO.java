package com.vo;


import org.springframework.beans.BeanUtils;

import com.entity.BooksChapter;

public class BooksChapterVO extends BaseVO  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;

	public BooksChapterVO(BooksChapter booksChapter) {
		if(booksChapter!=null) {
			BeanUtils.copyProperties(booksChapter, this);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
