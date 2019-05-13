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
                url:"/PersonalManagerSystem_war/login",
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

    $(".body1").click(function(){
        alert("body1");
        $(".body1").className = "active";
        $(".body2").className = "";
        $(".body3").className = "";
    });

    $(".body2").click(function(){
        alert("body2");
        $(".body2").className = "active";
        $(".body1").className = "";
        $(".body3").className = "";
    });

    $(".body3").click(function(){
        alert("body3");
        $(".body3").className = "active";
        $(".body2").className = "";
        $(".body1").className = "";
    });


});
