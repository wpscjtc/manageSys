


function createTree(url,serchConditions,treeId){

    invokingNoAsync(url,serchConditions,function (result) {
        var resultO = eval('(' + result + ')');
        var msgObject = eval('(' + resultO.restMsg + ')');
        $('#'+treeId).treeview({data: msgObject,enableLinks:true});
    });


}
