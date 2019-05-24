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
<%@ page import="java.util.List" %>
<%@ page import="com.github.pagehelper.PageInfo" %>
<%
    People people = (People) session.getAttribute("peopleinfo");
    PageInfo<Honor> honorPageInfo = (PageInfo<Honor>) session.getAttribute("honorPageInfo");
    PageInfo<Thesis> thesisPageInfo = (PageInfo<Thesis>) session.getAttribute("thesisPageInfo");
    int honorType = (int)session.getAttribute("honorType");
    int thesisType = (int)session.getAttribute("thesisType");

    String isInfoShow = (String)session.getAttribute("isinfoshow");
    String isHonorShow = (String)session.getAttribute("ishonorshow");
    String isThesisShow = (String)session.getAttribute("isthesisshow");
%>
<jsp:include page="common/tag.jsp"/>
<html>
    <head>
        <jsp:include page="common/bootstrap.jsp"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/peopleIndex.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/interaction.js"></script>
        <title><%=people.getName()%></title>
        <script type="text/javascript">
            function thesisDetail(thesisid){
                console.log("查看论文信息");
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"/PersonalManagerSystem_war/lookthesisinfo",
                    data: {
                        "thesisid":thesisid
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log("进入详情页");
                            window.location.href="/PersonalManagerSystem_war/thesisdetail";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
            function honorDetail(honorid) {
                console.log("查看荣誉信息");
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"/PersonalManagerSystem_war/lookhonorinfo",
                    data: {
                        "honorid":honorid
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log("进入详情页");
                            window.location.href="/PersonalManagerSystem_war/honordetail";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
            function honorJumpByLikes(pageNumber) {
                console.log("跳转到第"+pageNumber+"页");
                var awardname = $(".key-honor-awardname").val();
                var department = $(".key-honor-department").val();
                var awardlevel = $(".key-honor-awardlevel").val();
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"/PersonalManagerSystem_war/gethonorsbylikes",
                    data: {
                        "awardname":awardname,
                        "department":department,
                        "awardlevel":awardlevel,
                        "pagenum":pageNumber
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.info);
                            window.location.href="/PersonalManagerSystem_war/personalIndex?chosenmenu=honorinfo";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
            function honorJumpByNumber(pageNumber) {
                console.log("跳转到第"+pageNumber+"页");
                console.log("显示我的荣誉信息:");
                var number = $(".number").val();
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"//localhost:8080/PersonalManagerSystem_war/gethonorsbynumber",
                    data: {
                        "number":number,
                        "pagenum":pageNumber
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.info);
                            window.location.href="/PersonalManagerSystem_war/personalIndex?chosenmenu=honorinfo";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
            function thesisJumpByLikes(pageNumber)  {
                console.log("搜索我的论文信息");
                var thesisname = $(".key-thesis-name").val();
                var department = $(".key-thesis-department").val();
                var title = $(".key-thesis-title").val();
                var classify  = $(".key-thesis-classify").val();
                var magazine = $(".key-thesis-magazine").val();
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"/PersonalManagerSystem_war/getthesisbylikes",
                    data: {
                        "name":thesisname,
                        "title":title,
                        "department":department,
                        "classify":classify,
                        "magazine":magazine,
                        "pagenum":pageNumber

                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.info);
                            window.location.href="/PersonalManagerSystem_war/personalIndex?chosenmenu=thesisinfo";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
            function thesisJumpByNumber(pageNumber) {
                console.log("显示我的论文信息:");
                var number = $(".number").val();
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"/PersonalManagerSystem_war/getthesisbynumber",
                    data: {
                        "number":number,
                        "pagenum":pageNumber
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.info)
                            window.location.href="/PersonalManagerSystem_war/personalIndex?chosenmenu=thesisinfo";
                        } else {
                            alert(result.data);
                        }
                    }
                });
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
                <li class="body1"><a>个人信息</a></li>
                <li class="body2"><a>论文信息</a></li>
                <li class="body3"><a>荣誉信息</a></li>
                <li class="body4"><a>退出登陆</a></li>
            </ul>
        </div>
    </nav>

    <div class="peopleinfo" <%=isInfoShow%> >
        <div class="col-xs-12">
            <a class="list-group-item active">
                个人信息
            </a>
        </div>
        <div class="col-xs-12" >
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
                        <input type="text" class="name form-control" value="<%=people.getName()%>"/></div>
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
                        <input id="go-back" class="btn btn-primary col-sm-2" type="button" value="返回"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-xs-12">
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

    <div class="peoplethesis" <%=isThesisShow%>>
        <div class="col-xs-12">
            <a class="list-group-item active"> 论文信息</a>
        </div>
        <div class="col-xs-12">
            <br>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">论文标题</label>
                    <div class="col-sm-6">
                        <input aria-label="论文标题关键字" type="text" class="key-thesis-title form-control" name="title" placeholder="论文标题关键字"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">论文分类</label>
                    <div class="col-sm-6">
                        <input aria-label="期刊关键字" type="text" class="key-thesis-classify form-control" name="classify" placeholder="论文分类"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">期刊名称</label>
                    <div class="col-sm-6">
                        <input aria-label="期刊关键字" type="text" class="key-thesis-magazine form-control" name="magazine" placeholder="期刊关键字"/>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3"></label>
                    <div class="col-sm-6">
                        <input type="button" class="my-thesis-search btn col-sm-2 btn-primary" value="搜索"/>
                        <label class="col-sm-2">    </label>
                        <input type="button" class="display-my-thesis btn col-sm-4 btn-primary" value="显示全部论文"/>
                    </div>
                </div>
            </form>
        </div>

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
                                <td><button class="thesis-detail btn btn-primary" onclick="thesisDetail(${thesis.thesisid})">查看详情</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--显示分页信息--%>
        <div class="row">
            <%--显示分页信息摘要--%>
            <div class="col-md-6">
                <label class="col-md-2">      </label>
                <strong style="color: #1592ef">当前${thesisPageInfo.pageNum }页,总${thesisPageInfo.pages }页,总${thesisPageInfo.total }条记录</strong>
            </div>
            <div class="col-md-6">
                <nav aria-label="=Page navigation">
                    <ul class="pagination">
                        <li>
                            <c:if test="${thesisType == 1}">
                                <a onclick="thesisJumpByLikes(1)">首页</a>
                            </c:if>
                            <c:if test="${thesisType == 2}">
                                <a onclick="thesisJumpByNumber(1)">首页</a>
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
                                <c:if test="${thesisType == 2}">
                                    <a onclick="thesisJumpByNumber(${thesisPageInfo.pageNum-1})" aria-label="Previous">
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
                                    <c:if test="${thesisType == 2}">
                                        <a onclick="thesisJumpByNumber(${page_Num})">${page_Num }</a>
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
                                <c:if test="${thesisType == 2}">
                                    <a onclick="thesisJumpByNumber(${thesisPageInfo.pageNum+1})" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:if>
                            </li>
                        </c:if>
                        <li>
                            <c:if test="${thesisType == 1}">`
                                <a onclick="thesisJumpByLikes(${thesisPageInfo.pages})">末页</a>
                            </c:if>
                            <c:if test="${thesisType == 2}">
                                <a onclick="thesisJumpByNumber(${thesisPageInfo.pages})">末页</a>
                            </c:if>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <div class="peopleawards" <%=isHonorShow%> >
        <div class="col-xs-12">
            <a  class="list-group-item active">荣誉信息</a>
        </div>

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
                    <label class="col-sm-3"></label>
                    <div class="col-sm-6">
                        <input type="button" class="my-award-search btn col-sm-2 btn-primary" value="搜索"/>
                        <label class="col-sm-2">    </label>
                        <input type="button" class="display-my-award btn col-sm-4 btn-primary" value="显示全部荣誉"/>
                    </div>
                </div>
            </form>

        </div>

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
                                <td><button class="btn btn-primary" onclick="honorDetail(${honor.honorid})">查看详情</button> </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <%--显示分页信息摘要--%>
            <div class="col-md-6">
                <label class="col-md-2">      </label>
                <strong style="color:darkcyan">当前${honorPageInfo.pageNum }页,总${honorPageInfo.pages }页,总${honorPageInfo.total }条记录</strong>
            </div>
            <div class="col-md-6">
                <nav aria-label="=Page navigation">
                    <ul class="pagination">
                        <li>
                            <c:if test="${honorType == 1}">
                                <a onclick="honorJumpByLikes(1)">首页</a>
                            </c:if>
                            <c:if test="${honorType == 2}">
                                <a onclick="honorJumpByNumber(1)">首页</a>
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
                                <c:if test="${honorType == 2}">
                                    <a onclick="honorJumpByNumber(${honorPageInfo.pageNum-1})" aria-label="Previous">
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
                                    <c:if test="${honorType == 2}">
                                        <a onclick="honorJumpByNumber(${page_Num})">${page_Num }</a>
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
                                <c:if test="${honorType == 2}">
                                    <a onclick="honorJumpByNumber(${honorPageInfo.pageNum+1})" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:if>
                            </li>
                        </c:if>
                        <li>
                            <c:if test="${honorType == 1}">
                                <a onclick="honorJumpByLikes(${honorPageInfo.pages})">末页</a>
                            </c:if>
                            <c:if test="${honorType == 2}">
                                <a onclick="honorJumpByNumber(${honorPageInfo.pages})">末页</a>
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
