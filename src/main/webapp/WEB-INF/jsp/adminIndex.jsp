<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gujiafei1104
  Date: 2019-05-08
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="edu.gy.personalmanagersystem.pojo.*" %>
<%@ page import="com.github.pagehelper.PageInfo" %>
<%
    People people = (People) session.getAttribute("admin_login");
    PageInfo<Honor> honorPageInfo = (PageInfo<Honor>) session.getAttribute("admin_honorPageInfo");
    PageInfo<Thesis> thesisPageInfo = (PageInfo<Thesis>) session.getAttribute("admin_thesisPageInfo");
    PageInfo<People> peoplePageInfo = (PageInfo<People>) session.getAttribute("admin_peoplePageInfo");

    int honorType = (int) session.getAttribute("admin_honorDataType");
    int thesisType = (int) session.getAttribute("admin_thesisType");
    int peopleType = (int) session.getAttribute("admin_thesisType");

    String isInfoShow = (String) session.getAttribute("admin_isInfoShow");
    String isHonorShow = (String) session.getAttribute("admin_isHonorShow");
    String isThesisShow = (String) session.getAttribute("admin_isThesisShow");
    String isPeopleShow = (String) session.getAttribute("admin_isStuffShow");
%>
<jsp:include page="common/tag.jsp"/>
<html>
<head>
    <jsp:include page="common/bootstrap.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
    <title><%=people.getName()%></title>
    <script type="text/javascript">
        function thesisDetail(thesisid) {
            console.log("查看论文信息");
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/lookthesisinfo",
                data: {
                    "thesisid": thesisid
                },
                success: function (result) {
                    if (result.code === 200) {
                        window.location.href = "${pageContext.request.contextPath}/thesisdetail";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function honorDetail(honorid) {
            console.log("查看荣誉信息");
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/lookhonorinfo",
                data: {
                    "honorid": honorid
                },
                success: function (result) {
                    if (result.code === 200) {
                        window.location.href = "${pageContext.request.contextPath}/honordetail";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function peopleDetail(number) {
            console.log("查看教职工信息");
            $.ajax({
                type:"get",
                datatype:"form-date",
                url:"${pageContext.request.contextPath}/lookpeopleinfo",
                data:{
                    "number":number
                },
                success:function(result){
                    if (result.code === 200){
                        window.location.href="${pageContext.request.contextPath}/peopledetail";
                    } else {
                        alert(result.data);
                    }
                }
            })
        }

        function honorJumpByAll(pageNumber) {
            console.log("跳转到第" + pageNumber + "页");
            console.log("显示我的荣誉信息:");
            var number = $(".number").val();
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/getallhonor",
                data: {
                    "pagenum": pageNumber
                },
                success: function (result) {
                    if (result.code === 200) {
                        console.log(result.info);
                        window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=honorinfo";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function honorJumpByLikes(pageNumber) {
            console.log("跳转到第" + pageNumber + "页");
            var awardname = $(".key-honor-awardname").val();
            var department = $(".key-honor-department").val();
            var awardlevel = $(".key-honor-awardlevel").val();
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/gethonorsbylikes",
                data: {
                    "awardname": awardname,
                    "department": department,
                    "awardlevel": awardlevel,
                    "pagenum": pageNumber
                },
                success: function (result) {
                    if (result.code === 200) {
                        console.log(result.info);
                        window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=honorinfo";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function thesisJumpByLikes(pageNumber) {
            console.log("搜索我的论文信息");
            var thesisname = $(".key-thesis-name").val();
            var department = $(".key-thesis-department").val();
            var title = $(".key-thesis-title").val();
            var classify = $(".key-thesis-classify").val();
            var magazine = $(".key-thesis-magazine").val();
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/getthesisbylikes",
                data: {
                    "who":'admin',
                    "name": thesisname,
                    "title": title,
                    "department": department,
                    "classify": classify,
                    "magazine": magazine,
                    "pagenum": pageNumber

                },
                success: function (result) {
                    if (result.code === 200) {
                        console.log(result.info);
                        window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=thesisinfo";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function thesisJumpByAll(pageNumber) {
            console.log("显示我的论文信息:");
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/getallthesis",
                data: {
                    "pagenum": pageNumber
                },
                success: function (result) {
                    if (result.code === 200) {
                        console.log(result.info);
                        window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=thesisinfo";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function peopleJumpByLikes(pageNumber) {
            console.log("peopleJumpByLikes");
            var name = $(".key-people-name").val();
            var department = $(".key-people-department").val();
            var birthplace = $(".key-people-birth-place").val();
            var nation = $(".key-people-nation").val();
            var position = $(".key-people-position").val();
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/querypeoplebylikes",
                data: {
                    "name": name,
                    "birthplace": birthplace,
                    "department": department,
                    "position": position,
                    "nation": nation,
                    "pagenum": pageNumber
                },
                success: function (result) {
                    if (result.code === 200) {
                        console.log(result.info);
                        window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=peoplesinfo";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function peopleJumpByAll(pageNumber) {
            console.log("peopleJumpByAll");
            $.ajax({
                type: "get",
                datatype: "form-data",
                url: "${pageContext.request.contextPath}/getallpeople",
                data: {
                    "pagenum": pageNumber
                },
                success: function (result) {
                    if (result.code === 200) {
                        console.log(result.info);
                        window.location.href = "${pageContext.request.contextPath}/adminIndex?chosenmenu=peoplesinfo";
                    } else {
                        alert(result.data);
                    }
                }
            });
        }

        function addNewThesis() {
            console.log("addNewThesis");
            window.location.href="${pageContext.request.contextPath}/addnewthesis"
        }

        function addNewPeople() {
            console.log("addNewPeople");
            window.location.href="${pageContext.request.contextPath}/addnewpeople"
        }

        function addNewAward() {
            console.log("addNewAward");
            window.location.href="${pageContext.request.contextPath}/addnewhonor"
        }
    </script>
</head>

<body>
<!--菜单栏-->
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">高校人事管理系统</a>
    </div>

    <div>
        <ul class="nav nav-tabs navbar-right">
            <li class="body1"><a>个人信息</a></li>
            <li class="body5"><a>教职工管理</a></li>
            <li class="body2"><a>论文管理</a></li>
            <li class="body3"><a>荣誉管理</a></li>
            <li class="body4"><a>退出登陆</a></li>
        </ul>
    </div>
</nav>

<div class="peopleinfo" <%=isInfoShow%>>
    <div class="col-xs-10">
        <a class="list-group-item active">
            个人信息
        </a>
    </div>

    <div class="col-xs-10">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">教职工编号</label>
                <div class="col-sm-6">
                    <input type="text" readonly="readonly" class="number form-control" value="<%=people.getNumber()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="name form-control" value="<%=people.getName()%>"/></div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别</label>
                <div class="col-sm-6">
                    <input type="text" class="sex form-control" value="<%=people.getSex()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">年龄</label>
                <div class="col-sm-6">
                    <input type="text" class="age form-control" value="<%=people.getAge()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="department form-control" value="<%=people.getDepartment()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">职位</label>
                <div class="col-sm-6">
                    <input type="text" class="position form-control" value="<%=people.getPosition()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">籍贯</label>
                <div class="col-sm-6">
                    <input type="text" class="birthplace form-control" value="<%=people.getBirthplace()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">民族</label>
                <div class="col-sm-6">
                    <input type="text" class="nation form-control" value="<%=people.getNation()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">身份证号</label>
                <div class="col-sm-6">
                    <input type="text" class="identityno form-control" value="<%=people.getIdentityno()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">政治面貌</label>
                <div class="col-sm-6">
                    <input type="text" class="politicalstatus form-control" value="<%=people.getPoliticalstatus()%>"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">电话号码</label>
                <div class="col-sm-6">
                    <input type="text" class="phonenumber form-control" value="<%=people.getPhonenumber()%>"/>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label class="col-sm-3"></label>
                <div class="col-sm-6">
                    <input id="changeInfo" class="btn btn-primary col-sm-4" type="button" value="确定修改信息"/>
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
                <label class="col-sm-2 control-label">确认密码</label>
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

<div class="stuffinfo" <%=isPeopleShow%> >
    <!--标题-->
    <div class="col-xs-12">
        <a class="list-group-item active">教职工信息</a>
    </div>
    <!--数据检索-->
    <div class="col-xs-12">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="key-people-name form-control" placeholder="姓名"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="key-people-department form-control" placeholder="所属部门"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">籍贯</label>
                <div class="col-sm-6">
                    <input aria-label="论文标题关键字" type="text" class="key-people-birth-place form-control"
                           placeholder="籍贯"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">民族</label>
                <div class="col-sm-6">
                    <input aria-label="论文标题关键字" type="text" class="key-people-nation form-control" placeholder="民族"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">职位</label>
                <div class="col-sm-6">
                    <input aria-label="论文标题关键字" type="text" class="key-people-position form-control" placeholder="职位"/>
                </div>
            </div>
            <hr>
            <div class="form-group">

                <div class="col-sm-8">
                    <label class="col-sm-2">    </label>
                    <input type="button" onclick="peopleJumpByLikes(1)" class="people-search btn col-sm-2 btn-primary"
                           value="搜索"/>
                    <label class="col-sm-1">    </label>
                    <input type="button" onclick="peopleJumpByAll(1)"
                           class="display-all-people btn col-sm-3 btn-primary" value="显示全部教职工信息"/>
                    <label class="col-sm-1">    </label>
                    <input type="button" onclick="addNewPeople()" class="btn col-sm-2 btn-primary" value="新增教职工"/>
                </div>
            </div>
        </form>
    </div>
    <%--当前页面信息--%>
    <div class="col-xs-12">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        教职工编号
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        部门
                    </th>
                    <th>
                        职位
                    </th>
                    <th>
                        手机号
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${peoplePageInfo.list}" var="one">
                    <tr>
                        <td>${one.number}</td>
                        <td>${one.name}</td>
                        <td>${one.department}</td>
                        <td>${one.position}</td>
                        <td>${one.phonenumber}</td>
                        <td>
                            <button class="btn btn-primary" onclick="peopleDetail(${one.number})">
                                查看详情
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页页码--%>
    <div class="row">
        <%--显示分页信息摘要--%>
        <div class="col-md-6">
            <label class="col-md-2"> </label>
            <strong style="color: #1592ef">当前${peoplePageInfo.pageNum }页,总${peoplePageInfo.pages }页,总${peoplePageInfo.total }条记录</strong>
        </div>
        <div class="col-md-6">
            <nav aria-label="=Page navigation">
                <ul class="pagination">
                    <li>
                        <c:if test="${peopleType == 1}">
                            <a onclick="peopleJumpByLikes(1)">首页</a>
                        </c:if>
                        <c:if test="${peopleType == 2}">
                            <a onclick="peopleJumpByAll(1)">首页</a>
                        </c:if>
                    </li>
                    <!-- 判断是否有上一页，以便显示点击按钮 -->
                    <c:if test="${peoplePageInfo.hasPreviousPage }">
                        <li>
                            <c:if test="${peopleType == 1}">
                                <a onclick="peopleJumpByLikes(${peoplePageInfo.pageNum-1})" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                            <c:if test="${peopleType == 2}">
                                <a onclick="peopleJumpByAll(${peoplePageInfo.pageNum-1})" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <!-- 遍历页码 -->
                    <c:forEach items="${peoplePageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == peoplePageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num}</a></li>
                        </c:if>
                        <c:if test="${page_Num != peoplePageInfo.pageNum }">
                            <li>
                                <c:if test="${peopleType == 1}">
                                    <a onclick="peopleJumpByLikes(${page_Num})">${page_Num }</a>
                                </c:if>
                                <c:if test="${peopleType == 2}">
                                    <a onclick="peopleJumpByAll(${page_Num})">${page_Num }</a>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                    <!-- 判断是否有下一页 -->
                    <c:if test="${peoplePageInfo.hasNextPage }">
                        <li>
                            <c:if test="${peopleType == 1}">
                                <a onclick="peopleJumpByLikes(${peoplePageInfo.pageNum+1})" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                            <c:if test="${peopleType == 2}">
                                <a onclick="peopleJumpByAll(${peoplePageInfo.pageNum+1})" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <li>
                        <c:if test="${peopleType == 1}">
                            <a onclick="peopleJumpByLikes(${peoplePageInfo.pages})">末页</a>
                        </c:if>
                        <c:if test="${peopleType == 2}">
                            <a onclick="peopleJumpByAll(${peoplePageInfo.pages})">末页</a>
                        </c:if>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>

<div class="peoplethesis" <%=isThesisShow%>>
    <!--标题-->
    <div class="col-xs-12">
        <a class="list-group-item active">论文信息</a>
    </div>
    <!--数据检索-->
    <div class="col-xs-12">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-6">
                    <input type="text" class="key-thesis-name form-control" name="name" placeholder="论文作者"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="key-thesis-department form-control" name="department" placeholder="所属部门"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">论文标题</label>
                <div class="col-sm-6">
                    <input aria-label="论文标题关键字" type="text" class="key-thesis-title form-control" name="title"
                           placeholder="论文标题关键字"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">论文分类</label>
                <div class="col-sm-6">
                    <input aria-label="期刊关键字" type="text" class="key-thesis-classify form-control" name="classify"
                           placeholder="论文分类"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">期刊名称</label>
                <div class="col-sm-6">
                    <input aria-label="期刊关键字" type="text" class="key-thesis-magazine form-control" name="magazine"
                           placeholder="期刊关键字"/>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2"></label>
                    <input type="button" onclick="thesisJumpByLikes(1)" class="btn col-sm-2 btn-primary" value="搜索"/>
                    <label class="col-sm-1"> </label>
                    <input type="button" onclick="thesisJumpByAll(1)" class="btn col-sm-4 btn-primary" value="显示全部论文"/>
                    <label class="col-sm-1"> </label>
                    <input type="button" onclick="addNewThesis()" class="btn col-sm-2 btn-primary" value="新增论文"/>
                </div>
            </div>
        </form>
    </div>
    <%--当前页面信息--%>
    <div class="col-xs-12">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        序号
                    </th>
                    <th>
                        作者
                    </th>
                    <th>
                        标题
                    </th>
                    <th>
                        分类
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${thesisPageInfo.list}" var="thesis">
                    <tr>
                        <td>${thesis.thesisid}</td>
                        <td>${thesis.name}</td>
                        <td>${thesis.title}</td>
                        <td>${thesis.classify}</td>
                        <td>
                            <button class="thesis-detail btn btn-primary" onclick="thesisDetail(${thesis.thesisid})">
                                查看详情
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%--显示分页页码--%>
    <div class="row">
        <%--显示分页信息摘要--%>
        <div class="col-md-6">
            <label class="col-md-2"> </label>
            <strong style="color: #1592ef">当前${thesisPageInfo.pageNum }页,总${thesisPageInfo.pages }页,总${thesisPageInfo.total }条记录</strong>
        </div>
        <div class="col-md-6">
            <nav aria-label="=Page navigation">
                <ul class="pagination">
                    <li>
                        <c:if test="${thesisType == 1}">
                            <a onclick="thesisJumpByLikes(1)">首页</a>
                        </c:if>
                        <c:if test="${thesisType == 3}">
                            <a onclick="thesisJumpByAll(1)">首页</a>
                        </c:if>
                    </li>
                    <!-- 判断是否有上一页，以便显示点击按钮 -->
                    <c:if test="${thesisPageInfo.hasPreviousPage }">
                        <li>
                            <c:if test="${thesisType == 1}">
                                <a onclick="thesisJumpByLikes(${thesisPageInfo.pageNum-1})" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                            <c:if test="${thesisType == 3}">
                                <a onclick="thesisJumpByAll(${thesisPageInfo.pageNum-1})" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <!-- 遍历页码 -->
                    <c:forEach items="${thesisPageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == thesisPageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num}</a></li>
                        </c:if>
                        <c:if test="${page_Num != thesisPageInfo.pageNum }">
                            <li>
                                <c:if test="${thesisType == 1}">
                                    <a onclick="thesisJumpByLikes(${page_Num})">${page_Num }</a>
                                </c:if>
                                <c:if test="${thesisType == 3}">
                                    <a onclick="thesisJumpByAll(${page_Num})">${page_Num }</a>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                    <!-- 判断是否有下一页 -->
                    <c:if test="${thesisPageInfo.hasNextPage }">
                        <li>
                            <c:if test="${thesisType == 1}">
                                <a onclick="thesisJumpByLikes(${thesisPageInfo.pageNum+1})" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                            <c:if test="${thesisType == 3}">
                                <a onclick="thesisJumpByAll(${thesisPageInfo.pageNum+1})" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <li>
                        <c:if test="${thesisType == 1}">
                            <a onclick="thesisJumpByLikes(${thesisPageInfo.pages})">末页</a>
                        </c:if>
                        <c:if test="${thesisType == 3}">
                            <a onclick="thesisJumpByAll(${thesisPageInfo.pages})">末页</a>
                        </c:if>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>


<div class="peopleawards" <%=isHonorShow%>>
    <!--标题-->
    <div class="col-xs-12">
        <a class="list-group-item active">荣誉信息</a>
    </div>
    <!--数据检索-->
    <div class="col-xs-12">
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label">荣誉名称</label>
                <div class="col-sm-6">
                    <input type="text" class="key-honor-awardname form-control" name="awardname" placeholder="荣誉名称"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">部门</label>
                <div class="col-sm-6">
                    <input type="text" class="key-honor-department form-control" name="department" placeholder="部门"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">获奖级别</label>
                <div class="col-sm-6">
                    <input type="text" class="key-honor-awardlevel form-control" name="awardlevel" placeholder="获奖级别"/>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-8">
                    <label class="col-sm-2"> </label>
                    <input type="button" onclick="honorJumpByLikes(1)" class="btn col-sm-2 btn-primary" value="搜索"/>
                    <label class="col-sm-1"> </label>
                    <input type="button" onclick="honorJumpByAll(1)" class="btn col-sm-4 btn-primary" value="显示全部荣誉"/>
                    <label class="col-sm-1"> </label>
                    <input type="button" onclick="addNewAward()" class="btn col-sm-2 btn-primary" value="新增荣誉"/>
                </div>
            </div>
        </form>

    </div>
    <%--当前页面信息--%>
    <div class="col-xs-12">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        序号
                    </th>
                    <th>
                        教职工编号
                    </th>
                    <th>
                        荣誉名称
                    </th>
                    <th>
                        获奖等级
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody id="honors-body">
                <c:forEach items="${honorPageInfo.list}" var="honor">
                    <tr>
                        <td>${honor.honorid}</td>
                        <td>${honor.number}</td>
                        <td>${honor.awardname}</td>
                        <td>${honor.awardlevel}</td>
                        <td>
                            <button class="btn btn-primary" onclick="honorDetail(${honor.honorid})">查看详情</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <%--显示分页页码--%>
    <div class="row">
        <%--显示分页信息摘要--%>
        <div class="col-md-6">
            <label class="col-md-2"> </label>
            <strong style="color:darkcyan">当前${honorPageInfo.pageNum }页,总${honorPageInfo.pages }页,总${honorPageInfo.total }条记录</strong>
        </div>
        <div class="col-md-6">
            <nav aria-label="=Page navigation">
                <ul class="pagination">
                    <li>
                        <c:if test="${honorType == 1}">
                            <a onclick="honorJumpByLikes(1)">首页</a>
                        </c:if>
                        <c:if test="${honorType == 3}">
                            <a onclick="honorJumpByAll(1)">首页</a>
                        </c:if>
                    </li>
                    <!-- 判断是否有上一页，以便显示点击按钮 -->
                    <c:if test="${honorPageInfo.hasPreviousPage }">
                        <li>
                            <c:if test="${honorType == 1}">
                                <a onclick="honorJumpByLikes(${honorPageInfo.pageNum-1})" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                            <c:if test="${honorType == 3}">
                                <a onclick="honorJumpByAll(${honorPageInfo.pageNum-1})" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <!-- 遍历页码 -->
                    <c:forEach items="${honorPageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == honorPageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num }</a></li>
                        </c:if>
                        <c:if test="${page_Num != honorPageInfo.pageNum }">
                            <li>
                                <c:if test="${honorType == 1}">
                                    <a onclick="honorJumpByLikes(${page_Num})">${page_Num }</a>
                                </c:if>
                                <c:if test="${honorType == 3}">
                                    <a onclick="honorJumpByAll(${page_Num})">${page_Num }</a>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                    <!-- 判断是否有下一页 -->
                    <c:if test="${honorPageInfo.hasNextPage }">
                        <li>
                            <c:if test="${honorType == 1}">
                                <a onclick="honorJumpByLikes(${honorPageInfo.pageNum+1})" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                            <c:if test="${honorType == 3}">
                                <a onclick="honorJumpByAll(${honorPageInfo.pageNum+1})" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                        </li>
                    </c:if>
                    <li>
                        <c:if test="${honorType == 1}">
                            <a onclick="honorJumpByLikes(${honorPageInfo.pages})">末页</a>
                        </c:if>
                        <c:if test="${honorType == 3}">
                            <a onclick="honorJumpByAll(${honorPageInfo.pages})">末页</a>
                        </c:if>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<jsp:include page="common/frame.jsp"/>
</html>
