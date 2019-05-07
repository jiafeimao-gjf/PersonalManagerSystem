<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/tag.jsp"/>
<html>

<head>
    <jsp:include page="common/head.jsp"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css/login.css">
    <title>高校人事管理系统</title>

</head>
<body>

<img class="bgone" src="<%=request.getContextPath()%>/static/img/1.jpg" />
<img class="pic" src="<%=request.getContextPath()%>/static/img/a.png" />

<div class="table">
    <div class="wel">高校人事管理系统</div>
    <div class="wel1">College Personnel Management System</div>
    <div class="user">
        <div class="yonghu" ><img src="<%=request.getContextPath()%>/static/img/yhm.png" /></div>
        <input type="text" name="username" placeholder="请输入用户名" />
    </div>
    <div class="password">
        <div class="yonghu"><img src="<%=request.getContextPath()%>/static/img/mm.png" /></div>
        <input type="password" name="password" placeholder="请输入密码"/>
    </div>
    <input class="btn" type="button" name="登录" value="登录" />
</div>
</body>`
</html>
