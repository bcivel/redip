<%-- 
    Document   : parameter
    Created on : 19 sept. 2013, 12:14:01
    Author     : bcivel
--%>


<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Date DatePageStart = new Date() ; %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
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
    
        <title>Parameters</title>

        <script type="text/javascript">      
            $(document).ready(function(){
                $('#parametersTable').dataTable({
                    "aLengthMenu": [
                        [20, 50, 100, 200, -1],
                        [20, 50, 100, 200, "All"]
                    ], 
                    "iDisplayLength" : 20,
                    "bServerSide": false,
                    "sAjaxSource": "GetParameter",
                    "bJQueryUI": true,
                    "bProcessing": true,
                    "sPaginationType": "full_numbers",
                    "bSearchable": false, "aTargets": [ 0 ],
                    "aoColumns": [
                        {"sName": "Parameter"},
                        {"sName": "Value"},
                        {"sName": "Description"}
                    ]
                }
            ).makeEditable({
                    sUpdateURL: "UpdateParameter",
                    fnOnEdited: function(status){
                        $(".dataTables_processing").css('visibility', 'hidden');
                    },
                    "aoColumns": [
                        null,
                        {
                            tooltip: 'Click to edit parameter.',
                            type: 'textarea',
                            submit:'Save changes'},
                        {
                            tooltip: 'Click to edit parameter.',
                            type: 'textarea',
                            submit:'Save changes'}
                    ]
                });
            });
        </script>
    </head>
    <body>
        <%@ include file="static.jsp" %>
        <div style="width: 80%; padding: 25px; font: 90% sans-serif">
            <table id="parametersTable" class="display">
                <thead>
                    <tr>
                        <th>Parameter</th>
                        <th>Value</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <br>
    </body>
</html>
