package com.qacart.todo.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {

    @JsonProperty("isCompleted")
    private Boolean isCompleted;

    @JsonProperty("_id")
    private String id;

    private String item;

    private String userID;

    private String createdAt;

    @JsonProperty("__v")
    private String version;

    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("__v")
    public String getVersion() {
        return version;
    }

    @JsonProperty("__v")
    public void setVersion(String version) {
        this.version = version;
    }

    public Todo(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    public Todo() {
    }

    public Todo(String item) {
        this.item = item;
    }
}
