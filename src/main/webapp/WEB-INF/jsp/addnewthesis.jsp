<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/tag.jsp"/>
<%
    People people = (People) session.getAttribute("login_people");
    Role role = (Role) session.getAttribute("roleInfo");
%>
<html>
<head>
    <jsp:include page="common/bootstrap.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
    <title><%=people.getName()%></title>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">高校人事管理系统</a>
    </div>
    <div>
        <ul class="nav nav-tabs navbar-right">
            <c:if test="<%=role.getRoleid() == 2%>">
                <li ><a href="${pageContext.request.contextPath}/personalIndex?chosenmenu=honorinfo">返回个人主页</a></li>
            </c:if>
            <c:if test="<%=role.getRoleid() == 1%>">
                <li ><a href="${pageContext.request.contextPath}/adminIndex?chosenmenu=honorinfo">返回管理员主页</a></li>
            </c:if>
        </ul>
    </div>
</nav>
<div class="new-thesis-info">
    <div class="col-xs-12">
        <a class="list-group-item active">
            录入论文详情
        </a>
    </div>
    <div class="col-xs-12">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <c:if test="<%=role.getRoleid() ==  1%>">
                        <input type="text" class="new-thesis-number form-control" />
                    </c:if>
                    <c:if test="<%=role.getRoleid() == 2%>">
                        <input type="text" readonly="readonly" class="new-thesis-number form-control" value="<%=people.getNumber()%>"/>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">作者</label>
                <div class="col-sm-6">
                    <input type="text" class="new-thesis-name form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="new-thesis-depart form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">论文标题</label>
                <div class="col-sm-6">
                    <input type="text" class="new-thesis-title form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">论文分类</label>
                <div class="col-sm-6">
                    <input type="text" class="new-thesis-classify form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">发布期刊</label>
                <div class="col-sm-6">
                    <input type="text" class="new-thesis-maga form-control" />
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="add-new-thesis" class="btn btn-primary col-sm-3" type="button" value="新增论文信息"/>
                </div>
            </div>
        </form>
    </div>

    <div class="col-xs-10">
        <hr>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">上传Excel文件</label>
                <div class="col-sm-6">
                    <input type="file" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="add-many-thesis" class="btn btn-primary col-sm-3" type="button" value="批量录入教职工信息"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
