/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.service.IGraphLayoutService;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class GraphLayoutServiceImpl implements IGraphLayoutService {

    @Override
    public BufferedImage generateLineChart(CategoryDataset categorydataset, String name, int count) {
        BufferedImage bi = null;


        JFreeChart jfreechart = ChartFactory.createLineChart(name, "Category", "Count", categorydataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        Shape point = ShapeUtilities.createDiagonalCross(1, 1);
        String[] seriesColors = {"#FF0000", "#D7D6F6", "#0F07F3", "#EEFFBD", "#75C53E", "#FED7BA", "#FE6F01"};
        String[] seriesColors2 = {"#D7D6F6", "#0F07F3", "#EEFFBD", "#75C53E", "#FED7BA", "#FE6F01"};



        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true);
        lineandshaperenderer.setBaseShapesFilled(true);
        for (int a = 0; a < count; a++) {
            lineandshaperenderer.setSeriesShapesVisible(a, true);
            lineandshaperenderer.setSeriesLinesVisible(a, true);
            lineandshaperenderer.setSeriesStroke(a, new BasicStroke(1.0F));
            lineandshaperenderer.setSeriesShape(a, point);
        }

        lineandshaperenderer.setDrawOutlines(true);
        lineandshaperenderer.setUseFillPaint(true);
        lineandshaperenderer.setBaseFillPaint(Color.white);

        //DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
        //dateaxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));

        bi = jfreechart.createBufferedImage(500, 270);





        return bi;
    }

    @Override
    public BufferedImage generateLineChart(TimeSeriesCollection timeseriescollection, String title,
            String xname, String yname, int countSerie, int countHistoSerie) {
        BufferedImage bi = null;
        boolean fc = false;
        XYDataset xydataset = timeseriescollection;

        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, xname, yname, xydataset, true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) xyplot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(true);
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(false);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            Shape point = ShapeUtilities.createDiagonalCross(1, 1);
            String[] seriesColors = {"#0F07F3", "#75C53E", "#FE6F01", "#0F07F3"};
            String[] seriesHistoColors = {"#D7D6F6", "#EEFFBD", "#FED7BA", "#D7D6F6"};
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(true);
            xylineandshaperenderer.setBaseShapesFilled(true);
            for (int a = 0; a < countSerie; a++) {
                xylineandshaperenderer.setSeriesShape(a, point);
                xyitemrenderer.setSeriesStroke(a, new BasicStroke(1.0F));
                xylineandshaperenderer.setSeriesPaint(a, Color.decode(seriesColors[a]));
            }
            for (int b = 0; b < countHistoSerie; b++) {
                xylineandshaperenderer.setSeriesShape(b + countSerie, point);
                xyitemrenderer.setSeriesStroke(b + countSerie, new BasicStroke(1.0F));
                xylineandshaperenderer.setSeriesPaint(b + countSerie, Color.decode(seriesHistoColors[b]));
            }

        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));


        bi = jfreechart.createBufferedImage(500, 270);



        return bi;
    }

    @Override
    public BufferedImage generatePieChart(DefaultPieDataset defaultpiedataset, String name, int i) {
        BufferedImage bi = null;


        //String[] seriesColors = { "#D7D6F6","#EEFFBD","#FED7BA", "#0F07F3","#75C53E", "#FE6F01","#EEFFBD","#FED7BA", "#0F07F3","#75C53E", "#FE6F01" };
        JFreeChart jfreechart = ChartFactory.createPieChart(name, defaultpiedataset, true, false, false);
        TextTitle texttitle = jfreechart.getTitle();
        texttitle.setToolTipText("A title tooltip!");
        PiePlot pieplot = (PiePlot) jfreechart.getPlot();
        pieplot.setLabelFont(new Font("SansSerif", 0, 10));
        pieplot.setNoDataMessage("No data available");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);
        //for (int a = 0; a < i ; a++){
        //pieplot.setSectionPaint(a, Color.decode(seriesColors[a]));}


        bi = jfreechart.createBufferedImage(500, 270);

        return bi;
    }

    @Override
    public BufferedImage generateBarChart(CategoryDataset categorydataset, String name, int i, Color color) {
        BufferedImage bi = null;

//    public static void main(String args[])
//    {
//        

        JFreeChart jfreechart = ChartFactory.createBarChart3D("IPS", "", "Value", categorydataset, PlotOrientation.VERTICAL, false, false, false);
                    CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
                    CategoryAxis categoryaxis = categoryplot.getDomainAxis();
                    categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.2D));
                    CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
                    categoryitemrenderer.setBaseItemLabelsVisible(true);
                    BarRenderer barrenderer = (BarRenderer)categoryitemrenderer;
                    barrenderer.setItemMargin(200D);
                    
//        JFreeChart jfreechart = ChartFactory.createBarChart3D(name, "Category", "Value", categorydataset, PlotOrientation.VERTICAL, false, true, false);
//        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
//        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
//        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        numberaxis.setUpperMargin(0.14999999999999999D);
//        CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
//        categoryitemrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//        categoryitemrenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
//        categoryitemrenderer.setSeriesPaint(0, Color.RED);
//        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
//        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

//        RefineryUtilities.centerFrameOnScreen(jfree);
//        jfree.setVisible(true);
//        bi = jfreechart.createBufferedImage(400, 210);
//
//        ChartFactory.setChartTheme(StandardChartTheme.createDarknessTheme());
//        
        return bi;
    }

    @Override
    public BufferedImage generateGanttChart(TaskSeriesCollection taskSeriesCollection, String title, String date) {

        BufferedImage bi = null;

        try {
            JFreeChart jfreechart = ChartFactory.createGanttChart(title, "", date, taskSeriesCollection, false, true, false);
            CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
            //categoryplot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10F);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date2 = dateFormat.parse("2013-04-26 13:43");
//        
//                ValueMarker valueMarker = new ValueMarker(date2.getTime(),Color.blue,new BasicStroke(1.0F));
//                valueMarker.setLabel("Marker Label");
//                valueMarker.setLabelFont(new Font("Dialog", 0, 11));
//                valueMarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
//                valueMarker.setLabelOffset(new RectangleInsets(2D, 5D, 2D, 5D));
//                categoryplot.addRangeMarker(valueMarker) ;
//              
            CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
            
            for (int a = 0; a < taskSeriesCollection.getSeriesCount(); a++) {
                categoryitemrenderer.setSeriesPaint(a, Color.white);
                categoryitemrenderer.setSeriesItemLabelsVisible(a, true);
            }


            //categoryitemrenderer.setSeriesStroke(0, new BasicStroke(0.2F));
            bi = jfreechart.createBufferedImage(500, 270);

        } catch (ParseException ex) {
            Logger.getLogger(GraphLayoutServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bi;
    }

    @Override
    public BufferedImage generateDualAxisChart(TimeSeriesCollection timeseriescollection,
            TimeSeriesCollection timeseriescollection2, TimeSeriesCollection timeseriescollectionSla,
            String title, String xname, String yname, int countSerie,
            int countHistoSerie) {

        BufferedImage bi = null;
        boolean fc = false;
        XYDataset xydataset = timeseriescollection;
        XYDataset xydataset1 = timeseriescollection2;
        XYDataset xydatasetSla = timeseriescollectionSla;

        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, xname, yname, xydataset, true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) xyplot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(true);
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(false);
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            Shape point = ShapeUtilities.createDiagonalCross(1, 1);
            String[] seriesColors = {"#0F07F3", "#75C53E", "#FE6F01", "#0F07F3"};
            String[] seriesHistoColors = {"#D7D6F6", "#EEFFBD", "#FED7BA", "#D7D6F6"};
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(true);
            xylineandshaperenderer.setBaseShapesFilled(true);
            for (int a = 0; a < countSerie; a++) {
                xylineandshaperenderer.setSeriesShape(a, point);
                xyitemrenderer.setSeriesStroke(a, new BasicStroke(1.0F));
                xylineandshaperenderer.setSeriesPaint(a, Color.decode(seriesColors[a]));
            }
            for (int b = 0; b < countHistoSerie; b++) {
                xylineandshaperenderer.setSeriesShape(b + countSerie, point);
                xyitemrenderer.setSeriesStroke(b + countSerie, new BasicStroke(1.0F));
                xylineandshaperenderer.setSeriesPaint(b + countSerie, Color.decode(seriesHistoColors[b]));
            }

        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));


        NumberAxis numberaxis = new NumberAxis("Secondary");
        xyplot.setRangeAxis(1, numberaxis);
        xyplot.mapDatasetToRangeAxis(1, 1);
        xyplot.setDataset(1, xydataset1);
        xyplot.setDataset(2, xydatasetSla);

        XYBarRenderer barrenderer = new XYBarRenderer();
        barrenderer.setSeriesPaint(0, Color.RED);
        barrenderer.setSeriesPaint(1, Color.RED);

        xyplot.setRenderer(1, barrenderer);
        xyplot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);


        bi = jfreechart.createBufferedImage(500, 270);



        return bi;
    }

    @Override
    public BufferedImage generateHorizontalBarChart(CategoryDataset categorydataset, String name, int i) {
        BufferedImage bi = null;


        JFreeChart jfreechart = ChartFactory.createBarChart(name, "Category", "Value", categorydataset, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setUpperMargin(0.14999999999999999D);
        CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
        categoryitemrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryitemrenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
        categoryitemrenderer.setSeriesPaint(0, Color.BLUE);
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);


        bi = jfreechart.createBufferedImage(500, 270);

        return bi;
    }
}
