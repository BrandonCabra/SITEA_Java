package com.mycompany.webserviceconsumer;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

// POJOs para parseo JSON con Gson (Drive v3)



@Named("listaDriveApiKeyBean")
@RequestScoped
public class ListaDriveApiKeyBean implements Serializable {

    // Pega aquí tu API key y el ID de la carpeta PÚBLICA
    private static final String API_KEY   = "AIzaSyBUy94SNyrhXB8AGr0s-LMGrWfHO5qZlhk";
    private static final String FOLDER_ID = "1R66OGE6RBZQW00-vaccutnJ_yd0vv2yM";

     
    private List<DriveItem> archivos;

    public ListaDriveApiKeyBean() {
        archivos = new ArrayList<>();
        try {
            // Mismos parámetros que validaste en el navegador:
            // q='FOLDER_ID' in parents and trashed=false
            // fields=files(id,name,mimeType,webViewLink)
            // pageSize y orderBy opcionales para orden/volumen
            String q = "'" + FOLDER_ID + "' in parents and trashed=false";
            String fields = "files(id,name,mimeType,webViewLink)";
            String endpoint = "https://www.googleapis.com/drive/v3/files"
                    + "?q=" + urlEncode(q)
                    + "&fields=" + urlEncode(fields)
                    + "&pageSize=100"
                    + "&orderBy=" + urlEncode("modifiedTime desc")
                    + "&key=" + API_KEY;

            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(15000);

            int code = conn.getResponseCode();
            if (code != 200) {
                throw new RuntimeException("HTTP " + code + " al consultar Drive");
            }

            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line; while ((line = br.readLine()) != null) sb.append(line);
            }
            conn.disconnect();

            // Parseo con Gson
            Gson gson = new Gson();
            DriveList dl = gson.fromJson(sb.toString(), DriveList.class);
            if (dl != null && dl.files != null) {
                archivos = dl.files;
            }
        } catch (Exception e) {
            e.printStackTrace();
            archivos = new ArrayList<>();
        }
    }

    private static String urlEncode(String s) throws Exception {
        return java.net.URLEncoder.encode(s, "UTF-8");
    }

    public List<DriveItem> getArchivos() {
        return archivos;
    }
}