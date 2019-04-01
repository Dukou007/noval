package com.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "books_chapter")
public class BooksChapter extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String url;

	private Books books;
	
	public void setBooks(Books books) {
		this.books = books;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "books_id")
	public Books getBooks() {
		return books;
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
