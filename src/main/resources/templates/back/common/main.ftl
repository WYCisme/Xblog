<!doctype html>
<html class="x-admin-sm">

<#include 'head.ftl' />
    <body class="index">
        <!-- 顶部开始 -->
        <#include 'top.ftl' />
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
            <!-- 左侧菜单开始 -->
            <#include 'left.ftl' />
            <!-- 左侧菜单结束 -->
            <!-- 右侧主体开始 -->
            <#include 'right.ftl' />
            <!-- 右侧主体结束 -->
        <!-- 中部结束 -->
    </body>
    <script>
        //JavaScript代码区域
        layui.use(['element','layer'], function () {
            var element = layui.element;
            var layer = layui.layer;

            var message = "${message!''}";
            if(message != ""){
                layer.msg(message);
            }

        });
    </script>



<#include 'footer.ftl' />
</html>