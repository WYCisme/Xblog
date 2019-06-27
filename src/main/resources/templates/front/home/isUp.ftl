<h2 class="hometitle">推荐文章</h2>
  <ul class="tjpic">
    <#if one??>
        <#if (one.images?size > 0)>
            <i><img src="${ (one.images?first.src)!''}"></i>
        <#else >
            <i><img src="${ (one.images?first.src)!''}"></i>
        </#if>
        <p><a href="${request.contextPath}/home/detail?id=${one.id}">${(one.title)!''}</a></p>
    </#if>
  </ul>
<ul class="sidenews">
<#if pages?? && pages.data??>
    <#list pages.data as article>
        <#if article_index != 0 >
         <li><i>
            <#if (article.images?size > 0)>
                <img src="${(article.images?first.src)!''}">
            <#else >
                <img src="${(article.images?first.src)!''}">
            </#if>
         </i>
             <p><a href="${request.contextPath}/home/detail?id=${article.id}">${(article.title)!''}</a></p>
             <span>${(article.createDate?date)!''}</span></li>
        </#if>
    </#list>
</#if>
</ul>