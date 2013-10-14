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
<script src='javascript/jquery.min.js'></script>
<script src='javascript/jquery-ui.custom.min.js'></script>
<script src='javascript/fullcalendar/fullcalendar.min.js'></script>
<script>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
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
//                                        allDay: false
//				}
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
        <br>
<div id='calendar'></div>
</body>
</html>

