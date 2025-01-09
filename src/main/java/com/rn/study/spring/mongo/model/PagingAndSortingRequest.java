package com.rn.study.spring.mongo.model;

import java.util.HashMap;

public class PagingAndSortingRequest {
    private Integer page = 0;         // Default to page 0
    private Integer limit = 10;      // Default to 10 items per page
    private String sortBy = "id";    // Default sort field
    private String sortType = "asc"; // Default sort type ("asc" or "desc")
    private HashMap<String, Object> fields = new HashMap<>(); // Dynamic filter fields

    // Getters and Setters

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }
}
