package com.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.BooksChapter;

@Repository
public interface BooksChapterRepository extends JpaRepository<BooksChapter, Integer> {

	List<BooksChapter> findByBooksId(Integer booksId);

	@Query(value = "select * from books_chapter  where books_id=?1 and id <?2 order by id desc limit 1", nativeQuery = true)
	BooksChapter findPreviousByBooksIdAndId(Integer booksId, Integer id);

	@Query(value = "select * from books_chapter  where books_id=?1 and id >?2 limit 1", nativeQuery = true)
	BooksChapter findNextByBooksIdAndId(Integer booksId, Integer id);

}
