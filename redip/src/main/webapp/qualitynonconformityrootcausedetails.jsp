<%-- 
    Document   : qualitynonconformitydetails
    Created on : 23 août 2012, 13:14:26
    Author     : bcivel
--%>

<%@page import="com.redip.entity.QualityNonconformitiesRootCause"%>
<%@page import="com.redip.service.IQualityNonconformitiesRootCauseService"%>
<%@page import="com.redip.entity.QualityNonconformitiesFollower"%>
<%@page import="com.redip.entity.QualityNonconformitiesExchange"%>
<%@page import="com.redip.entity.QualityNonconformitiesAction"%>
<%@page import="com.redip.service.IQualityNonconformitiesFollowerService"%>
<%@page import="com.redip.service.IQualityNonconformitiesExchangeService"%>
<%@page import="com.redip.service.IQualityNonconformitiesActionService"%>
<%@page import="com.redip.entity.QualityNonconformitiesImpact"%>
<%@page import="com.redip.service.impl.QualityNonconformitiesImpactServiceImpl"%>
<%@page import="com.redip.entity.QualityNonconformities"%>
<%@page import="com.redip.service.impl.QualityNonconformitiesServiceImpl"%>
<%@page import="com.redip.dao.impl.QualityNonconformitiesDAOImpl"%>
<%@page import="com.redip.dao.IQualityNonconformitiesImpactDAO"%>
<%@page import="com.redip.service.IQualityNonconformitiesImpactService"%>
<%@page import="com.redip.dao.IQualityNonconformitiesDAO"%>
<%@page import="com.redip.service.IQualityNonconformitiesService"%>
<%@page import="org.springframework.stereotype.Service"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Date"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RootCauseDetails</title>
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
    <body id="wrapper">
        <script type="text/javascript">
            function updateNonconformityAction(value, columnName, id) {
                var sValue = value.value;
                var sColumnName = columnName;
                var sId = id;
                document.forms["UpdateNonConformityActionDetails"].elements["ncaValue"].value = sValue;
                document.forms["UpdateNonConformityActionDetails"].elements["ncaId"].value = sId;
                document.forms["UpdateNonConformityActionDetails"].elements["ncaColumnName"].value = sColumnName;

                //document.UpdateNonConformityDetails.submit();
                var xhttp = new XMLHttpRequest();
                xhttp.open("GET", "NonConformityUpdateAction?value=" + sValue + "&id=" + id + "&columnName=" + columnName, true);
                xhttp.send();
                var xmlDoc = xhttp.responseText;
                //alert(xmlDoc);

//            var mylink = "UpdateNonConformity?value="+sValue+"&id="+id+"&columnName="+columnName;
//            alert(mylink) ;

            }
        </script>
        <script type="text/javascript">
            function updateNonconformityRC(value, columnName, id) {
                //document.UpdateNonConformity.action = "UpdateNonConformity?value="+value+"&id="+id+"&columnName="+columnName;
                //document.UpdateNonConformity.submit();
                var sValue = value.value;
                var sColumnName = columnName;
                var sId = id;

                document.forms["UpdateNonConformityDetails"].elements["value"].value = sValue;
                document.forms["UpdateNonConformityDetails"].elements["id"].value = sId;
                document.forms["UpdateNonConformityDetails"].elements["columnName"].value = sColumnName;

                //document.UpdateNonConformityDetails.submit();
                var xhttp = new XMLHttpRequest();
                //xhttp.open("GET", "NonConformityUpdateRootCause?value=" + sValue + "&id=" + id + "&columnName=" + columnName, true);
                xhttp.open("POST", "NonConformityUpdateRootCause", true);
                xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                xhttp.send("value=" + sValue + "&id=" + id + "&columnName=" + columnName);
                var xmlDoc = xhttp.responseText;
                //alert(xmlDoc);

                //            var mylink = "UpdateNonConformity?value="+sValue+"&id="+id+"&columnName="+columnName;
                //            alert(mylink) ;

            }
        </script>
        <script>
            (document).ready($(function() {
                $('input').filter('.dateClass').datepicker({dateFormat: 'yy-mm-dd'});

            }));
        </script>
        <script>
            (document).ready($(function() {
                $('input').filter('.timeClass').timepicker();
            }));
        </script>


        <%@ include file="static.jsp" %>  
        <%
            Date DatePageStart = new Date();

            int rcid = 1;
            if (request.getParameter("rcid") != null) {
                rcid = Integer.valueOf(request.getParameter("rcid"));
            }


            try {

                ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
                IQualityNonconformitiesRootCauseService nonconformitiesService = appContext.getBean(IQualityNonconformitiesRootCauseService.class);
                IQualityNonconformitiesActionService nonconformitiesActionService = appContext.getBean(IQualityNonconformitiesActionService.class);
                IQualityNonconformitiesExchangeService nonconformitiesExchangeService = appContext.getBean(IQualityNonconformitiesExchangeService.class);
                /*Pagination*/
                List<QualityNonconformitiesRootCause> qnrcList = nonconformitiesService.findAllNonconformitiesRootCause();
                int maxid = qnrcList.size();

                QualityNonconformitiesRootCause qrc = nonconformitiesService.findOneNonconformitiesRootCause(rcid);

                List<QualityNonconformitiesAction> qnca = nonconformitiesActionService.findQualityNonconformitiesActionByID(rcid);
                List<QualityNonconformitiesExchange> qnce = nonconformitiesExchangeService.findQualityNonconformitiesExchangeByID(rcid);


                %>
        <br>
        <div class="ncdescriptioncontour">
        <div class="ncdescriptionfirstpart">
            <div style="width: 270px; float:left">
                <form for="Pagination"  style="width: 260px;" class="ncheader">RootCause ID</form>
                <div id="Pagination" name="Pagination">
                </div>
            </div>
            <div style="width: 400px; float:left">
                <form for="RootCauseCategory"  class="ncheader" style="width:390px">RootCause</form>
                <input id="RootCauseCategory" name="RootCauseCategory" style="width:390px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformityRC(this, 'RootCauseCategory', '<%=rcid%>')"
                       value="<%=qrc.getRootCauseCategory()%>">
            </div>
            <div style="width: 180px; float:left">
                <form for="Status" style="width: 170px" class="ncheader">Status</form>
                <select id="Status" name="Status" style="width: 170px;font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformityRC(this, 'Status', '<%=rcid%>')"
                       value="<%=qrc.getStatus()%>"></select>
            </div>
            <div style="width: 180px; float:left">
                <form for="Severity" style="width: 170px" class="ncheader">Severity</form>
                <select id="Severity" name="Severity" style="width:170px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformityRC(this, 'Severity', '<%=rcid%>')"
                       value="<%=qrc.getSeverity()%>">
                    <option value="NotDefined">-- To Be Defined --</option>
                </select>
            </div>
        </div>
        <br>
        <div class="nctablecontour"  style="background-color:#E2E4FF">
            <div class="ncdescriptionheader"  colspan="3" style="height:30px">RootCause Description
            <input type="button" class="smallbutton" style="background-color:red; float:right" onClick="javascript:setInvisibleDiv('nonconformitiesDescriptionContent')">
            <input type="button" class="smallbutton" style="background-color: green; float:right" onClick="javascript:setVisibleDiv('nonconformitiesDescriptionContent')">
            </div>
<div id="nonconformitiesDescriptionContent">
    <div>
            <div  style="width: 200px; float:left">
                <form for="Responsabilities" class="ncheader" style="width: 200px;height:20px">ProblemCategory :</form>
                <input id="Responsabilities" name="Responsabilities" style="width:200px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformityRC(this, 'Responsabilities', '<%=rcid%>')"
                       value="<%=qrc.getResponsabilities()%>">
            </div>
            <div  style="width: 200px; float:left">
                <form for="Component"  class="ncheader" style="width: 200px">Component :</form>
                <input id="Component" name="Component" style="width:200px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformityRC(this, 'Component', '<%=rcid%>')"
                       value="<%=qrc.getComponent()%>">
            </div>
            
</div>
            <br><br><br>
            <div>
            <div style="width: 600px;float:left">
                <form for="RootCauseDescription" class="ncheader" style="width: 590px;height:20px">RootCauseDescription :</form>
                <textarea id="RootCauseDescription" name="RootCauseDescription" style="width:590px; font-size: medium" 
                       class="ncdetailstext" rows="9"
                       onChange="javascript:updateNonconformityRC(this, 'RootCauseDescription', '<%=rcid%>')"
                       value="<%=qrc.getRootCauseDescription()%>"><%=qrc.getRootCauseDescription()%></textarea>
            </div>
        </div><div class="nctablefooter" style="height:6px; clear:both"></div></div>
        <br>
        <div class="nctablecontour" style="clear:both; background-color:#E2E4FF">
                <div class="ncdescriptionheader"  colspan="3" style="height:30px">Action Plan</div>   
                <form action="AddNonConformityAction" method="post" name="AddNonConformityAction">
                <table id="nonconfaction" style="text-align: left; border-collapse: collapse" border="0px" cellpadding="0px" cellspacing="0px">
                    <tr>
                        <td class="ncheader">Action</td>
                        <td class="ncheader">Date</td>
                        <td class="ncheader">Deadline</td>
                        <td class="ncheader">Follower</td>
                        <td class="ncheader">Percentage</td>
                        <td class="ncheader">Status</td>
                    </tr>
                    <%
                        for (QualityNonconformitiesAction qncaInd : qnca) {
                            int numActInc = qncaInd.getIdQualityNonconformitiesActions();
                    %>
                <tr>
                    <td>
                        <input id="Action<%=numActInc%>" name="Action<%=numActInc%>" style="width:500px; font-size: medium" 
                               class="ncdetailstext" 
                               onChange="javascript:updateNonconformityAction(this, 'Action', '<%=numActInc%>')"
                               value="<%=qncaInd.getAction()%>">
                    </td>
                    <td>
                        <input id="Date<%=numActInc%>" name="Date<%=numActInc%>" style="width:100px; font-size: medium" 
                               class="ncdetailstext" 
                               onChange="javascript:updateNonconformityAction(this, 'Date', '<%=numActInc%>')"
                               value="<%=qncaInd.getDate()%>">
                    </td>
                    <td>
                        <input id="Deadline<%=numActInc%>" name="Deadline<%=numActInc%>" style="width:100px; font-size: medium" 
                               class="ncdetailstext" 
                               onChange="javascript:updateNonconformityAction(this, 'Deadline', '<%=numActInc%>')"
                               value="<%=qncaInd.getDeadline()%>">
                    </td>
                    <td>
                        <input id="Follower<%=numActInc%>" name="Follower<%=numActInc%>" style="width:100px; font-size: medium" 
                               class="ncdetailstext" 
                               onChange="javascript:updateNonconformityAction(this, 'Follower', '<%=numActInc%>')"
                               value="<%=qncaInd.getFollower()%>">
                    </td>
                    <td>
                        <input id="Percentage<%=numActInc%>" name="Percentage<%=numActInc%>" style="width:100px; font-size: medium" 
                               class="ncdetailstext" 
                               onChange="javascript:updateNonconformityAction(this, 'Percentage', '<%=numActInc%>')"
                               value="<%=qncaInd.getPercentage()%>">
                    </td>
                    <td>
                        <input id="Status<%=numActInc%>" name="Status<%=numActInc%>" style="width:100px; font-size: medium" 
                               class="ncdetailstext" 
                               onChange="javascript:updateNonconformityAction(this, 'Status', '<%=numActInc%>')"
                               value="<%=qncaInd.getStatus()%>">
                    </td>


                    <%
                        }
                    %>
                </table><table><tr><td class="wob"><input type="button" value="+" onclick="addNCAction(<%=rcid%>)"></td>
                    <td class="wob"><input type="submit" id="saveActionButton" style="display:none" value="Save"></td>
                </tr></table></form>
            <div class="nctablefooter" style="height:6px"></div>
            
            </div>
        <br>
        <div class="nctablecontour" style="clear:both">
            <div class="ncdescriptionheader"  colspan="3" style="height:30px">Exchange Follow up</div>   
            <form action="AddNonConformityExchange" method="post" name="AddNonConformityExchange">
                <table id="nonconfimpact" style="text-align: left; border-collapse: collapse" border="0px" cellpadding="0px" cellspacing="0px">
                    <tr>
                        <td class="ncheader">Date</td>
                        <td class="ncheader">Title</td>
                        <td class="ncheader">User</td>
                        <td class="ncheader">Exchange</td>
                    </tr>
                <%
                    for (QualityNonconformitiesExchange qnceInd : qnce) {
                        int numExcInc = qnceInd.getIdQualityNonconformitiesExchange();




                %>
                <tr><td>
                    <input id="ExchangeDate<%=numExcInc%>" name="ExchangeDate<%=numExcInc%>" style="width:100px; font-size: small" 
                           class="ncdetailstext" 
                           onChange="javascript:updateNonconformityExchange(this, 'Date', '<%=numExcInc%>')"
                           value="<%=qnceInd.getDate()%>">
                </td>
                <td>
                    <input id="ExchangeTitle<%=numExcInc%>" name="ExchangeTitle<%=numExcInc%>" style="width:100px; font-size: small" 
                           class="ncdetailstext" 
                           onChange="javascript:updateNonconformityExchange(this, 'ExchangeTitle', '<%=numExcInc%>')"
                           value="<%=qnceInd.getExchangeTitle()%>">
                </td>
                <td>
                    <input id="ExchangeUser<%=numExcInc%>" name="ExchangeUser<%=numExcInc%>" style="width:100px; font-size: small" 
                           class="ncdetailstext" 
                           onChange="javascript:updateNonconformityExchange(this, 'User', '<%=numExcInc%>')"
                           value="<%=qnceInd.getUser()%>">
                </td>
                <td>
                    <textarea id="ExchangeContent<%=numExcInc%>" name="ExchangeContent<%=numExcInc%>" style="width:100%; font-size: medium" 
                           class="ncdetailstext" 
                           onChange="javascript:updateNonconformityExchange(this, 'ExchangeContent', '<%=numExcInc%>')"
                           value="<%=qnceInd.getExchangeContent()%>"><%=qnceInd.getExchangeContent()%></textarea>
                </td>
                </tr>



                <%
                    }
                %>
            </table><table><tr><td class="wob"><input type="button" value="+" onclick="addNCAction(<%=rcid%>)"></td>
                <td class="wob"><input type="submit" id="saveButton" style="display:none" value="Save"></td>
            </tr></table></form>
        <div class="nctablefooter" style="height:6px"></div>
        
        </div>
        </div>
               
    <form action="UpdateNonConformity" method="post" name="UpdateNonConformityDetails">
        <input id="id" name="id" style="display:none">
        <input id="value" name="value" style="display:none">
        <input id="columnName" name="columnName" style="display:none">
        <input id="jsvalue" name="jsvalue" style="display:none" value="Status">
    </form>
    <form action="UpdateNonConformityImpact" method="post" name="UpdateNonConformityImpactDetails">
        <input id="nciId" name="nciId" style="display:none">
        <input id="nciValue" name="nciValue" style="display:none">
        <input id="nciColumnName" name="nciColumnName" style="display:none">
        <input id="nciJsvalue" name="nciJsvalue" style="display:none" value="Status">
    </form>
                <form action="UpdateNonConformityAction" method="post" name="UpdateNonConformityActionDetails">
        <input id="ncaId" name="ncaId" style="display:none">
        <input id="ncaValue" name="ncaValue" style="display:none">
        <input id="ncaColumnName" name="ncaColumnName" style="display:none">
        <input id="ncaJsvalue" name="ncaJsvalue" style="display:none" value="Status">
    </form>
    <input id="maxid" name="maxid" value="<%=maxid%>" style="display:none">
    <input id="ncid" name="ncid" value="<%=rcid%>" style="display:none">

    <script type="text/javascript">
                        (document).ready($.get('GetInvariantList?idName=severity', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Severity").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                            $("#Severity").val("<%=qrc.getSeverity()%>");
                        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromRootCause?parameter=rootcausecategory', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#RootCauseCategory").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#RootCauseCategory").val("<%=qrc.getRootCauseCategory()%>");
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=status', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#Status").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#Status").val("<%=qrc.getStatus()%>");
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName==responsablities', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#Responsabilities").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#Responsabilities").val("<%=qrc.getResponsabilities()%>");
        }));
    </script>
   
    <script>
        (document).ready($('#Pagination').paginate({
            count: <%=maxid%>,
            start: <%=rcid%>,
            display: 6,
            border: false,
            text_color: "black",
            background_color: "white",
            text_hover_color: "red",
            onChange: function(data) {
                window.open("qualitynonconformityrootcausedetails.jsp?rcid=" + data, "_self");
            }
        }));

    </script>
    

    <%
        } catch (Exception e) {
            out.println(e);
        }
    %>
    </body>

    </html>
