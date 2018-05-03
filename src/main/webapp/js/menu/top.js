document.writeln("    <div class=\'top_nav\'>");
document.writeln("        <div class=\'nav_menu\'>");
document.writeln("            <nav>");
document.writeln("                <div class=\'nav toggle\'>");
document.writeln("                    <a id=\'menu_toggle\'><i class=\'fa fa-bars\'></i></a>");
document.writeln("                </div>");
document.writeln("");
document.writeln("                <ul class=\'nav navbar-nav navbar-right\'>");
document.writeln("                    <li class=\'\'>");
document.writeln("                        <a href=\'javascript:;\' class=\'user-profile dropdown-toggle\' data-toggle=\'dropdown\' aria-expanded=\'false\'>您好,");
document.writeln(                             loginUserName);
document.writeln("                            <span class=\' fa fa-angle-down\'></span>");
document.writeln("                        </a>");
document.writeln("                        <ul class=\'dropdown-menu dropdown-usermenu pull-right\'>");
document.writeln("                            <li><a href=\'javascript:logOut();\'><i class=\'fa fa-sign-out pull-right\'></i> 登出</a></li>");
document.writeln("                        </ul>");
document.writeln("                    </li>");
document.writeln("                    ");
document.writeln("                </ul>");
document.writeln("            </nav>");
document.writeln("        </div>");
document.writeln("    </div>");

function logOut(){
    if(loginToken==""||loginToken==undefined){
        window.location="/login/login.html";
    }else{

        invoking("/login/logout",{},function () {
            sessionStorage.xxfLoginToken="";
            sessionStorage.xxfLoginUserID="";
            sessionStorage.xxfLoginUserName="";
            sessionStorage.xxfLoginStatus="";
            sessionStorage.xxfAuth="";
            window.location="/login/login.html";
        });
    }
}