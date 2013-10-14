/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.QualityNonconformitiesAction;
import com.redip.log.Logger;
import com.redip.service.IParameterService;
import com.redip.service.IQualityNonconformitiesActionService;
import com.redip.service.impl.ParameterService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
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
public class UpdateActionOrder extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesActionService actionService = appContext.getBean(IQualityNonconformitiesActionService.class);

          String id = request.getParameter("id");
          String fromPosition = request.getParameter("fromPosition");
          String toPosition = request.getParameter("toPosition");
          String direction = request.getParameter("direction");

          Logger.log(UpdateActionOrder.class.getName(), Level.INFO, "fromPosition :"+fromPosition+", toPosition :"+toPosition+", direction :"+direction);
          
    if (direction.equals("back"))
    {
        Integer from = Integer.valueOf(fromPosition);
        List<QualityNonconformitiesAction> actionList = actionService.findQualityNonconformitiesUsingLimit(toPosition, fromPosition);
        for (QualityNonconformitiesAction action : actionList){
        String log = actionService.updateQualityNonConformitiesAction(action.getIdQualityNonconformitiesActions(), "priority", from.toString());
        Logger.log(UpdateActionOrder.class.getName(), Level.INFO,log);
        from -= 1;
        }
        
    }
    else
    {
        Integer to = Integer.valueOf(toPosition);
        List<QualityNonconformitiesAction> actionList = actionService.findQualityNonconformitiesUsingLimit(fromPosition, toPosition);
        for (QualityNonconformitiesAction action : actionList){
        String log = actionService.updateQualityNonConformitiesAction(action.getIdQualityNonconformitiesActions(), "priority", to.toString());
        Logger.log(UpdateActionOrder.class.getName(), Level.INFO,log);
        to -= 1;
        }
    }

        } finally {            
            out.close();
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
