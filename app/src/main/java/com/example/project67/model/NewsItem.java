package com.example.project67.model;

public class NewsItem {
    private String title;
    private String description;
    private String releaseDate;
    private String url;

    public NewsItem(String title, String description, String releaseDate, String url) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrl() {
        return url;
    }
}

