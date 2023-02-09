package com.example.tugas1_marchellaps.controller;

import com.example.tugas1_marchellaps.DTO.Response;
import com.example.tugas1_marchellaps.entity.Book;
import com.example.tugas1_marchellaps.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookSrvc;

    @GetMapping("/getBook")
    public ResponseEntity<Response<Book>> getAllBooks(){
        Response<Book> responseData = new Response<>();

        try{
            return bookSrvc.getAllBooks();
        } catch (Exception e){
            responseData.setStatus(HttpStatus.MULTI_STATUS.value());
            responseData.setMessage(e.getMessage());
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseData);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Response<Book>> createNewBook(@RequestBody Book book){
        Response<Book> responseData = new Response<>();

        try{
            return bookSrvc.addBook(book);
        } catch (Exception e){
            responseData.setStatus(HttpStatus.MULTI_STATUS.value());
            responseData.setMessage(e.getMessage());
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseData);
        }
    }

    @GetMapping("/deleteBook/{id}")
    public ResponseEntity<Response<Book>> deleteBook(@PathVariable("id") Long id){
        Response<Book> responseData = new Response<>();

        try{
            return bookSrvc.deleteBook(id);
        } catch (Exception e){
            responseData.setStatus(HttpStatus.MULTI_STATUS.value());
            responseData.setMessage(e.getMessage());
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseData);
        }
    }

    @PostMapping("/updateBook/{id}")
    public ResponseEntity<Response<Book>> updateBook(@PathVariable("id") Long id, @RequestBody Book book){
        Response<Book> responseData = new Response<>();

        try{
            return bookSrvc.updateBook(id, book);
        } catch (Exception e){
            responseData.setStatus(HttpStatus.MULTI_STATUS.value());
            responseData.setMessage(e.getMessage());
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseData);
        }
    }

    @GetMapping("/searchBookById/{id}")
    public ResponseEntity<Response<Book>> findBookById(@PathVariable("id") Long id ){
        Response<Book> responseData = new Response<>();

        try{
            return bookSrvc.searchBookById(id);
        } catch (Exception e){
            responseData.setStatus(HttpStatus.MULTI_STATUS.value());
            responseData.setMessage(e.getMessage());
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseData);
        }
    }
}
