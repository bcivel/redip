/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesReportingDAO;
import com.redip.entity.calculated.NonconformitiesReporting;
import com.redip.service.IGraphLayoutService;
import com.redip.service.IQualityNonconformitiesReportingService;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesReportingServiceImpl implements IQualityNonconformitiesReportingService {

    @Autowired
    private IQualityNonconformitiesReportingDAO qualityNonconformitiesReportingDao;
    @Autowired
    private IGraphLayoutService graphGenerationService;

    @Override
    public String rootCauseDescriptionList(String fromDate, String toDate, String country) {

        String nonc = "<table>";
        nonc += "<tr style=\"background-color:#cad3f1\"><td>Date</td><td>Duration</td><td>Severity</td><td>Category</td><td>Description</td><td>LostOrders</td></tr>";

        List<NonconformitiesReporting> ncr = qualityNonconformitiesReportingDao.getRootCauseDescriptionList(fromDate, toDate, country);
        int i = 1;
        for (NonconformitiesReporting ncrList : ncr) {
            if (i % 2 == 0) {
                nonc += "<tr style=\"background-color:#f3f6fa\"><td>";
            } else {
                nonc += "<tr><td>";
            }
            nonc += ncrList.getStartDate();
            nonc += "</td><td>";
            nonc += ncrList.getUnavailabilityDuration();
            nonc += "</td><td>";
            nonc += ncrList.getSeverity();
            nonc += "</td><td>";
            nonc += ncrList.getRootCauseCategory();
            nonc += "</td><td>";
            nonc += ncrList.getRootCauseDescritpion();
            nonc += "</td><td>";
            nonc += "<a href=\"http://192.168.134.35/index/qualitynonconformitydetails.jsp?ncid=";
            nonc += ncrList.getId();
            nonc += "\">";
            nonc += ncrList.getNumberOfLostOrders();
            nonc += "</a></td></tr>";
            i = i + 1;
        }
        nonc += "</table>";


        return nonc;
    }

    @Override
    public BufferedImage impactPerResp(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.getNonconformitiesPerResponsabilities(fromDate, toDate, country);
        String name = "Orders Lost per Responsability";

        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        int i = 0;
        for (NonconformitiesReporting ncr : ncrList) {
            String title = "\"" + ncr.getResponsabilities() + "\"";
            String dbl = String.valueOf(ncr.getNumberOfLostOrders()) + "D";
            defaultpiedataset.setValue(title, new Double(dbl));
            i++;
        }

        bi = graphGenerationService.generatePieChart(defaultpiedataset, name, i);

        return bi;
    }

    @Override
    public BufferedImage nonconformitiesPerProblemCategory(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.getNonconformitiesPerProblemCategory(fromDate, toDate, country);
        String name = "Nonconformities per Category";

        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        int i = 0;
        for (NonconformitiesReporting ncr : ncrList) {
            String title = "\"" + ncr.getProblemCategory() + "\"";
            String dbl = String.valueOf(ncr.getNumberOfNonconformities()) + "D";
            defaultpiedataset.setValue(title, new Double(dbl));
            i++;
        }

        bi = graphGenerationService.generatePieChart(defaultpiedataset, name, i);

        return bi;
    }

    @Override
    public BufferedImage impactPerRootCause(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.getNonconformitiesPerRootCause(fromDate, toDate, country);
        String name = "Orders Lost per Root Cause";

        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        int i = 0;
        for (NonconformitiesReporting ncr : ncrList) {
            String title = "\"" + ncr.getRootCauseCategory() + "\"";
            String dbl = String.valueOf(ncr.getNumberOfLostOrders()) + "D";
            defaultpiedataset.setValue(title, new Double(dbl));
            i++;
        }

        bi = graphGenerationService.generatePieChart(defaultpiedataset, name, i);

        return bi;
    }

    @Override
    public String unavailability(String fromDate, String toDate, String country) {

        String nonc = "Total Unavailability (Hour) : ";

        nonc += qualityNonconformitiesReportingDao.unavailability(fromDate, toDate, country);
        return nonc;
    }

    @Override
    public String numberOfNonconformities(String fromDate, String toDate, String country) {

        String nonc = "Number of nonconformities opened : ";

        nonc += qualityNonconformitiesReportingDao.numberOfNonconformities(fromDate, toDate, country);
        return nonc;
    }

    @Override
    public String orderLost(String fromDate, String toDate, String country) {

        String nonc = "Number of Lost Orders : ";

        nonc += qualityNonconformitiesReportingDao.orderLost(fromDate, toDate, country);
        return nonc;
    }

    @Override
    public BufferedImage unavailabilityPerDay(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.unavailabilityPerDay(fromDate, toDate, country);
        String name = "Availibility (%)";
        Color color = Color.getColor("red");

        try {
            //Date DatePageStart = new Date() ;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fDate = new Date();
            fDate = formatter.parse(fromDate.toString());
            Date tDate = formatter.parse(toDate.toString());

            DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

            GregorianCalendar calendar = new java.util.GregorianCalendar();


            for (Date start = fDate; !start.equals(tDate);) {

                String title = new SimpleDateFormat("yyyy-MM-dd").format(start);
                int dbi = 100;
                for (NonconformitiesReporting ncr : ncrList) {
                    if (ncr.getStartDate().equals(title)) {
                        dbi -= ncr.getNumberOfNonconformities() * 100 / 24;
                    }
                }
                String dbl = String.valueOf(dbi) + "D";
                defaultcategorydataset.addValue(new Double(dbl), name, title);
                calendar.setTime(start);
                calendar.add(Calendar.DATE, 1);
                start = calendar.getTime();
            }
            int i = 0;

            bi = graphGenerationService.generateBarChart(defaultcategorydataset, name, i, color);

        } catch (ParseException ex) {
            Logger.getLogger(QualityNonconformitiesReportingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bi;
    }

    @Override
    public BufferedImage performancePerDay(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.performancePerDay(fromDate, toDate, country);
        String name = "Performance (%)";
        Color color = Color.getColor("blue");

        try {
            //Date DatePageStart = new Date() ;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fDate = new Date();
            fDate = formatter.parse(fromDate.toString());
            Date tDate = formatter.parse(toDate.toString());

            DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

            GregorianCalendar calendar = new java.util.GregorianCalendar();


            for (Date start = fDate; !start.equals(tDate);) {

                String title = new SimpleDateFormat("yyyy-MM-dd").format(start);
                int dbi = 100;
                for (NonconformitiesReporting ncr : ncrList) {
                    if (ncr.getStartDate().equals(title)) {
                        dbi -= ncr.getNumberOfNonconformities() * 100 / 24;
                    }
                }
                String dbl = String.valueOf(dbi) + "D";
                defaultcategorydataset.addValue(new Double(dbl), name, title);
                calendar.setTime(start);
                calendar.add(Calendar.DATE, 1);
                start = calendar.getTime();
            }
            int i = 0;

            bi = graphGenerationService.generateBarChart(defaultcategorydataset, name, i, color);

        } catch (ParseException ex) {
            Logger.getLogger(QualityNonconformitiesReportingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bi;
    }

    @Override
    public BufferedImage nonconformityPerWeek(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        String name = "Non Conformities per Week";


        try {
            //Date DatePageStart = new Date() ;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            GregorianCalendar calendar = new java.util.GregorianCalendar();

            Date fDate = new Date();
            fDate = formatter.parse(fromDate.toString());
            Date tDate = formatter.parse(toDate.toString());

            calendar.setTime(fDate);
            calendar.add(Calendar.DATE, -56);
            Date init = calendar.getTime();
            String end = new SimpleDateFormat("yyyy-MM-dd").format(init);

            List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.nonconformityPerWeek(end, fromDate, country);

            DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();


            for (NonconformitiesReporting ncr : ncrList) {

                defaultcategorydataset.addValue(ncr.getNumberOfNonconformities(), ncr.getProblemCategory(), String.valueOf(ncr.getWeekNumber()).concat("/").concat("2012"));
            }


            bi = graphGenerationService.generateLineChart(defaultcategorydataset, name, ncrList.size());

        } catch (ParseException ex) {
            Logger.getLogger(QualityNonconformitiesReportingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bi;
    }

    @Override
    public BufferedImage toto(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        //List<NonconformitiesReporting> ncrList = nonconformitiesReportingDao.getNonconformitiesPerRootCause(fromDate, toDate);
        String name = "toto";
        Color color = Color.getColor("blue");

        String s = "First";
        String s1 = "Second";
        String s2 = "Third";
        String s3 = "Category 1";
        String s4 = "Category 2";
        String s5 = "Category 3";
        String s6 = "Category 4";
        String s7 = "Category 5";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(1.0D, s, s3);
        defaultcategorydataset.addValue(4D, s, s4);
        defaultcategorydataset.addValue(3D, s, s5);
        defaultcategorydataset.addValue(5D, s, s6);
        defaultcategorydataset.addValue(5D, s, s7);
        defaultcategorydataset.addValue(5D, s1, s3);
        defaultcategorydataset.addValue(7D, s1, s4);
        defaultcategorydataset.addValue(6D, s1, s5);
        defaultcategorydataset.addValue(8D, s1, s6);
        defaultcategorydataset.addValue(4D, s1, s7);
        defaultcategorydataset.addValue(4D, s2, s3);
        defaultcategorydataset.addValue(3D, s2, s4);
        defaultcategorydataset.addValue(2D, s2, s5);
        defaultcategorydataset.addValue(3D, s2, s6);
        defaultcategorydataset.addValue(6D, s2, s7);

        int i = 5;

        bi = graphGenerationService.generateBarChart(defaultcategorydataset, name, i, color);

        return bi;
    }

    @Override
    public BufferedImage nonconformitiesPerSeverity(ApplicationContext appContext, String fromDate, String toDate, String country) {
        BufferedImage bi = null;
        List<NonconformitiesReporting> ncrList = qualityNonconformitiesReportingDao.getNonconformitiesPerSeverity(fromDate, toDate, country);
        String name = "Nonconformities per Severity";

        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        int i = 0;
        for (NonconformitiesReporting ncr : ncrList) {
            String title = "\"" + ncr.getSeverity() + "\"";
            String dbl = String.valueOf(ncr.getNumberOfNonconformities()) + "D";
            defaultpiedataset.setValue(title, new Double(dbl));
            i++;
        }

        bi = graphGenerationService.generatePieChart(defaultpiedataset, name, i);

        return bi;
    }
}
