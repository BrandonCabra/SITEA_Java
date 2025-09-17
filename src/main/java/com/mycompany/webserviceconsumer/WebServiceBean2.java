/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webserviceconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author tec.ingdesarrollo
 */
@Named("webServiceBean2")
@SessionScoped
public class WebServiceBean2 implements Serializable {

    private List<Tarea> tareaList = new ArrayList<>();

    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    
    @PostConstruct
        public void init(){
            callWebService();
    }

    public void callWebService(){
          String url = "https://jsonplaceholder.typicode.com/todos";
        try {
            TrustManager[] certficados = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                   
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                   
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, certficados, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
            try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                ObjectMapper objectMapper = new ObjectMapper();                
                tareaList = objectMapper.readValue(in, objectMapper.getTypeFactory().constructCollectionType(List.class, Tarea.class));
            }
                
                
                
            }
            
                    
        } catch (Exception e) {
        }
    
    
    }


}
