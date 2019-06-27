<@override name="middle">
<#--<div class="pagebg timer"> </div>-->
<div class="container">
    <h1 class="t_nav"><span>时光轴</span> <#--<a href="/" class="n1">网站首页</a><a href="/" class="n2">时间轴</a> --></h1>
    <div class="timebox">
        <ul id="list" >
            <#if pages?? && pages.data??>
                <#list pages.data as article>
                     <li><span>${article.createDate?date}</span><a href="${request.contextPath}/home/detail?id=${article.id}" title="${(article.title)!''}">${(article.title)!''}</a></li>
                </#list>
            </#if>
        </ul>
        <ul id="list2">
        </ul>
    </div>
</div>
</@override>
<@extends name="/front/common/main.ftl"></@extends>