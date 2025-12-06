package com.sena.sitea.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Cliente Java para consumir el servicio Python de carga de archivos
 */
public class UploadServiceClient {
    
    private static final String SERVICE_URL = "http://localhost:5000";
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * Verifica si el servicio Python está activo
     */
    public static boolean isServiceAvailable() {
        try {
            URL url = new URL(SERVICE_URL + "/health");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Sube un archivo al servicio Python
     */
    public static UploadResponse uploadFile(InputStream fileStream, String filename, 
                                           String usuarioId, String modulo, String descripcion) {
        try {
            String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
            
            URL url = new URL(SERVICE_URL + "/upload");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            
            try (OutputStream output = conn.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true)) {
                
                // Archivo
                writer.append("--").append(boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                      .append(filename).append("\"\r\n");
                writer.append("Content-Type: application/octet-stream\r\n\r\n");
                writer.flush();
                
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                output.flush();
                writer.append("\r\n");
                
                // Metadata
                if (usuarioId != null) {
                    writer.append("--").append(boundary).append("\r\n");
                    writer.append("Content-Disposition: form-data; name=\"usuario_id\"\r\n\r\n");
                    writer.append(usuarioId).append("\r\n");
                }
                
                if (modulo != null) {
                    writer.append("--").append(boundary).append("\r\n");
                    writer.append("Content-Disposition: form-data; name=\"modulo\"\r\n\r\n");
                    writer.append(modulo).append("\r\n");
                }
                
                if (descripcion != null) {
                    writer.append("--").append(boundary).append("\r\n");
                    writer.append("Content-Disposition: form-data; name=\"descripcion\"\r\n\r\n");
                    writer.append(descripcion).append("\r\n");
                }
                
                writer.append("--").append(boundary).append("--\r\n");
                writer.flush();
            }
            
            int responseCode = conn.getResponseCode();
            String response = readResponse(conn);
            
            JsonNode jsonResponse = mapper.readTree(response);
            
            UploadResponse uploadResponse = new UploadResponse();
            uploadResponse.setSuccess(jsonResponse.get("success").asBoolean());
            uploadResponse.setMessage(jsonResponse.get("message").asText());
            
            if (uploadResponse.isSuccess() && jsonResponse.has("data")) {
                JsonNode data = jsonResponse.get("data");
                uploadResponse.setFilename(data.get("filename").asText());
                uploadResponse.setOriginalFilename(data.get("original_filename").asText());
                uploadResponse.setSize(data.get("size").asLong());
                uploadResponse.setMimeType(data.get("mime_type").asText());
            } else if (jsonResponse.has("error")) {
                uploadResponse.setError(jsonResponse.get("error").asText());
            }
            
            conn.disconnect();
            return uploadResponse;
            
        } catch (Exception e) {
            UploadResponse errorResponse = new UploadResponse();
            errorResponse.setSuccess(false);
            errorResponse.setError("Error al conectar con el servicio: " + e.getMessage());
            return errorResponse;
        }
    }
    
    /**
     * Lista todos los archivos subidos
     */
    public static List<FileInfo> listFiles() {
        List<FileInfo> files = new ArrayList<>();
        
        try {
            URL url = new URL(SERVICE_URL + "/files");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            String response = readResponse(conn);
            JsonNode jsonResponse = mapper.readTree(response);
            
            if (jsonResponse.get("success").asBoolean()) {
                JsonNode dataArray = jsonResponse.get("data");
                for (JsonNode fileNode : dataArray) {
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFilename(fileNode.get("filename").asText());
                    fileInfo.setSize(fileNode.get("size").asLong());
                    fileInfo.setCreated(fileNode.get("created").asText());
                    fileInfo.setMimeType(fileNode.get("mime_type").asText());
                    files.add(fileInfo);
                }
            }
            
            conn.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return files;
    }
    
    /**
     * Elimina un archivo
     */
    public static boolean deleteFile(String filename) {
        try {
            URL url = new URL(SERVICE_URL + "/delete/" + filename);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            
            return responseCode == 200;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static String readResponse(HttpURLConnection conn) throws IOException {
        InputStream inputStream = conn.getResponseCode() < 400 
            ? conn.getInputStream() 
            : conn.getErrorStream();
            
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
    
    // Clases auxiliares
    public static class UploadResponse {
        private boolean success;
        private String message;
        private String error;
        private String filename;
        private String originalFilename;
        private long size;
        private String mimeType;
        
        // Getters y Setters
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        
        public String getFilename() { return filename; }
        public void setFilename(String filename) { this.filename = filename; }
        
        public String getOriginalFilename() { return originalFilename; }
        public void setOriginalFilename(String originalFilename) { this.originalFilename = originalFilename; }
        
        public long getSize() { return size; }
        public void setSize(long size) { this.size = size; }
        
        public String getMimeType() { return mimeType; }
        public void setMimeType(String mimeType) { this.mimeType = mimeType; }
    }
    
    public static class FileInfo {
        private String filename;
        private long size;
        private String created;
        private String mimeType;
        
        // Getters y Setters
        public String getFilename() { return filename; }
        public void setFilename(String filename) { this.filename = filename; }
        
        public long getSize() { return size; }
        public void setSize(long size) { this.size = size; }
        
        public String getCreated() { return created; }
        public void setCreated(String created) { this.created = created; }
        
        public String getMimeType() { return mimeType; }
        public void setMimeType(String mimeType) { this.mimeType = mimeType; }
        
        public String getSizeFormatted() {
            if (size < 1024) return size + " B";
            if (size < 1024 * 1024) return String.format("%.2f KB", size / 1024.0);
            return String.format("%.2f MB", size / (1024.0 * 1024.0));
        }
    }
}
