package com.example.tugas1_marchellaps.service;

import com.example.tugas1_marchellaps.DTO.Response;
import com.example.tugas1_marchellaps.entity.Book;
import com.example.tugas1_marchellaps.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepos;

    public ResponseEntity<Response<Book>> getAllBooks(HttpStatus status){
        Response<Book> responseData = new Response<>();

        List<Book> searchAllBooks = bookRepos.findAll();

        if(searchAllBooks.isEmpty()){
            responseData.setStatus(status.value());
            responseData.setMessage("Data kosong");
            responseData.setPayload(null);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setStatus(status.value());
            responseData.setMessage("Data found");
            responseData.setPayload(searchAllBooks);

            return ResponseEntity.ok(responseData);
        }
    }

    public ResponseEntity<Response<Book>> addBook(Book book, HttpStatus status){
        Response<Book> responseData = new Response<>();

        Book addBook = bookRepos.save(book);

        responseData.setStatus(status.value());
        responseData.setMessage("Data buku berhasil disimpan");
        responseData.setPayload(addBook);

        return ResponseEntity.ok(responseData);
    }

    public ResponseEntity<Response<Book>> deleteBook(Long id, HttpStatus status){
        Response<Book> responseData = new Response<>();

        // find book dari id yang dikirim, apabila id buku ada maka bisa di delete
        Optional<Book> dataBuku = bookRepos.findById(id);

        if (!dataBuku.isPresent()){
            responseData.setStatus(status.value());
            responseData.setMessage("Data buku dengan id " + id + " tidak ada");
            responseData.setPayload(null);

            return ResponseEntity.ok(responseData);
        } else {
            bookRepos.deleteById(id);
            responseData.setStatus(status.value());
            responseData.setMessage("Data buku berhasil dihapus");
            responseData.setPayload(null);

            return ResponseEntity.ok(responseData);
        }
    }

    public ResponseEntity<Response<Book>> updateBook(Long id, Book book, HttpStatus status){
        Response<Book> responseData = new Response<>();

        // find book dari id yang dikirim
        Book dataBuku = bookRepos.findById(id).get();

        if (dataBuku == null){
            responseData.setStatus(status.value());
            responseData.setMessage("Data buku dengan id " + id + " tidak ada");
            responseData.setPayload(null);

            return ResponseEntity.ok(responseData);
        } else {
            System.out.println("data buku from db = " + dataBuku);
            dataBuku.setJudulBuku(book.getJudulBuku());
            dataBuku.setPenerbit(book.getPenerbit());
            dataBuku.setPengarang(book.getPengarang());
            dataBuku.setKategoriBuku(book.getKategoriBuku());
            dataBuku.setTahunTerbit(book.getTahunTerbit());

            Book saveBook = bookRepos.save(dataBuku);

            responseData.setStatus(status.value());
            responseData.setMessage("Data berhasil diupdate");
            responseData.setPayload(saveBook);

            return ResponseEntity.ok(responseData);
        }
    }

    public ResponseEntity<Response<Book>> searchBookById(Long id, HttpStatus status){
        Response<Book> responseData = new Response<>();

        Optional<Book> findBook = bookRepos.findById(id);

        if (!findBook.isPresent()){
            responseData.setStatus(status.value());
            responseData.setMessage("Data kosong");
            responseData.setPayload(null);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setStatus(status.value());
            responseData.setMessage("Data found");
            responseData.setPayload(findBook);

            return ResponseEntity.ok(responseData);
        }
    }
}
