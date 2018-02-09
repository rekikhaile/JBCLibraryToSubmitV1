package com.riri.jbclibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

    @NotNull
    @Size(min = 1, message = "Enter at least {min} characters")
    private String title;

    @NotNull
    @Size(min=2,message="Enter at least {min} characters")
    private String author;

    @NotNull
    @Size(min = 4, message = "Enter at least {min} characters")
    private String year;

    private String isbn;

    private String image;

    private boolean isAvailable;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Book() {
        isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

/*    public boolean isAvailable() {
        isAvailable=true;
        return isAvailable;
    }*/
    public boolean getIsAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
