package com.raghav.kotlinplayground;

import java.util.StringJoiner;

public class CourseJava {
    private Integer id;
    private String name;
    private String author;

    public CourseJava(Integer id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CourseJava.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("author='" + author + "'")
                .toString();
    }
}
