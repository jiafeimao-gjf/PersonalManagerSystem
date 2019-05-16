$(document).ready(function () {
    var peopleInfo;
    $("#login").click(function () {
        console.log("login");
        var no = $("#username").val();
        var p = $("#pwd").val();
        console.log("number = "+no);
        console.log("pwd = "+p);
        if (no.length != 6 ){
            alert("请输入合法用户名，用户名应为6个数字组成");
        } else if (pwd === null) {
            alert("请输入合法密码");
        } else {
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"//localhost:8080/PersonalManagerSystem_war/login",
                data:{
                    "number":no,
                    "pwd":p
                },
                success:function (result) {
                    if (result.code === 200) {
                        console.log(result.data);
                        alert("Log In Successfully");
                        window.location.href="//localhost:8080/PersonalManagerSystem_war/personalIndex";
                    } else {
                        alert("Log in failed for "+result.info);
                    }
                }
            });
        }
    });

    $("#changeInfo").click(function () {
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
            url:"//localhost:8080/PersonalManagerSystem_war/updatePeopleInfo",
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
        })
    });

    $("#changepwd").click(function () {
        console.log("修改密码，需要重新登录");
        var number = $(".number").val();
        var newpwd = $(".newpwd").val();
        var newpwdconformed = $(".newpwdconformed").val();
        if (newpwd === newpwdconformed){
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"//localhost:8080/PersonalManagerSystem_war/changepwd",
                data: {
                    "number":number,
                    "password":newpwdconformed
                },
                success:function (result) {
                    if (result.code === 200) {
                        console.log("修改密码成功");
                        alert(result.data+"请重新登录！");
                        window.location.href="//localhost:8080/PersonalManagerSystem_war/index";
                    } else {
                        alert(result.data);
                    }
                }
            });
        } else {
            alert("两次输入的密码不一致");
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
        console.log("荣誉信息");;
        $(".peopleinfo").hide();
        $(".peoplethesis").hide();
        $(".peopleawards").show();
    });

    $(".display-all-award").click(function () {
       console.log("显示全部荣誉信息:");

    });

    $(".display-all-thesis").click(function () {
        console.log("显示全部论文信息:");
    });

    $(".award-search").click(function () {
        console.log("搜索荣誉信息");
    });

    $(".thesis-search").click(function () {
        console.log("搜索论文信息")
    });
});
