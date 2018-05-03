
var urlR;
var serchConditionsR;
var headR;
var serchInfoR;
var tableIdR;
var keyNameR;
var dictR;
function goSerch(){
    serchConditionsR["pageNum"]=1;
    getTable(urlR,serchConditionsR,headR,serchInfoR,tableIdR,keyNameR,dictR);
}

function goForPage(pageNum){
    serchConditionsR["pageNum"]=pageNum;
    getTable(urlR,serchConditionsR,headR,serchInfoR,tableIdR,keyNameR,dictR);
}



function getTable(url,serchConditions,head,serchInfo,tableId,keyName,dict) {

    tableIdR=tableId;
    urlR=url;
    serchConditionsR=serchConditions;
    headR=head;
    serchInfoR=serchInfo;
    keyNameR=keyName;
    var pageNum=serchConditions["pageNum"];
    if(dict==undefined){
        dict={};
    }
    dictR=dict;
    var tableHtml;

    jQuery.each(serchInfo, function(i, val) {
        var condition=$("#"+i).val();

        if(condition!=undefined&&condition!=""){
            condition=condition.replace(/(^\s*)|(\s*$)/g, "");
            serchConditions[i]=condition;
        }else{
            delete serchConditions[i];
        }

    });
    invokingNoAsync(url, serchConditions, function (result) {
        var resultO = eval('(' + result + ')');
        var msgObject = eval('(' + resultO.restMsg + ')');
        var msgList = msgObject.list;
        var headTd="";
        var bodyTd="";
        var serchHtml="";
        var pageHtml="";
        var prePage="";

        if(msgObject["pages"]>1){
            if(msgObject["hasPreviousPage"]==true){
                prePage="<li class=\"paginate_button previous \" id=\"datatable_previous\"><a href=\"javascript:goForPage("+msgObject["prePage"]+");\" aria-controls=\"datatable\" data-dt-idx=\"0\" tabindex=\"0\">上一页</a></li>";
            }
            var nextPage="";
            if(msgObject["hasNextPage"]==true){
                nextPage=" <li class=\"paginate_button next\" id=\"datatable_next\"><a href=\"javascript:goForPage("+msgObject["nextPage"]+");\" aria-controls=\"datatable\" data-dt-idx=\"7\" tabindex=\"0\">下一页</a></li>";
            }
            var pageInfo="";
            for(var p=0;p<msgObject["navigatepageNums"].length;p++){
                var thisPage=msgObject["navigatepageNums"][p];
                if(thisPage==msgObject["pageNum"]){
                    pageInfo+="<li class=\"paginate_button active\"> <a href=\"#\" aria-controls=\"datatable\" data-dt-idx=\""+thisPage+"\" tabindex=\"0\">"+thisPage +
                        "</a></li>";
                }else{
                    pageInfo+="<li class=\"paginate_button \"><a href=\"javascript:goForPage("+thisPage+");\" aria-controls=\"datatable\" data-dt-idx=\"2\" tabindex=\"0\">"+thisPage+"</a></li><li class=\"paginate_button \">";
                }
            }
            pageHtml=prePage+pageInfo+nextPage;
        }


        if (resultO.status == 1) {
            var buttonTd="";
            //console.log(head);
            jQuery.each(head, function(i, val) {
                if(i=="TrButton"){
                    headTd+="                                    <th class=\'column-title\'>功能选项</th>" ;
                    buttonTd=val;
                }else{
                    headTd+="                                    <th class=\'column-title\'>"+val+"</th>" ;
                }

            });
            jQuery.each(serchInfo, function(i, val) {
                if(serchConditions[i]!=null&&serchConditions[i]!=undefined&&serchConditions[i]!=""){
                    serchHtml+="<label >"+val+":<input value='"+serchConditions[i]+"' id='"+i+"' style='margin-left: 10px;height: 30px' /></label> "
                }else{
                    serchHtml+="<label >"+val+":<input id='"+i+"' style='margin-left: 10px;height: 30px' /></label> "
                }

            });



            for (var i=0;i<msgList.length;i++) {
                var trH="                                <tr id='tr_"+msgList[i][keyName]+"' class=\'even pointer\'>"+
                    "<td class=\'a-center \'>" +
                    "                                        <input type=\'checkbox\' class=\'flat\ ' id='"+msgList[i][keyName]+"' name=\'table_records\'>" +
                    "                                    </td>";
                var trB="";
                var obuttonTd=buttonTd;
                var buttonHtml="<td>";

                for (var c=0;c<obuttonTd.length;c++){
                   // buttonHtml+="<td onclick='"+obuttonTd[c][0]+"("+msgList[i][keyName]+")'>"++"</td>";
                    //buttonHtml+= "<td><button type=\"button\" onclick='"+obuttonTd[c][0]+"("+msgList[i][keyName]+")' class=\"btn btn-info\">"obuttonTd[c][1]"</button></td>";
                    buttonHtml+="<button type='buttom' onclick="+obuttonTd[c][0]+"('"+msgList[i][keyName]+"','"+pageNum+"') class='btn btn-success btn-xs'>"+obuttonTd[c][1]+"</button>";
                }
                buttonHtml+="</td>";
                if(buttonHtml=="<td></td>"){
                    buttonHtml="";
                }

                var trE="                                </tr>";

                jQuery.each(head, function(n, val) {

                    if(n!="TrButton"){
                        if(dict[n]!=undefined&&dict[n]!=null){
                            trB+="                                    <td class=\'"+n+"\'>"+dict[n][msgList[i][n]]+"</td>";
                        }else{
                            if(msgList[i][n]==undefined){
                                trB+="                                    <td class=\'"+n+"\'>"+"/"+"</td>";
                            }else{
                                trB+="                                    <td class=\'"+n+"\'>"+msgList[i][n]+"</td>";
                            }

                        }
                    }

                });

                bodyTd+=(trH+trB+buttonHtml+trE);
            }

            var tableHtmlHead =  "                <div class=\'x_panel\'>" +
                "                 <div class=\"x_title\">" +
                serchHtml +"<button class='btn btn-success' onclick='goSerch()' >搜索</button>"+
                toolHtml+
                "                   </div>"+
                "                    <div class=\'x_content\'>" +
                "                   <div class=\"clearfix\"></div>" +
                "                        <div class=\'table-responsive\'>" ;
            var tableHtmlStart = "                            <table id=\'datatable-buttons\' class=\'table table-striped jambo_table bulk_action\'>" +
                "                                <thead>" +
                "                                <tr class=\'headings\'>" +
                "                                    <th>" +
                "                                        <input type=\'checkbox\' id=\'check-all\' class=\'flat\'>" +
                "                                    </th>";


            var tableHtmlMid = "                  </tr>" +
                "                                </thead>" +
                "                                <tbody>";

            var tableHtmlTail = "               </tbody>" +
                "                            </table>" +
                "                        </div>" +
                                            "<div class=\"row\">" +
                                            "<div style='margin-left:auto;margin-right:45%;' class=\"dataTables_paginate paging_simple_numbers\" id=\"datatable_paginate\">" +
                                            "  <ul class=\"pagination\">" +
                                            pageHtml+
                                                " </ul>" +
                                                "" +
                                            "</div>" +
                                       "</div>"+
                "                    </div>" +
                "                </div>" ;


            if(msgObject["pages"]<=0){
                headTd="";
                tableHtmlStart="";
                bodyTd="<h4 align='center'>暂无数据</h4>";
            }
            tableHtml= tableHtmlHead+tableHtmlStart+headTd+tableHtmlMid+bodyTd+tableHtmlTail;
            $("#"+tableId).html(tableHtml);
            $.getScript("../build/js/custom.min_t.js");
        }
    })
}

