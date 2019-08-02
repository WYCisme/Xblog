<#import "../common/homePage.ftl" as pg>
<@override name="middle">
<#-- 内容 -->
<div class="blogsbox" id="blogsbox">
    <#if pages?? && pages.data?? && pages.data.getRecords()?? >
        <#list  pages.data.getRecords() as article >
            <div class="blogs" data-scroll-reveal="enter bottom over 1s" >
                <h3 class="blogtitle"><a href="${request.contextPath}/home/detail?id=${article.id}" target="_blank">${article.title}</a></h3>
                    <#if (article.images?size > 0)>
                         <span <#if (article.images?size == 1) && (article.images[0].isBig)> class="bigpic" <#elseif  (article.images?size == 1)> class="blogpic"  </#if>  <#if (article.images?size gt 1)> class="bplist"  </#if>   >
                             <a href="${request.contextPath}/home/detail?id=${article.id}" title="">
                            <#list article.images as i >
                                <img src="${(i.src)!""}" alt="">
                            </#list>
                             </a></span>
                    </#if>
                <p class="blogtext">${(article.intro)!''} </p>
                <div class="bloginfo">
                    <ul>
                        <li class="author"><a href="">${article.adminName}</a></li>
                        <li class="lmname"><a href="">${article.labels}</a></li>
                        <li class="timer">${article.createDate?date}</li>
                        <li class="view"><span>${article.viewCount}</span></li>
                        <li class="like">${article.upCount}</li>
                    </ul>
                </div>
            </div>
        </#list>
    </#if>
    <@pg.page _url='/home/' _data=pages.data _params= ('title=' + title +'&labels='+ labels)!'' /><#t>
</div>

<!--右边-->
 <div class="sidebar">
    <#include "../common/right.ftl"/>
 </div>

</@override>
<@extends name="/front/common/main.ftl"></@extends>
<script>

</script>