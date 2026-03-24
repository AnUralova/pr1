package com.example.library.model;

public class Book {
    private String title;
    private String binding;
    private String publisher;
    private int publicationYear;
    private String genre;

    public Book() {
    }

    public Book(String title, String binding, String publisher, int publicationYear, String genre) {
        this.title = title;
        this.binding = binding;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}