var toolHtml=""
var orgIdR="";
function getAuths(orgId){
    //console.log(123213);
    orgIdR=orgId;
    getTable("/role/getRoleByOderId",{"orderId":orgId,pageNum:1},{"id":"角色ID","name":"角色名","Category":"角色类别","roleCode":"角色编号","TrButton":[["authAllocation","权限配置"]]},{"id":"角色ID","name":"角色名","roleCode":"角色编号"},"roleTable","id",{"Category":{"1":"喜相逢汽车服务股份有限公司总部","2":"喜相逢汽车服务股份有限公司分部","3":"喜相逢汽车服务股份有限公司"}})
}
createTree("/org/orgTree",{},"orgTree");