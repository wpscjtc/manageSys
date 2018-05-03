var toolHtml="<ul class=\"nav navbar-right panel_toolbox\">\n" +
"                      <li><a class=\"collapse-link\"><i class=\"fa fa-chevron-up\"></i></a>\n" +
"                      </li>\n" +
"                      <li class=\"dropdown\">\n" +
"                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-expanded=\"false\"><i class=\"fa fa-wrench\"></i></a>\n" +
"                        <ul class=\"dropdown-menu\" role=\"menu\">\n" +
"                          <li><a href=\"javascript:goAddAuth();\">新增</a>\n" +
"                          </li>\n" +
    "                          <li><a href=\"javascript:delSelectedAuth();\">删除</a>\n" +
    "                          </li>\n" +
"                        </ul>\n" +
"                      </li>\n" +
"                      <li><a class=\"close-link\"><i class=\"fa fa-close\"></i></a>\n" +
"                      </li>\n" +
"                    </ul>";

function goAddAuth(){

    $("#addAuth").modal();
}

function goUpdAuth(authId){
    $("#updateFormSet").removeAttr("disabled");
    $("#sendAuthFormForUpdate").show();
    invokingNoAsync("/auth/getAuthById",{"authId":authId},function (result) {
        var rO=eval("("+result+")");
        var restMsg=eval("("+rO.restMsg+")")[0];
        if(rO.status==1){
            $("#updateAuth .form-control").each(function () {
                var valIn=restMsg[$(this).attr("name")];
                if(valIn!=undefined){
                    $(this).val(valIn);
                }

            })
            $("#updateAuth").modal();
        }else{
            alert("跳转修改失败");
        }
    })
}

function detAuth(authId){
    $("#updateFormSet").attr("disabled","");
    $("#sendAuthFormForUpdate").hide();
    invokingNoAsync("/auth/getAuthById",{"authId":authId},function (result) {
        var rO=eval("("+result+")");
        var restMsg=eval("("+rO.restMsg+")")[0];
        if(rO.status==1){
            $("#updateAuth .form-control").each(function () {
                var valIn=restMsg[$(this).attr("name")];
                if(valIn!=undefined){
                    $(this).val(valIn);
                    $(this)
                }

            })
            $("#updateAuth").modal();
        }else{
            alert("跳转失败");
        }
    })
}

$("#sendAuthFormForUpdate").click(function () {
    var isAllCheck=true;
    $("#updateAuth .form-control").each(function (index,domEle){
        if($(domEle).attr("required")=="required"&&($(domEle).val()==undefined||$(domEle).val()=="")){
            isAllCheck=false;
        }
    });
    console.log(serializeObject($("#updateAuthForm").serializeArray()));
    if(isAllCheck){
        invokingNoAsync("/auth/updateAuth",serializeObject($("#updateAuthForm").serializeArray()),function (result) {
            var rO=eval("("+result+")");
            if(rO.status==1){
                location.reload();
            }else{
                alert("修改失败");
            }
        })
    }else{
        alert("请填写所有必填信息！");
    }

});


$("#sendAuthForm").click(function () {
    var isAllCheck=true;
    $("#addAuth .form-control").each(function (index,domEle){
        if($(domEle).attr("required")=="required"&&($(domEle).val()==undefined||$(domEle).val()=="")){
            isAllCheck=false;
        }
    });
    if(isAllCheck){
        invokingNoAsync("/auth/addAuth",serializeObject($("#addAuthForm").serializeArray()),function (result) {
            var rO=eval("("+result+")");
            if(rO.status==1){
                location.reload();
            }else{
                alert("新增失败");
            }
        })
    }else{
        alert("请填写所有必填信息！");
    }

});

function delAuth(authId,pageNum){
    invokingNoAsync("/auth/del",{"authIds":authId},function (result) {
        var rO=eval("("+result+")");
        if(rO.status==1){
            getTable("/auth/listAuths",{"pageNum":pageNum},{"AuthID":"权限ID","authName":"权限名","authType":"权限类型","authWay":"权限点","TrButton":[["delAuth","删除"],["goUpdAuth","修改"],["detAuth","详情"]]},{"AuthID":"权限ID","authName":"权限名","authType":"权限类型","authWay":"权限点"},"authTable","AuthID");
        }else{
            alert("删除失败");
        }
    })
}

function delSelectedAuth(){
    var authIds="";

    if($(".flat:checked").length>0){
        $(".flat:checked").each(function () {
            if($(this).attr("id")!="check-all"){
                authIds+=($(this).attr("id"))+",";
            }
        })
        console.log(authIds);
        invokingNoAsync("/auth/del",{"authIds":authIds},function (result) {
            var rO=eval("("+result+")");
            if(rO.status==1){
                location.reload();
            }else{
                alert("删除失败");
            }
        })
    }else{
        alert("请勾选要删除的行");
    }

}


getTable("/auth/listAuths",{pageNum:1},{"AuthID":"权限ID","authName":"权限名","authType":"权限类型","authWay":"权限点","TrButton":[["delAuth","删除"],["goUpdAuth","修改"],["detAuth","详情"]]},{"AuthID":"权限ID","authName":"权限名","authType":"权限类型","authWay":"权限点"},"authTable","AuthID");