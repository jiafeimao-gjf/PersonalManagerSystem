<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gujiafei1104
  Date: 2019-05-20
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.gy.personalmanagersystem.pojo.*" %>

<jsp:include page="common/tag.jsp"/>
<%
    Thesis thesis = (Thesis) session.getAttribute("thesisinfo");
    Role role = (Role) session.getAttribute("roleinfo");
%>
<html>
<head>
    <jsp:include page="common/bootstrap.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
    <title><%=thesis.getName()%></title>
    <script type="text/javascript">
        function checkThesis(thesisid) {
            console.log("论文信息审核通过");
            if (confirm("确定审核通过？")) {
                console.log("论文信息审核通过");
                $.ajax({
                    type:"post",
                    datatype:"form-data",
                    url:"${pageContext.request.contextPath}/checkthesis",
                    data:{
                        "thesisid":thesisid
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.data);
                            window.location.href="${pageContext.request.contextPath}/thesisdetail";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
        }

        function deleteThesis(thesisid) {
            console.log("确定删除论文信息");
            if (confirm("确定删除论文信息？")) {
                console.log("确定删除论文信息");
                $.ajax({
                    type:"post",
                    datatype:"form-data",
                    url:"${pageContext.request.contextPath}/deletethesis",
                    data:{
                        "thesisid":thesisid
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.data);
                            window.location.href="${pageContext.request.contextPath}/adminIndex?chosenmenu=thesisinfo";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
        }
    </script>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">高校人事管理系统</a>
    </div>
    <div>
        <ul class="nav nav-tabs navbar-right">
            <c:if test="<%=role.getRoleid() == 2%>">
                <li ><a href="${pageContext.request.contextPath}/personalIndex?chosenmenu=thesisinfo">返回个人主页</a></li>
            </c:if>
            <c:if test="<%=role.getRoleid() == 1%>">
                <li ><a href="${pageContext.request.contextPath}/adminIndex?chosenmenu=thesisinfo">返回管理员主页</a></li>
            </c:if>
        </ul>
    </div>
</nav>
<div class="thesis-detail">
    <div class="col-xs-12">
        <a class="list-group-item active">
            论文详情
        </a>
    </div>
    <div class="col-xs-12">
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
                    <input type="text" readonly="readonly" class="thesis-number form-control" value="<%=thesis.getNumber()%>"/>
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
                    <input type="text" class="thesis-depart form-control" value="<%=thesis.getCompany()%>"/>
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
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="changethesisinfo" class="btn btn-primary col-sm-3" type="button" value="修改论文信息"/>
                    <c:if test="<%=role.getRoleid() == 1%>">
                        <c:if test="<%=thesis.getChecked() == 2%>">
                            <label class="col-sm-1">    </label>
                            <button class="btn btn-primary col-sm-2" onclick="checkThesis(<%=thesis.getThesisid()%>)">审核通过</button>
                        </c:if>
                        <label class="col-sm-1">    </label>
                        <button class="btn btn-primary col-sm-3" onclick="deleteThesis(<%=thesis.getThesisid()%>)">删除论文信息</button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<jsp:include page="common/frame.jsp"/>
</html>
