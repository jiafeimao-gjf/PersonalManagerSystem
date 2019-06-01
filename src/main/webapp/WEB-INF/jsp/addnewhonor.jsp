<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/tag.jsp"/>
<%
    People people ;
    String type;
    Role role = (Role) session.getAttribute("roleInfo");
    if (role.getRoleid() == 1){
        type = "admin";
    } else {
        type = "user";
    }
    people = (People) session.getAttribute(type +"_login");
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

<div class="new-honor-input">
    <div class="col-xs-12">
        <a class="list-group-item active">
            荣誉信息录入
        </a>
    </div>
    <div class="col-xs-12">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <c:if test="<%=role.getRoleid() == 1%>">
                        <input type="text" class="new-honor-number form-control"/>
                    </c:if>
                    <c:if test="<%=role.getRoleid() ==  2%>">
                        <input type="text" readonly="readonly" class="new-honor-number form-control" value="<%=people.getNumber()%>">
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所属部门</label>
                <div class="col-sm-6">
                    <input type="text" class="new-honor-depart form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">荣誉名称</label>
                <div class="col-sm-6">
                    <input type="text" class="new-honor-award-name form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">荣誉级别</label>
                <div class="col-sm-6">
                    <input type="text" class="new-honor-award-level form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">奖励单位</label>
                <div class="col-sm-6">
                    <input type="text" class="new-honor-award-depart form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">获奖等级</label>
                <div class="col-sm-6">
                    <input type="text" class="new-honor-grade form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-6">
                    <input type="text" class="new-honor-remarks form-control"/>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="add-new-honor" class="btn btn-primary col-sm-3" type="button" value="新增荣誉信息"/>
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
                    <input id="add-many-honor" class="btn btn-primary col-sm-3" type="button" value="批量录入荣誉信息"/>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
<jsp:include page="common/frame.jsp"/>
</html>
