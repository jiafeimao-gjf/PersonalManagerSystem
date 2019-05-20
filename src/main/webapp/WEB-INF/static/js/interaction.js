$(document).ready(function () {
    $("#login").click(function () {
        console.log("login");
        var no = $("#username").val();
        var p = $("#pwd").val();
        console.log("number = "+no);
        console.log("pwd = "+p);
        if (no.length === 0){
            alert("请输入合法密码");
        } else if (pwd.length === 0) {
            alert("请输入合法密码");
        } else {
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"/PersonalManagerSystem_war/login",
                data:{
                    "number":no,
                    "pwd":p
                },
                success:function (result) {
                    if (result.code === 200) {
                        console.log(result.data);
                        alert("Log In Successfully");
                        window.location.href="/PersonalManagerSystem_war/personalIndex";
                    } else {
                        alert("Log in failed for "+result.info);
                    }
                }
            });
        }
    });

    $("#changeInfo").click(function () {
        if (confirm("确定要修改信息？")){
            console.log("修改信息");
            var number = $(".number").val();
            var name = $(".name").val();
            var age = $(".age").val();
            var sex = $(".sex").val();
            var depart = $(".department").val();
            var position = $(".position").val();
            var birthplace = $(".birthplace").val();
            var nation = $(".nation").val();
            var identityno = $(".identityno").val();
            var politicalstatus = $(".politicalstatus").val();
            var phonenumber = $(".phonenumber").val();
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"/PersonalManagerSystem_war/updatePeopleInfo",
                data:{
                    "number":number,
                    "name":name,
                    "age":age,
                    "sex":sex,
                    "department":depart,
                    "position":position,
                    "birthplace":birthplace,
                    "nation":nation,
                    "identityno":identityno,
                    "politicalstatus":politicalstatus,
                    "phonenumber":phonenumber
                },
                success:function (resultVO) {
                    if (resultVO.code === 200) {
                        console.log("修改成功");
                        alert("个人信息修改成功");
                    } else {
                        alert("个人信息修改失败，因为"+resultVO.info);
                    }
                }
            });
        }
    });

    $("#changepwd").click(function () {
        console.log("修改密码，需要重新登录");
        var number = $(".number").val();
        var newpwd = $(".newpwd").val();
        var newpwdconformed = $(".newpwdconformed").val();
        console.log("newpwd: "+newpwd);
        console.log("newpwdconformed: "+newpwdconformed);
        if (newpwdconformed.length === 0 && newpwd.length === 0){
            alert("密码不能为空");
        } else {
            if (newpwd === newpwdconformed){
                if (confirm("确定要修改密码？")) {
                    $.ajax({
                        type:"post",
                        datatype:"form-data",
                        url:"/PersonalManagerSystem_war/changepwd",
                        data: {
                            "number":number,
                            "password":newpwdconformed
                        },
                        success:function (result) {
                            if (result.code === 200) {
                                console.log("修改密码成功");
                                alert(result.data+"请重新登录！");
                                window.location.href="/PersonalManagerSystem_war/index";
                            } else {
                                alert(result.data);
                            }
                        }
                    });
                }
            } else {
                alert("两次输入的密码不一致");
            }
        }
    });

    $(".body1").click(function(){
        console.log("个人信息页");
        $(".peopleinfo").show();
        $(".peoplethesis").hide();
        $(".peopleawards").hide();
    });

    $(".body2").click(function(){
        console.log("论文信息");
        $(".peopleinfo").hide();
        $(".peoplethesis").show();
        $(".peopleawards").hide();
    });

    $(".body3").click(function(){
        console.log("荣誉信息");
        $(".peopleinfo").hide();
        $(".peoplethesis").hide();
        $(".peopleawards").show();
    });

    $(".body4").click(function(){
        if (confirm("确定退出登录？")){
            console.log("清除session，重新登录");
            window.location.href = "/PersonalManagerSystem_war/index";
        } else {
            console.log("取消重新登录");
        }
    });

    $(".display-all-award").click(function () {
       console.log("显示全部荣誉信息:");
       var number = $(".number").val();
       $.ajax({
           type:"get",
           datatype:"form-data",
           url:"//localhost:8080/PersonalManagerSystem_war/gethonorsbynumber",
           data: {
               "number":number
           },
           success:function (result) {
               if (result.code === 200) {
                   console.log(result.info);
                   alert(result.data);
                   window.location.href="/PersonalManagerSystem_war/personalIndex";
                   $(".peopleinfo").hide();
                   $(".peoplethesis").hide();
                   $(".peopleawards").show();
               } else {
                   alert(result.data);
               }
           }
       });
    });

    $(".display-all-thesis").click(function () {
        console.log("显示全部论文信息:");
        var number = $(".number").val();
        $.ajax({
            type:"get",
            datatype:"form-data",
            url:"/PersonalManagerSystem_war/getthesisbynumber",
            data: {
                "number":number
            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.info);
                    alert(result.data);
                    window.location.href="/PersonalManagerSystem_war/personalIndex";
                    console.log("论文信息");
                    $(".peopleinfo").hide();
                    $(".peoplethesis").show();
                    $(".peopleawards").hide();
                } else {
                    alert(result.data);
                }
            }
        });
    });

    $(".award-search").click(function () {
        console.log("搜索荣誉信息");
        var awardname = $(".honor-awardname").val();
        var department = $(".honor-department").val();
        var awardlevel = $(".honor-awardlevel").val();
        $.ajax({
            type:"get",
            datatype:"form-data",
            url:"/PersonalManagerSystem_war/gethonorsbylikes",
            data: {
                "awardname":awardname,
                "department":department,
                "awardlevel":awardlevel
            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.info);
                    alert(result.data);
                    window.location.href="/PersonalManagerSystem_war/personalIndex";
                    $(".peopleinfo").hide();
                    $(".peoplethesis").hide();
                    $(".peopleawards").show();
                } else {
                    alert(result.data);
                }
            }
        });
    });

    $(".thesis-search").click(function () {
        console.log("搜索论文信息");
        var thesisname = $(".thesis-name").val();
        var department = $(".thesis-department").val();
        var title = $(".thesis-title").val();
        var classify  = $(".thesis-classify").val();
        var magazine = $(".thesis-magazine").val();
        $.ajax({
            type:"get",
            datatype:"form-data",
            url:"/PersonalManagerSystem_war/getthesisbylikes",
            data: {
                "name":thesisname,
                "title":title,
                "department":department,
                "classify":classify,
                "magazine":magazine

            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.info);
                    alert(result.data);
                    window.location.href="/PersonalManagerSystem_war/personalIndex";
                    console.log("论文信息");
                    $(".peopleinfo").hide();
                    $(".peoplethesis").show();
                    $(".peopleawards").hide();
                } else {
                    alert(result.data);
                }
            }
        });
    });

    $("#changethesisinfo").click(function () {
       console.log("修改论文信息");

    });



});
