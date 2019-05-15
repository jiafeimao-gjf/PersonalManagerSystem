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
                    number:no,
                    pwd:p
                },
                success:function (result) {
                    console.log(result.data);
                    // var peopleInfo = $.parseJSON(result);
                    if (result.code === 200) {
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
    });

    $("#cancelChange").click(function () {
        console.log("取消修改");
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
});
