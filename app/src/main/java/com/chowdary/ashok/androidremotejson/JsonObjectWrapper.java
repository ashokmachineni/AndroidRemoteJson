package com.chowdary.ashok.androidremotejson;

public class JsonObjectWrapper {

    private String title;

    private String description;

    private String videoUrl;

    private String category;

    private String poster;

    public JsonObjectWrapper(String title, String description, String videoUrl, String category, String poster) {
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.category = category;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getCategory() {
        return category;
    }

    public String getPoster() {
        return poster;
    }
}
