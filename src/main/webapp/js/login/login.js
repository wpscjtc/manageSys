if(sessionStorage.loginClickTime==undefined){
    sessionStorage.loginClickTime=0;
}
$(function () {
    if(sessionStorage.loginClickTime>=4&&$("#vrifyCode").length==0){
        $("#vrifyCodeDiv").html("<input id=\"vrifyCode\" type=\"text\" class=\"form-control\" placeholder=\"验证码\" required=\"\" />");
        $("#vrifyCodeImg").html("<img class=\"reset_pass\" alt=\"验证码\" onclick = \"this.src='/login/defaultKaptcha?d='+new Date()*1\" src=\"/login/defaultKaptcha\" />");
    }
    var userId=GetQueryString("userId");
    var key=GetQueryString("key");
    var timeStamp=GetQueryString("timeStamp");

    if(userId!=null&&key!=null&&timeStamp!=null){

        invoking("/login/loginForIm",{userId:userId,key:key,timeStamp:timeStamp},function (result) {
            if(result.status==0){
                alert(result.msg);
            }else if(result.status==2){
                window.location="/login/login.html";
            }else{
                var resulObject =eval('(' + result.restMsg + ')');
                sessionStorage.xxfLoginToken=resulObject.loginToken;
                sessionStorage.xxfLoginUserID=resulObject.loginUserID;//当前登录用户名
                sessionStorage.xxfLoginUserName=resulObject.loginUserName;//当前登录用户名
                sessionStorage.xxfLoginStatus=resulObject.loginStatus;//当前登录用户状态
                window.location="/index/index.html";
            }
        });
    }

    $("#loginButton").click(function(){
        var userId=$("#userId").val();
        var password=$("#password").val();

        var vrifyCode="";
        if($("#vrifyCode").length>0){
            vrifyCode=$("#vrifyCode").val();
        }
        if((userId=="")||(password=="")){
            alert("用户名密码不能为空！");
            sessionStorage.loginClickTime++;
            //错误登录次数超过四次，出现验证码窗口
            if(sessionStorage.loginClickTime>=4&&$("#vrifyCode").length==0){
                $("#vrifyCodeDiv").html("<input id=\"vrifyCode\" type=\"text\" class=\"form-control\" placeholder=\"验证码\" required=\"\" />");
                $("#vrifyCodeImg").html("<img class=\"reset_pass\" alt=\"验证码\" onclick = \"this.src='/login/defaultKaptcha?d='+new Date()*1\" src=\"/login/defaultKaptcha\" />");
            }
        }else{

            invoking("/login/login",{userID:userId,password:password,vrifyCode:vrifyCode},function (result) {
                if(result.status==0){
                    alert(result.msg);
                    sessionStorage.loginClickTime++;
                    //错误登录次数超过四次，出现验证码窗口
                    if(sessionStorage.loginClickTime>=4&&$("#vrifyCode").length==0){
                        $("#vrifyCodeDiv").html("<input id=\"vrifyCode\" type=\"text\" class=\"form-control\" placeholder=\"验证码\" required=\"\" />");
                        $("#vrifyCodeImg").html("<img class=\"reset_pass\" alt=\"验证码\" onclick = \"this.src='/login/defaultKaptcha?d='+new Date()*1\" src=\"/login/defaultKaptcha\" />");
                    }
                }else{
                    var resulObject =eval('(' + result.restMsg + ')');
                    sessionStorage.xxfLoginToken=resulObject.loginToken;
                    sessionStorage.xxfLoginUserID=resulObject.loginUserID;//当前登录用户名
                    sessionStorage.xxfLoginUserName=resulObject.loginUserName;//当前登录用户名
                    sessionStorage.xxfLoginStatus=resulObject.loginStatus;//当前登录用户状态
                    sessionStorage.xxfAuth=JSON.stringify(resulObject.authMap);
                    window.location="/index/index.html";
                }
            });
        }
    })
})