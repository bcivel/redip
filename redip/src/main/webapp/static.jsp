<%-- 

Document   : menu
    Created on : 29 May. 2012, 11:43:27
    Author     : bcivel
--%>


<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.apache.log4j.Level"%>
<%@page import="javax.xml.datatype.Duration"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>


        <script type='text/javascript' src='javascript/Form.js'></script>
<br>
    
<div id="menu" >
    <table>
        <td id="wob" rowspan="2" style="font-weight: bold; font-size:large; text-align: center">
        <!--<img src="pictures/index.png" style="width:170px; height:70px" valign="Top" alt="Index"/>-->
        La Redoute : DSI Quality Follow Up</td>
<!--        <td id="wob" style="font-weight: bold; font-size:large; text-align: center" >Quality Application
        </td>-->
        </tr>
        <tr>
        <td id="wob" valign="Bottom">
     <div id="navcontainer">
     <ul id="navlist">
<!--     <li><a name="menu" id="Index" href="index.jsp" style="width:130px">Index</a></li>-->
<!--     <li id="active"><a id="current" name="menu" href="#" style="width:230px">Non Conformities</a>
     <ul id="subnavlist">
     <li id="subactive"><a name="menu" href="qualitynonconformities.jsp" style="width:230px">List</a></li>
        <li><a name="menu" href="qualitynonconformitydetails.jsp" style="width:230px">Details</a></li>
        <li><a name="menu" href="qualitynonconformitiesreporting.jsp" style="width:230px">Reporting</a></li>
        </ul>
         </li>-->
    <li><a name="menu" href="qualitynonconformities.jsp" style="width:160px">Non Conformity List</a></li>
    <li><a name="menu" href="qualitynonconformitydetails.jsp" style="width:160px">Non Conformity Details</a></li>
    <li><a name="menu" href="qualitynonconformitiespriority.jsp" style="width:160px">BackLog</a></li> 
    <li><a name="menu" href="nonconformitiesplanning.jsp" style="width:160px">Calendar</a></li>
    <li><a name="menu" href="qualitynonconformitiesreporting.jsp" style="width:160px">Reporting</a></li>
    </ul></div>
        </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    menuColoring();
</script>




