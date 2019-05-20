<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gujiafei1104
  Date: 2019-05-20
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page import="java.util.List" %>
<%
    Thesis thesis = (Thesis) session.getAttribute("thesisinfo");
%>
<html>
<head>
    <jsp:include page="common/bootstrap.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
    <title><%=thesis.getNumber()%>
    </title>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">高校人事管理系统</a>
    </div>
    <div>
        <ul class="nav nav-tabs navbar-right">
            <li class="body1"><a href="${pageContext.request.contextPath}/personalIndex">返回个人主页</a></li>
        </ul>
    </div>
</nav>
<div class="thesis-detail">
    <div class="col-xs-10">
        <a class="list-group-item active">
            论文详情
        </a>
    </div>
    <div class="col-xs-10">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">论文id</label>
                <div class="col-sm-6">
                    <input type="text" readonly="readonly" class="thesis-id form-control" value="<%=thesis.getThesisid()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <input type="text" class="thesis-number form-control" value="<%=thesis.getNumber()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">作者</label>
                <div class="col-sm-6">
                    <input type="text" class="thesis-name form-control" value="<%=thesis.getName()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="thesis-dep form-control" value="<%=thesis.getCompany()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">论文标题</label>
                <div class="col-sm-6">
                    <input type="text" class="thesis-title form-control" value="<%=thesis.getTitle()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">论文分类</label>
                <div class="col-sm-6">
                    <input type="text" class="thesis-classify form-control" value="<%=thesis.getClassify()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">发布期刊</label>
                <div class="col-sm-6">
                    <input type="text" class="thesis-maga form-control" value="<%=thesis.getMagazine()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">审核情况</label>
                <div class="col-sm-6">
                    <c:if test="<%=thesis.getChecked() == 1%>">
                        <input type="text" readonly="readonly" class="thesis-checked form-control" value="已审核"/>
                    </c:if>
                    <c:if test="<%=thesis.getChecked() == 2%>">
                        <input type="text" readonly="readonly" class="thesis-checked form-control" value="未审核"/>
                    </c:if>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label class="col-sm-3"></label>
                <div class="col-sm-6">
                    <input id="changethesisinfo" class="btn btn-primary col-sm-2" type="button" value="修改论文信息"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
