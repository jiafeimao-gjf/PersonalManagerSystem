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
                url:"/PersonalManagerSystem/login",
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
                            window.location.href="/PersonalManagerSystem/personalIndex";
                        } else if (result.data === "admin"){
                            alert("欢迎你，系统管理员");
                            window.location.href="/PersonalManagerSystem/adminIndex";
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
                url:"/PersonalManagerSystem/updatePeopleInfo",
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
                            window.location.href="/PersonalManagerSystem/personalIndex";
                        } else if (result.data === "admin"){
                            window.location.href="/PersonalManagerSystem/adminIndex";
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
                url:"/PersonalManagerSystem/updatepeopleinfo",
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
                    "checked":2
                },
                success:function (resultVO) {
                    if (resultVO.code === 200) {
                        console.log("修改成功");
                        window.location.href="/PersonalManagerSystem/peopledetail";
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
                        url:"/PersonalManagerSystem/changepwd",
                        data: {
                            "number":number,
                            "password":newpwdconformed
                        },
                        success:function (result) {
                            if (result.code === 200) {
                                console.log("修改密码成功");
                                alert(result.data+"请重新登录！");
                                window.location.href="/PersonalManagerSystem/?logout="+number;
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
            var number = $(".number").val();
            window.location.href = "/PersonalManagerSystem/?logout="+number;
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
           url:"/PersonalManagerSystem/gethonorsbynumber",
           data: {
               "number":number
           },
           success:function (result) {
               if (result.code === 200) {
                   console.log(result.info);
                   window.location.href="/PersonalManagerSystem/personalIndex?chosenmenu=honorinfo";
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
            url:"/PersonalManagerSystem/getthesisbynumber",
            data: {
                "number":number
            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.info);
                    window.location.href="/PersonalManagerSystem/personalIndex?chosenmenu=thesisinfo";
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
        if (awardname.length === 0 && department.length === 0 && awardlevel === 0){
            alert("至少输入一个搜索项");
        } else {
            if (awardname.length === 0) {
                alert("荣誉名称是必填项");
            } else {
                $.ajax({
                    type:"get",
                    datatype:"form-data",
                    url:"/PersonalManagerSystem/gethonorsbylikes",
                    data: {
                        "awardname":awardname,
                        "department":department,
                        "awardlevel":awardlevel,
                        "pagenum":1
                    },
                    success:function (result) {
                        if (result.code === 200) {
                            console.log(result.info);
                            window.location.href="/PersonalManagerSystem/personalIndex?chosenmenu=honorinfo";
                        } else {
                            alert(result.data);
                        }
                    }
                });
            }
        }
    });

    $(".my-thesis-search").click(function () {
        console.log("搜索我的论文信息");
        var title = $(".key-thesis-title").val();
        var classify  = $(".key-thesis-classify").val();
        var magazine = $(".key-thesis-magazine").val();
        $.ajax({
            type:"get",
            datatype:"form-data",
            url:"/PersonalManagerSystem/getthesisbylikes",
            data: {
                "title":title,
                "classify":classify,
                "magazine":magazine,
                "pagenum":1
            },
            success:function (result) {
                if (result.code === 200) {
                    console.log(result.info);
                    window.location.href="/PersonalManagerSystem/personalIndex?chosenmenu=thesisinfo";
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
            url:"/PersonalManagerSystem/updatethesisinfo",
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
                    window.location.href="/PersonalManagerSystem/thesisdetail";
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
            url:"/PersonalManagerSystem/updatehonorinfo",
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
                    window.location.href="/PersonalManagerSystem/lookhonorinfo";
                } else {
                    console.log(result.data);
                    alert(result.data);
                }
            }
        });
    });

    $("#add-new-stuff").click(function () {
        console.log("add-new-stuff");
        var number = $(".new-stuff-number").val();
        var name = $(".new-stuff-name").val();
        var sex = $(".new-stuff-sex").val();
        var age = $(".new-stuff-age").val();
        var depart = $(".new-stuff-department").val();
        var position = $(".new-stuff-position").val();
        var birth = $(".new-stuff-birthplace").val();
        var nation = $(".new-stuff-nation").val();
        var identity = $(".new-stuff-identityno").val();
        var political = $(".new-stuff-politicalstatus").val();
        var phone = $(".new-stuff-phonenumber").val();
        if (confirm("确定增加该教职工？增加后将会为其创建登录账户。")) {
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"/PersonalManagerSystem/addnewpeople",
                data:{
                    "number":number,
                    "name":name,
                    "sex":sex,
                    'age':age,
                    "department":depart,
                    "position":position,
                    "birthplace":birth,
                    "nation":nation,
                    "politicalstaus":political,
                    "identityno":identity,
                    "phonenumber":phone,
                    "checked":1
                },
                success:function (result) {
                    if (result.code === 200) {
                        console.log(result.data);
                        alert("成功"+result.data);
                        $(".new-stuff-number").val("");
                        $(".new-stuff-name").val("");
                        $(".new-stuff-sex").val("");
                        $(".new-stuff-age").val("");
                        $(".new-stuff-department").val("");
                        $(".new-stuff-position").val("");
                        $(".new-stuff-birthplace").val("");
                        $(".new-stuff-nation").val("");
                        $(".new-stuff-identityno").val("");
                        $(".new-stuff-politicalstatus").val("");
                    } else {
                        alert("失败："+result.data);
                    }
                }
            });
        }
    });
    $("#add-new-honor").click(function () {
        console.log("add-new-honor");
        var number = $(".new-honor-number").val();
        var depart = $(".new-honor-depart").val();
        var awardname = $(".new-honor-award-name").val();
        var awardlevel = $(".new-honor-award-level").val();
        var awarddepart = $(".new-honor-award-depart").val();
        var grade = $(".new-honor-grade").val();
        var remarks = $(".new-honor-remarks").val();
        if (confirm("确定增加该荣誉信息？")) {
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"/PersonalManagerSystem/addnewhonor",
                data:{
                    "number":number,
                    "company":depart,
                    "awardname":awardname,
                    "awardlevel":awardlevel,
                    "awardcpy":awarddepart,
                    "grade":grade,
                    "remarks":remarks,
                    "checked":1
                },
                success:function (result) {
                    if (result.code === 200) {
                        console.log(result.data);
                        alert("成功"+result.data);
                    } else {
                        alert("失败："+result.data);
                    }
                }
            });
        }
    });

    $("#add-new-thesis").click(function () {
        console.log("add-new-thesis");
        var number = $(".new-thesis-number").val();
        var name = $(".new-thesis-name").val();
        var depart = $(".new-thesis-depart").val();
        var title = $(".new-thesis-title").val();
        var classify = $(".new-thesis-classify").val();
        var magazine = $(".new-thesis-maga").val();
        if (confirm("确定增加该荣誉信息？")) {
            $.ajax({
                type:"post",
                datatype:"form-data",
                url:"/PersonalManagerSystem/addnewthesis",
                data:{
                    "number":number,
                    "company":depart,
                    "name":name,
                    "title":title,
                    "classify":classify,
                    "magazine":magazine,
                    "checked":1
                },
                success:function (result) {
                    if (result.code === 200) {
                        console.log(result.data);
                        alert("成功："+result.data);
                    } else {
                        alert("失败："+result.data);
                    }
                }
            });
        }
    });
    $("#add-many-stuff").click(function () {
        console.log("add-many-stuff");
        var filePath = $("#people-excel")[0].files[0];
        console.log(filePath);
        var form = new FormData();
        form.append("stuffs", filePath);

        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "/PersonalManagerSystem/addpeoplebyfile",
            "method": "POST",
            "headers": {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            "processData": false,
            "contentType": false,
            "mimeType": "multipart/form-data",
            "data": form
        };

        $.ajax(settings).done(function (result) {
            if (result.code === 200) {
                alert(result.info);
            } else {
                alert(result.info+result.data);
            }
        });

    });
    $("#add-many-honor").click(function () {
        console.log("add-many-honor");
    });
    $("#add-many-thesis").click(function () {
        console.log("add-many-thesis");
    });


});
