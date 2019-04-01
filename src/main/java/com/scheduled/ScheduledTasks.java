package com.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entity.Books;
import com.service.BooksChapterService;
import com.service.BooksService;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
    
	
	@Autowired
    private BooksService bookService;
    @Autowired
    private BooksChapterService booksChapterService;
    
    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime(){
    	List<Books> findByBookState = bookService.findByBookState(0);
    	
        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date ()));
    }

    //每1分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
    
}

