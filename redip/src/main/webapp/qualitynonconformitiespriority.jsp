<%-- 
    Document   : qualitynonconformities
    Created on : 23 août 2012, 13:14:26
    Author     : bcivel
--%>
<%@page import="com.redip.entity.Invariant"%>
<%@page import="com.redip.service.IInvariantService"%>
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
        <script type="text/javascript" src="javascript/jquery.dataTables.rowReordering.js"></script>
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

    </head>
    <body  id="wrapper">
        <%@ include file="static.jsp" %>
        <br>
        
        <div>
        <%
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IInvariantService invariantService = appContext.getBean(IInvariantService.class);
        
            List<Invariant> invList = invariantService.findListOfInvariantById("responsabilities");
            
            
            for (Invariant inv : invList){
            
            String resp = inv.getValue();
        %>
        <script type="text/javascript">      
            $(document).ready(function(){
                $('#nonConformityList<%=resp%>').dataTable({
                    "aaSorting": [[ 0, "desc" ]],
//                    "sDom": '<"top"p>',
                    "bServerSide": true,
                    "sAjaxSource": "NonConformityList?Responsabilities=<%=resp%>",
                    "bJQueryUI": true,
                    "bProcessing": true,
                    "bPaginate": true,
                    "bAutoWidth": false,
                    "sPaginationType": "full_numbers",
                    "bSearchable": true, "aTargets": [ 0 ],
                    "aoColumns": [
                        {"sName": "ID",bVisible:false, "sWidth": "5%"},
                        {"sName": "Priority", "sWidth": "5%"},
                        {"sName": "PartnerID", "sWidth": "10%"},
                        {"sName": "ProblemTitle", "sWidth": "30%"},
                        {"sName": "Status", "sWidth": "10%"},
                        {"sName": "Severity", "sWidth": "10%"},
                        {"sName": "Deadline", "sWidth": "10%"},
                        {"sName": "Comments", "sWidth": "30%"},
                        {"sName": "Link", "sWidth": "3%"}
                    ]
                }
            ).makeEditable({
                    sAddURL: "AddNonConformity",
                    sAddHttpMethod: "POST",
                    

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
                    sUpdateURL: "NonConformityUpdateTable",
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
                        {onblur: 'submit',
                            type: 'text',
                            placeholder:''},
                        {onblur: 'submit',
                            type: 'text',
                            placeholder:''},
                        {onblur: 'submit',
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
                        {onblur: 'submit',
                            type: 'text',
                            placeholder:''},
                        {onblur: 'submit',
                            type: 'text',
                            placeholder:''},
                        { }
                    ]
                }).rowReordering({
                    sURL: "NonConformityUpdatePriority?Responsabilities=<%=resp%>" ,
                    sRequestType: "GET"});
            });
            
        </script>
        <p><%=resp%></p>
        <div style="width: 80%;  font: 90% sans-serif">
            <table id="nonConformityList<%=resp%>" class="display">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Priority</th>
                        <th>PartnerID</th>
                        <th>ProblemTitle</th>
                        <th>Status</th>
                        <th>Priority</th>
                        <th>Deadline</th>
                        <th>Comments</th>
                        <th>Link</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
                <br><br>
        <%}
            
        %>
        </div><br>

    </body>
</html>