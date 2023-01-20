package com.Book.book.model;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private String author;
    private Double price;
}
