
//公用参数
var loginToken=sessionStorage.xxfLoginToken;//登录token
var loginUserID=sessionStorage.xxfLoginUserID;//当前登录用户名
var loginUserName=sessionStorage.xxfLoginUserName;//当前登录用户名
var loginStatus=sessionStorage.xxfLoginStatu;//当前登录用户状态
var auth="";
if(sessionStorage.xxfAuth!=""&&sessionStorage.xxfAuth!=undefined){
    auth=eval("("+sessionStorage.xxfAuth+")");//当前登录用户的权限
    console.log(auth)
}

Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-top',
    theme: 'flat'
}

window.alert = function(str){
    Messenger().post({
        message: str,
        type: 'error',
        showCloseButton: true,
        hideAfter: 2
    });
}

function serializeObject(a){
    var o = {};
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

//公用后台调用接口(同步
function invoking(url,params,returnMethod){
    params["authToken"]=loginToken;
    $.getJSON(url,params,function(result,status){
        if(status=="success"){
            returnMethod(result);
        }else{
            alert("系统异常");
        }
    }).fail(function (result) {
        console.log(result.status);
        if(result.status=="401"){
            window.location="/login/login.html";
        }else if(result.status=="553"){
            alert("权限不足");
        }else{
            alert("系统异常");
        }

    });

}

//公用后台调用接口（异步
function invokingNoAsync(url,params,returnMethod){
    params["authToken"]=loginToken;
    $.ajax({url:url,
            data:params,
            async:false,
            success:function(result,status){
            if(status=="success"){
                returnMethod(result);
            }else{
                alert("系统异常");
            }},
            dataTyoe:"json",
            error:function (result) {
        console.log(result.status);
                if(result.status=="401"){
                    window.location="/login/login.html";
                }else if(result.status=="553"){
                    alert("权限不足");
                }else{
                    alert("系统异常");
                }

            }
    })
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}