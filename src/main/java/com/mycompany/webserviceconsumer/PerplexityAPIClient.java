package com.mycompany.webserviceconsumer;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerplexityAPIClient {

    private static final String API_URL = "https://api.perplexity.ai/chat/completions";
    private static final String API_KEY = System.getenv("PERPLEXITY_API_KEY");

    public String obtenerRecomendacion(String fichaEstudiante) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setDoOutput(true);

        // Crear el JSON para el request
        String jsonInputString = 
            "{"
            + "\"model\": \"sonar\","
            + "\"messages\": [{\"role\": \"user\", \"content\": \"" + fichaEstudiante.replace("\"", "\\\"") + "\"}],"
            + "\"max_tokens\": 150"
            + "}";

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);           
        }

        int code = conn.getResponseCode();
        BufferedReader br;
        if (code == 200) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
        }

        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        return response.toString();
    }
}
