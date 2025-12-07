package com.sitea.models;

import java.io.Serializable;

/**
 * Modelo para representar un servicio
 */
public class Servicio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String icon;
    private String title;
    private String description;
    
    public Servicio() {
    }
    
    public Servicio(String icon, String title, String description) {
        this.icon = icon;
        this.title = title;
        this.description = description;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
