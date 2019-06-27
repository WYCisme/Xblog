<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> ${(system.systemName)!''}</title>
    <#include "head.ftl"/>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin" >
    <#-- 头部区域 -->
    <#include "top.ftl"/>
        <#-- 左边区域 -->
    <#include "left.ftl"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;"> <@block name="middle" ></@block></div>
    </div>
    <#-- 底部区域 -->
    <#include "footer.ftl"/>
</div>
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
</body>
</html>
