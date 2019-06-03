<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/tag.jsp"/>
<%
    People people = (People) session.getAttribute("login_people");
    People stuff = (People)session.getAttribute("stuffInfo");
    Role role = (Role) session.getAttribute("roleInfo");
%>
<html>
<head>
    <jsp:include page="common/bootstrap.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
    <title><%=people.getName()%></title>
    <script type="text/javascript">
        function checkStuff(number) {
            console.log("审核教职工信");
            if (confirm("确定通过审核？")){
                $.ajax({
                    type:"post",
                    datatype:"form-data",
                    url:"${pageContext.request.contextPath}/checkstuff",
                    data:{
                        "number":number,
                        "checked":1
                    },
                    success:function (result) {
                        if (result.data === 200) {
                            window.location.href = "${pageContext.request.contextPath}/peopledetail";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
        }
        function deleteStuff(number) {
            console.log("删除教职工信");
             if (confirm("是否确定删除？")){
                 $.ajax({
                     type:"post",
                     datatype:"form-data",
                     url:"${pageContext.request.contextPath}/deletepeople",
                     data:{
                         "number":number
                     },
                     success:function (result) {
                         if (result.data === 200) {
                             window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=stuffinfo";
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
            <li ><a href="${pageContext.request.contextPath}/adminIndex?chosenmenu=peoplesinfo">返回管理员主页</a></li>
        </ul>
    </div>
</nav>

<div class="peopleinfo" >
    <div class="col-xs-10">
        <a class="list-group-item active">
            <%=stuff.getNumber()%> 个人信息详情
        </a>
    </div>
    <hr>
    <div class="col-xs-10">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <input type="text" readonly="readonly" class="stuff-number form-control" value="<%=stuff.getNumber()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-name form-control" value="<%=stuff.getName()%>"/></div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-sex form-control" value="<%=stuff.getSex()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">年龄</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-age form-control" value="<%=stuff.getAge()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-department form-control" value="<%=stuff.getDepartment()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">职位</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-position form-control" value="<%=stuff.getPosition()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">籍贯</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-birthplace form-control" value="<%=stuff.getBirthplace()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">民族</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-nation form-control" value="<%=stuff.getNation()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-identityno form-control" value="<%=stuff.getIdentityno()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">政治面貌</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-politicalstatus form-control" value="<%=stuff.getPoliticalstatus()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">电话号码</label>
                <div class="col-sm-6">
                    <input type="text" class="stuff-phonenumber form-control" value="<%=stuff.getPhonenumber()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">审核情况</label>
                <div class="col-sm-6">
                    <c:if test="<%=stuff.getChecked() == 1%>">
                        <input type="text" readonly="readonly" class="people-checked form-control" value="已审核"/>
                    </c:if>
                    <c:if test="<%=stuff.getChecked() == 2%>">
                        <input type="text" readonly="readonly" class="people-checked form-control" value="未审核"/>
                    </c:if>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="changeStuffInfo" class="btn btn-primary col-sm-3" type="button" value="修改教职工信息"/>
                    <c:if test="<%=role.getRoleid() == 1%>">
                        <c:if test="<%=stuff.getChecked() == 2%>">
                            <label class="col-sm-1">    </label>
                            <button class="btn btn-primary col-sm-2" onclick="checkStuff(<%=stuff.getNumber()%>)">审核通过</button>
                        </c:if>
                        <label class="col-sm-1">    </label>
                        <button class="btn btn-primary col-sm-3" onclick="deleteStuff(<%=stuff.getNumber()%>)">删除教职工信息</button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
