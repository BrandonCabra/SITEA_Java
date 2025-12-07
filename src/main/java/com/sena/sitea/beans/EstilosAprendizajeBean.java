package com.sena.sitea.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "estilosBean")
@ViewScoped
public class EstilosAprendizajeBean implements Serializable {

    // Clases internas para estructurar el test
    public static class Opcion implements Serializable {
        private String texto;
        private String tipo; // "Visual", "Auditivo", "Kinestesico"

        public Opcion(String texto, String tipo) {
            this.texto = texto;
            this.tipo = tipo;
        }
        // Getters
        public String getTexto() { return texto; }
        public String getTipo() { return tipo; }
    }

    public static class Pregunta implements Serializable {
        private String enunciado;
        private List<Opcion> opciones;
        private String tipoSeleccionado; // Aquí guardamos qué eligió el usuario

        public Pregunta(String enunciado, List<Opcion> opciones) {
            this.enunciado = enunciado;
            this.opciones = opciones;
        }
        
        public String getEnunciado() { return enunciado; }
        public List<Opcion> getOpciones() { return opciones; }
        public String getTipoSeleccionado() { return tipoSeleccionado; }
        public void setTipoSeleccionado(String tipoSeleccionado) { this.tipoSeleccionado = tipoSeleccionado; }
    }

    // Variables del Bean
    private List<Pregunta> listaPreguntas;
    private boolean testFinalizado;
    
    // Resultados
    private int puntajeVisual;
    private int puntajeAuditivo;
    private int puntajeKinestesico;
    private String estiloDominante;
    private String recomendacion;

    @PostConstruct
    public void init() {
        testFinalizado = false;
        cargarPreguntas();
    }

    // Carga de preguntas basadas en el modelo VAK (Modelo PNL)
    private void cargarPreguntas() {
        listaPreguntas = new ArrayList<>();

        // Pregunta 1
        List<Opcion> op1 = new ArrayList<>();
        op1.add(new Opcion("Ver películas", "Visual"));
        op1.add(new Opcion("Escuchar música", "Auditivo"));
        op1.add(new Opcion("Bailar o hacer deporte", "Kinestesico"));
        Collections.shuffle(op1); // Mezclamos para que no siempre sea el mismo orden
        listaPreguntas.add(new Pregunta("1. ¿Qué actividad disfrutas más en tu tiempo libre?", op1));

        // Pregunta 2
        List<Opcion> op2 = new ArrayList<>();
        op2.add(new Opcion("El diseño, los colores y las fotos", "Visual"));
        op2.add(new Opcion("Las conversaciones y los debates", "Auditivo"));
        op2.add(new Opcion("Las sensaciones, emociones y el movimiento", "Kinestesico"));
        Collections.shuffle(op2);
        listaPreguntas.add(new Pregunta("2. ¿Qué te llama más la atención de un evento o lugar?", op2));

        // Pregunta 3
        List<Opcion> op3 = new ArrayList<>();
        op3.add(new Opcion("Mirar mapas o diagramas", "Visual"));
        op3.add(new Opcion("Pedir indicaciones verbales", "Auditivo"));
        op3.add(new Opcion("Guiarme por la intuición o caminar hasta encontrarlo", "Kinestesico"));
        Collections.shuffle(op3);
        listaPreguntas.add(new Pregunta("3. Cuando te pierdes, ¿qué prefieres hacer?", op3));
        
        // Pregunta 4
        List<Opcion> op4 = new ArrayList<>();
        op4.add(new Opcion("Escribiéndolo o dibujándolo varias veces", "Visual"));
        op4.add(new Opcion("Repitiéndolo en voz alta o explicándoselo a alguien", "Auditivo"));
        op4.add(new Opcion("Haciendo gestos o caminando mientras estudio", "Kinestesico"));
        Collections.shuffle(op4);
        listaPreguntas.add(new Pregunta("4. ¿Cómo aprendes mejor algo nuevo?", op4));

        // Pregunta 5
        List<Opcion> op5 = new ArrayList<>();
        op5.add(new Opcion("Recuerdo las caras pero olvido los nombres", "Visual"));
        op5.add(new Opcion("Recuerdo los nombres pero olvido las caras", "Auditivo"));
        op5.add(new Opcion("Recuerdo lo que hice con esa persona", "Kinestesico"));
        Collections.shuffle(op5);
        listaPreguntas.add(new Pregunta("5. Cuando conoces a alguien nuevo...", op5));

        // NOTA: Puedes agregar las 40 preguntas del PDF siguiendo este patrón.
    }

    public void calcularResultado() {
        puntajeVisual = 0;
        puntajeAuditivo = 0;
        puntajeKinestesico = 0;
        boolean faltanRespuestas = false;

        for (Pregunta p : listaPreguntas) {
            if (p.getTipoSeleccionado() == null) {
                faltanRespuestas = true;
                break;
            }
            switch (p.getTipoSeleccionado()) {
                case "Visual": puntajeVisual++; break;
                case "Auditivo": puntajeAuditivo++; break;
                case "Kinestesico": puntajeKinestesico++; break;
            }
        }

        if (faltanRespuestas) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Por favor responde todas las preguntas."));
            return;
        }

        // Determinar dominante
        if (puntajeVisual >= puntajeAuditivo && puntajeVisual >= puntajeKinestesico) {
            estiloDominante = "VISUAL";
            recomendacion = "Aprendes mejor viendo. Usa mapas mentales, videos, colores y diagramas.";
        } else if (puntajeAuditivo >= puntajeVisual && puntajeAuditivo >= puntajeKinestesico) {
            estiloDominante = "AUDITIVO";
            recomendacion = "Aprendes mejor escuchando. Usa audiolibros, debates, y repite en voz alta.";
        } else {
            estiloDominante = "CINESTÉSICO";
            recomendacion = "Aprendes mejor haciendo. Involucra movimiento, experimentos y práctica manual.";
        }

        testFinalizado = true;
        
        // Aquí podrías guardar en base de datos el resultado asociado al estudiante
    }
    
    public void reiniciarTest() {
        testFinalizado = false;
        listaPreguntas.clear();
        cargarPreguntas();
    }

    // Getters necesarios para la vista
    public List<Pregunta> getListaPreguntas() { return listaPreguntas; }
    public boolean isTestFinalizado() { return testFinalizado; }
    public int getPuntajeVisual() { return puntajeVisual; }
    public int getPuntajeAuditivo() { return puntajeAuditivo; }
    public int getPuntajeKinestesico() { return puntajeKinestesico; }
    public String getEstiloDominante() { return estiloDominante; }
    public String getRecomendacion() { return recomendacion; }
}