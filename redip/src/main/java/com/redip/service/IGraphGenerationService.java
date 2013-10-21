/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;


import com.redip.entity.GraphScript;
import com.redip.exception.QualityException;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IGraphGenerationService {

    List<GraphScript> getScript(String title) throws QualityException;

    /**
     * 
     * @param graph Name of the graph
     * @param group Name of the way to agregate the data
     * @param indicator Name of the indicator/ component of the graph
     * @param country Name of the country
     * @return GraphIndicatorCountry object containing SLA type and values
     */
    
    /**
     * 
     * @param script Script to execute
     * @return  List of string containing resultset
     */
    List<List<String>> getDataForGraph(String script);
    
    
    /**
     * 
     * @param data List of data
     * @param historicData List of historical data
     * @param versus number of day 
     * @param title Title of the graphic
     * @param xLabel Label of the abcisse
     * @return Line graph in a BufferedImage format
     */
    BufferedImage generateLineGraph(List<List<String>> data, List<List<String>> historicData, String versus, String title, String xLabel);

   
    /**
     * 
     * @param data List of string for the graph
     * @param title Title of the graphic
     * @param xLabel Label of the abcisse axe
     * @return Gantt graphic in BufferedImage format
     */
    BufferedImage generateGanttGraph(List<List<String>> data, String title, String xLabel);

    /**
     * 
     * @param data List of data for the Line part of the graph
     * @param dataBar List of data for the Bar part
     * @param dataSla List of data for the SLA
     * @param title Title of the graphic
     * @param xLabel Label of the abcisse axis
     * @return DualAxis Graph with Line and Bar in BufferedImage format
     */
    BufferedImage generateDualAxisGraph(List<List<String>> dataLine,
            List<List<String>> dataBar, List<List<String>> dataSla, String title, String xLabel);

    /**
     * 
     * @param data List of data
     * @param title Title of the Graphic
     * @param xLabel Label of the abcisse axis
     * @return HorizontalBarGraph in a BufferedImage format
     */
    BufferedImage generateHorizontalBarGraph(List<List<String>> data, String title, String xLabel);
    
    /**
     * 
     * @param data List of data
     * @param title Title of the Graphic
     * @param xLabel Label of the abcisse axis
     * @return HorizontalBarGraph in a BufferedImage format
     */
    BufferedImage generateVerticalBarGraph(List<List<String>> data, String title, String xLabel);

    /**
     * 
     * @param data List of data
     * @param title Title of the Graphic
     * @param xLabel Label of the abcisse axis
     * @return HorizontalBarGraph in a BufferedImage format
     */
    BufferedImage generateStackedVerticalBarGraph(List<List<String>> data, String title, String xLabel);

    /**
     * 
     * @param data List of data
     * @param title Title of the graphic
     * @return PieChart
     */
    BufferedImage generatePieGraph(List<List<String>> data, String title);

    /**
     * 
     * @param date Input date to convert
     * @param type Type of date (fromDate or toDate)
     * @param versus Number of day for the versus
     * @return String with calculated date
     */
    String convertStringDate(String date, String type, Integer versus);
}
