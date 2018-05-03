var toolHtml="";/*"<ul class=\"nav navbar-right panel_toolbox\">\n" +
"                      <li><a class=\"collapse-link\"><i class=\"fa fa-chevron-up\"></i></a>\n" +
"                      </li>\n" +
"                      <li class=\"dropdown\">\n" +
"                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-expanded=\"false\"><i class=\"fa fa-wrench\"></i></a>\n" +
"                        <ul class=\"dropdown-menu\" role=\"menu\">\n" +
"                          <li><a href=\"javascript:authAllocation();\">权限赋予</a>\n" +
"                          </li>\n" +
"                        </ul>\n" +
"                      </li>\n" +
"                      <li><a class=\"close-link\"><i class=\"fa fa-close\"></i></a>\n" +
"                      </li>\n" +
"                    </ul>";*/

getTable("/org/getAllOrg",{pageNum:1},{"id":"组织ID","orgType":"组织类型","orgCode":"组织编号","name":"组织名称","describe":"组织介绍","fState":"组织状态","perNum":"人数"},{"orgCode":"组织编号","name":"组织名称",},"roleTable","id",{"orgType":{"1":"集团","2":"公司","3":"部门","4":"小组","5":"中心"},"fState":{"1":"使用","2":"停止"}})
