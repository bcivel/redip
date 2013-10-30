<%-- 
    Document   : nonconformitiesplanning
    Created on : 13 oct. 2013, 01:36:12
    Author     : bcivel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Planning</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel='stylesheet' href='css/jquery-ui.min.css' />
<link href='javascript/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='javascript/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<link type="text/css" rel="stylesheet" href="javascript/jqplot/jquery.multiselect.css">
<style media="screen" type="text/css">
            @import "css/demo_page.css";
            @import "css/demo_table.css";
            @import "css/demo_table_jui.css";
            @import "css/themes/base/jquery-ui.css";
            @import "css/themes/smoothness/jquery-ui-1.7.2.custom.css";
</style>
<script src='javascript/jquery.min.js'></script>
<script type="text/javascript" src="javascript/jquery.js"></script>
<script src='javascript/jquery-ui.custom.min.js'></script>
<!--<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>-->
<script src='javascript/fullcalendar/fullcalendar.min.js'></script>
<script type="text/javascript" src="javascript/jqplot/jquery.multiselect.js" charset="utf-8"></script>
<script>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
//			dayClick: function(date, allDay, jsEvent, view) {
//                            $('#calendar').fullCalendar('clientEvents', function(event) {
//                                if(event.start <= date && event.end >= date) {
//                                        return true;
//                                    }
//                                    return false;
//                                });},
                        theme: true,
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
                        events: "NonConformityCalendar",
                        
//			events: [
//				{
//					title: 'Test',
//					start: new Date(y, m, 2, 10, 30),
//                                        end: new Date(y, m, 2, 11, 30),
//                                        allDay: false, 
//                                        className :'custom',
//				},
//				{
//					title: 'Long Event',
//					start: new Date(y, m, d-5),
//					end: new Date(y, m, d-2)
//				},
//				{
//					id: 999,
//					title: 'Repeating Event',
//					start: new Date(y, m, d-3, 16, 0),
//					allDay: false
//				},
//				{
//					id: 999,
//					title: 'Repeating Event',
//					start: new Date(y, m, d+4, 16, 0),
//					allDay: false
//				},
//				{
//					title: 'Meeting',
//					start: new Date(y, m, d, 10, 30),
//					allDay: false
//				},
//				{
//					title: 'Lunch',
//					start: new Date(y, m, d, 12, 0),
//					end: new Date(y, m, d, 14, 0),
//					allDay: false
//				},
//				{
//					title: 'Birthday Party',
//					start: new Date(y, m, d+1, 19, 0),
//					end: new Date(y, m, d+1, 22, 30),
//					allDay: false
//				},
//				{
//					title: 'Click for Google',
//					start: new Date(y, m, 28),
//					end: new Date(y, m, 29),
//					url: 'http://google.com/'
//				}
//			]
		});
		
	});

</script>
<style>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 13px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}

	#calendar {
		width: 900px;
		margin: 0 auto;
		}

</style>
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
                    
                    if (request.getParameterValues("deadline") != null && !request.getParameter("deadline").equals("All")){
                        String[] deadline = request.getParameterValues("deadline");
                        for (int a = 0; a < deadline.length ; a++){
                    uri += "&deadline="+deadline[a];}
                    };
                    
                    if (request.getParameterValues("startDate") != null && !request.getParameter("startDate").equals("All")){
                        String[] startDate = request.getParameterValues("startDate");
                        for (int a = 0; a < startDate.length ; a++){
                    uri += "&startDate="+startDate[a];}
                    };
                    %>
        <br>
        <input id="testtest" value="<%=uri%>" style="display:none">
        <div class="ncdescriptionfirstpart" style="vertical-align: central">
        <form action="qualitynonconformities.jsp" method="get" name="ExecFilters" id="ExecFilters">
            <div style="width: 230px;float:left">
            <!--<p style="float:left">creator</p>-->
            <select style="width: 200px;float:left" multiple="multiple"  id="creator" name="creator">
                 </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">application functionnality</p>-->
            <select style="width: 200px;float:left" multiple="multiple"  id="applicationFunctionnality" name="applicationFunctionnality">
                 </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="status" name="status">
               </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="deadline" name="deadline">
               </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="startDate" name="startDate">
               </select>
        </div>
            <div><input style="float:left" type="button" value="Filter" onClick="document.ExecFilters.submit()"></div>
        </form>
        </div>
        <br>
        <style>
            #calendar{
                width:1200px;
            }
        </style>
<div id='calendar'></div>
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
        (document).ready($.get('GetDistinctValueFromNonconformities?parameter=detection', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#creator").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#creator").multiselect({
   header: "Creator",
   noneSelectedText:"Select Creator",
   selectedText: "# of # creator selected"
});
      
    }
        ));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformities?parameter=deadline', function(data) {
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
        (document).ready($.get('GetDistinctValueFromNonconformities?parameter=startdate', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#startDate").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#startDate").multiselect({
   header: "StartDate",
   noneSelectedText:"Select StartDate",
   selectedText: "# of # StartDate selected"
});
      
    }
        ));
    </script>
</body>
</html>

