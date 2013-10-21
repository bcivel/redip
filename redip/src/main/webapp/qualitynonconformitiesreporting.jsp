<%-- 
    Document   : qualitynonconformitiesreporting
    Created on : 2 oct. 2012, 22:47:23
    Author     : bcivel
--%>

<%@page import="com.redip.service.impl.QualityNonconformitiesReportingServiceImpl"%>
<%@page import="com.redip.dao.IQualityNonconformitiesReportingDAO"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="com.redip.dao.impl.QualityNonconformitiesReportingDAOImpl"%>
<%@page import="com.redip.service.IQualityNonconformitiesReportingService"%>
<%@page import="java.awt.Component"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="javax.swing.JFrame"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.keypoint.PngEncoder"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.mail.util.ByteArrayDataSource"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-te-1.4.0.css">
        <link type="text/css" rel="stylesheet" href="css/jPaginate/style.css">
        <style media="screen" type="text/css">
            @import "css/demo_page.css";
            @import "css/demo_table.css";
            @import "css/demo_table_jui.css";
            @import "css/themes/base/jquery-ui.css";
            @import "css/themes/smoothness/jquery-ui-1.7.2.custom.css";
        </style>
        <link rel="shortcut icon" type="image/x-icon" href="pictures/favicon.ico" >
        <script type="text/javascript" src="javascript/jquery.js"></script>
        <script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.jeditable.mini.js"></script>
        <script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.dataTables.editable.js"></script>
        <script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.paginate.js"></script>
        <script type="text/javascript" src="javascript/jquery.datepicker.addons.js"></script>
        <script type="text/javascript" src="javascript/jquery-te-1.4.0.min.js" charset="utf-8"></script>
    </head>
    <body>
        <script>
            $(function() {
                $( 'input' ).filter('.dateClass').datepicker({dateFormat: 'yy-mm-dd'});
                
            });
        </script>
        <%@ include file="static.jsp" %>  

        <%
                
            String fromDate = "";
            if (request.getParameter("fromDate") != null) {
                fromDate = request.getParameter("fromDate");
            }
            String toDate = "";
            if (request.getParameter("toDate") != null) {
                toDate = request.getParameter("toDate");
            }
            String app = "all";
            if (request.getParameter("application") != null) {
                app = request.getParameter("application");
            }

            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IQualityNonconformitiesReportingService nonconformitiesReportingService = appContext.getBean(IQualityNonconformitiesReportingService.class);
            
            
        %>

        <form action="qualitynonconformitiesreporting.jsp" method="get" name="reporting">
            <table id="arrond">
                <tr><td id="wob" style="width: 50px; font-weight: bold;">From Date</td>
                    <td class="simpleline" style="width:127px;"><input name="fromDate" style="width:127px;" id="fromDate" class="dateClass" class="wob" value="<%=fromDate%>"></td>
                    <td class="wob" style="width: 70px; font-weight: bold;">To Date</td>
                    <td class="simpleline" style="width:127px;"><input name="toDate" style="width:127px;" id="toDate" class="dateClass" class="wob" value="<%=toDate%>"></td>
                    <td class="wob" style="width: 70px; font-weight: bold;">Country</td>
                    <td class="simpleline" style="width:127px;"><select id="Application" name="Application" 
                                                                        style="width:127px;"></select></td>
                    <td  class="wob"><input id="loadbutton" class="button" type="submit" name="Load" value="Load"></td>
                </tr>
            </table>    
        </form>
  <script type="text/javascript">
                        $.get('GetInvariantList?idName=application', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Application").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                            $("#Application").val("<%=app%>");
                        });
                    </script>

        
        <table><tr><td>
                    <table id="arrond">
                        <tr><td><img src="GeneratePicture?graph=nc_opened"></br>
                            </td>
                        <td><img src="GeneratePicture?graph=nc_week_opened"></br>
                            </td></tr>
                        <tr><td><img src="GeneratePicture?graph=nc_week_closed"></br>
                            </td>
                        <td><img src="GeneratePicture?graph=nc_week_team"></br>
                            </td></tr>
                    </table>


                </td></tr></table>
        
    </body>
</html>
