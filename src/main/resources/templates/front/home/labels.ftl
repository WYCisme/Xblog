<h2 class="hometitle">标签云</h2>
<ul>
    <#if labels?? >
        <#list labels as l >
            <a href="${request.contextPath}/home/?page=1&labels=${l.name}">${(l.name)!''}</a>
        </#list>
    </#if>
</ul>