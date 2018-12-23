package com.zlk.blog.model;

import com.zlk.blog.entity.Blog;

import java.util.List;

public class ArticleByDate {
    private String year;
    private String month;
    private List<ArticleByDate> months;
    private List<Blog> blogs;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<ArticleByDate> getMonths() {
        return months;
    }

    public void setMonths(List<ArticleByDate> months) {
        this.months = months;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
