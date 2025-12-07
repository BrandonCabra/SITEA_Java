package com.sitea.models;

import java.io.Serializable;

public class Manual implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String icon;
    private String title;
    private String description;
    private String badge;
    private String size;
    private String pages;
    private String file;
    
    public Manual() {}
    
    public Manual(String icon, String title, String description, String badge, String size, String pages, String file) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.badge = badge;
        this.size = size;
        this.pages = pages;
        this.file = file;
    }
    
    // Getters y Setters
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getPages() { return pages; }
    public void setPages(String pages) { this.pages = pages; }
    public String getFile() { return file; }
    public void setFile(String file) { this.file = file; }
}
