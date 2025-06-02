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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ChartAdminPanel {

    private javax.swing.JPanel Panel;
    private String title = "Title Default", xname = "XDefault", yname = "YDefault";
    private double[] xvalues = {95, 49, 14, 59, 50};
    private String[] yvalues = {"Peso1", "Peso2", "Peso3", "Peso4", "Peso5"};
    private int tam = 20;
    private PlotOrientation orientacion = PlotOrientation.VERTICAL;
    private Color[] colores = {
        new Color(255, 105, 180),
        new Color(255, 153, 204),
        new Color(255, 192, 203),
        new Color(255, 182, 193),
        new Color(255, 204, 229),
        new Color(255, 218, 225),
        new Color(255, 228, 225),
        new Color(255, 240, 245),
        new Color(255, 250, 250)
    };
    private Color background = new Color(255, 255, 255);
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public ChartAdminPanel(javax.swing.JPanel panel) {
        this.Panel = panel;
    }

    public void setNames(String title, String xname, String yname) {
        this.title = title;
        this.xname = xname;
        this.yname = yname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrientacionVertical() {
        orientacion = PlotOrientation.VERTICAL;
    }

    public void setOrientacionHorizontal() {
        orientacion = PlotOrientation.HORIZONTAL;
    }

    public void SetColores(Color[] colores) {
        this.colores = colores;
    }

    public void setTamHistograma(int tam) {
        this.tam = tam;
    }

    public boolean setValues(double[] xvalues, String[] yvalues) {
        if (xvalues.length == yvalues.length) {
            this.xvalues = xvalues;
            this.yvalues = yvalues;
            return true;
        }
        return false;
    }

    public void setBackground(Color color) {
        this.background = color;
    }

    public void showPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < xvalues.length; i++) {
            dataset.setValue(yvalues[i], xvalues[i]);
        }

        chart = ChartFactory.createPieChart(title, dataset, false, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        for (int i = 0; i < yvalues.length; i++) {
            plot.setSectionPaint(yvalues[i], colorselected(i));
        }

        plot.setBackgroundPaint(background);
        plot.setLabelBackgroundPaint(colores[2]);

        chartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(chartPanel, BorderLayout.CENTER);
        Panel.validate();
    }

    public void showLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < xvalues.length; i++) {
            dataset.setValue(xvalues[i], "Amount", yvalues[i]);
        }

        chart = ChartFactory.createLineChart(title, xname, yname, dataset, orientacion, false, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(colores[2]);
        plot.setBackgroundPaint(background);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colores[0]);

        chartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(chartPanel, BorderLayout.CENTER);
        Panel.validate();
    }

    public void showHistogram() {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Valores", xvalues, tam);

        chart = ChartFactory.createHistogram(title, xname, yname, dataset, orientacion, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(background);
        plot.setRangeGridlinePaint(colores[2]);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, colores[0]);

        chartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(chartPanel, BorderLayout.CENTER);
        Panel.validate();
    }

    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < xvalues.length; i++) {
            dataset.setValue(xvalues[i], xname, yvalues[i]);
        }

        chart = ChartFactory.createBarChart(title, yname, xname, dataset, orientacion, false, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(colores[2]);
        plot.setBackgroundPaint(background);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colores[0]);

        chartPanel = new ChartPanel(chart);
        Panel.removeAll();
        Panel.add(chartPanel, BorderLayout.CENTER);
        Panel.validate();
    }
    

public void showScatterPlot() {
    XYSeries series = new XYSeries("Datos");

    // Convertir los arrays xvalues (double[]) y yvalues (String[]) a coordenadas XY
    for (int i = 0; i < xvalues.length && i < yvalues.length; i++) {
        try {
            double y = Double.parseDouble(yvalues[i]);
            series.add(xvalues[i], y);
        } catch (NumberFormatException e) {
            System.err.println("❌ Error al convertir yvalue a número: " + yvalues[i]);
        }
    }

    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(series);

    chart = ChartFactory.createScatterPlot(
        title,
        xname,
        yname,
        dataset,
        orientacion,
        false,
        true,
        false
    );

    XYPlot plot = chart.getXYPlot();
    plot.setBackgroundPaint(background);
    plot.setRangeGridlinePaint(colores[2]);

    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    renderer.setSeriesLinesVisible(0, false); // Solo puntos, sin líneas
    renderer.setSeriesShapesVisible(0, true);
    renderer.setSeriesPaint(0, colores[0]);
    plot.setRenderer(renderer);

    chartPanel = new ChartPanel(chart);
    Panel.removeAll();
    Panel.add(chartPanel, BorderLayout.CENTER);
    Panel.validate();
}

    
    

    private Color colorselected(int i) {
        return colores[i % colores.length];
    }

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

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChartAdminPanel {\n");
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
                if (i < colores.length - 1) sb.append(", ");
            }
        }
        sb.append("]\n");

        sb.append("  xvalues: ").append(xvalues != null ? java.util.Arrays.toString(xvalues) : "null").append("\n");
        sb.append("  yvalues: ").append(yvalues != null ? java.util.Arrays.toString(yvalues) : "null").append("\n");

        sb.append("}");
        return sb.toString();
    }
}
