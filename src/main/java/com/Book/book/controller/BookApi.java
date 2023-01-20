package com.Book.book.controller;

import com.Book.book.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookApi {
    private static Logger logger= LoggerFactory.getLogger(BookApi.class);
    private HashMap<Integer, Book> bookStorage= new HashMap<Integer, Book>();
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id){
        if(bookStorage.containsKey(id)) {
            logger.info("Getting the data based on Id:, {}",id);
            return new ResponseEntity(bookStorage.get(id), HttpStatus.OK);
        }else{
            logger.error("Not giving the proper id:,{}",id);
            return new ResponseEntity("Data not found:"+" "+id, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/book/data")
    public ResponseEntity<List<Book>> getAllBook(){
        logger.info("Getting all the data");
        return new ResponseEntity(bookStorage, HttpStatus.OK);

    }

    @PostMapping("/book/read")
    public ResponseEntity insertBook(@RequestBody Book bookBody){
        if(bookStorage.containsKey(bookBody.getId())){
            logger.error("Book already present,{}", bookBody.getId());
            return new ResponseEntity("Book already present with this ID:"+" "+bookBody.getId(),HttpStatus.BAD_REQUEST);

        }else{
            bookStorage.put(bookBody.getId(), bookBody);
            logger.info("Data inserted successfully");
            return new ResponseEntity("Data inserted successfully with this ID:"+ " "+bookBody.getId() ,HttpStatus.OK);
        }
    }

    @PutMapping("/book/update")
    public ResponseEntity updateBook(@RequestBody Book bookUpdate){
        if(bookStorage.containsKey(bookUpdate.getId())){
            bookStorage.put(bookUpdate.getId(), bookUpdate);
            logger.info("Data Updated with this id:,{}", bookUpdate.getId());
            return new ResponseEntity("Updated the data successfully:"+" "+bookUpdate.getId(), HttpStatus.OK);
        } else{
            logger.error("Id is not present,{}", bookUpdate.getId());
            return new ResponseEntity("Not able to find the given ID:"+" "+bookUpdate.getId(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
       bookStorage.remove(id);
       logger.info("date deleted successfully");
       return new ResponseEntity("Deleted the data successfully:"+" "+ id, HttpStatus.OK);
    }


}
