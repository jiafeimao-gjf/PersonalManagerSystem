$(document).ready(function () {
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
            });
        }
    });

    $("#changepwd").click(function () {
        console.log("修改密码，需要重新登录");
        var number = $(".number").val();
        var newpwd = $(".newpwd").val();
        var newpwdconformed = $(".newpwdconformed").val();
        if (newpwdconformed === null || newpwd === null){
            alert("密码不能为空");
        } else {
            if (newpwd === newpwdconformed){
                if (confirm("确定要修改密码？")) {
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
                                sessionStorage.clear();
                                window.location.href="//localhost:8080/PersonalManagerSystem_war/index";
                                $(".peopleinfo").show();
                                $(".peoplethesis").hide();
                                $(".peopleawards").hide();
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
        console.log("荣誉信息");;
        $(".peopleinfo").hide();
        $(".peoplethesis").hide();
        $(".peopleawards").show();
    });

    $(".body4").click(function(){
        console.log("清除session，重新登录");
        sessionStorage.clear();
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
                   var honorList = sessionStorage.getItem("honorList");
                   console.log(honorList);
                   // var honor_body = $("#honors-body");
                   // for (var i = 0;i < honorList.size(); i++){
                   //     var row = document.createElement('tr');
                   //     var idCell = document.createElement('td');
                   //     idCell.innerHTML = honorList[i].honorid;
                   //     row.appendChild(idCell);
                   //     var numberCell = document.createElement('td');
                   //     numberCell.innerHTML = honorList[i].number;
                   //     row.appendChild(numberCell);
                   //     var awardNameCell = document.createElement('td');
                   //     awardNameCell.innerHTML = honorList[i].awardname;
                   //     row.appendChild(awardNameCell);
                   //     var awardLevelCell = document.createElement('td');
                   //     awardLevelCell.innerHTML = honorList[i].awardlevel;
                   //     row.appendChild(awardLevelCell);
                   //     var opeartion = document.createElement('td');
                   //     var btnDetail = document.createElement('input'); //创建一个input控件
                   //     btnDetail.setAttribute('type','button'); //type="button"
                   //     btnDetail.setAttribute('value','查看详情');
                   //     opeartion.appendChild(btnDetail);
                   //     var btnUpdate = document.createElement('input'); //创建一个input控件
                   //     btnUpdate.setAttribute('type','button'); //type="button"
                   //     btnUpdate.setAttribute('value','更新');
                   //     opeartion.appendChild(btnUpdate);
                   //     row.appendChild(opeartion);
                   //     honor_body.appendChild(row);
                   // }
               } else {
                   alert(result.data);
               }
           }
       });
    });

    $(".display-all-thesis").click(function () {
        console.log("显示全部论文信息:");
    });

    $(".award-search").click(function () {
        console.log("搜索荣誉信息");

    });

    $(".thesis-search").click(function () {
        console.log("搜索论文信息");

    });

    // 修改个人信息控制
    $("#change-name").click(function () {
        $(".name").onchange(function () {

        });
    });

    $("#change-age").click(function () {
        $(".age").readonly = "";
    });

});
