package com.mycompany.webserviceconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Bean JSF para consumir dataset de trastornos desde datos.gov.co
 */
@Named("webServiceMenBean")
@SessionScoped
public class WebServiceMenBean implements Serializable {
    
    private List<TrastornoDto> trastornos = new ArrayList<>();
    private String mensajeEstado = "Inicial";
    private int cantidadRegistros = 0;
    
    // Getters
    public List<TrastornoDto> getTrastornos() { return trastornos; }
    public String getMensajeEstado() { return mensajeEstado; }
    public int getCantidadRegistros() { return cantidadRegistros; }
    
    @PostConstruct
    public void init() {
        consultarTrastornos();
    }
    
    
    
    /**
 * Método para consultar trastornos desde datos.gov.co
 */
    public void consultarTrastornos() {
    HttpURLConnection con = null;
    BufferedReader in = null;
    
    try {
        mensajeEstado = "Consultando...";
        
        // URL del dataset x7m4-b6ys en formato SODA
        String resourceId = "x7m4-b6ys";
        String baseUrl = "https://www.datos.gov.co/resource/" + resourceId + ".json";
        
        // CORREGIDO: Sin $order ya que no hay columna 'codigo', solo limitar resultados
        String parametros = "?$limit=20"; // Sin ordenamiento para evitar errores
        String urlCompleta = baseUrl + parametros;
        
        System.out.println("Consultando URL: " + urlCompleta);
        
        // Crear conexión HTTP
        URL url = new URL(urlCompleta);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "SITEA-WebConsumer/1.0");
        
        // Timeout de 30 segundos
        con.setConnectTimeout(30000);
        con.setReadTimeout(30000);
        
        int responseCode = con.getResponseCode();
        System.out.println("Código respuesta: " + responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Leer respuesta JSON
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            
            // Parsear JSON con Jackson (igual que tus beans existentes)
            ObjectMapper objectMapper = new ObjectMapper();
            trastornos = objectMapper.readValue(
                in, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, TrastornoDto.class)
            );
            
            cantidadRegistros = trastornos.size();
            mensajeEstado = "OK - " + cantidadRegistros + " registros cargados";
            
            System.out.println("Trastornos cargados: " + cantidadRegistros);
            if (!trastornos.isEmpty()) {
                System.out.println("Primer registro: " + trastornos.get(0));
            }
            
        } else {
            mensajeEstado = "Error HTTP " + responseCode;
            trastornos = new ArrayList<>();
            cantidadRegistros = 0;
            
            // Leer mensaje de error si está disponible
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
                String errorMsg = errorReader.readLine();
                if (errorMsg != null) {
                    System.out.println("Error del servidor: " + errorMsg);
                    mensajeEstado += " - " + errorMsg;
                }
            } catch (Exception ignored) {}
        }
        
    } catch (Exception ex) {
        System.out.println("Excepción al consultar trastornos: " + ex.getMessage());
        ex.printStackTrace();
        
        mensajeEstado = "Error: " + ex.getMessage();
        trastornos = new ArrayList<>();
        cantidadRegistros = 0;
        
    } finally {
        // Cerrar recursos
        try { if (in != null) in.close(); } catch (Exception ignored) {}
        if (con != null) con.disconnect();
    }
    
}



    
    /**
     * Método para refrescar desde la interfaz
     */
    public void refrescar() {
        System.out.println("Refrescando datos...");
        consultarTrastornos();
    }
}
