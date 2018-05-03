document.writeln("    <div class=\'col-md-3 left_col\'>");
document.writeln("        <div class=\'left_col scroll-view\'>");
document.writeln("            <div class=\'navbar nav_title\' style=\'border: 0;\'>");
document.writeln("                <a href=\'/index/index.html\' class=\'site_title\'><img style='height: 100%' src='/imgs/25120.png' /> <span>预算管理系统</span></a>");
document.writeln("            </div>");
document.writeln("");
document.writeln("            <div class=\'clearfix\'></div>");
document.writeln("            <br />");
document.writeln("            <div id=\'sidebar-menu\' class=\'main_menu_side hidden-print main_menu\'>");
document.writeln("                <div class=\'menu_section\'>");
document.writeln("                    <ul class=\'nav side-menu\'>");
//会员权限
if(auth.auth!=undefined){
    document.writeln("                        <li><a><i class=\'fa fa-home\'></i> 会员权限 <span class=\'fa fa-chevron-down\'></span></a>");
    document.writeln("                            <ul class=\'nav child_menu\'>");
    if((auth.auth).indexOf("all")>=0){
        document.writeln("                                <li><a href=\'/user/user_manager.html\'>会员管理</a></li>");
    }
    if((auth.auth).indexOf("all")>=0) {
        document.writeln("                                <li><a href=\'/user/auth_manager.html\'>权限管理</a></li>");
    }
    if((auth.auth).indexOf("all")>=0) {
        document.writeln("                                <li><a href=\'/user/role_manager.html\'>角色管理</a></li>");
    }
    if((auth.auth).indexOf("all")>=0) {
        document.writeln("                                <li><a href=\'/user/org_manager.html\'>部门管理</a></li>");
    }
    document.writeln("                            </ul>");
    document.writeln("                        </li>");
}

//流程管理
if(auth.liuc!=undefined) {
    document.writeln("                        <li><a><i class=\'fa fa-edit\'></i> 流程管理 <span class=\'fa fa-chevron-down\'></span></a>");
    document.writeln("                            <ul class=\'nav child_menu\'>");
    if((auth.liuc).indexOf("all")>=0) {
        document.writeln("                                <li><a href=\'form.html\'>xxx1流程</a></li>");
    }
    if((auth.liuc).indexOf("all")>=0) {
        document.writeln("                                <li><a href=\'form_advanced.html\'>xxx2流程</a></li>");
    }
    if((auth.liuc).indexOf("all")>=0) {
        document.writeln("                                <li><a href=\'form_validation.html\'>xxx3流程</a></li>");
    }
    document.writeln("                            </ul>");
    document.writeln("                        </li>");
}
    document.writeln("                    </ul>");
    document.writeln("                </div>");
    document.writeln("            </div>");
    document.writeln("        </div>");
    document.writeln("    </div>");
