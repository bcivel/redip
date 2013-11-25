<%-- 
    Document   : qualitynonconformitydetails
    Created on : 23 août 2012, 13:14:26
    Author     : bcivel
--%>

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
        <title>NonConformityDetails</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-te-1.4.0.css">
        <link type="text/css" rel="stylesheet" href="css/jPaginate/style.css">
        <style media="screen" type="text/css">
            @import "css/demo_page.css";
            @import "css/demo_table.css";
            @import "css/demo_table_jui.css";
            @import "css/smoothness/jquery-ui-1.10.2.custom.min.css";
            @import "css/elrte.min.css";
        </style>
        <link rel="stylesheet" type="text/css" href="css/elrte.min.css">
        <link rel="shortcut icon" type="image/x-icon" href="pictures/favicon.ico" >
        <script type="text/javascript" src="javascript/jquery.js"></script>
        <script type="text/javascript" src="javascript/jquery-migrate-1.2.1.min.js"></script>
        <script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
        <script type="text/javascript" src="javascript/elrte.min.js"></script>
        <script type="text/javascript" src="javascript/i18n/elrte.en.js"></script>
        <script type="text/javascript" src="javascript/jquery.jeditable.mini.js"></script>
        <script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.dataTables.editable.js"></script>
        <script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.paginate.js"></script>
        <script type="text/javascript" src="javascript/jquery.datepicker.addons.js"></script>
        <!--<script type="text/javascript" src="javascript/jquery-te-1.4.0.min.js" charset="utf-8"></script>-->
        <script>
           $().ready(function() {
                elRTE.prototype.options.toolbars.redip = ['style', 'alignment', 'colors', 'format', 'indent', 'lists', 'links'];
                var opts = {
                    lang         : 'en',
                    styleWithCSS : false,
                    width        : 1180,
                    height       : 150,
                    toolbar      : 'complete',
                    allowSource  : true
                };
                $('#Screenshot').elrte(opts);
                   
            });
            
        </script>
    </head>
    <body id="wrapper">
        <script type="text/javascript">
            function updateNonconformity(value, columnName, id) {
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
                xhttp.open("POST", "UpdateNonConformity", true);
                xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
                xhttp.send(JSON.stringify({value:sValue, id: id, columnName: columnName}));
//       xhttp.send("host=" + vhost + "&port=" + vport + "&template=" + vtemplate + "&subject=" + vsubject + "&from=" + vfrom + "&to=" + vto + "&cc=" + vcc);
                var xmlDoc = xhttp.responseText;
                //xhttp.open("GET", "UpdateNonConformity?value=" + sValue + "&id=" + id + "&columnName=" + columnName, true);
                //xhttp.send();
                //var xmlDoc = xhttp.responseText;
                //alert(xmlDoc);

                //            var mylink = "UpdateNonConformity?value="+sValue+"&id="+id+"&columnName="+columnName;
                //            alert(mylink) ;

            }
        </script>
        <script type="text/javascript">
            function updateNonconformityImpact(value, columnName, id) {
                var sValue = value.value;
                var sColumnName = columnName;
                var sId = id;
                document.forms["UpdateNonConformityImpactDetails"].elements["nciValue"].value = sValue;
                document.forms["UpdateNonConformityImpactDetails"].elements["nciId"].value = sId;
                document.forms["UpdateNonConformityImpactDetails"].elements["nciColumnName"].value = sColumnName;

                //document.UpdateNonConformityDetails.submit();
                var xhttp = new XMLHttpRequest();
                // xhttp.open("POST", "UpdateNonConformityImpact", true);
                //xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
                //xhttp.send(JSON.stringify({value:sValue, id: id, columnName: columnName}));
                //var xmlDoc = xhttp.responseText;
                xhttp.open("GET", "UpdateNonConformityImpact?value=" + sValue + "&id=" + id + "&columnName=" + columnName, true);
                xhttp.send();
                var xmlDoc = xhttp.responseText;
                //alert(xmlDoc);

//            var mylink = "UpdateNonConformity?value="+sValue+"&id="+id+"&columnName="+columnName;
//            alert(mylink) ;

            }
        </script>
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
        <script>
            function selectField(field) {

                document.getElementById(document.getElementById("jsvalue").value).style.background = "white";
                document.getElementById(field).style.background = "lightsteelblue";
                document.getElementById("jsvalue").value = field;

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

            int ncid = 1;
            if (request.getParameter("ncid") != null) {
                ncid = Integer.valueOf(request.getParameter("ncid"));
            }


            try {

                ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
                IQualityNonconformitiesService nonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
                IQualityNonconformitiesImpactService nonconformitiesImpactService = appContext.getBean(IQualityNonconformitiesImpactService.class);
                IQualityNonconformitiesActionService nonconformitiesActionService = appContext.getBean(IQualityNonconformitiesActionService.class);
                IQualityNonconformitiesExchangeService nonconformitiesExchangeService = appContext.getBean(IQualityNonconformitiesExchangeService.class);
//                IQualityNonconformitiesFollowerService nonconformitiesFollowerService = appContext.getBean(IQualityNonconformitiesFollowerService.class);
                /*Pagination*/
                //QualityNonconformities qncCount = nonconformitiesService.getAllNonconformities().size();
                int maxid = nonconformitiesService.getAllNonconformities().size();

                QualityNonconformities qnc = nonconformitiesService.getOneNonconformities(ncid);
                
                String screenshot = qnc.getScreenshot();
//                                if (screenshot == null || screenshot.compareTo("null") == 0) {
//                                    screenshot = new String(" ");
//                                } else {
//                                    screenshot = screenshot.replace(">", "&gt;");
//                                }


                /*NCImpact information*/

                List<QualityNonconformitiesImpact> qnci = nonconformitiesImpactService.getImpactForNonconformitiesId(ncid);
                List<QualityNonconformitiesAction> qnca = nonconformitiesActionService.findQualityNonconformitiesActionByID(ncid);
                List<QualityNonconformitiesExchange> qnce = nonconformitiesExchangeService.findQualityNonconformitiesExchangeByID(ncid);
//                List<QualityNonconformitiesFollower> qncf = nonconformitiesFollowerService.findQualityNonconformitiesFollowerByID(ncid);
%>

<br>
        <div class="ncdescriptioncontour">
        <div class="ncdescriptionfirstpart">
            <div style="width: 270px; float:left">
                <form for="Pagination"  style="width: 260px;" class="ncheader">Nonconformity ID</form>
                <div id="Pagination" name="Pagination">
                </div>
            </div>
            <div style="width: 400px; float:left">
                <form for="ProblemTitle"  class="ncheader" style="width:390px">ProblemTitle</form>
                <input id="ProblemTitle" name="ProblemTitle" style="width:390px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'ProblemTitle', '<%=ncid%>')"
                       value="<%=qnc.getProblemTitle()%>">
            </div>
            <div style="width: 180px; float:left">
                <form for="Status" style="width: 170px" class="ncheader">Status</form>
                <select id="Status" name="Status" style="width: 170px;font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'Status', '<%=ncid%>')"
                       value="<%=qnc.getStatus()%>"></select>
            </div>
            <div style="width: 180px; float:left">
                <form for="QualityFollower" style="width: 170px" class="ncheader">QualityFollower</form>
                <select id="QualityFollower" name="QualityFollower" style="width:170px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'QualityFollower', '<%=ncid%>')"
                       value="<%=qnc.getQualityFollower()%>">
                    <option value="NotDefined">-- To Be Defined --</option>
                </select>
            </div>
            <div class="field" style="width: 130px; float:left">
                <form for="ShowInReporting" style="width: 120px" class="ncheader">ShowInReporting</form>
                <input id="ShowInReporting" name="ShowInReporting" type="checkbox" style="width:120px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'ShowInReporting', '<%=ncid%>')"
                       value="<%=qnc.getShowInReporting()%>">
            </div>
        </div>
        <br>
        <div style="clear:both" class="nctablecontour">
            <form action="AddNonConformityImpact" method="post" name="AddNonConformityImpact">
            <div class="ncdescriptionheader"  style="height:30px;vertical-align: middle">Non Conformity Impacts
            <input type="button" value="Add Nonconformities Impacts" class="button" style="float:left; vertical-align: middle" onclick="addNCImpact(<%=ncid%>)">
            <input type="submit" id="saveImpactButton" style="display:none; float:left; vertical-align: middle" value="Save">
            <input type="button" class="smallbutton" style="background-color:red; float:right" onClick="javascript:setInvisibleDiv('nonconformitiesImpactContent')">
            <input type="button" class="smallbutton" style="background-color: green; float:right" onClick="javascript:setVisibleDiv('nonconformitiesImpactContent')">
            
            </div>
            <div id="nonconformitiesImpactContent" style="background-color:#E2E4FF">
            
                <table id="nonconfimpact" style="text-align: left; border-collapse: collapse" border="0px" cellpadding="0px" cellspacing="0px">
                    <tr><td></td>
                        <td class="ncheader">Application</td>
                        <td class="ncheader">StartDate</td>
                        <td class="ncheader">StartTime</td>
                        <td class="ncheader">EndDate</td>
                        <td class="ncheader">EndTime</td>
                        <td class="ncheader">OrderImpacted</td>
                        <td class="ncheader">ErrorPages</td>
                        <td class="ncheader">TimeConsumed</td>
                        <td class="wob"></td>
                    </tr>
                    <%
                        for (QualityNonconformitiesImpact qnciInd : qnci) {
                            int numInc = qnciInd.getIdqualitynonconformitiesimpact();
                            
                           
                    %>
                    <tr>
                        <td><img style="vertical-align: middle" src="pictures/ButtonDelete.png" onClick="deleteLine('<%=numInc%>', '<%=ncid%>')"></img></td>
                        <td class="simpleline" style="width:250px;">
                            <select id="Application<%=numInc%>" name="Application<%=numInc%>" style="width:250px;" 
                                    class="ncdetailstext" onChange="javascript:updateNonconformityImpact(this, 'Application', '<%=numInc%>')"
                                    ></select></td>
                        <td class="simpleline" style="width:127px;"><input name="StartDate<%=numInc%>" style="width:127px;" id="StartDate<%=numInc%>" class="dateClass" value="<%=qnciInd.getStartDate()%>" onChange="javascript:updateNonconformityImpact(this, 'StartDate', '<%=numInc%>'); javascript:updateNonconformity(this, 'StartDate', '<%=ncid%>')"></td>
                        <td class="simpleline" style="width:127px;"><input name="StartTime<%=numInc%>" style="width:127px;" id="StartTime<%=numInc%>" class="timeClass" class="wob" value="<%=qnciInd.getStartTime()%>" onChange="javascript:updateNonconformityImpact(this, 'StartTime', '<%=numInc%>');" onChange="javascript:updateNonconformity(this, 'StartTime', '<%=ncid%>')"></td>
                        <td class="simpleline" style="width:127px;"><input name="EndDate<%=numInc%>" style="width:127px;" id="EndDate<%=numInc%>" class="dateClass" class="wob" value="<%=qnciInd.getEndDate()%>" onChange="javascript:updateNonconformityImpact(this, 'EndDate', '<%=numInc%>');"></td>
                        <td class="simpleline" style="width:127px;"><input name="EndTime<%=numInc%>" style="width:127px;" id="EndTime<%=numInc%>" class="timeClass" class="wob" value="<%=qnciInd.getEndTime()%>" onChange="javascript:updateNonconformityImpact(this, 'EndTime', '<%=numInc%>');"></td>
                        <td class="simpleline" style="width:127px;"><input name="OrderImpacted<%=numInc%>" style="width:127px" id="OrderImpacted<%=numInc%>" class="wob" value="<%=qnciInd.getOrderImpacted()%>" onChange="javascript:updateNonconformityImpact(this, 'OrderImpacted', '<%=numInc%>');"></td>
                        <td class="simpleline" style="width:127px;"><input name="ErrorPages<%=numInc%>" style="width:127px" id="ErrorPages<%=numInc%>" class="wob" value="<%=qnciInd.getErrorPages()%>" onChange="javascript:updateNonconformityImpact(this, 'ErrorPages', '<%=numInc%>');"></td>
                        <td class="simpleline" style="width:127px;"><input name="TimeConsumed<%=numInc%>" style="width:127px" id="TimeConsumed<%=numInc%>" class="wob" class="ncdetailstext" value="<%=qnciInd.getTimeConsumed()%>" onChange="javascript:updateNonconformityImpact(this, 'TimeConsumed', '<%=numInc%>');"></td>
                    </tr>
                    <script type="text/javascript">
            (document).ready($.get('GetInvariantList?idName=country', function(data) {
                for (var i = 0; i < data.length; i++) {
                    $("#Country<%=numInc%>").append($("<option></option>")
                            .attr("value", data[i])
                            .text(data[i]))
                }
                $("#Country<%=numInc%>").val("<%=qnciInd.getCountry()%>");
            }));
                    </script>
                    <script type="text/javascript">
                        (document).ready($.get('GetInvariantList?idName=application', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Application<%=numInc%>").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                            $("#Application<%=numInc%>").val("<%=qnciInd.getApplication()%>");
                        }));
                    </script>
                    <%}%>
                </table><table><tr><td class="wob"></td>
                    </tr></table></div></form>
                        <div class="nctablefooter" style="height:6px"></div>
                        
        </div>
                <!--<input type="button" class="smallbutton" style="float:right" onClick="javascript:popupFrame('qualitynonconformitiesaddnc.jsp')">-->
        <br>
        <div class="nctablecontour"  style="background-color:#E2E4FF">
            <div class="ncdescriptionheader"  colspan="3" style="height:30px">Non Conformity Description
            <input type="button" class="smallbutton" style="background-color:red; float:right" onClick="javascript:setInvisibleDiv('nonconformitiesDescriptionContent')">
            <input type="button" class="smallbutton" style="background-color: green; float:right" onClick="javascript:setVisibleDiv('nonconformitiesDescriptionContent')">
            </div>
<div id="nonconformitiesDescriptionContent">
    <div>
            <div  style="width: 200px; float:left">
                <form for="ProblemCategory" class="ncheader" style="width: 200px;height:20px">ProblemCategory :</form>
                <input id="ProblemCategory" name="ProblemCategory" style="width:200px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'ProblemCategory', '<%=ncid%>')"
                       value="<%=qnc.getProblemCategory()%>">
            </div>
            <div  style="width: 200px; float:left">
                <form for="ProblemType"  class="ncheader" style="width: 200px">ProblemType :</form>
                <input id="ProblemType" name="ProblemType" style="width:200px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'ProblemType', '<%=ncid%>')"
                       value="<%=qnc.getProblemType()%>">
            </div>
            <div style="width: 200px; float:left">
                <form for="Detection" style="width: 200px" class="ncheader" >Detection</form>
                <input id="Detection" name="Detection" style="width:200px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'Detection', '<%=ncid%>')"
                       value="<%=qnc.getDetection()%>">
            </div>
            <div  style="width: 100px; float:left">
                <form  for="Severity" class="ncheader" style="width: 100px">Severity</form>
                <select id="Severity" name="Severity" style="width:100px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'Severity', '<%=ncid%>')"
                       value="<%=qnc.getSeverity()%>"></select>
            </div>
            
            <div style="float:right; border-style: solid; border-width: 1px; background-color: white ">
                
                <form  class="ncheader" style="width: 100%">Joined File</form>
    <form action="ImportFile" method="post" name="selectFile" enctype="multipart/form-data">    
        <div style="float:right; border-style: solid; border-width: 1px; background-color: white ">
        Add File : 
        <input type="file" id="file" name="file" style="width:300px">
        <input id="Load" name="Load" style="display:none" value="Y">
        <input id="idNC" name="idNC" style="display:none" value="<%=ncid%>">
        <input type="submit" value="Save Documents">
        </div>
    </form>
        <div id="DocLink" name="DocLink"></div>
                
            </div>
            
</div>
            <br><br><br>
            <div>
            <div style="width: 600px;float:left">
                <form for="ProblemDescription" class="ncheader" style="width: 590px;height:20px">ProblemDescription :</form>
                <textarea id="ProblemDescription" name="ProblemDescription" style="width:590px; font-size: medium" 
                       class="ncdetailstext" rows="9"
                       onChange="javascript:updateNonconformity(this, 'ProblemDescription', '<%=ncid%>')"
                       value="<%=qnc.getProblemDescription()%>"><%=qnc.getProblemDescription()%></textarea>
            </div>
            <div  style="width: 300px; float:left">
                <form for="Reproductibility" class="ncheader" style="width: 290px;">Reproductibility :</form>
                <textarea id="Reproductibility" name="Reproductibility" style="width:290px; font-size: medium" 
                       class="ncdetailstext"  rows="9"
                       onChange="javascript:updateNonconformity(this, 'Reproductibility', '<%=ncid%>')"
                       value="<%=qnc.getReproductibility()%>"><%=qnc.getReproductibility()%></textarea>
            </div>
            <div  style="width: 290px; float:left">
                <form for="BehaviorExpected" class="ncheader" style="width: 290px;">BehaviorExpected :</form>
                <textarea id="BehaviorExpected" name="BehaviorExpected" style="width:290px; font-size: medium" 
                       class="ncdetailstext"  rows="9"
                       onChange="javascript:updateNonconformity(this, 'BehaviorExpected', '<%=ncid%>')"
                       value="<%=qnc.getBehaviorExpected()%>"><%=qnc.getBehaviorExpected()%></textarea>
            </div>
            
            </div>
            <div style="width: 1180px;float:left">
                <form for="Screenshot" class="ncheader" style="width: 1180px;height:20px">Screenshot :</form>
<!--                <textarea id="Screenshot" name="Screenshot" style="width:1180px;height:150px; font-size: medium" 
                       class="ncdetailstext" rows="9"
                       onChange="javascript:updateNonconformity(this, 'Screenshot', '<%=ncid%>')"></textarea>
                 <input id="ScreenshotDetail" name="ScreenshotDetail" style="display:none" value="<%=qnc.getScreenshot()%>" />-->
           <%out.print(screenshot);%>
            </div>
            
            <div class="field" style="width: 250px; clear:both">
                <br>
                <form for="TestToAvoid" class="ncheader" style="width: 550px">Test To Implement</form>
                <input id="TestToAvoid" name="TestToAvoid" style="width:550px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'TestToAvoid', '<%=ncid%>')"
                       value="<%=qnc.getTestToAvoid()%>">
            </div>
            <div style="width: 100px; float:left">
                <label for="Application" class="ncheader" >Application</label><br/>
                <input id="Application" name="Application" style="font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'Application', '<%=ncid%>')"
                       value="<%=qnc.getApplication()%>">
            </div>
            <div style="width: 200px; float:left">
                <label for="ApplicationFunctionnality" class="ncheader" >ApplicationFunctionnality</label><br/>
                <input id="ApplicationFunctionnality" name="ApplicationFunctionnality" style="font-size: medium;width: 200px" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'ApplicationFunctionnality', '<%=ncid%>')"
                       value="<%=qnc.getApplicationFunctionnality()%>">
            </div>
            <div style="width: 100px; float:left">
                <label for="Deadline" class="ncheader" style="width: 100px">Deadline</label><br/>
                <input id="Deadline" name="Deadline" style="width:100px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'Deadline', '<%=ncid%>')"
                       value="<%=qnc.getDeadline()%>">
            </div>
             <div style="width: 150px; float:left">
                <label for="Responsabilities" class="ncheader" style="width: 150px">Responsabilities</label><br/>
                <select id="Responsabilities" name="Responsabilities" style="width:150px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'Responsabilities', '<%=ncid%>')"
                       value="<%=qnc.getResponsabilities()%>">
                    <option value="NotDefined">-- To Be Defined --</option>
                </select>
            </div>
            <div style="width: 200px; float:left">
                <label for="PartnerId" class="ncheader" style="width: 200px">PartnerId</label><br/>
                <input id="PartnerId" name="PartnerId" style="width:200px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'PartnerId', '<%=ncid%>')"
                       value="<%=qnc.getPartnerId()%>">
            </div>
                       <div style="width: 200px; float:right; vertical-align: middle"><img src="pictures/mail_send.png" onclick="sendMail('<%=ncid%>')"</img>
                       </div>
        </div><div class="nctablefooter" style="height:6px; clear:both"></div></div>
        <br>
        <div class="nctablecontour" style="clear:both;background-color:#E2E4FF">
            <div class="ncdescriptionheader"  colspan="3" style="height:30px">Root Cause
            <input type="button" class="smallbutton" style="background-color:red; float:right" onClick="javascript:setInvisibleDiv('nonconformitiesDescriptionContent')">
            <input type="button" class="smallbutton" style="background-color: green; float:right" onClick="javascript:setVisibleDiv('nonconformitiesDescriptionContent')">
            </div>
            <div style="width: 100px; clear:both">
                <label for="RootCauseCategory" class="ncheader" style="width: 100px">RootCauseCategory</label><br/>
                <select id="RootCauseCategory" name="RootCauseCategory" style="width:300px; font-size: medium" 
                       class="ncdetailstext" 
                       onChange="javascript:updateNonconformity(this, 'RootCauseCategory', '<%=ncid%>')"
                       value="<%=qnc.getRootCauseCategory()%>">
                    <option value="To_Be_Defined">-- To Be Defined --</option>
                </select>
            </div>
            <div class="nctablefooter" style="height:6px"></div>
        </div>

            

            
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
                        <input id="Action<%=numActInc%>" name="Action<%=numActInc%>" style="width:680px; font-size: medium" 
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
                </table><table><tr><td class="wob"><input type="button" value="+" onclick="addNCAction(<%=ncid%>)"></td>
                    <td class="wob"><input type="submit" id="saveActionButton" style="display:none" value="Save"></td>
                </tr></table></form>
            <div class="nctablefooter" style="height:6px"></div>
            
            </div>
        <br>
        <div class="nctablecontour" style="clear:both">
<!--            <div class="ncdescriptionheader"  colspan="3" style="height:30px">Exchange Follow up</div>   
            <form action="AddNonConformityExchange" method="post" name="AddNonConformityExchange">
                <table id="nonconfexchange" style="text-align: left; border-collapse: collapse" border="0px" cellpadding="0px" cellspacing="0px">
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
            </table><table><tr><td class="wob"><input type="button" value="+" onclick="addNCExchange(<%=ncid%>)"></td>
                <td class="wob"><input type="submit" id="saveExchangeButton" style="display:none" value="Save"></td>
            </tr></table></form>-->
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
    <input id="ncid" name="ncid" value="<%=ncid%>" style="display:none">

    <script type="text/javascript">
                        (document).ready($.get('GetInvariantList?idName=severity', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Severity").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                            $("#Severity").val("<%=qnc.getSeverity()%>");
                        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=qualityfollower', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#QualityFollower").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#QualityFollower").val("<%=qnc.getQualityFollower()%>");
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=problemcategory', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#ProblemCategory").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#ProblemCategory").val("<%=qnc.getProblemCategory()%>");
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromRootCause?parameter=rootcausecategory', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#RootCauseCategory").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#RootCauseCategory").val("<%=qnc.getRootCauseCategory()%>");
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=status', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#Status").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#Status").val("<%=qnc.getStatus()%>");
        }));
    </script>
   <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformities?parameter=responsabilities', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#Responsabilities").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#Responsabilities").val("<%=qnc.getResponsabilities()%>");
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetLinkToDoc?idNC=<%=ncid%>', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#DocLink").append($("<a></a>")
                    .attr("href", "http://192.168.134.35/CerberusDocuments/redip/"+<%=ncid%>+"/"+data[i].link)    
                    .text(data[i].link)
                    .append("<br>"))
            }
            
        }));
    </script>
   
    <br> 

<script>
        (document).ready($("#Screenshot").elrte('val', $('#ScreenshotDetail').val()));
        //var valu = window.document.getElementById("ScreenshotDetail").value;
//        var valu = "toti";
//        alert("toto");
//       
//    $("#Screenshot").elrte('val', "valu");
//});
</script>

    <script>
        (document).ready($('#Pagination').paginate({
            count: <%=maxid%>,
            start: <%=ncid%>,
            display: 6,
            border: false,
            text_color: "black",
            background_color: "white",
            text_hover_color: "red",
            onChange: function(data) {
                window.open("qualitynonconformitydetails.jsp?ncid=" + data, "_self");
            }
        }));

    </script>
    <script>
        function deleteLine(idnci, id){
    ;
    if (confirm('You are deleting an impact. Do you want to continue ??')) {
        window.open("NonConformityDeleteImpact?id=" + id+"&idnci=" + idnci, "_self");
//    var xhttp = new XMLHttpRequest();
//    xhttp.open("GET", "NonConformityDeleteImpact?id=" + id+"&idnci=" + idnci , true);
//    xhttp.send();
//    window.open("qualitynonconformitydetails.jsp?ncid=" + id, "_self");
//    var xmlDoc = xhttp.responseText;
    } 

    
}
    </script>
    <script>
        function sendMail(id){
    ;
//    if (confirm('You are deleting an impact. Do you want to continue ??')) {
        window.open("nonconformitiesemail.jsp?id=" + id);
//    var xhttp = new XMLHttpRequest();
//    xhttp.open("GET", "NonConformityDeleteImpact?id=" + id+"&idnci=" + idnci , true);
//    xhttp.send();
//    window.open("qualitynonconformitydetails.jsp?ncid=" + id, "_self");
//    var xmlDoc = xhttp.responseText;
//    } 

    
}
    </script>
    <%
        } catch (Exception e) {
            out.println(e);
        }
    %>
    </body>

    </html>
