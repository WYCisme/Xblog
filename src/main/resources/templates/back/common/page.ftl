<#macro page _url='' _data='' _params=''>

    <#local _url=request.contextPath+_url/>
<#--当前页-->
    <#local _pageStr='page='+_data.getCurrent()/>
<#--下一页-->
    <#local _nextPageStr='page='+(_data.getCurrent()+1)/>
<#--上一页-->
    <#local _perPageStr='page='+(_data.getCurrent()-1)/>
<#--每页大小-->
    <#local _sizeStr='size='+_data.getSize()/>

<#--当前分页总页数-->
    <#if _data.getSize()==0>
        <#local _totalPgae=0/>
    <#elseif _data.getTotal()%_data.getSize()!=0>
        <#local _totalPgae=(_data.getTotal()/_data.getSize()+1)?floor/>
    <#else >
        <#local _totalPgae=(_data.getTotal()/_data.getSize())?floor/>
    </#if >


<#--分页的最后一页-->
    <#local _lastPageStr='page='+_totalPgae>


<#--条件参数-->
    <#if _params??>
        <#local _params='&'+_params/>
    </#if >
<#--LIST下标参数-->
    <#if _totalPgae gt 10>
        <#if _data.getCurrent() > 5 && _data.getCurrent()+5 < _totalPgae >
            <#local _markList = (_data.getCurrent()-5).._data.getCurrent() + (_data.getCurrent().._data.getCurrent()+5) />
        <#elseif _data.getCurrent() > 5 && _data.getCurrent() <= _totalPgae >
            <#local _markList = (_data.getCurrent()-5).._data.getCurrent() + (_data.getCurrent().._totalPgae) />
        <#else>
            <#local _markList = (1).._totalPgae />
        </#if>
    <#else>
        <#local _markList = 1.._totalPgae />
    </#if>


<#--构造分页栏HTML代码-->
    <#local _html>
        <@compress single_line=true><#if (_data.getCurrent()-1 > 0 ) ><a href="${_url}?page=1&${_sizeStr}${_params}" target="_self">&lt;&lt;</a>



        <#else><a href="#" target="_self">&lt;&lt;</a></#if><#if (_data.getCurrent()-1 >= 1) >


        <a class="prev" href="${_url}?${_perPageStr}&${_sizeStr}${_params}" target="_self">&lt;</a><#else><a href="#" target="_self">&lt;</a>

        </#if ><#if (_totalPgae > 0) >
                <#if _totalPgae gt 4 >
                    <#list _markList as v>

                        <#if v == _data.getCurrent() >
                            <span class="current" href="${_url}?page=${v}&${_sizeStr}${_params}" target="_self">${v}</span>
                        <#else>
                            <a class="num" href="${_url}?page=${v}&${_sizeStr}${_params}" target="_self">${v}</a>
                        </#if>

                    </#list >
                <#else>
                    <#list 1.._totalPgae as v>
                        <#if v == _data.getCurrent() ><span class="current" href="${_url}?page=${v}&${_sizeStr}${_params}" target="_self">${v}</span><#else><a class="num" href="${_url}?page=${v}&${_sizeStr}${_params}" target="_self">${v}</a></#if>
                    </#list >
                </#if>
            </#if>

            <#if _data.getCurrent()<_totalPgae><a class="next" href="${_url}?${_nextPageStr}&${_sizeStr}${_params}" target="_self">&gt;</a><#else ><a class="next" href="#" target="_self">&gt;</a></#if><#if (_data.getCurrent() > 0 ) ><a href="${_url}?${_lastPageStr}&${_sizeStr}${_params}">&gt;&gt;</a><#else><a href="#">&gt;&gt;</a></#if></@compress>
    </#local >
<!--将分页HTML代码放置到页面-->
 <div class="page">
     <div>
         ${_html}
     </div>
 </div>


</#macro>