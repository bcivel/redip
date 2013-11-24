<%-- 
    Document   : nonconformitiesemail
    Created on : 20 oct. 2013, 22:17:30
    Author     : bcivel
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.redip.service.IEmailService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="css/jquery-te-1.4.0.css">
        <style media="screen" type="text/css">
             @import "css/demo_page.css";
            @import "css/demo_table.css";
            @import "css/demo_table_jui.css";
            @import "css/smoothness/jquery-ui-1.10.2.custom.min.css";
            @import "css/elrte.min.css";
        </style>
        <link rel="stylesheet" type="text/css" href="css/elrte.min.css">
        
<!--        <script type="text/javascript" src="javascript/jquery.js"></script>-->
        <script type="text/javascript" src="javascript/jquery.js"></script>
        <script type="text/javascript" src="javascript/jquery-migrate-1.2.1.min.js"></script>
        <script type="text/javascript" src="javascript/jquery-te-1.4.0.min.js"></script>
        <script type="text/javascript" src="javascript/elrte.min.js"></script>
        <script type="text/javascript" src="javascript/i18n/elrte.en.js"></script>
        <script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
         <script type="text/javascript" src="javascript/jquery.jeditable.mini.js"></script>
         <script type="text/javascript" src="javascript/jquery-migrate-1.2.1.min.js"></script>
    </head>
    
    <body>
       <% Integer id = Integer.valueOf(request.getParameter("id"));
       
       ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
       IEmailService emailService = appContext.getBean(IEmailService.class);
            
       String host = "172.26.193.73";
               //"smtp.free.fr";
       int port = 25;
       String template = "";
       String subject = "test";
       String from = "bcivel@redoute.fr";
       String to = "bcivel@redoute.fr";
       String cc = "bcivel@redoute.fr";
            
       %>
       
        <h3>Send a communication about the non conformity !</h3>
        <form id="mail" name="mail">  
        <input name="host" id="host" style="display:none" value="">
        <input name="port" id="port" style="display:none" value="">
        <text style="width:50px;">From</text>
        <input name="from" id="from" style="width:500px; height:15px;" value="">
        <br>
        <text style="width:50px;">To</text>
        <input name="to" id="to" style="width:500px; height:15px" value="">
        <br>
        <text style="width:50px;">Cc</text>
        <input name="cc" id="cc" style="width:500px; height:15px" value="">
        <br>
        <text style="width:50px;">Subject</text>
        <input name="subject" id="subject" value="" style="width:500px; height:15px">
        <br>
        <text style="width:50px;">Body</text>
        <textarea name="template" id="template" style="width:500px; height:105px">
        </textarea>
        <input id="templateDetail" name="templateDetail" type="hidden" value="" />
        </form>
        <input type="button" onclick="$('#templateDetail').val($('#template').elrte('val'));javascript:sendMail()">
        <script>
           $().ready(function() {
                elRTE.prototype.options.toolbars.redip = ['style', 'alignment', 'colors', 'format', 'indent', 'lists', 'links'];
                var opts = {
                    lang         : 'en',
                    styleWithCSS : false,
                    width        : 615,
                    height       : 200,
                    toolbar      : 'complete',
                    allowSource  : false
                };
                $('#template').elrte(opts);
                
            });
        </script>
<!--        <script>
        (document).ready($("#template").jqte(
                $.get('GenerateEmail?id=<%=id%>', function(data) {
                $("#subject").attr("value", data.subject).text(data.subject);
                $("#host").attr("value", data.host).text(data.host);
                $("#port").attr("value", data.port).text(data.port);
                $("#from").attr("value", data.from).text(data.from);
                $("#to").attr("value", data.to).text(data.to);
                $("#cc").attr("value", data.cc).text(data.cc);
                $("#template").val(data.template);
               })));
        </script>-->
        <script type="text/javascript">
        (document).ready($.get('GenerateEmail?id=<%=id%>', function(data) {
                $("#subject").attr("value", data.subject).text(data.subject);
                $("#host").attr("value", data.host).text(data.host);
                $("#port").attr("value", data.port).text(data.port);
                $("#from").attr("value", data.from).text(data.from);
                $("#to").attr("value", data.to).text(data.to);
                $("#cc").attr("value", data.cc).text(data.cc);
                $("#template").elrte('val', data.template);
               }));
    </script>
    
<script>
   function sendMail(){
       var vhost = document.forms["mail"].elements["host"].value;
       var vport = document.forms["mail"].elements["port"].value;
       var vtemplate = document.forms["mail"].elements["templateDetail"].value;
//       var vtemplate = $("#template").elrte('val');
       var vsubject = document.forms["mail"].elements["subject"].value;
       var vfrom = document.forms["mail"].elements["from"].value;
       var vto = document.forms["mail"].elements["to"].value;
       var vcc = document.forms["mail"].elements["cc"].value;
       
       var xhttp = new XMLHttpRequest();
       xhttp.open("POST", "SendEmail", true);
       xhttp.setRequestHeader("Content-Type","application/json;charset=UTF-8");
       xhttp.send(JSON.stringify({host:vhost, port: vport, template: vtemplate,subject: vsubject,from: vfrom, to: vto ,cc: vcc}));
//       xhttp.send("host=" + vhost + "&port=" + vport + "&template=" + vtemplate + "&subject=" + vsubject + "&from=" + vfrom + "&to=" + vto + "&cc=" + vcc);
       var xmlDoc = xhttp.responseText;

   } 
    
</script>
    </body>
</html>
