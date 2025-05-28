/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fundamentoproyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author Osmin Tovar
 */
public class ChartAdminPanel {

    private javax.swing.JPanel Panel;
    private String title = "Title Default", xname = "XDefault", yname = "YDefault";
    private double xvalues[] = {95, 49, 14, 59, 50
    };
    private String yvalues[] = {"Peso1", "Peso2", "Peso3", "Peso4", "Peso5"};
    private int tam = 20;
    private PlotOrientation orientacion = PlotOrientation.VERTICAL;
    private Color[] colores = {
        new Color(255, 105, 180), // 🎯 Hot Pink – fuerte y saturado, ideal para elementos principales
        new Color(255, 153, 204), // 💖 Rosado pastel fuerte – para líneas secundarias
        new Color(255, 192, 203), // 🌸 Pink – suave pero aún visible
        new Color(255, 182, 193), // 🌷 Light Pink – menos saturado
        new Color(255, 204, 229), // 🍬 Rosado personalizado pastel – más tenue
        new Color(255, 218, 225), // 🧁 Rosado apagado suave – más sutil
        new Color(255, 228, 225), // ☁️ Misty Rose – muy tenue, ideal para fondos o detalles
        new Color(255, 240, 245), // 🪻 Lavender Blush – casi blanco, muy suave
        new Color(255, 250, 250), // ❄️ Snow – blanco con tinte rosado
    };
    private Color background = new Color(255, 255, 255); // ⚪ White puro – solo si necesitás blanco real
    private JFreeChart chart;

    /**
     * Inicializa el panel de gráficos con el JPanel donde se mostrará el
     * gráfico. Es esencial que el JPanel utilice un BorderLayout para que el
     * gráfico se muestre correctamente.
     *
     * @param panel Un objeto JPanel donde se insertará el gráfico.
     */
    public ChartAdminPanel(javax.swing.JPanel panel) {
        this.Panel = panel;
    }

    /**
     * Establece los nombres para el título del gráfico y los ejes X e Y.
     *
     * @param title El título principal del gráfico.
     * @param xname El nombre o etiqueta del eje X.
     * @param yname El nombre o etiqueta del eje Y.
     */
    public void setNames(String title, String xname, String yname) {
        this.title = title;
        this.xname = xname;
        this.yname = yname;
    }

    /**
     * Establece únicamente el título principal del gráfico.
     *
     * @param title El título del gráfico.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Configura la orientación del gráfico como vertical.
     */
    public void setOrientacionVertical() {
        orientacion = PlotOrientation.VERTICAL;
    }

    /**
     * Configura la orientación del gráfico como horizontal.
     */
    public void setOrientacionHorizontal() {
        orientacion = PlotOrientation.HORIZONTAL;
    }

    /**
     * Establece el arreglo de colores que se usarán en el gráfico.
     *
     * @param colores Un arreglo de objetos Color que define la paleta de
     * colores del gráfico.
     */
    public void SetColores(Color[] colores) {
        this.colores = colores;
    }

    /**
     * Define el tamaño (número de bins) del histograma.
     *
     * @param tam El número de intervalos o bins que tendrá el histograma.
     */
    public void setTamHistograma(int tam) {
        this.tam = tam;
    }

    /**
     * Actualiza los datos del gráfico con los valores numéricos y sus etiquetas
     * correspondientes.
     *
     * @param xvalues Un arreglo de valores numéricos que representan los datos.
     * @param yvalues Un arreglo de cadenas que representan las etiquetas para
     * cada valor.
     * @return true si los arreglos tienen la misma longitud y los datos se
     * actualizaron correctamente; false en caso contrario.
     */
    public boolean setValues(double[] xvalues, String[] yvalues) {
        if (xvalues.length == yvalues.length) {
            this.xvalues = xvalues;
            this.yvalues = yvalues;
            return true;
        }
        return false;
    }

    /**
     * Establece el color de fondo del gráfico.
     *
     * @param color El objeto Color que se usará como fondo del gráfico.
     */
    public void setBackground(Color color) {
        this.background = color;
    }

    /**
     * Muestra un gráfico circular (Pie Chart) en el panel. Construye el dataset
     * con los valores actuales y configura los colores de cada sección. Además,
     * establece el fondo y el color de fondo de las etiquetas. Reemplaza el
     * contenido del panel con el gráfico generado.
     */
    public void showPieChart() {

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();
        for (int i = 0; i < xvalues.length; i++) {
            barDataset.setValue(yvalues[i], new Double(xvalues[i]));
        }

        //create chart
        chart = ChartFactory.createPieChart(title, barDataset, false, true, false);
        PiePlot piePlot = (PiePlot) chart.getPlot();

        //changing pie chart blocks colors
        for (int i = 0; i < yvalues.length; i++) {
            piePlot.setSectionPaint(yvalues[i], colorselected(i));
        }

        piePlot.setBackgroundPaint(background);
        piePlot.setLabelBackgroundPaint(colores[2]);
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(barChartPanel, BorderLayout.CENTER);
        Panel.validate();
    }

    /**
     * Muestra un gráfico de líneas (Line Chart) en el panel. Construye un
     * dataset con valores y etiquetas, configura el color de la línea y el
     * fondo del gráfico. Reemplaza el contenido del panel con el gráfico
     * generado.
     */
    public void showLineChart() {
        //create dataset for the graph
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < xvalues.length; i++) {
            dataset.setValue(xvalues[i], "Amount", yvalues[i]);
        }
        //create chart
        chart = ChartFactory.createLineChart(title, xname, yname,
                dataset, orientacion, false, true, false);

        //create plot object
        CategoryPlot lineCategoryPlot = chart.getCategoryPlot();
        lineCategoryPlot.setRangeGridlinePaint(colores[2]);
        lineCategoryPlot.setBackgroundPaint(background);
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = colores[0];
        lineRenderer.setSeriesPaint(0, lineChartColor);

        //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(lineChartPanel, BorderLayout.CENTER);
        Panel.validate();
    }

    /**
     * Muestra un histograma en el panel. Crea un dataset para el histograma con
     * los valores actuales y el tamaño especificado. Configura el color del
     * fondo, de las líneas de la cuadrícula y del histograma. Reemplaza el
     * contenido del panel con el gráfico generado.
     */
    public void showHistogram() {

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Valores", xvalues, tam);

        chart = ChartFactory.createHistogram(title, xname, yname, dataset, orientacion, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(background);
        plot.setRangeGridlinePaint(colores[2]);

        XYItemRenderer renderer = plot.getRenderer();
        Color clr3 = colores[0];  // color principal para barras
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(barpChartPanel2, BorderLayout.CENTER);
        Panel.validate();
    }

    /**
     * Muestra un gráfico de barras (Bar Chart) en el panel. Construye un
     * dataset con valores y etiquetas, configura colores y el fondo del
     * gráfico. Reemplaza el contenido del panel con el gráfico generado.
     */
    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < xvalues.length; i++) {
            dataset.setValue(xvalues[i], xname, yvalues[i]);
        }

        chart = ChartFactory.createBarChart(title, yname, xname,
                dataset, orientacion, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(colores[2]);
        categoryPlot.setBackgroundPaint(background);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = colores[0];
        renderer.setSeriesPaint(0, clr3);
        ChartPanel barpChartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(barpChartPanel, BorderLayout.CENTER);
        Panel.validate();
    }

    private Color colorselected(int i) {
        return colores[i % colores.length];
    }

    /**
     * Exporta el gráfico actual a un archivo de imagen PNG.
     *
     * IMPORTANTE: La ruta proporcionada debe incluir el nombre completo del
     * archivo con extensión ".png". Por ejemplo: "C:\\grafico.png" No se debe
     * pasar solo la carpeta, ya que causará un error de acceso.
     *
     * @param filePath La ruta completa del archivo PNG donde se guardará el
     * gráfico.
     * @param width El ancho de la imagen exportada en píxeles.
     * @param height La altura de la imagen exportada en píxeles.
     * @return true si la exportación fue exitosa; false en caso contrario.
     */
    public boolean exportChartToPNG(String filePath, int width, int height) {
        try {
            File file = new File(filePath);
            ChartUtils.saveChartAsPNG(file, chart, width, height);
            System.out.println("Gráfico exportado exitosamente a " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar gráfico: " + e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve una representación en forma de cadena de texto del estado actual
     * del objeto ChartAdminPanel. Incluye información sobre el título, nombres
     * de ejes, orientación, tamaño del histograma, color de fondo, arreglo de
     * colores, y los valores x e y almacenados.
     *
     * @return Una cadena con la descripción detallada de los atributos internos
     * del objeto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChartAdminPanel {").append("\n");
        sb.append("  title: ").append(title).append("\n");
        sb.append("  xname: ").append(xname).append("\n");
        sb.append("  yname: ").append(yname).append("\n");
        sb.append("  orientacion: ").append(orientacion).append("\n");
        sb.append("  tamHistograma: ").append(tam).append("\n");
        sb.append("  background: ").append(background).append("\n");

        sb.append("  colores: [");
        if (colores != null) {
            for (int i = 0; i < colores.length; i++) {
                sb.append(colores[i]);
                if (i < colores.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]\n");

        sb.append("  xvalues: ");
        if (xvalues != null) {
            sb.append(java.util.Arrays.toString(xvalues));
        } else {
            sb.append("null");
        }
        sb.append("\n");

        sb.append("  yvalues: ");
        if (yvalues != null) {
            sb.append(java.util.Arrays.toString(yvalues));
        } else {
            sb.append("null");
        }
        sb.append("\n");

        sb.append("}");
        return sb.toString();
    }

}
