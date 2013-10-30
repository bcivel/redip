/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.rootcause;

import com.redip.servlet.nonconformity.NonConformityDetails;
import com.redip.entity.QualityNonconformitiesRootCause;
import com.redip.log.Logger;
import com.redip.service.IQualityNonconformitiesRootCauseService;
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
 *
 * @author bcivel
 */
public class NonConformityRootCause extends HttpServlet {

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
        String[] cols = {"Idqualitynonconformitiesrootcause", "RootCauseCategory",
            "RootCauseDescription", "Status", "Severity", " "};

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        int amount = 10;
        int start = 0;
        int col = 0;

        String idqualitynonconformitiesrootcause = "";
        String rootCauseCategory = "";
        String rootCauseDescription = "";
        String status = "";
        String severity = "";


        idqualitynonconformitiesrootcause = request.getParameter("sSearch_0");
        rootCauseCategory = request.getParameter("sSearch_1");
        rootCauseDescription = request.getParameter("sSearch_2");
        status = request.getParameter("sSearch_3");
        severity = request.getParameter("sSearch_4");

        List<String> sArray = new ArrayList<String>();
        if (!idqualitynonconformitiesrootcause.equals("")) {
            String sidqualitynonconformitiesrootcause = " idqualitynonconformitiesrootcause like '%" + idqualitynonconformitiesrootcause + "%'";
            sArray.add(sidqualitynonconformitiesrootcause);
        }
        if (!rootCauseCategory.equals("")) {
            String srootCauseCategory = " rootCauseCategory like '%" + rootCauseCategory + "%'";
            sArray.add(srootCauseCategory);
        }
        if (!rootCauseDescription.equals("")) {
            String srootCauseDescription = " rootCauseDescription like '%" + rootCauseDescription + "%'";
            sArray.add(srootCauseDescription);
        }
        if (!status.equals("")) {
            String sStatus = " status like '%" + status + "%'";
            sArray.add(sStatus);
        }
        if (!severity.equals("")) {
            String sSeverity = " severity like '%" + severity + "%'";
            sArray.add(sSeverity);
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
        IQualityNonconformitiesRootCauseService qualityNonconformitiesService = appContext.getBean(IQualityNonconformitiesRootCauseService.class);

        List<QualityNonconformitiesRootCause> nonconformitieslist = qualityNonconformitiesService.findAllNonconformitiesRootCause(start, amount, colName, dir, searchTerm, inds);
        Integer size = nonconformitieslist.size();

        try {
            JSONObject jsonResponse = new JSONObject();

            for (QualityNonconformitiesRootCause listofnonconformities : nonconformitieslist) {
                JSONArray row = new JSONArray();
                //"<a href=\'javascript:popup(\"qualitynonconformitydetails.jsp?ncid="+listofnonconformities.getIdqualitynonconformities()+"\")\'>"+listofnonconformities.getIdqualitynonconformities()+"</a>"
                row.put(listofnonconformities.getIdqualitynonconformitiesrootcause())
                        .put(listofnonconformities.getRootCauseCategory())
                        .put(listofnonconformities.getRootCauseDescription())
                        .put(listofnonconformities.getStatus())
                        .put(listofnonconformities.getSeverity())
                        .put("<a href=\"qualitynonconformityrootcausedetails.jsp?rcid=" + listofnonconformities.getIdqualitynonconformitiesrootcause() + "\">edit</a>");
                data.put(row);
            }
            jsonResponse.put("aaData", data);
            jsonResponse.put("sEcho", echo);
            jsonResponse.put("iTotalRecords", size);
            jsonResponse.put("iDisplayLength", data.length());
            jsonResponse.put("iTotalDisplayRecords", size);

            response.setContentType("application/json");
            response.getWriter().print(jsonResponse.toString());
        } catch (JSONException e) {
            Logger.log(NonConformityDetails.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
