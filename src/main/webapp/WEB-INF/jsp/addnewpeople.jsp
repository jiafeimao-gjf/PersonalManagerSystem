<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/tag.jsp"/>
<%
    People people = (People) session.getAttribute("peopleinfo");
    Role role = (Role) session.getAttribute("roleinfo");
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
            <li ><a href="${pageContext.request.contextPath}/adminIndex?chosenmenu=peoplesinfo">返回管理员主页</a></li>
        </ul>
    </div>
</nav>

<div class="new-people-info" >
    <div class="col-xs-10">
        <a class="list-group-item active">
            录入个人信息详情
        </a>
    </div>

    <div class="col-xs-10">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-number form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-name form-control"/></div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-sex form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">年龄</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-age form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-department form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">职位</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-position form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">籍贯</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-birthplace form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">民族</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-nation form-control" >
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-identityno form-control" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">政治面貌</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-politicalstatus form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">电话号码</label>
                <div class="col-sm-6">
                    <input type="text" class="new-stuff-phonenumber form-control" />
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="add-new-stuff" class="btn btn-primary col-sm-3" type="button" value="增加教职工信息"/>
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
                    <input id="add-many-stuff" class="btn btn-primary col-sm-3" type="button" value="批量录入教职工信息"/>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
