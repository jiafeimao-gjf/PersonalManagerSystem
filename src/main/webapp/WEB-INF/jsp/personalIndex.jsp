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
                <li class="body1"><a>个人信息</a></li>
                <li class="body2"><a>论文信息</a></li>
                <li class="body3"><a>荣誉信息</a></li>
                <li id=""><a href="${pageContext.request.contextPath}/index">退出登陆</a></li>
            </ul>
        </div>
    </nav>

    <div class="peopleinfo">
        <div class="col-xs-10">
            <a class="list-group-item active">
                个人信息
            </a>
        </div>
        <hr>
        <div class="col-xs-10" >
            <br>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">教职工编号</label>
                    <div class="col-sm-6">
                        <input type="text" readonly="readonly" class="number form-control" value="<%=people.getNumber()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="name form-control" value="<%=people.getName()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >性别</label>
                    <div class="col-sm-6">
                        <input type="text" class="sex form-control" value="<%=people.getSex()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >年龄</label>
                    <div class="col-sm-6">
                        <input type="text" class="age form-control" value="<%=people.getAge()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >部门</label>
                    <div class="col-sm-6">
                        <input type="text" class="department form-control" value="<%=people.getDepartment()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >职位</label>
                    <div class="col-sm-6">
                        <input type="text" class="position form-control" value="<%=people.getPosition()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >籍贯</label>
                    <div class="col-sm-6">
                        <input type="text" class="birthplace form-control" value="<%=people.getBirthplace()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >民族</label>
                    <div class="col-sm-6">
                        <input type="text" class="nation form-control" value="<%=people.getNation()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >身份证号</label>
                    <div class="col-sm-6">
                        <input type="text" class="identityno form-control" value="<%=people.getIdentityno()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >政治面貌</label>
                    <div class="col-sm-6">
                        <input type="text" class="politicalstatus form-control" value="<%=people.getPoliticalstatus()%>"/>
                        <div></div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >电话号码</label>
                    <div class="col-sm-6">
                        <input type="text" class="phonenumber form-control" value="<%=people.getPhonenumber()%>"/>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3"></label>
                    <div class="col-sm-6">
                        <input id="changeInfo" class="btn btn-primary col-sm-2" type="button" value="修改信息"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-xs-10">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="newpwd form-control" placeholder="输入新密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="newpwdconformed form-control" placeholder="输入确认密码"/>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3"></label>
                    <div class="col-sm-6">
                        <input id="changepwd" class="btn btn-primary col-sm-2" type="button" value="修改密码"/>
                    </div>

                </div>
            </form>

        </div>

    </div>

    <div class="peoplethesis" hidden>
        <div class="col-xs-10">
            <a class="list-group-item active"> 论文信息</a>
        </div>
        <hr>
        <div class="col-xs-10">
            <br>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" placeholder="<%=people.getName()%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" >部门</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" placeholder="<%=people.getDepartment()%>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">论文标题</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" placeholder="论文标题关键字"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">期刊名称</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" placeholder="期刊关键字"/>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3"></label>
                    <div class="col-sm-6">
                        <input type="button" class="thesis-search btn col-sm-2 btn-primary" value="搜索"/>
                        <label class="col-sm-2">    </label>
                        <input type="button" class="display-all-thesis btn col-sm-4 btn-primary" value="显示全部论文"/>
                    </div>
                </div>

            </form>
        </div>

        <div class="col-xs-10">
            <div class="col-md-12 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            编号
                        </th>
                        <th>
                            产品
                        </th>
                        <th>
                            交付时间
                        </th>
                        <th>
                            状态
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            1
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            01/04/2012
                        </td>
                        <td>
                            Default
                        </td>
                    </tr>
                    <tr class="success">
                        <td>
                            1
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            01/04/2012
                        </td>
                        <td>
                            Approved
                        </td>
                    </tr>
                    <tr class="error">
                        <td>
                            2
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            02/04/2012
                        </td>
                        <td>
                            Declined
                        </td>
                    </tr>
                    <tr class="warning">
                        <td>
                            3
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            03/04/2012
                        </td>
                        <td>
                            Pending
                        </td>
                    </tr>
                    <tr class="info">
                        <td>
                            4
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            04/04/2012
                        </td>
                        <td>
                            Call in to confirm
                        </td>
                    </tr>
                    </tbody>
                </table>
                <ul class="pagination pagination-sm">
                    <li>
                        <a href="#">Prev</a>
                    </li>
                    <li>
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">Next</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="peopleawards" hidden>
        <div class="col-xs-10">
            <a  class="list-group-item active">荣誉信息</a>
        </div>
        <hr>
        <div class="col-xs-10">
            <br>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">荣誉名称</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="awardname" placeholder="荣誉名称"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">部门</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" placeholder="部门"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">获奖级别</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" placeholder="论文标题关键字"/>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3"></label>
                    <div class="col-sm-6">
                        <input type="button" class="award-search btn col-sm-2 btn-primary" value="搜索"/>
                        <label class="col-sm-2">    </label>
                        <input type="button" class="display-all-award btn col-sm-4 btn-primary" value="显示全部荣誉"/>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-xs-10">
            <div class="col-md-12 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            编号
                        </th>
                        <th>
                            产品
                        </th>
                        <th>
                            交付时间
                        </th>
                        <th>
                            状态
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            1
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            01/04/2012
                        </td>
                        <td>
                            Default
                        </td>
                    </tr>
                    <tr class="success">
                        <td>
                            1
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            01/04/2012
                        </td>
                        <td>
                            Approved
                        </td>
                    </tr>
                    <tr class="error">
                        <td>
                            2
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            02/04/2012
                        </td>
                        <td>
                            Declined
                        </td>
                    </tr>
                    <tr class="warning">
                        <td>
                            3
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            03/04/2012
                        </td>
                        <td>
                            Pending
                        </td>
                    </tr>
                    <tr class="info">
                        <td>
                            4
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            04/04/2012
                        </td>
                        <td>
                            Call in to confirm
                        </td>
                    </tr>
                    </tbody>
                </table>
                <ul class="pagination pagination-sm">
                    <li>
                        <a href="#">Prev</a>
                    </li>
                    <li>
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">Next</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    </body>
    <jsp:include page="common/frame.jsp"/>
</html>
