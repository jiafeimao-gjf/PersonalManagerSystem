$(document).ready(function () {
    // 登陆实现
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
                        console.log(result.info);
                        alert("Log In Successfully");
                        console.log("用户类型："+result.data);
                        if (result.data === "user"){
                            window.location.href="/PersonalManagerSystem_war/personalIndex";
                        } else if (result.data === "admin"){
                            alert("欢迎你，系统管理员");
                            window.location.href="/PersonalManagerSystem_war/adminIndex";
                        }
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
                        if (result.data === "user"){
                            window.location.href="/PersonalManagerSystem_war/personalIndex";
                        } else if (result.data === "admin"){
                            window.location.href="/PersonalManagerSystem_war/adminIndex";
                        }
                    } else {
                        alert("个人信息修改失败，因为"+resultVO.info);
                    }
                }
            });
        }
    });

    $("#changeStuffInfo").click(function () {
        if (confirm("确定要修改信息？")){
            console.log("修改信息");
            var number = $(".stuff-number").val();
            var name = $(".stuff-name").val();
            var age = $(".stuff-age").val();
            var sex = $(".stuff-sex").val();
            var depart = $(".stuff-department").val();
            var position = $(".stuff-position").val();
            var birthplace = $(".stuff-birthplace").val();
            var nation = $(".stuff-nation").val();
            var identityno = $(".stuff-identityno").val();
            var politicalstatus = $(".stuff-politicalstatus").val();
            var phonenumber = $(".stuff-phonenumber").val();
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"/PersonalManagerSystem_war/updatepeopleinfo",
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
                    "phonenumber":phonenumber,
                    "checked":1
                },
                success:function (resultVO) {
                    if (resultVO.code === 200) {
                        console.log("修改成功");
                        window.location.href="/PersonalManagerSystem_war/peopledetail";
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
                                window.location.href="/PersonalManagerSystem_war/";
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
        $(".stuffinfo").hide();
        $(".peopleawards").hide();
    });

    $(".body2").click(function(){
        console.log("论文信息");
        $(".peopleinfo").hide();
        $(".peoplethesis").show();
        $(".stuffinfo").hide();
        $(".peopleawards").hide();
    });

    $(".body3").click(function(){
        console.log("荣誉信息");
        $(".peopleinfo").hide();
        $(".peoplethesis").hide();
        $(".stuffinfo").hide();
        $(".peopleawards").show();
    });
    $(".body5").click(function(){
        console.log("荣誉信息");
        $(".peopleinfo").hide();
        $(".peoplethesis").hide();
        $(".stuffinfo").show();
        $(".peopleawards").hide();
    });

    $(".body4").click(function(){
        if (confirm("确定退出登录？")){
            console.log("清除session，重新登录");
            window.location.href = "/PersonalManagerSystem_war/";
        } else {
            console.log("取消重新登录");
        }
    });

    $(".display-my-award").click(function () {
       console.log("显示我的荣誉信息:");
       var number = $(".number").val();
       $.ajax({
           type:"get",
           datatype:"form-data",
           url:"/PersonalManagerSystem_war/gethonorsbynumber",
           data: {
               "number":number
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
    });

    $(".display-my-thesis").click(function () {
        console.log("显示我的论文信息:");
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
                    window.location.href="/PersonalManagerSystem_war/personalIndex?chosenmenu=thesisinfo";
                } else {
                    alert(result.data);
                }
            }
        });
    });

    $(".my-award-search").click(function () {
        console.log("搜索我的荣誉信息");
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
                "pagenum":1
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
    });

    $(".my-thesis-search").click(function () {
        console.log("搜索我的论文信息");
        var title = $(".key-thesis-title").val();
        var classify  = $(".key-thesis-classify").val();
        var magazine = $(".key-thesis-magazine").val();
        $.ajax({
            type:"get",
            datatype:"form-data",
            url:"/PersonalManagerSystem_war/getthesisbylikes",
            data: {
                "title":title,
                "classify":classify,
                "magazine":magazine,
                "pagenum":1
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
    });



    $("#changethesisinfo").click(function () {
        console.log("修改论文信息");
        var thesisid = $(".thesis-id").val();
        var name = $(".thesis-name").val();
        var department = $(".thesis-depart").val();
        var title = $(".thesis-title").val();
        var classify = $(".thesis-classify").val();
        var magazine = $(".thesis-maga").val();
        $.ajax({
            type:"post",
            datatype:"form-data",
            url:"/PersonalManagerSystem_war/updatethesisinfo",
            data:{
                "thesisid":thesisid,
                "name":name,
                "company":department,
                "title":title,
                "classify":classify,
                "magazine":magazine,
                'checked':2
            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.data);
                    $(".thesis-checked").val("未审核");
                    alert(result.data);
                } else {
                    console.log(result.data);
                    alert(result.data);
                }
            }
        });
    });

    $('#changehonorinfo').click(function () {
        console.log("修改荣誉信息");
        var honorid = $(".honor-id").val();
        var department = $(".honor-depart").val();
        var awardname = $(".honor-award-name").val();
        var awardlevel = $(".honor-award-level").val();
        var awardcpy = $(".honor-award-depart").val();
        var grade = $(".honor-grade").val();
        var remarks = $(".honor-remarks").val();
        $.ajax({
            type:"post",
            datatype:"form-data",
            url:"/PersonalManagerSystem_war/updatehonorinfo",
            data:{
                "honorid":honorid,
                "awardname":awardname,
                "company":department,
                "awardlevel":awardlevel,
                "awardcpy":awardcpy,
                "grade":grade,
                "remarks":remarks,
                "checked":2
            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.data);
                    window.location.href="/PersonalManagerSystem_war/lookhonorinfo";
                } else {
                    console.log(result.data);
                    alert(result.data);
                }
            }
        });
    });

    $("#add-new-stuff").click(function () {
       console.log("add-new-stuff");
    });
    $("#add-new-honor").click(function () {
        console.log("add-new-honor");
    });
    $("#add-new-thesis").click(function () {
        console.log("add-new-thesis");
    });
    $("#add-many-stuff").click(function () {
        console.log("add-new-stuff");
    });
    $("#add-many-honor").click(function () {
        console.log("add-many-honor");
    });
    $("#add-many-thesis").click(function () {
        console.log("add-many-thesis");
    });

});
