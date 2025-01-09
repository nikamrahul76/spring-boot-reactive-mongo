package com.rn.study.spring.mongo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageDto<T> implements Serializable {
    private static final long serialVersionUID = 7983855213393435630L;
    private List<T> content = new ArrayList<>();
    private int number;
    private int numberOfElements;
    private int size;
    private long totalElements;
    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public PageDto<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public PageDto<T> setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public PageDto<T> setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public int getSize() {
        return size;
    }

    public PageDto<T> setSize(int size) {
        this.size = size;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public PageDto<T> setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PageDto<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
