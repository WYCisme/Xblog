<!doctype html>
<html>
<!--顶部 JS CSS -->
<#include "./top.ftl"/>
<body>
<header>
    <!--顶部菜单-->
    <#include "./menu.ftl"/>
</header>
<#--    <#include "./banner.ftl"/>-->
<div class="pagebg sh"></div>
<div class="container">
    <!--blogsbox begin 主体标签-->
    <@block name="middle" ></@block>
    <!--blogsbox end-->

</div>
<!--底部-->
<#include "./footer.ftl"/>
<a href="#" class="cd-top">Top</a>
</body>
</html>
