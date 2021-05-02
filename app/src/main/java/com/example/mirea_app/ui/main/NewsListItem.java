package com.example.mirea_app.ui.main;

import java.util.ArrayList;
import java.util.List;

public class NewsListItem {
    private String head;
    private String body;
    private String url;

    public NewsListItem(String head, String body, String url) {
        this.head = head;
        this.body = body;
        this.url = url;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        return url;
    }
}