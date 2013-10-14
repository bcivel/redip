/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IGraphScriptDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.GraphScript;
import com.redip.exception.QualityException;
import com.redip.log.Logger;
import com.redip.service.IGraphLayoutService;
import com.redip.service.IGraphGenerationService;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Level;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Minute;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class GraphGenerationServiceImpl implements IGraphGenerationService {

    @Autowired
    IGraphLayoutService graphGenerationService;
    @Autowired
    DatabaseSpring databaseDao;
    @Autowired
    IGraphScriptDAO graphScriptDAO;
    
    @Override
    public List<GraphScript> getScript(String title) throws QualityException {
        return graphScriptDAO.findGraphScriptByTitle(title);
    }

    @Override
    public List<List<String>> getDataForGraph(String script) {
        List<List<String>> result = new ArrayList();
        result = databaseDao.getDataForGraph(script);
        return result;
    }

    @Override
    public BufferedImage generateLineGraph(List<List<String>> data,
            List<List<String>> historicData, String versus, String title, String xLabel) {

        BufferedImage result = null;
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();

        try {
            /*Create timeseries with the data*/
            String timeseriesname = "";
            TimeSeries timeseries = new TimeSeries("", Minute.class);
            String s = "init";
            for (List<String> row : data) {
                if (!row.get(0).equals(s)) {
                    if (!s.equals("init")) {
                        timeseriescollection.addSeries(timeseries);
                    }
                    s = row.get(0);
                    timeseriesname = row.get(0);
                    timeseries = new TimeSeries(timeseriesname, Minute.class);
                }
                String tims = row.get(1).toString();
                int year = Integer.valueOf(tims.substring(0, 4));
                int month = Integer.valueOf(tims.substring(5, 7));
                int day = Integer.valueOf(tims.substring(8, 10));
                int hour = Integer.valueOf(tims.substring(11, 13));
                int min = Integer.valueOf(tims.substring(14, 16));
                float value = Float.valueOf(row.get(2).toString());
                timeseries.addOrUpdate(new Minute(min, hour, day, month, year), value);
            }
            timeseriescollection.addSeries(timeseries);

            int countSerie = timeseriescollection.getSeriesCount();
            int countHistoSerie = 0;
            if (!versus.equals("")) {
                s = "init";
                for (List<String> row : historicData) {

                    if (!row.get(0).equals(s)) {
                        if (!s.equals("init")) {
                            timeseriescollection.addSeries(timeseries);
                        }
                        s = row.get(0);
                        timeseriesname = row.get(0) + " (" + versus + " Days)";
                        timeseries = new TimeSeries(timeseriesname, Minute.class);
                    }
                    Calendar cal = Calendar.getInstance();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    cal.setTime(dateFormat.parse(row.get(1).toString()));
                    cal.add(Calendar.DATE, -Integer.valueOf(versus));


                    String tims = dateFormat.format(cal.getTime());
                    int year = Integer.valueOf(tims.substring(0, 4));
                    int month = Integer.valueOf(tims.substring(5, 7));
                    int day = Integer.valueOf(tims.substring(8, 10));
                    int hour = Integer.valueOf(tims.substring(11, 13));
                    int min = Integer.valueOf(tims.substring(14, 16));
                    float value = Float.valueOf(row.get(2).toString());
                    timeseries.addOrUpdate(new Minute(min, hour, day, month, year), value);

                }
                timeseriescollection.addSeries(timeseries);
                countHistoSerie = timeseriescollection.getSeriesCount() - countSerie;
            }
            result = graphGenerationService.generateLineChart(timeseriescollection, title, xLabel, title, countSerie, countHistoSerie);
            //result = graphGenerationService.generateLineChart(defaultcategorydataset, timeseriesname, 4);
        } catch (ParseException ex) {
            Logger.log(GraphGenerationServiceImpl.class.getName(), Level.FATAL, ex.toString());
        }

        return result;

    }

    
    @Override
    public BufferedImage generateGanttGraph(List<List<String>> data, String title, String xLabel) {

        BufferedImage bi = null;
        TaskSeries taskseries = new TaskSeries("Scheduled");

        try {
//                String datatodisplay = "/c//dat/Chain1/p/2001/09/05 07:09/p/2001/09/05 08:19/p/1.0/d/"
//                         + "Chain2/p/2001/09/05 08:09/p/2001/09/05 17:00/p/0.0/d/"
//                         + "Chain3/p/2001/09/05 07:29/p/2001/09/05 07:59/p/1.0/d/"
//                         + "Chain4/p/2001/09/05 07:19/p/2001/09/05 14:39/p/1.0";
//                
            SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd hh:mm");

            for (int xxx = 0; xxx < data.size(); xxx++) {
                Date dateFin;

                dateFin = dateSimple.parse(data.get(xxx).get(2));

                Date dateDeb = dateSimple.parse(data.get(xxx).get(1));
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateDeb);
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(dateFin);

                double status = Double.valueOf(data.get(xxx).get(3));
                long milliDeb = cal.getTimeInMillis();
                long milliFin = cal2.getTimeInMillis();

                Task task = new Task(data.get(xxx).get(0), new SimpleTimePeriod(milliDeb, milliFin));
                task.setPercentComplete(status);

                taskseries.add(task);
            }

            TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
            taskseriescollection.add(taskseries);

            bi = graphGenerationService.generateGanttChart(taskseriescollection, title, xLabel);

        } catch (ParseException ex) {
            Logger.log(GraphGenerationServiceImpl.class.getName(), Level.FATAL, ex.toString());
        }

        return bi;
    }

    @Override
    public BufferedImage generateDualAxisGraph(List<List<String>> data,
            List<List<String>> dataBar, List<List<String>> dataSla, String title, String xLabel) {
        BufferedImage result = null;
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        TimeSeriesCollection timeseriescollection2 = new TimeSeriesCollection();
        TimeSeriesCollection timeseriescollectionSla = new TimeSeriesCollection();
        try {
            /*Create timeseries with the data*/
            String timeseriesname = "";
            TimeSeries timeseries = new TimeSeries("", Minute.class);
            String s = "init";
            for (List<String> row : data) {
                if (!row.get(0).equals(s)) {
                    if (!s.equals("init")) {
                        timeseriescollection.addSeries(timeseries);
                    }
                    s = row.get(0);
                    timeseriesname = row.get(0);
                    timeseries = new TimeSeries(timeseriesname, Minute.class);
                }
                String tims = row.get(1).toString();
                int year = Integer.valueOf(tims.substring(0, 4));
                int month = Integer.valueOf(tims.substring(5, 7));
                int day = Integer.valueOf(tims.substring(8, 10));
                int hour = Integer.valueOf(tims.substring(11, 13));
                int min = Integer.valueOf(tims.substring(14, 16));
                float value = Float.valueOf(row.get(2).toString());
                timeseries.addOrUpdate(new Minute(min, hour, day, month, year), value);
            }
            timeseriescollection.addSeries(timeseries);

            int countSerie = timeseriescollection.getSeriesCount();

            String timeseriesname2 = "";
            TimeSeries timeseries2 = new TimeSeries("", Minute.class);
            String s2 = "init";
            for (List<String> row : dataBar) {
                if (!row.get(0).equals(s2)) {
                    if (!s2.equals("init")) {
                        timeseriescollection2.addSeries(timeseries2);
                    }
                    s2 = row.get(0);
                    timeseriesname2 = row.get(0);
                    timeseries2 = new TimeSeries(timeseriesname2, Minute.class);
                }
                String tims = row.get(1).toString();
                int year = Integer.valueOf(tims.substring(0, 4));
                int month = Integer.valueOf(tims.substring(5, 7));
                int day = Integer.valueOf(tims.substring(8, 10));
                int hour = Integer.valueOf(tims.substring(11, 13));
                int min = Integer.valueOf(tims.substring(14, 16));
                float value = Float.valueOf(row.get(2).toString());
                timeseries2.addOrUpdate(new Minute(min, hour, day, month, year), value);
            }
            timeseriescollection2.addSeries(timeseries2);
            int countBarSerie = timeseriescollection2.getSeriesCount();

            String timeseriesnameSla = "";
            TimeSeries timeseriesSla = new TimeSeries("", Minute.class);
            String s3 = "init";
            for (List<String> row : dataSla) {
                if (!row.get(0).equals(s3)) {
                    if (!s3.equals("init")) {
                        timeseriescollectionSla.addSeries(timeseriesSla);
                    }
                    s3 = row.get(0);
                    timeseriesnameSla = row.get(0);
                    timeseriesSla = new TimeSeries(timeseriesnameSla, Minute.class);
                }
                String tims = row.get(1).toString();
                int year = Integer.valueOf(tims.substring(0, 4));
                int month = Integer.valueOf(tims.substring(5, 7));
                int day = Integer.valueOf(tims.substring(8, 10));
                int hour = Integer.valueOf(tims.substring(11, 13));
                int min = Integer.valueOf(tims.substring(14, 16));
                float value = Float.valueOf(row.get(2).toString());
                timeseriesSla.addOrUpdate(new Minute(min, hour, day, month, year), value);
            }
            timeseriescollectionSla.addSeries(timeseriesSla);


            result = graphGenerationService.generateDualAxisChart(timeseriescollection,
                    timeseriescollection2, timeseriescollectionSla, title,
                    xLabel, s2, countSerie, countBarSerie);

        } catch (Exception ex) {
            Logger.log(GraphGenerationServiceImpl.class.getName(), Level.FATAL, ex.toString());
        }

        return result;
    }

    
    @Override
    public BufferedImage generateHorizontalBarGraph(List<List<String>> data, String title, String xLabel) {
        BufferedImage bi = null;

        try {
            DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

            for (List<String> listOfData : data) {

                String value = listOfData.get(0) + "D";
                String serie = listOfData.get(1);
                String name = listOfData.get(2);
                defaultcategorydataset.addValue(new Double(value), serie, name);
                Logger.log(GraphGenerationServiceImpl.class.getName(), Level.FATAL, value + "/" + serie + "/" + name);
            }

            bi = graphGenerationService.generateHorizontalBarChart(defaultcategorydataset, title, data.size());

        } catch (Exception ex) {
            Logger.log(GraphGenerationServiceImpl.class.getName(), Level.FATAL, ex.toString());
        }
        return bi;
    }

    @Override
    public BufferedImage generatePieGraph(List<List<String>> data, String title) {
        BufferedImage bi = null;

        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        for (List<String> listOfData : data) {
            String legend = "\"" + listOfData.get(0) + "\"";
            String dbl = String.valueOf(listOfData.get(1)) + "D";
            defaultpiedataset.setValue(legend, new Double(dbl));

        }

        bi = graphGenerationService.generatePieChart(defaultpiedataset, title, data.size());

        return bi;
    }

    @Override
    public String convertStringDate(String date, String type, Integer versus) {
        String result = null;

        try {

            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            Date yesterday = cal.getTime();
            cal.add(Calendar.DATE, -1);
            Date dayBefore = cal.getTime();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (type.equals("fromDate")) {
                if (date.equals("TODAY")) {
                    cal.setTime(yesterday);
                    cal.add(Calendar.DATE, Integer.valueOf(versus) - 1);
                    result = dateFormat.format(cal.getTime());
                } else if (date.equals("YESTERDAY")) {
                    cal.setTime(dayBefore);
                    cal.add(Calendar.DATE, Integer.valueOf(versus) - 1);
                    result = dateFormat.format(cal.getTime());
                } else {
                    cal.setTime(dateFormat.parse(date));
                    cal.add(Calendar.DATE, Integer.valueOf(versus) - 1);
                    result = dateFormat.format(cal.getTime());
                }
            }

            if (type.equals("toDate")) {
                if (date.equals("TODAY")) {
                    cal.setTime(today);
                    cal.add(Calendar.DATE, Integer.valueOf(versus) - 1);
                    result = dateFormat.format(cal.getTime());
                } else if (date.equals("YESTERDAY")) {
                    cal.setTime(yesterday);
                    cal.add(Calendar.DATE, Integer.valueOf(versus) - 1);
                    result = dateFormat.format(cal.getTime());
                } else {
                    cal.setTime(dateFormat.parse(date));
                    cal.add(Calendar.DATE, Integer.valueOf(versus) - 1);
                    result = dateFormat.format(cal.getTime());

                }
            }

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(GraphGenerationServiceImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return result;
    }
}
