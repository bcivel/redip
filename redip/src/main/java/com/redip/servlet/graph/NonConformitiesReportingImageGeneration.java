/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.graph;

import com.redip.service.IQualityNonconformitiesReportingService;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class NonConformitiesReportingImageGeneration extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");

        ServletOutputStream os = response.getOutputStream();
        String img = request.getParameter("source");

        String fromDate = "2012-11-01";
        if (request.getParameter("fromDate") != null) {
            fromDate = request.getParameter("fromDate");
        }
        String toDate = "2012-11-01";
        if (request.getParameter("toDate") != null) {
            toDate = request.getParameter("toDate");
        }
        String country = "all";
        if (request.getParameter("country") != null) {
            country = request.getParameter("country");
        }
        String indicator = "";
        if (request.getParameter("indicator") != null) {
            indicator = request.getParameter("indicator");
        }
        String versus = "";
        if (request.getParameter("versus") != null) {
            versus = request.getParameter("versus");
        }
        String splitby = "";
        if (request.getParameter("splitby") != null) {
            splitby = request.getParameter("splitby");
        }

        if (img.equals("toto")) {
            ImageIO.write(getChart(request, fromDate, toDate, country), "png", os);
        }

        if (img.equals("tata")) {
            ImageIO.write(getChart2(request, fromDate, toDate, country), "png", os);
        }

        if (img.equals("titi")) {
            ImageIO.write(getChart3(request, fromDate, toDate, country), "png", os);
        }

        if (img.equals("tutu")) {
            ImageIO.write(getChart4(request, fromDate, toDate, country), "png", os);
        }


        if (img.equals("tete")) {
            ImageIO.write(getChart5(request, fromDate, toDate, country), "png", os);
        }

        if (img.equals("tyty")) {
            ImageIO.write(getChart6(request, fromDate, toDate, country), "png", os);
        }

        if (img.equals("performance")) {
            ImageIO.write(getChart8(request, fromDate, toDate, country), "png", os);
        }



        os.close();
    }

    private RenderedImage getChart(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.nonconformitiesPerProblemCategory(appContext, fromDate, toDate, country);
        return bi;
    }

    private RenderedImage getChart2(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.nonconformitiesPerSeverity(appContext, fromDate, toDate, country);

        return bi;
    }

    private RenderedImage getChart3(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.impactPerRootCause(appContext, fromDate, toDate, country);

        return bi;
    }

    private RenderedImage getChart4(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.unavailabilityPerDay(appContext, fromDate, toDate, country);

        return bi;
    }

    private RenderedImage getChart5(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.toto(appContext, fromDate, toDate, country);

        return bi;
    }

    private RenderedImage getChart6(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.nonconformityPerWeek(appContext, fromDate, toDate, country);

        return bi;
    }

    private RenderedImage getChart8(HttpServletRequest request, String fromDate, String toDate, String country) {
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesReportingService qualityNonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);

        BufferedImage bi = qualityNonconformitiesReportingService.performancePerDay(appContext, fromDate, toDate, country);

        return bi;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
