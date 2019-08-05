<!DOCTYPE html>
<html class="x-admin-sm">
<#include '../common/head.ftl'/>
<body>
<div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="#">首页</a>
                <a href="#">${childrenTitle}</a>
                <a>
                    <cite>${childrenSubTitle}</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<@block name="middle" ></@block>
</body>
</html>

