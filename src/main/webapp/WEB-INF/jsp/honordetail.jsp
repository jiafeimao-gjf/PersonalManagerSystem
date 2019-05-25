<%--
  Created by IntelliJ IDEA.
  User: gujiafei1104
  Date: 2019-05-20
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    People people = (People) session.getAttribute("peopleinfo");
    Honor honor = (Honor) session.getAttribute("honorinfo");
    Role role = (Role) session.getAttribute("roleinfo");
%>
<html>
<head>
    <jsp:include page="common/bootstrap.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
    <title><%=people.getName()%></title>
    <script type="text/javascript">
        function checkHonor(honorid) {
            if (confirm("确定审核通过？")){
                console.log("审核荣誉信息");
                $.ajax({
                    type:"post",
                    datatype:"form-data",
                    url:"${pageContext.request.contextPath}/checkhonor",
                    data:{
                        "honorid":honorid
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.data);
                            window.location.href="${pageContext.request.contextPath}/honordatail";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
        }

        function deleteHonor(honorid){
            if (confirm("确定删除该荣誉信息？")){
                console.log("删除荣誉信息");
                $.ajax({
                    type:"post",
                    datatype:"form-data",
                    url:"${pageContext.request.contextPath}/deletehonor",
                    data:{
                        "honorid":honorid
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.data);
                            // 删除之后返回管理页
                            window.location.href="${pageContext.request.contextPath}/adminIndex?chosenmenu=honorinfo";
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
                <li ><a href="${pageContext.request.contextPath}/personalIndex?chosenmenu=honorinfo">返回个人主页</a></li>
            </c:if>
            <c:if test="<%=role.getRoleid() == 1%>">
                <li ><a href="${pageContext.request.contextPath}/adminIndex?chosenmenu=honorinfo">返回管理员主页</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<div class="honor-detail">
    <div class="col-xs-12">
        <a class="list-group-item active">
            荣誉详情
        </a>
    </div>
    <div class="col-xs-12">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">荣誉id</label>
                <div class="col-sm-6">
                    <input type="text" readonly="readonly" class="honor-id form-control"
                           value="<%=honor.getHonorid()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <input type="text" readonly="readonly" class="honor-number form-control" value="<%=honor.getNumber()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所属部门</label>
                <div class="col-sm-6">
                    <input type="text" class="honor-depart form-control" value="<%=honor.getCompany()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">荣誉名称</label>
                <div class="col-sm-6">
                    <input type="text" class="honor-award-name form-control" value="<%=honor.getAwardname()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">荣誉级别</label>
                <div class="col-sm-6">
                    <input type="text" class="honor-award-level form-control" value="<%=honor.getAwardlevel()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">奖励单位</label>
                <div class="col-sm-6">
                    <input type="text" class="honor-award-depart form-control" value="<%=honor.getAwardcpy()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">获奖等级</label>
                <div class="col-sm-6">
                    <input type="text" class="honor-grade form-control" value="<%=honor.getGrade()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">审核情况</label>
                <div class="col-sm-6">
                    <c:if test="<%=honor.getChecked() == 1%>">
                        <input type="text" readonly="readonly" class="honor-checked form-control" value="已审核"/>
                    </c:if>
                    <c:if test="<%=honor.getChecked() == 2%>">
                        <input type="text" readonly="readonly" class="honor-checked form-control" value="未审核"/>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-6">
                    <input type="text" class="honor-remarks form-control" value="<%=honor.getRemarks()%>"/>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input id="changehonorinfo" class="btn btn-primary col-sm-3" type="button" value="修改荣誉信息"/>
                    <c:if test="<%=role.getRoleid() == 1 && honor.getChecked() == 2%>">
                        <label class="col-sm-1">    </label>
                        <button class="btn btn-primary col-sm-2" onclick="checkHonor(<%=honor.getHonorid()%>)">审核通过</button>
                        <label class="col-sm-1">    </label>
                        <button class="btn btn-primary col-sm-3" onclick="deleteHonor(<%=honor.getHonorid()%>)">删除荣誉信息</button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
<jsp:include page="common/frame.jsp"/>
</html>