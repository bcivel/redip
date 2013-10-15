/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.QualityNonconformities;
import com.redip.log.Logger;
import com.redip.service.IQualityNonconformitiesService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author bcivel
 */
public class NonConformityDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String echo = request.getParameter("sEcho");
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");
        String dir = "asc";
        String[] cols = {"Idqualitynonconformities", "ProblemTitle",
            "ProblemDescription", "Status", "Severity", " "};
        String[] creator = null;
        String fromDate = null;
        String toDate = null;
        String[] applicationFunctionnality = null;
        String[] statusList = null;

        if (request.getParameterValues("creator") != null) {
            creator = request.getParameterValues("creator");
        }
        if (request.getParameterValues("fromDate") != null) {
            fromDate = request.getParameter("fromDate");
        }
        if (request.getParameterValues("toDate") != null) {
            toDate = request.getParameter("toDate");
        }
        if (request.getParameterValues("applicationFunctionnality") != null) {
            applicationFunctionnality = request.getParameterValues("applicationFunctionnality");
        }
        if (request.getParameterValues("status") != null) {
            statusList = request.getParameterValues("status");
        }

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        int amount = 10;
        int start = 0;
        int col = 0;

        String idqualitynonconformities = "";
        String problemTitle = "";
        String problemDescription = "";
        String status = "";
        String severity = "";


        idqualitynonconformities = request.getParameter("sSearch_0");
        problemTitle = request.getParameter("sSearch_1");
        problemDescription = request.getParameter("sSearch_2");
        status = request.getParameter("sSearch_3");
        severity = request.getParameter("sSearch_4");

        List<String> sArray = new ArrayList<String>();
        if (!idqualitynonconformities.equals("")) {
            String sIdqualitynonconformities = " idqualitynonconformities like '%" + idqualitynonconformities + "%'";
            sArray.add(sIdqualitynonconformities);
        }
        if (!problemTitle.equals("")) {
            String sProblemTitle = " ProblemTitle like '%" + problemTitle + "%'";
            sArray.add(sProblemTitle);
        }
        if (!problemDescription.equals("")) {
            String sProblemDescription = " problemDescription like '%" + problemDescription + "%'";
            sArray.add(sProblemDescription);
        }
        if (!status.equals("")) {
            String sStatus = " status like '%" + status + "%'";
            sArray.add(sStatus);
        }
        if (!severity.equals("")) {
            String sSeverity = " severity like '%" + severity + "%'";
            sArray.add(sSeverity);
        }
        if (creator != null) {
            String sCreator = " (";
            for (int a = 0; a < creator.length - 1; a++) {
                sCreator += " Detection like '%" + creator[a] + "%' or";
            }
            sCreator += " Detection like '%" + creator[creator.length - 1] + "%') ";
            sArray.add(sCreator);
        }
//        if (fromDate != null) {
//        String sFromDate = " fromDate > '" + fromDate + "'";
//        sArray.add(sFromDate);
//        }
//        if (toDate != null) {
//        String sFromDate = " fromDate > '" + toDate + "'";
//        sArray.add(sFromDate);
//        }
        if (applicationFunctionnality != null) {
            String sApplicationFunctionnality = " (";
            for (int a = 0; a < applicationFunctionnality.length - 1; a++) {
                sApplicationFunctionnality += " applicationFunctionnality like '%" + applicationFunctionnality[a] + "%' or";
            }
            sApplicationFunctionnality += " applicationFunctionnality like '%" + applicationFunctionnality[applicationFunctionnality.length - 1] + "%') ";
            sArray.add(sApplicationFunctionnality);
        }
        if (statusList != null) {
            String sStatusList = " (";
            for (int a = 0; a < statusList.length - 1; a++) {
                sStatusList += " status like '%" + statusList[a] + "%' or";
            }
            sStatusList += " status like '%" + statusList[statusList.length - 1] + "%') ";
            sArray.add(sStatusList);
        }

        StringBuilder individualSearch = new StringBuilder();
        if (sArray.size() == 1) {
            individualSearch.append(sArray.get(0));
        } else if (sArray.size() > 1) {
            for (int i = 0; i < sArray.size() - 1; i++) {
                individualSearch.append(sArray.get(i));
                individualSearch.append(" and ");
            }
            individualSearch.append(sArray.get(sArray.size() - 1));
        }

        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10 || amount > 100) {
                amount = 10;
            }
        }

        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0 || col > 5) {
                col = 0;
            }
        }
        if (sdir != null) {
            if (!sdir.equals("asc")) {
                dir = "desc";
            }
        }
        String colName = cols[col];

        String searchTerm = "";
        if (!request.getParameter("sSearch").equals("")) {
            searchTerm = request.getParameter("sSearch");
        }


        String inds = String.valueOf(individualSearch);

        JSONArray data = new JSONArray(); //data that will be shown in the table

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesService qualityNonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);

        QualityNonconformities numberOfNC = qualityNonconformitiesService.getNumberOfNonconformities();
        List<QualityNonconformities> nonconformitieslist = qualityNonconformitiesService.getAllNonconformities(start, amount, colName, dir, searchTerm, inds);

        try {
            JSONObject jsonResponse = new JSONObject();

            for (QualityNonconformities listofnonconformities : nonconformitieslist) {
                JSONArray row = new JSONArray();
                //"<a href=\'javascript:popup(\"qualitynonconformitydetails.jsp?ncid="+listofnonconformities.getIdqualitynonconformities()+"\")\'>"+listofnonconformities.getIdqualitynonconformities()+"</a>"
                row.put(listofnonconformities.getIdqualitynonconformities())
                        .put(listofnonconformities.getProblemTitle())
                        .put(listofnonconformities.getProblemDescription())
                        //                        .put(listofnonconformities.getRootCauseCategory())
                        //                        .put(listofnonconformities.getRootCauseDescription())
                        //                        .put(listofnonconformities.getResponsabilities())
                        .put(listofnonconformities.getStatus())
                        //                        .put(listofnonconformities.getComments())
                        .put(listofnonconformities.getSeverity())
                        .put("<a href=\"qualitynonconformitydetails.jsp?ncid=" + listofnonconformities.getIdqualitynonconformities() + "\">edit</a>");
                data.put(row);
            }
            jsonResponse.put("aaData", data);
            jsonResponse.put("sEcho", echo);
            jsonResponse.put("iTotalRecords", numberOfNC.getCount());
            jsonResponse.put("iDisplayLength", data.length());
            jsonResponse.put("iTotalDisplayRecords", numberOfNC.getCount());

            response.setContentType("application/json");
            response.getWriter().print(jsonResponse.toString());
        } catch (JSONException e) {
            Logger.log(NonConformityDetails.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
