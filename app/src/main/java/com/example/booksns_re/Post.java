package com.example.booksns_re;

public class Post {
    public String userId;
    public String time;
    public String bookName;
    public String category;
    public String text;

    public Post(String time,String bookName,String category, String text) {
        this.time=time;
        this.bookName=bookName;
        this.category=category;
        this.text=text;
    }
}
