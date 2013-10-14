<%-- 
    Document   : qualitynonconformitiesaddnc
    Created on : 19 sept. 2013, 10:53:48
    Author     : bcivel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <form id="formAddNewRow" action="#" title="Add NonConformity" style="width:500px" method="post">
                <label for="Application">Application</label>
                <select id="Application" name="Application" style="width:250px;" 
                                    class="ncdetailstext"
                                    ></select>
                <label for="Severity">Severity</label>
                <select id="Severity" name="Severity" style="width:250px;" 
                                    class="ncdetailstext"
                                    ></select><br /><br />
                <label for="ProblemTitle">ProblemTitle</label>
                <input type="text" name="ProblemTitle" id="ProblemTitle" style="width:550px;"/>
                </br></br>
                <label for="ProblemDescription">ProblemDescription</label>
                <textarea name="ProblemDescription" id="ProblemDescription" maxlength="5000">

                </textarea>
                <br /><br />
                <label for="StartDate">StartDate</label>
                <input type="text" name="StartDate" id="StartDate" class="dateClass" maxlength="100" style="width:100px"/>
                <label for="StartTime">StartTime</label>
                <input type="text" name="StartTime" id="StartTime" class="timeClass" maxlength="100" style="width:100px"/>
                <label for="EndDate">EndDate</label>
                <input type="text" name="EndDate" id="EndDate" class="dateClass" maxlength="100" style="width:100px"/>
                <label for="EndTime">EndTime</label>
                <input type="text" name="EndTime" id="EndTime" class="timeClass" maxlength="100" style="width:100px"/>
                <br /><br />
                <label for="Reproductibility">How to Reproduce</label>
                <textarea name="Reproductibility" id="Reproductibility"></textarea>
                <br /><br />
                <button id="btnAddNewRowOk">Add</button>
                <button id="btnAddNewRowCancel">Cancel</button>
            </form>
        </div>
    </body>
</html>
