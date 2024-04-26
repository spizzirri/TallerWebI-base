package com.tallerwebi.dominio;

import java.util.List;

public class Joke {

    private List<String> categories;
    private String createdAt;
    private String iconUrl;
    private String id;
    private String updated_at;
    private String url;
    private String value;

    public Joke(String id, List<String> categories, String createdAt, String iconUrl, String updatedAt, String url, String value) {
        this.categories = categories;
        this.createdAt = createdAt;
        this.iconUrl = iconUrl;
        this.id = id;
        this.updated_at = updatedAt;
        this.url = url;
        this.value = value;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "categories=" + categories +
                ", createdAt='" + createdAt + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", id=" + id +
                ", updated_at='" + updated_at + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
