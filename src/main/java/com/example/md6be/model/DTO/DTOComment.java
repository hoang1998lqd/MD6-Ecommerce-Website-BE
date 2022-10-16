package com.example.md6be.model.DTO;

import java.time.LocalDateTime;

public class DTOComment {
    private String review;
    private String name;

    private String time;

    public DTOComment() {

    }

    public DTOComment(String review, String name, String time) {
        this.review = review;
        this.name = name;
        this.time = time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
