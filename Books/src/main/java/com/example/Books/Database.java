package com.example.Books;

public class Database {

    private String url;
    public Database() {
        super();
    }

    public Database(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
