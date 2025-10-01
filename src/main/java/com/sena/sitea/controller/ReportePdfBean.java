/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.sena.sitea.controller;

import com.sena.sitea.services.CaracterizacionFacadeLocal;
import com.sena.sitea.services.EstudianteFacadeLocal;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.awt.image.BufferedImage;
import com.lowagie.text.Image;



@Named("reportePdfBean")
@RequestScoped
public class ReportePdfBean {
    
    @Inject
    private EstudianteFacadeLocal estudianteFacade;
    
    @Inject
    private CaracterizacionFacadeLocal caracterizacionFacade;
    
    @Inject
    private ReporteBean reporteBean; // Para obtener los mismos datos
    
    public void generarPdf() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte-estadistico.pdf\"");
            
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            
            // Título y estadísticas
            document.add(new Paragraph("Reporte Estadístico"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Estudiantes registrados: " + estudianteFacade.count()));
            document.add(new Paragraph("Caracterizaciones registradas: " + caracterizacionFacade.count()));
            document.add(new Paragraph(" "));
            
            // Generar gráfico con JFreeChart
            JFreeChart chart = crearGraficoJFreeChart();
            if (chart != null) {
                BufferedImage bufferedImage = chart.createBufferedImage(600, 400);
                com.lowagie.text.Image chartImage = com.lowagie.text.Image.getInstance(bufferedImage, null);
                chartImage.scalePercent(80f);
                document.add(chartImage);
            }
            
            document.close();
            fc.responseComplete();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private JFreeChart crearGraficoJFreeChart() {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            // Usar los mismos datos que en la vista web
            dataset.addValue(estudianteFacade.count(), "Cantidad", "Total Estudiantes");
            dataset.addValue(caracterizacionFacade.count(), "Cantidad", "Total Caracterizaciones");
            
            // Agregar diagnósticos
            for (ReporteBean.DiagnosticoDTO diag : reporteBean.getDiagnosticos()) {
                dataset.addValue(diag.getCantidad(), "Cantidad", diag.getDiagnostico());
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Gráfica Diagnósticos", "Categoría", "Cantidad", dataset,
                PlotOrientation.VERTICAL, false, true, false
            );
            
            // Personalizar colores para que coincidan con Chart.js
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(0x007bff)); // Azul
            renderer.setSeriesPaint(1, new Color(0x28a745)); // Verde  
            renderer.setSeriesPaint(2, new Color(0xffc107)); // Amarillo
            
            return chart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
