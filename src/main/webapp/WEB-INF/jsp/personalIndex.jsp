<%--
  Created by IntelliJ IDEA.
  User: gujiafei1104
  Date: 2019-05-08
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%
    People people = (People) session.getAttribute("peopleinfo");
    System.out.println("user name: "+people.getName());
%>
<jsp:include page="common/tag.jsp"/>
<html>
    <head>
        <jsp:include page="common/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/css/interaction.js"></script>
        <title><%=people.getName()%></title>
    </head>

    <body>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">高校人事管理系统</a>
        </div>
        <div>
            <ul class="nav nav-tabs navbar-right">
                <li id="body1"><a>个人信息</a></li>
                <li id="body2"><a>论文信息</a></li>
                <li id="body3"><a>荣誉信息</a></li>
                <li id=""><a href="//localhost:8080/PersonalManagerSystem_war/index">退出登陆</a></li>
            </ul>
        </div>
    </nav>
    <div class="peopleinfo">
        <div class="row">
            <div class="col-xs-5"><p>教职工编号：<%=people.getNumber()%></p></div>
            <div class="col-xs-5"><p>姓名：<%=people.getName()%></p></div>
            <div class="col-xs-5"><p>性别：<%=people.getSex()%></p></div>
            <div class="col-xs-5"><p>年龄：<%=people.getAge()%></p></div>
            <div class="col-xs-5"><p>部门：<%=people.getDepartment()%></p></div>
            <div class="col-xs-5"><p>职位：<%=people.getPosition()%></p></div>
            <div class="col-xs-5"><p>籍贯：<%=people.getBirthplace()%></p></div>
            <div class="col-xs-5"><p>民族：<%=people.getNation()%></p></div>
            <div class="col-xs-5"><p>身份证号：<%=people.getIdentityno()%></p></div>
            <div class="col-xs-5"><p>政治面貌：<%=people.getPoliticalstatus()%></p></div>
            <div class="col-xs-5"><p>电话号码：<%=people.getPhotonumber()%></p></div>
        </div>
        <div class="row">
            <div class="col-xs-6"><button class="btn btn-default">修改个人信息</button></div>
        </div>
    </div>

    <div class="peoplethesis">
        <div class="col-xs-5">
            论文信息
        </div>
    </div>

    <div class="peopleawards">
        <div>荣誉信息</div>
    </div>
    </body>
</html>
