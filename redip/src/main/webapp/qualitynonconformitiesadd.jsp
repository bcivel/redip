<%-- 
    Document   : qualitynonconformitiesadd
    Created on : 17 nov. 2013, 23:05:22
    Author     : bcivel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add NonConformity</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link type="text/css" rel="stylesheet" href="css/jquery-te-1.4.0.css">
        <link type="text/css" rel="stylesheet" href="javascript/jqplot/jquery.multiselect.css">
        <style media="screen" type="text/css">
            @import "css/demo_page.css";
            @import "css/demo_table.css";
            @import "css/demo_table_jui.css";
            @import "css/themes/base/jquery-ui.css";
            @import "css/smoothness/jquery-ui-1.10.2.custom.min.css";
            @import "css/elrte.min.css";
        </style>
        <link rel="stylesheet" type="text/css" href="css/elrte.min.css">
        
        <link rel="shortcut icon" type="image/x-icon" href="pictures/favicon.ico" >
        <script type="text/javascript" src="javascript/jquery.js"></script>
        <script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
        <script type="text/javascript" src="javascript/elrte.min.js"></script>
        <script type="text/javascript" src="javascript/i18n/elrte.en.js"></script>
        <script type="text/javascript" src="javascript/jquery.jeditable.mini.js"></script>
        <script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.dataTables.editable.js"></script>
        <script type="text/javascript" src="javascript/jquery.validate.min.js"></script>
        <script type="text/javascript" src="javascript/jquery.datepicker.addons.js"></script>
        <script type="text/javascript" src="javascript/jquery-te-1.4.0.min.js" charset="utf-8"></script>
        <script type="text/javascript" src="javascript/jquery-migrate-1.2.1.min.js"></script>
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
    </head>
   <body  id="wrapper">
        
        <%@ include file="static.jsp" %>
       <div id="dialog">
            <form id="formAddNewRow" action="AddNonConformity" title="Add NonConformity" style="width:350px" method="post" enctype="multipart/form-data">
                <br>
                <div style="width: 1110px;float:left">
                <div style="width: 310px; float:left">
                    <label for="Application" style="font-weight:bold">Application</label>
                    <select id="Application" name="Application" style="width:210px;" 
                            class="ncdetailstext"></select>
                </div>
                <div style="width: 250px; float:left">
                <label for="Severity" style="font-weight:bold">Severity</label>
                <select id="Severity" name="Severity" style="width:150px;" 
                                    class="ncdetailstext"></select>
                </div>
                <div style="width: 200px; float:left">
                <label for="StartDate" style="font-weight:bold">StartDate</label>
                <input type="text" name="StartDate" id="StartDate" class="dateClass" maxlength="100" style="width:100px"/>
                </div>
                <div style="width: 200px; float:left">
                <label for="StartTime" style="font-weight:bold">StartTime</label>
                <input type="text" name="StartTime" id="StartTime" class="timeClass" maxlength="100" style="width:100px"/>
                </div>
                </div>
                <br><br>
                <div style="width: 800px; float:left">
                <label for="Detection" style="font-weight:bold">Email</label>
                <input type="text" name="Detection" id="Detection" class="ncdetailstext" maxlength="100" style="width:400px"/>
                </div>
                <br><br>
                <div style="width: 900px; clear:both">
                <label for="ProblemTitle" style="font-weight:bold">ProblemTitle</label>
                <input type="text" name="ProblemTitle" class="ncdetailstext" id="ProblemTitle" style="width:700px;"/>
                </div>
                <br><br>
                <div style="width: 1110px;float:left">
                <div style="width: 520px; float:left">
                <label for="ProblemDescription" style="font-weight:bold">ProblemDescription</label><br>
                <textarea name="ProblemDescription" class="ncdetailstext" id="ProblemDescription" style="width:500px; height:255px"></textarea>
                </div>
                <div style="width: 400px; float:left">
                <label for="Screenshot" style="font-weight:bold">Screenshot</label><br>
                <textarea name="Screenshot" id="Screenshot" class="ncdetailstext"  style="width:500px; height:85px"></textarea>
                <input id="ScreenshotDetail" class="ncdetailstext" name="ScreenshotDetail" type="hidden" value="" />
                </div>
                </div>
                <br><br><br><br><br>
                <div style="clear:both">
                <div style="width: 600px; float:left">
                    <br>
                <label for="Reproductibility" style="font-weight:bold">How to Reproduce (Describe bellow how to reproduce the problem)</label><br>
                <textarea name="Reproductibility" id="Reproductibility" class="ncdetailstext" style="width:600px;" rows="5"></textarea>
                </div>
                <div style="width: 500px;float:left; border-style: solid; border-width: 1px; background-color: white ">
                Join File : 
                <input type="file" id="file" name="file" style="width:300px">
                <input id="Load" name="Load" style="display:none" value="Y">
                <!--<input type="submit" value="Save Documents">-->
                </div>
                </div>
                <br /><br />
                <button id="btnAddNewRowOk" submit="submit">Add</button>
                <button id="btnAddNewRowCancel">Cancel</button>
                
            </form>
        </div>
       <script>
           $().ready(function() {
                elRTE.prototype.options.toolbars.redip = ['style', 'alignment', 'colors', 'format', 'indent', 'lists', 'links'];
                var opts = {
                    lang         : 'en',
                    styleWithCSS : false,
                    width        : 615,
                    height       : 150,
                    toolbar      : 'complete',
                    allowSource  : false
                };
                $('#Screenshot').elrte(opts);
                
            });
        </script>
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
                                $("#Severity").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                        }));
    </script>
    </body>
</html>
