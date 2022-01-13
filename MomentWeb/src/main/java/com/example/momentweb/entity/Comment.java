package com.example.momentweb.entity;

import lombok.Getter;

@Getter
public class Comment {
    private String url = null;
    private String comment = null;

    public Comment(String url, String comment) {
        this.url = url;
        this.comment = comment;
    }

    public Comment(String url) {
        this.url = url;
    }
}