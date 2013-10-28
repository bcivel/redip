/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redip.servlet;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesAction;
import com.redip.log.Logger;
import com.redip.service.IQualityNonconformitiesActionService;
import com.redip.service.IQualityNonconformitiesService;
import java.io.IOException;
import java.io.PrintWriter;
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
 *
 * @author bcivel
 */
public class NonConformityActionList extends HttpServlet {

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
        String[] cols = {"Idqualitynonconformitiesaction", "action",
            "deadline", "follower", "status", "date", "percentage", "priority"};
        String[] follower = null;
        String[] date = null;
        String[] statusList = null;
        String[] deadline = null;
        String[] priority = null;

        if (request.getParameterValues("follower") != null) {
            follower = request.getParameterValues("follower");
        }
        if (request.getParameterValues("date") != null) {
            date = request.getParameterValues("date");
        }
        if (request.getParameterValues("status") != null) {
            statusList = request.getParameterValues("status");
        }
        if (request.getParameterValues("deadline") != null) {
            deadline = request.getParameterValues("deadline");
        }
        if (request.getParameterValues("priority") != null) {
            priority = request.getParameterValues("priority");
        }

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        int amount = 10;
        int start = 0;
        int col = 0;

        String field1 = "";
        String field2 = "";
        String field3 = "";
        String field4 = "";
        String field5 = "";


        field1 = request.getParameter("sSearch_0");
        field2 = request.getParameter("sSearch_1");
        field3 = request.getParameter("sSearch_2");
        field4 = request.getParameter("sSearch_3");
        field5 = request.getParameter("sSearch_4");

        List<String> sArray = new ArrayList<String>();
        if (!field1.equals("")) {
            String sIdqualitynonconformitiesaction = " idqualitynonconformitiesaction like '%" + field1 + "%'";
            sArray.add(sIdqualitynonconformitiesaction);
        }
        if (!field2.equals("")) {
            String sAction = " action like '%" + field2 + "%'";
            sArray.add(sAction);
        }
//        if (!problemDescription.equals("")) {
//            String sProblemDescription = " problemDescription like '%" + problemDescription + "%'";
//            sArray.add(sProblemDescription);
//        }
//        if (!status.equals("")) {
//            String sStatus = " status like '%" + status + "%'";
//            sArray.add(sStatus);
//        }
//        if (!severity.equals("")) {
//            String sSeverity = " severity like '%" + severity + "%'";
//            sArray.add(sSeverity);
//        }
        
        if (follower != null) {
            String sFollower = " (";
            for (int a = 0; a < follower.length - 1; a++) {
                sFollower += " follower like '%" + follower[a] + "%' or";
            }
            sFollower += " follower like '%" + follower[follower.length - 1] + "%') ";
            sArray.add(sFollower);
        }
        if (date != null) {
            String sDate = " (";
            for (int a = 0; a < date.length - 1; a++) {
                sDate += " `date` like '%" + date[a] + "%' or";
            }
            sDate += " `date` like '%" + date[date.length - 1] + "%') ";
            sArray.add(sDate);
        }
        if (deadline != null) {
            String sDeadline = " (";
            for (int a = 0; a < deadline.length - 1; a++) {
                sDeadline += " deadline like '%" + deadline[a] + "%' or";
            }
            sDeadline += " deadline like '%" + deadline[deadline.length - 1] + "%') ";
            sArray.add(sDeadline);
        }
//        if (fromDate != null) {
//        String sFromDate = " fromDate > '" + fromDate + "'";
//        sArray.add(sFromDate);
//        }
//        if (toDate != null) {
//        String sFromDate = " fromDate > '" + toDate + "'";
//        sArray.add(sFromDate);
//        }
        if (priority != null) {
            String sPriority = " (";
            for (int a = 0; a < priority.length - 1; a++) {
                sPriority += " priority like '%" + priority[a] + "%' or";
            }
            sPriority += " priority like '%" + priority[priority.length - 1] + "%') ";
            sArray.add(sPriority);
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
        IQualityNonconformitiesActionService qualityNonconformitiesActionService = appContext.getBean(IQualityNonconformitiesActionService.class);

        List<QualityNonconformitiesAction> nonconformitiesActionlist = qualityNonconformitiesActionService.getAllNonconformitiesAction(start, amount, colName, dir, searchTerm, inds);

        try {
            JSONObject jsonResponse = new JSONObject();

            for (QualityNonconformitiesAction listofnonconformitiesAction : nonconformitiesActionlist) {
                JSONArray row = new JSONArray();
                //"<a href=\'javascript:popup(\"qualitynonconformitydetails.jsp?ncid="+listofnonconformities.getIdqualitynonconformities()+"\")\'>"+listofnonconformities.getIdqualitynonconformities()+"</a>"
                row.put(listofnonconformitiesAction.getIdQualityNonconformitiesActions())
                        .put(listofnonconformitiesAction.getAction())
                        .put(listofnonconformitiesAction.getFollower())
                        //                        .put(listofnonconformities.getRootCauseCategory())
                        //                        .put(listofnonconformities.getRootCauseDescription())
                        //                        .put(listofnonconformities.getResponsabilities())
                        .put(listofnonconformitiesAction.getStatus())
                        //                        .put(listofnonconformities.getComments())
                        .put(listofnonconformitiesAction.getDeadline())
                        .put("<a href=\"qualitynonconformitydetails.jsp?ncid=" + listofnonconformitiesAction.getIdQualityNonconformities() + "\">edit</a>");
                data.put(row);
            }
            jsonResponse.put("aaData", data);
            jsonResponse.put("sEcho", echo);
            jsonResponse.put("iTotalRecords", nonconformitiesActionlist.size());
            jsonResponse.put("iDisplayLength", data.length());
            jsonResponse.put("iTotalDisplayRecords", nonconformitiesActionlist.size());

            response.setContentType("application/json");
            response.getWriter().print(jsonResponse.toString());
        } catch (JSONException e) {
            Logger.log(NonConformityActionList.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
