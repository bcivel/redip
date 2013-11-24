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
        <title>NonConformities</title>
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
              var oTable =  $('#nonConformityList').dataTable({
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
                    "iDisplayLength":25,
//                    "fnDrawCallback": function(){oTable.fnReloadAjax();},
//                    "fnDrawCallback": function( nRow, aData, iDisplayIndex ) {
//                            $(nRow).attr("id", aData[0][0]);
//                            alert("TOTO");
//                            return nRow;
//                        },
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
                        {"sName": "Idqualitynonconformities", "sWidth": "5%"},
                        {"sName": "Detection", "sWidth": "10%", "sClass": "center"},
                        {"sName": "DateCre", "sWidth": "5%", "sClass": "center"},
                        {"sName": "Status", "sWidth": "10%"},
                        {"sName": "Severity", "sWidth": "10%"},
                        {"sName": "ProblemTitle", "sWidth": "30%"},
                        {"sName": "ProblemDescription", "sWidth": "30%"},
                        {"sName": "Responsabilities", "sWidth": "30%"},
                        {"sName": "Edit", "sWidth": "5%"}
                    ]
                }
            ).makeEditable({
                    sAddURL: "AddNonConformity",
                    sAddHttpMethod: "POST",
//                    
                   
                    oAddNewRowButtonOptions: {
                        label: "<b>Declare NonConformity...</b>",
                        background:"#AAAAAA",
                        icons: {primary:'ui-icon-plus'}
//                        onclick: elrtEditor()
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
                        { },
                        {onblur: 'submit',
                            placeholder:''},
                        { },
                        {loadtext: 'loading...',
                            type: 'select',
                            onblur: 'submit',
                            data: "{'NEW':'NEW','OPEN':'OPEN','ANALYSE':'ANALYSE','TO BE VALIDATED':'TO BE VALIDATED','PENDING':'PENDING','CLOSED':'CLOSED'}" ,
                            placeholder:''},
                        {loadtext: 'loading...',
                            type: 'select',
                            onblur: 'submit',
                            data: "{'1 - HIGH':'1 - HIGH','2 - MEDIUM':'2 - MEDIUM','3 - LOW':'3 - LOW'}" ,
                            placeholder:''}, 
                        {onblur: 'submit'},
                        
                        {onblur: 'submit'},
                        
                        { },
                        { },
                        { },
                        { }
                    ]
                })
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
                    
                    if (request.getParameterValues("responsabilities") != null && !request.getParameter("responsabilities").equals("All")){
                        String[] responsabilities = request.getParameterValues("responsabilities");
                        for (int a = 0; a < responsabilities.length ; a++){
                    uri += "&responsabilities="+responsabilities[a];}
                    };
                    
                    if (request.getParameterValues("rootcausecategory") != null && !request.getParameter("rootcausecategory").equals("All")){
                        String[] rootcausecategory = request.getParameterValues("rootcausecategory");
                        for (int a = 0; a < rootcausecategory.length ; a++){
                    uri += "&rootcausecategory="+rootcausecategory[a];}
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
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="responsabilities" name="responsabilities">
               </select>
        </div>
        <div style="width: 230px;float:left">
            <!--<p style="float:left">status</p>-->
            <select style="width: 200px;float:left" multiple="multiple" id="rootcausecategory" name="rootcausecategory">
               </select>
        </div>
            <div><input style="float:left" type="button" value="Apply Filter" onClick="document.ExecFilters.submit()"></div>
        </form>
        </div>
        <br>
        <button id="create-nc" onclick="javascript:redirect()"> + Declare Nonconformity</button>
        <br>
        <div style="width: 80%;  font: 90% sans-serif">
            <table id="nonConformityList" class="display">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Creator</th>
                        <th>Creation Date</th>
                        <th>Status</th>
                        <th>Severity</th>
                        <th>ProblemTitle</th>
                        <th>ProblemDescription</th>
                        <th>Responsabilities</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <!--<textarea name="Screenshot" id="Screenshot" style="width:900px;" rows="8"></textarea>-->
        <div id="dialog" style="display:none">
            <form id="formAddNewRow2" action="#" title="Add NonConformity" style="width:350px" method="post">
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
                <textarea name="ProblemDescription" class="ncdetailstext" id="ProblemDescription" style="width:500px;" rows="10"></textarea>
                </div>
                <br><br>
                <div style="width: 400px; clear:both">
                <label for="Screenshot" style="font-weight:bold">Screenshot</label><br>
                <textarea name="Screenshot" id="Screenshot" class="ncdetailstext"  style="width:500px; height:105px"></textarea>
                <input id="ScreenshotDetail" class="ncdetailstext" name="ScreenshotDetail" type="hidden" value="" />
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
                                $("#Severity").append($("<option></option>")
                                        .attr("value", data[i])
                                        .text(data[i]))
                            }
                        }));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetInvariantList?idName=status', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#status").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]));
                
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
<!--    <script>
        $("#Screenshot").jqte();
</script>-->
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
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformities?parameter=responsabilities', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#responsabilities").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#responsabilities").multiselect({
   header: "Responsabilities",
   noneSelectedText:"Select Responsabilities",
   selectedText: "# of # Responsabilities selected"
});
      
    }
        ));
    </script>
    <script type="text/javascript">
        (document).ready($.get('GetDistinctValueFromNonconformities?parameter=rootcausecategory', function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#rootcausecategory").append($("<option></option>")
                        .attr("value", data[i])
                        .text(data[i]))
            }
            $("#rootcausecategory").multiselect({
   header: "RootCause",
   noneSelectedText:"Select RootCause",
   selectedText: "# of # RootCause selected"
});
      
    }
        ));
    </script>
     
    <script>
           $(function() {
                
                $( "#dialog" ).dialog({autoOpen: false,
                                        height: 300,
                                            width: 350
                                                });
                
                   elRTE.prototype.options.toolbars.redip = ['style', 'alignment', 'colors', 'format', 'indent', 'lists', 'links'];
                var opts = {
                    lang         : 'en',
                    styleWithCSS : false,
                    width        : 615,
                    height       : 200,
                    toolbar      : 'complete',
                    allowSource  : false
                }
             
            $('#Screenshot').elrte(opts);
            
            


            });
        </script>
        <script type="text/javascript">
    function redirect(){
        window.location = "qualitynonconformitiesadd.jsp";
    }
</script>
    
        <%
            
        %>
        <br>
        
    </body>
</html>