<#macro page _data='' _pageFun=''>

<#--当前页-->
    <#local _currPage= _data.current/>
<#--下一页-->
    <#local _nextPage= (_data.current+1)/>
<#--上一页-->
    <#local _perPage= (_data.current-1)/>
<#--每页大小-->
    <#local _pageSize = _data.size/>

<#--当前分页总页数-->
    <#if _data.size==0>
        <#local _totalPgae=0/>
    <#elseif _data.total%_data.size!=0>
        <#local _totalPgae=(_data.total/_data.size+1)?floor/>
    <#else >
        <#local _totalPgae=(_data.total/_data.size)?floor/>
    </#if>

<#--LIST下标参数-->
    <#if _totalPgae gt 10>
        <#if _data.current > 5 && _data.current+5 < _totalPgae >
            <#local _markList = (_data.current-5).._data.current + (_data.current.._data.current+5) />
        <#elseif _data.current > 5 && _data.current <= _totalPgae >
            <#local _markList = (_data.current-5).._data.current + (_data.current.._totalPgae) />
        <#else>
            <#local _markList = (1).._totalPgae />
        </#if>
    <#else>
        <#local _markList = 1.._totalPgae />
    </#if>


<#--构造分页栏HTML代码-->
    <#local _html>
        <@compress single_line=true><#if (_perPage > 0 ) ><a onclick="${_pageFun}(1,${_pageSize})" target="_self">&lt;&lt;</a>
        <#else>
        <a href="#" target="_self">&lt;&lt;</a></#if>
            <#if (_perPage >= 1) >


        <a class="prev" onclick="${_pageFun}(${_perPage},${_pageSize})" target="_self">&lt;</a><#else><a href="#"
                                                                                                         target="_self">&lt;</a>

            </#if ><#if (_totalPgae > 0) >
            <#if _totalPgae gt 4 >
                <#list _markList as v>
                    <#if v == _data.current >
                            <span class="current" onclick="${_pageFun}(${v},${_pageSize})" target="_self">${v}</span>
                    <#else>
                            <a class="num" onclick="${_pageFun}(${v},${_pageSize})" target="_self">${v}</a>
                    </#if>

                </#list >
            <#else>
                <#list 1.._totalPgae as v>
                    <#if v == _data.current ><span class="current" onclick="${_pageFun}(${v} , ${_pageSize} )"
                                                   target="_self">${v}</span>
                    <#else><a class="num" onclick="${_pageFun}(${v},${_pageSize})" target="_self">${v}</a></#if>
                </#list >
            </#if>
        </#if>

            <#if _data.current < _totalPgae >
            <a class="next" onclick="${_pageFun}(${_nextPage},${_pageSize})" target="_self">&gt;</a><#else >
                <a class="next" href="#" target="_self">&gt;</a></#if>
            <#if (_data.current < _totalPgae ) >
                <a onclick="${_pageFun}(${_totalPgae}, ${_pageSize})">&gt;&gt;</a>
            <#else><a href="#">&gt;&gt;</a></#if></@compress>
    </#local >
    <!--将分页HTML代码放置到页面-->
     <div class="page">
         <div>
             ${_html}
         </div>
     </div>


</#macro>