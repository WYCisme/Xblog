<#macro page _url='' _data='' _params=''>
    <#local _url=request.contextPath+_url/>
  <#--当前页-->
  <#local _page='page='+_data.getCurrent()/>
<#--下一页-->
  <#local _nextPage='page='+(_data.getCurrent()+1)/>
<#--上一页-->
  <#local _perPage='page='+(_data.getCurrent()-1)/>
  <#--每页大小-->
  <#local _size='size='+_data.getSize()/>
<#--当前分页总页数-->
    <#if _data.getSize()==0>
        <#local _totalPgae=0/>
    <#elseif _data.getTotal()%_data.getSize()!=0>
        <#local _totalPgae=(_data.getTotal()/_data.getSize()+1)?floor/>
    <#else >
        <#local _totalPgae=(_data.getTotal()/_data.getSize())?floor/>
    </#if >
<#--分页的最后一页-->
  <#local _lastPage='page='+_totalPgae>
 <#--条件参数-->
    <#if _params??>
        <#local _params='&'+_params/>
    </#if >
  <#--构造分页栏HTML代码-->
  <#local _html><#t>
    <#if (_data.getCurrent()-1 > 0 ) >
        <a href="${_url}?page=1&${_size}${_params}" target="_self">首页</a><#t>
    <#else>
    <a href="#" target="_self">首页</a><#t>
    </#if>
    <#if (_data.getCurrent()-1 >= 1) >
<a href="${_url}?${_perPage}&${_size}${_params}" target="_self">上一页</a><#t>
    <#else>
<a href="#" target="_self">上一页</a><#t>
    </#if >
    <#if (_totalPgae > 0) >
        <#list 1.._totalPgae as v><#t>
            <a href="${_url}?page=${v}&${_size}${_params}" target="_self">${v}</a><#t>
        </#list >
    </#if>
    <#if _data.getCurrent()<_totalPgae>
<a href="${_url}?${_nextPage}&${_size}${_params}" target="_self">下一页</a><#t>
    <#else >
<a href="#" target="_self">下一页</a><#t>
    </#if >
<#if (_data.getCurrent() > 0 ) >
<a href="${_url}?${_lastPage}&${_size}${_params}">尾页</a><#t>
<#else>
    <a href="#">尾页</a><#t>
</#if>

  </#local >
  <!--将分页HTML代码放置到页面-->
<div class="pagelist ">
    ${_html}
</div>
</#macro>
