/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author bcivel
 */
public interface IGraphLayoutService {

    /**
     * 
     * @param categorydataset
     * @param name Title of the graph
     * @param count Number of series for the graph
     * @return Line chart in BufferedImage format
     */
    public BufferedImage generateLineChart(CategoryDataset categorydataset, String name, int count);

    /**
     * 
     * @param timeseriescollection
     * @param title Title of the graph
     * @param xname Label of the abcisse axis
     * @param name
     * @param countSerie Number of principal series
     * @param countHistoSerie Number of historical series
     * @return Line chart
     */
    public BufferedImage generateLineChart(TimeSeriesCollection timeseriescollection, String title,
            String xname, String name, int countSerie, int countHistoSerie);
    
     /**
     * 
     * @param timeseriescollection
     * @param title Title of the graph
     * @param xname Label of the abcisse axis
     * @param name
     * @param countSerie Number of principal series
     * @param countHistoSerie Number of historical series
     * @return Line chart
     */
    public BufferedImage generateTimeBarChart(TimeSeriesCollection timeseriescollection, String title,
            String xname, String name);

    /**
     * 
     * @param defaultpiedataset
     * @param name Title of the graph
     * @param i 
     * @return Pie Chart in BufferedImage format
     */
    public BufferedImage generatePieChart(DefaultPieDataset defaultpiedataset, String name, int i);

    /**
     * 
     * @param categorydataset
     * @param name Title of the graph
     * @param i
     * @param color Color of the bar
     * @return Bar Chart
     */
    public BufferedImage generateBarChart(CategoryDataset categorydataset, String name, int i, Color color);

    /**
     * 
     * @param categorydataset
     * @param name
     * @param i
     * @return 
     */
    public BufferedImage generateHorizontalBarChart(CategoryDataset categorydataset, String name, int i);
    
    /**
     * 
     * @param categorydataset
     * @param name
     * @param i
     * @return 
     */
    public BufferedImage generateVerticalBarChart(CategoryDataset categorydataset, String name, int i);

     /**
     * 
     * @param categorydataset
     * @param name
     * @param i
     * @return 
     */
    public BufferedImage generateStackedVerticalBarChart(CategoryDataset categorydataset, String name, int i);

    /**
     * 
     * @param taskSeriesCollection
     * @param title
     * @param date
     * @return 
     */
    public BufferedImage generateGanttChart(TaskSeriesCollection taskSeriesCollection, String title, String date);

    /**
     * 
     * @param timeseriescollection
     * @param timeseriescollection2
     * @param timeseriescollectionSla
     * @param title Title of the graph
     * @param xname Label of the abcisse axis
     * @param name
     * @param countSerie Number of serie
     * @param countHistoSerie Number of historical series
     * @return Dual axis Chart with Line and Bar
     */
    public BufferedImage generateDualAxisChart(TimeSeriesCollection timeseriescollection,
            TimeSeriesCollection timeseriescollection2, TimeSeriesCollection timeseriescollectionSla, String title, String xname, String name, int countSerie, int countHistoSerie);
}
