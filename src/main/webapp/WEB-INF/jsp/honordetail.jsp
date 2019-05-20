<%--
  Created by IntelliJ IDEA.
  User: gujiafei1104
  Date: 2019-05-20
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page import="java.util.List" %>
<%
    People people = (People) session.getAttribute("peopleinfo");
    List<Honor> honorList = (List<Honor>) session.getAttribute("honorList");
    List<Thesis> thesisList = (List<Thesis>) session.getAttribute("thesisList");

%>
<html>
    <head>
        <jsp:include page="common/bootstrap.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
        <title><%=people.getName()%></title>
        <script type="text/javascript">
        </script>
    </head>

    <body>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">高校人事管理系统</a>
        </div>
        <div>
            <ul class="nav nav-tabs navbar-right">
                <li class="body1"><a href="/personalIndex">返回个人主页</a></li>
            </ul>
        </div>
    </nav>

    </body>
</html>