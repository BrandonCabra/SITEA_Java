/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webserviceconsumer;

import java.util.List;

/**
 *
 * @author brandon
 */
class DriveList { List<DriveItem> files; }
public class DriveItem {
    private String id;
    private String name;
    private String mimeType;
    private String webViewLink;

    public String getId() { return id; }
    public String getName() { return name; }
    public String getMimeType() { return mimeType; }
    public String getWebViewLink() { return webViewLink; }

    // Setters si lo necesitas para pruebas/manual mapping
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }
    public void setWebViewLink(String webViewLink) { this.webViewLink = webViewLink; }
}





