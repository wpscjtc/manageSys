
//弹出框
document.writeln("<!-- 弹出框-->");
document.writeln("<div class=\'modal fade\' id=\'locatAuth\' tabindex=\'-1\' role=\'dialog\' aria-labelledby=\'myModalLabel\' aria-hidden=\'true\'>");
document.writeln("    <div class=\'modal-dialog\'>");
document.writeln("        <div class=\'modal-content\'>");
document.writeln("            <div class=\'modal-header\'>");
document.writeln("                <button type=\'button\' class=\'close\' data-dismiss=\'modal\' aria-hidden=\'true\'>");
document.writeln("                    &times;");
document.writeln("                </button>");
document.writeln("");
document.writeln("                    <div>您将给<span id=\'locatRole\'></span>分配权限：</div>");
document.writeln("            </div>");
document.writeln("            <div class=\'modal-body\'>");
document.writeln("            </div>");
document.writeln("            <div class=\'modal-footer\'>");
document.writeln("                <button type=\'button\' class=\'btn btn-default\' data-dismiss=\'modal\'>关闭");
document.writeln("                </button>");
document.writeln("            </div>");
document.writeln("        </div><!-- /.modal-content -->");
document.writeln("    </div><!-- /.modal -->");
document.writeln("</div>");



function changeAuth(roleId,authId,orgId) {
    console.log(orgId);
    invokingNoAsync("/auth/chanegRoleAuth", {"roleId":authId,"authId":roleId,"orgId":orgId}, function (result) {

        var resultO = eval('(' + result + ')');
        console.log(resultO);

    })
}

function authAllocation(id){
    var tr=$("#tr_"+id);
    var name=$("#tr_"+id).find(".name").html();
    $("#locatRole").html(name)
    invokingNoAsync("/auth/getAuthByRoleId", {"roleId":id,"orgId":orgIdR}, function (result) {

        var resultO = eval('(' + result + ')');
        var msgList = eval('['+resultO.restMsg+ ']')[0];
        var oneOption="";
        for(var i=0;i<msgList.length;i++){
            var isCheck=1;
            if(msgList[i]["isCheck"]==1){
                isCheck="checked";
            }else{
                isCheck="unchecked";
            }
            oneOption+="<label>\n" +
                "                    &nbsp;&nbsp;&nbsp;<input id='authIn_"+id+"_"+msgList[i]["AuthID"]+"' onclick='changeAuth("+msgList[i]["AuthID"]+","+id+","+orgIdR+")' type=\"checkbox\" class=\"js-switch\" "+isCheck+" /> "+msgList[i]["authName"]+"|"+msgList[i]["authWay"]+"\n" +
                "                </label>&nbsp;&nbsp;&nbsp;"
        }


        $(".modal-body").html(oneOption);
        if ($(".js-switch")[0]) {
            var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
            elems.forEach(function (html) {
                var switchery = new Switchery(html, {
                    color: '#26B99A'
                });
            });
        }
        $("#locatAuth").modal();
    })
}

$("#authAllocation").click(function () {
    authAllocation();
})