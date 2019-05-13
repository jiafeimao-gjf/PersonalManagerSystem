<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/tag.jsp"/>
<html>
    <head>
        <jsp:include page="common/head.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
        <title>高校人事管理系统</title>

    </head>
    <body>

        <img class="bgone" src="${pageContext.request.contextPath}/static/img/1.jpg" />
        <img class="pic" src="${pageContext.request.contextPath}/static/img/a.png" />

        <div class="table">
            <div class="wel">高校人事管理系统</div>
            <div class="wel1">College Personnel Management System</div>
            <div class="user">
                <div class="yonghu" ><img src="${pageContext.request.contextPath}/static/img/yhm.png" /></div>
                <input id="username" type="text" name="username" placeholder="请输入用户名" />
            </div>
            <div class="password">
                <div class="yonghu"><img src="${pageContext.request.contextPath}/static/img/mm.png" /></div>
                <input id="pwd" type="password" name="password" placeholder="请输入密码"/>
            </div>
            <input id="login" class="btn" type="button" name="登录" value="登录"/>
        </div>
    </body>`
</html>
