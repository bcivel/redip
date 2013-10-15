/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.database.Database;
import com.redip.entity.Graph;
import com.redip.entity.GraphScript;
import com.redip.log.Logger;
import com.redip.service.IGraphGenerationService;
import com.redip.service.IGraphScriptService;
import com.redip.service.IGraphService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
@WebServlet
public class GeneratePicture extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();

        try {
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            Date yesterday = cal.getTime();
            cal.add(Calendar.DATE, -1);
            Date dayBefore = cal.getTime();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String graphName = request.getParameter("graph");

            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IGraphService loadService = appContext.getBean(IGraphService.class);
            IGraphScriptService gsService = appContext.getBean(IGraphScriptService.class);
            IGraphGenerationService ggs = appContext.getBean(IGraphGenerationService.class);
            Logger.log(GeneratePicture.class.getName(), Level.INFO, graphName);
            Database dtb = appContext.getBean(Database.class);

            Graph graph = loadService.findGraphByID(graphName);
            String title = graph.getTitle();
            String xLabel = "";
            String type = graph.getType();
            String fromDate = "";
            String toDate = "";

            Logger.log(GeneratePicture.class.getName(), Level.INFO, type);

            List<GraphScript> gsList = gsService.findGraphScriptByGraphName(graphName);

            List<List<String>> resultSetBar = new ArrayList();

            for (GraphScript indGraph : gsList) {
                List<List<String>> rs;

                String script = indGraph.getScript();

//                fromDate = yesterday.toString();
//                if (request.getParameter("fromdate") != null) {
//                    if (request.getParameter("fromdate").equals("TODAY")) {
//                        fromDate = dateFormat.format(yesterday);
//                    } else if (request.getParameter("fromdate").equals("YESTERDAY")) {
//                        fromDate = dateFormat.format(dayBefore);
//                    } else {
//                        cal.setTime(dateFormat.parse(request.getParameter("fromdate")));
//                        cal.add(Calendar.DATE, -1);
//                        fromDate = dateFormat.format(cal.getTime());
//                    }
//                    script = script.replace("%FROMDATE%", "\'" + fromDate + "\'");
//
//                }
//
//                toDate = today.toString();
//                if (request.getParameter("todate") != null) {
//
//                    if (request.getParameter("todate").equals("TODAY")) {
//                        toDate = dateFormat.format(today);
//                    } else if (request.getParameter("todate").equals("YESTERDAY")) {
//                        toDate = dateFormat.format(yesterday);
//                    } else {
//                        toDate = request.getParameter("todate");
//                    }
//                    script = script.replace("%TODATE%", "\'" + toDate + "\'");
//                }



                rs = dtb.getDataForGraph(script);

//                if (type.equals("bar")) {
                resultSetBar.addAll(rs);
//                }


            }

//            xLabel = fromDate + " / " + toDate;

            BufferedImage toto = null;


            if (type.equals("bar")) {
                toto = ggs.generateHorizontalBarGraph(
                        resultSetBar, title, xLabel);
            }
            if (type.equals("gantt")) {
                toto = ggs.generateGanttGraph(resultSetBar, title, xLabel);
            }

            ImageIO.write(toto, "png", os);
            os.close();

        } finally {
            os.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.log(GeneratePicture.class.getName(), Level.FATAL, ex.toString());
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.log(GeneratePicture.class.getName(), Level.FATAL, ex.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
