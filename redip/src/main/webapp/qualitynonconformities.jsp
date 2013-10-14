<%-- 
    Document   : qualitynonconformities
    Created on : 23 août 2012, 13:14:26
    Author     : bcivel
--%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.Date"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.redoute.qualityfollowup.entity.QualityNonconformities"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.redoute.qualityfollowup.service.impl.QualityNonconformitiesServiceImpl"%>
<%@page import="com.redoute.qualityfollowup.dao.impl.QualityNonconformitiesDAOImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.redoute.qualityfollowup.dao.IQualityNonconformitiesDAO"%>
<%@page import="com.redoute.qualityfollowup.service.IQualityNonconformitiesService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NonConformities</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-te-1.4.0.css">
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
        <script type="text/javascript" src="javascript/jquery.datepicker.addons.js"></script>
        <script type="text/javascript" src="javascript/jquery-te-1.4.0.min.js" charset="utf-8"></script>
    
        <script>
	$(function() {
		$( 'input' ).filter('.dateClass').datepicker({dateFormat: 'yy-mm-dd'});
                
	});
	</script>
        <script>
	$(function() {
		$( 'input' ).filter('.timeClass').timepicker();
	});
	</script>
        <script>
function getValue()
  {
  var x = document.getElementById("testtest").value;
  return x;
  }
</script>
        <script type="text/javascript">      
            
    $(document).ready(function(){
            var test = getValue();
                $('#nonConformityList').dataTable({
                    "aaSorting": [[ 0, "desc" ]],
//                    "sDom": '<"top"p>',
                    "bServerSide": true,
                    "sAjaxSource": "NonConformityDetails"+test,
                    "bJQueryUI": true,
                    "bProcessing": true,
                    "bPaginate": true,
                    "bAutoWidth": false,
                    "sPaginationType": "full_numbers",
                    "bSearchable": true,
                    "aTargets": [ 0 ],
                    "aoColumns": [
                        {"sName": "Idqualitynonconformities", "sWidth": "5%"},
                        {"sName": "ProblemTitle", "sWidth": "30%"},
                        {"sName": "ProblemDescription", "sWidth": "30%"},
                        {"sName": "Status", "sWidth": "10%"},
                        {"sName": "Severity", "sWidth": "10%"},
                        {"sName": "Edit", "sWidth": "5%"}
                    ]
                }
            ).makeEditable({
                    sAddURL: "AddNonConformity",
                    sAddHttpMethod: "POST",
                    callback: function(){window.open("qualitynonconformities", "_self");},
                    oAddNewRowButtonOptions: {
                        label: "Add NonConformity...",
                        icons: {primary:'ui-icon-plus'}
                    },
                    sDeleteHttpMethod: "POST",
                    sDeleteURL: "DeleteUser",
                    sAddDeleteToolbarSelector: ".dataTables_length",
                    oDeleteRowButtonOptions: {
                        label: "Remove",
                        icons: {primary:'ui-icon-trash'}
                    },
                    sUpdateURL: "UpdateNonConformity",
                    fnOnEdited: function(status){
                        $(".dataTables_processing").css('visibility', 'hidden');
                    },
                    oAddNewRowFormOptions: {
				title: 'Add NonConformity',
                                show: "blind",
                                hide: "explode",
                                width: "1000px"
                            },
                    "aoColumns": [
                        {   onchange: 'submit',
                            placeholder:''  },
                        {onchange: 'submit',
                            placeholder:''},
                        {onchange: 'submit',
                            placeholder:''},
                        {loadtext: 'loading...',
                            type: 'select',
                            onblur: 'submit',
                            data: "{'OPEN':'OPEN','ANALYSE':'ANALYSE','TO BE VALIDATED':'TO BE VALIDATED','PENDING':'PENDING','CLOSED':'CLOSED'}" ,
                            placeholder:''},
                        {loadtext: 'loading...',
                            type: 'select',
                            onblur: 'submit',
                            data: "{'1 - HIGH':'1 - HIGH','2 - MEDIUM':'2 - MEDIUM','3 - LOW':'3 - LOW'}" ,
                            placeholder:''}, 
                        { },
                        { },
                        { }
                    ]
                });
            });
            
        </script>
        
    </head>
    <body  id="wrapper">
        <%@ include file="static.jsp" %>
                    <%
                    String uri = "?";
                    
                    if (request.getParameterValues("creator") != null && !request.getParameter("creator").equals("All")){
                        String[] creators = request.getParameterValues("creator");
                        for (int a = 0; a < creators.length ; a++){
                    uri += "&creator="+creators[a];}
                    };
                    
                    String[] status = {"",""};
                    if (request.getParameterValues("status") != null && !request.getParameter("status").equals("All")){
                        status = request.getParameterValues("status");
                        for (int a = 0; a < status.length ; a++){
                    uri += "&status="+status[a];}
                    };
                    
                    if (request.getParameterValues("applicationFunctionnality") != null && !request.getParameter("applicationFunctionnality").equals("All")){
                        String[] applicationFunctionnality = request.getParameterValues("applicationFunctionnality");
                        for (int a = 0; a < applicationFunctionnality.length ; a++){
                    uri += "&applicationFunctionnality="+applicationFunctionnality[a];}
                    };
                    
                    %>
        <br>
        <input id="testtest" value="<%=uri%>" style="display:none">
        <div class="ncdescriptionfirstpart" style="vertical-align: central">
        <form action="qualitynonconformities.jsp" method="get" name="ExecFilters" id="ExecFilters">
            <div style="width: 170px;float:left">
            <p style="float:left">creator</p>
            <select style="width: 70px;float:left" id="creator" name="creator" onChange="document.ExecFilters.submit()">
                 <option value="All">-- ALL --</option>
                 <option value="csm">csm</option>
                 <option value="alicia">alicia</option>
            </select>
        </div>
        <div style="width: 270px;float:left">
            <p style="float:left">application functionnality</p>
            <select style="width: 70px;float:left" id="applicationFunctionnality" name="applicationFunctionnality" onChange="document.ExecFilters.submit()">
                 <option value="All">-- ALL --</option>
                 <option value="checkout - payment">checkout</option>
                 <option value="toto">toto</option>
            </select>
        </div>
            <div style="width: 270px;float:left">
            <p style="float:left">status</p>
            <select style="width: 70px;float:left" id="status" name="status" onChange="document.ExecFilters.submit()">
                <option value ="All">-- ALL --</option>
            </select>
        </div>
        </form>
        </div>
        <br>
        <div style="width: 80%;  font: 90% sans-serif">
            <table id="nonConformityList" class="display">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>ProblemTitle</th>
                        <th>ProblemDescription</th>
                        <th>Status</th>
                        <th>Severity</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div>
            <form id="formAddNewRow" action="#" title="Add NonConformity" style="width:350px" method="post">
                <br>
                <div style="width: 310px; float:left">
                    <label for="Application" style="font-weight:bold">Application</label>
                    <select id="Application" name="Application" style="width:210px;" 
                            class="ncdetailstext"></select>
                </div>
                <div style="width: 250px; float:left">
                <label for="Severity" style="font-weight:bold">Severity</label>
                <select id="Severity" name="Severity" style="width:150px;" 
                                    class="ncdetailstext"
                                    ></select>
                </div>
                <label for="StartDate" style="font-weight:bold">StartDate</label>
                <input type="text" name="StartDate" id="StartDate" class="dateClass" maxlength="100" style="width:100px"/>
                <label for="StartTime" style="font-weight:bold">StartTime</label>
                <input type="text" name="StartTime" id="StartTime" class="timeClass" maxlength="100" style="width:100px"/>
                
                <br /><br /><br>
                <div style="width: 900px; clear:both">
                <label for="ProblemTitle" style="font-weight:bold">ProblemTitle</label>
                <input type="text" name="ProblemTitle" class="ncdetailstext" id="ProblemTitle" style="width:700px;"/>
                </div>
                <br><br>
                <div style="width: 900px; clear:both">
                <label for="ProblemDescription" style="font-weight:bold">ProblemDescription</label><br>
                <textarea name="ProblemDescription" class="ncdetailstext" id="ProblemDescription" style="width:900px;" rows="8">

                </textarea>
                </div>
                <br /><br />
                <div style="width: 900px; clear:both">
                <label for="Reproductibility" style="font-weight:bold">How to Reproduce (Describe bellow how to reproduce the problem)</label><br>
                <textarea name="Reproductibility" id="Reproductibility" class="ncdetailstext" style="width:900px;" rows="5"></textarea>
                </div>
                <br /><br />
                <button id="btnAddNewRowOk">Add</button>
                <button id="btnAddNewRowCancel">Cancel</button>
            </form>
        </div>
        <script type="text/javascript">
                        $.get('GetInvariantList?idName=application', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Application").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }});
                    </script>
                     <script type="text/javascript">
                        $.get('GetInvariantList?idName=severity', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Severity").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                        });
    </script>
    <script type="text/javascript">
        $.get('GetInvariantList?idName=status', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#status").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#status").val("<%=status[0]%>");
        });
    </script>
            
        <%
            
        %>
        <br>
        
    </body>
</html>