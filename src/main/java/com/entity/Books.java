package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;// 小说名称

	private String author;// 小说作者

	private String intro;// 小说简介

	private String classify;//小说分类
	
	private String cover;// 小说封面

	private String latestChapter;// 最新章节

	private Integer bookState;// 状态
	
	private String url;

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLatestChapter() {
		return latestChapter;
	}

	public void setLatestChapter(String latestChapter) {
		this.latestChapter = latestChapter;
	}

	public Integer getBookState() {
		return bookState;
	}

	public void setBookState(Integer bookState) {
		this.bookState = bookState;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
