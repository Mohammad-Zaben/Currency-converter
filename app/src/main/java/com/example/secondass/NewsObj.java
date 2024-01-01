package com.example.secondass;

import androidx.annotation.NonNull;

public class NewsObj {
    private String title;
    private String description;

    public NewsObj(String title,String description) {
        this.title=title;
        this.description=description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return title + description;
    }
}
