<%-- 
    Document   : qualitynonconformities
    Created on : 23 août 2012, 13:14:26
    Author     : bcivel
--%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.Date"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.redip.entity.QualityNonconformities"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.redip.service.impl.QualityNonconformitiesServiceImpl"%>
<%@page import="com.redip.dao.impl.QualityNonconformitiesDAOImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.redip.dao.IQualityNonconformitiesDAO"%>
<%@page import="com.redip.service.IQualityNonconformitiesService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actions</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-te-1.4.0.css">
        <link type="text/css" rel="stylesheet" href="javascript/jqplot/jquery.multiselect.css">
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
        <script type="text/javascript" src="javascript/jqplot/jquery.multiselect.js" charset="utf-8"></script>
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
                $('#actionList').dataTable({
                    "aaSorting": [[ 0, "desc" ]],
//                    "sDom": '<"top"p>',
                    "bServerSide": true,
                    "sAjaxSource": "NonConformityActionList"+test,
                    "bJQueryUI": true,
                    "bProcessing": true,
                    "bPaginate": true,
                    "bAutoWidth": false,
                    "sPaginationType": "full_numbers",
                    "bSearchable": true,
                    "aTargets": [ 0 ],
                    "iDisplayLength":25,
                    "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
						/* Append the grade to the default row class name */
						if ( aData[3] == "NEW" )
						{
							nRow.className = "gradeX odd";
                                                        $('td:eq(0)', nRow).html( '<b>'+aData[0]+'</b>' );
                                                        $('td:eq(1)', nRow).html( '<b>'+aData[1]+'</b>' );
							$('td:eq(2)', nRow).html( '<b>'+aData[2]+'</b>' );
                                                        $('td:eq(3)', nRow).html( '<b>'+aData[3]+'</b>' );
                                                        $('td:eq(4)', nRow).html( '<b>'+aData[4]+'</b>' );
						}
					},
                    "aoColumns": [
                        {"sName": "IdAction", "sWidth": "5%"},
                        {"sName": "Action", "sWidth": "30%"},
                        {"sName": "Follower", "sWidth": "30%"},
                        {"sName": "Status", "sWidth": "10%"},
                        {"sName": "Deadline", "sWidth": "10%"},
                        {"sName": "Edit", "sWidth": "5%"}
                    ]
                }
            ).makeEditable({
//                    sAddURL: "AddNonConformity",
//                    sAddHttpMethod: "POST",
//                    callback: function(){window.open("qualitynonconformities", "_self");},
//                    oAddNewRowButtonOptions: {
//                        label: "<b>Declare NonConformity...</b>",
//                        background:"#AAAAAA",
//                        icons: {primary:'ui-icon-plus'}
//                    },
//                    sDeleteHttpMethod: "POST",
//                    sDeleteURL: "DeleteUser",
//                    sAddDeleteToolbarSelector: ".dataTables_length",
//                    oDeleteRowButtonOptions: {
//                        label: "Remove",
//                        icons: {primary:'ui-icon-trash'}
//                    },
                    sUpdateURL: "NonConformityUpdateAction",
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
                        { },
                        {onblur: 'submit',
                            placeholder:''},
                        {onblur: 'submit',
                            placeholder:''},
                        {loadtext: 'loading...',
                            type: 'select',
                            onblur: 'submit',
                            data: "{'NEW':'NEW','OPEN':'OPEN','ANALYSE':'ANALYSE','TO BE VALIDATED':'TO BE VALIDATED','PENDING':'PENDING','CLOSED':'CLOSED'}" ,
                            placeholder:''},
                        {onblur: 'submit',
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
                    
                    
                    if (request.getParameterValues("follower") != null && !request.getParameter("follower").equals("All")){
                        String[] followers = request.getParameterValues("follower");
                        for (int a = 0; a < followers.length ; a++){
                    uri += "&follower="+followers[a];}
                    };
                    
                    String[] status = {"",""};
                    if (request.getParameterValues("status") != null && !request.getParameter("status").equals("All")){
                        status = request.getParameterValues("status");
                        for (int a = 0; a < status.length ; a++){
                    uri += "&statusList="+status[a];}
                    };
                    
        
                    if (request.getParameterValues("date") != null && !request.getParameter("date").equals("All")){
                        String[] date = request.getParameterValues("date");
                        for (int a = 0; a < date.length ; a++){
                    uri += "&date="+date[a];}
                    };
                    
                    if (request.getParameterValues("deadline") != null && !request.getParameter("deadline").equals("All")){
                        String[] deadline = request.getParameterValues("deadline");
                        for (int a = 0; a < deadline.length ; a++){
                    uri += "&deadline="+deadline[a];}
                    };
                    
                    if (request.getParameterValues("priority") != null && !request.getParameter("priority").equals("All")){
                        String[] priority = request.getParameterValues("priority");
                        for (int a = 0; a < priority.length ; a++){
                    uri += "&priority="+priority[a];}
                    };
                    %>
        <br>
        <input id="testtest" value="<%=uri%>" style="display:none">
        <div class="ncdescriptionfirstpart" style="vertical-align: central">
        <form action="qualitynonconformitiesaction.jsp" method="get" name="ExecFilters" id="ExecFilters">
            <div style="width: 230px;float:left">
            <!--<p style="float:left">creator</p>-->
            <select style="width: 200px;float:left" multiple="multiple"  id="follower" name="follower">
                 </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">application functionnality</p>-->
            <select style="width: 200px;float:left" multiple="multiple"  id="status" name="status">
                 </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="priority" name="priority">
               </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="deadline" name="deadline">
               </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="date" name="date">
               </select>
        </div>
            <div><input style="float:left" type="button" value="Filter" onClick="document.ExecFilters.submit()"></div>
        </form>
        </div>
        <br>
        <div style="width: 80%;  font: 90% sans-serif">
            <table id="actionList" class="display">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Action</th>
                        <th>Follower</th>
                        <th>Status</th>
                        <th>Deadline</th>
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
                
                <br /><br>
                <label for="Detection" style="font-weight:bold">Email</label>
                <input type="text" name="Detection" id="Detection" class="ncdetailstext" maxlength="100" style="width:400px"/>
                
                <br /><br><br>
                <div style="width: 900px; clear:both">
                <label for="ProblemTitle" style="font-weight:bold">ProblemTitle</label>
                <input type="text" name="ProblemTitle" class="ncdetailstext" id="ProblemTitle" style="width:700px;"/>
                </div>
                <br><br>
                <div style="width: 520px; float:left">
                <label for="ProblemDescription" style="font-weight:bold">ProblemDescription</label><br>
                <textarea name="ProblemDescription" class="ncdetailstext" id="ProblemDescription" style="width:500px;" rows="10">

                </textarea>
                </div>
                <div style="width: 400px; float:left">
                <label for="Screenshot" style="font-weight:bold">Screenshot</label><br>
                <textarea name="Screenshot" class="ncdetailstext" id="Screenshot" style="width:400px;" rows="8">

                </textarea>
                </div>
                <br><br><br><br><br>
                <div style="width: 900px; clear:both">
                    <br>
                <label for="Reproductibility" style="font-weight:bold">How to Reproduce (Describe bellow how to reproduce the problem)</label><br>
                <textarea name="Reproductibility" id="Reproductibility" class="ncdetailstext" style="width:900px;" rows="5"></textarea>
                </div>
                <br /><br />
                <button id="btnAddNewRowOk">Add</button>
                <button id="btnAddNewRowCancel">Cancel</button>
            </form>
        </div>
        <script type="text/javascript">
                        (document).ready($.get('GetInvariantList?idName=application', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#Application").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }}));
                    </script>
                     <script type="text/javascript">
                        (document).ready($.get('GetInvariantList?idName=severity', function(data) {
                            for (var i = 0; i < data.length; i++) {
                                $("#priority").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                            $("#priority").multiselect({
   header: "Priority",
   noneSelectedText:"Select Priority",
   selectedText: "# of # Priority selected"
}).blur(function(){
    document.ExecFilters.submit();
});
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=status', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#status").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#status").multiselect({
   header: "Status",
   noneSelectedText:"Select Status",
   selectedText: "# of # status selected"
});
      
    }
        ));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=applicationfunctionnality', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#applicationFunctionnality").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#applicationFunctionnality").multiselect({
   header: "Functionnality",
   noneSelectedText:"Select Functionnality",
   selectedText: "# of # functionnality selected"
}).blur(function(){
    document.ExecFilters.submit();
});
        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformitiesAction?parameter=follower', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#follower").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#follower").multiselect({
   header: "Follower",
   noneSelectedText:"Select Follower",
   selectedText: "# of # Follower selected"
});
      
    }
        ));
    </script>
    <script>
        $("#Screenshot").jqte();
</script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformitiesAction?parameter=deadline', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#deadline").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#deadline").multiselect({
   header: "Deadline",
   noneSelectedText:"Select Deadline",
   selectedText: "# of # deadline selected"
});
      
    }
        ));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformitiesAction?parameter=date', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#date").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#date").multiselect({
   header: "date",
   noneSelectedText:"Select Date",
   selectedText: "# of # Date selected"
});
      
    }
        ));
    </script>
        <%
            
        %>
        <br>
        
    </body>
</html>