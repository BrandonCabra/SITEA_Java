package com.mycompany.webserviceconsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject; // Asegúrate de tener esta librería instalada
import org.json.JSONArray;

public class PerplexityAPIClient {
    
    private static final String API_URL = "https://api.perplexity.ai/chat/completions";
    // NOTA: En producción, nunca dejes la API KEY quemada en el código. Usa variables de entorno.
    private static final String API_KEY = "clave-API"; 

    // Configuración SSL (Mantenemos tu configuración actual para desarrollo)
    static {
        configureSSL();
    }
    
    private static void configureSSL() {
        try {
            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
                new javax.net.ssl.X509TrustManager(){
                    public java.security.cert.X509Certificate[] getAcceptedIssuers(){ return null; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){}
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){}
                }
            };
            javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generarEstrategia(String promptUsuario) throws Exception {
        // 1. Construcción SEGURA del JSON usando JSONObject
        JSONObject messageSystem = new JSONObject();
        messageSystem.put("role", "system");
        messageSystem.put("content", "Eres un experto en pedagogía inclusiva y diseño universal para el aprendizaje (DUA). Tu respuesta debe ser clara, estructurada y en formato texto plano (evita markdown complejo si es posible).");

        JSONObject messageUser = new JSONObject();
        messageUser.put("role", "user");
        messageUser.put("content", promptUsuario);

        JSONArray messages = new JSONArray();
        messages.put(messageSystem);
        messages.put(messageUser);

        JSONObject payload = new JSONObject();
        payload.put("model", "sonar-pro"); // O 'sonar-reasoning-pro'
        payload.put("messages", messages);
        payload.put("temperature", 0.7); // Añadido para controlar creatividad

        // 2. Enviar petición
        String jsonResponseRaw = sendRequest(payload.toString());
        
        // 3. Procesar respuesta
        return extraerContenidoRespuesta(jsonResponseRaw);
    }

    private static String sendRequest(String jsonInputString) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        if (code != 200) {
            // Leer el error del stream de error
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                throw new Exception("Error API (" + code + "): " + response.toString());
            }
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
    
    private static String extraerContenidoRespuesta(String jsonResponseRaw) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponseRaw);
            // Navegación segura del JSON
            return jsonObject.getJSONArray("choices")
                             .getJSONObject(0)
                             .getJSONObject("message")
                             .getString("content");
        } catch (Exception e) {
            System.err.println("Error parseando JSON: " + e.getMessage());
            return "Error al procesar la respuesta de la IA.";
        }
    }
}