<div class="container">
    <div class="logo">
        <a href="${request.contextPath}/back/index">=========</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">admin</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
             <#--   <dd>
                    <a onclick="xadmin.open('个人信息','http://www.baidu.com')">个人信息</a></dd>
                <dd>
                    <a onclick="xadmin.open('切换帐号','http://www.baidu.com')">切换帐号</a></dd>-->
                <dd>
                    <a href="${request.contextPath}/back/logout">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <a href="${request.contextPath}/">前台首页</a></li>
    </ul>
</div>