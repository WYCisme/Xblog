
<#include "head.ftl"/>

<@block name="middle" ></@block>


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

<script>
    $(function  () {
        layui.use('form', function(){
            var form = layui.form;

            //监听提交
            form.on('submit(login)', function(data){
                layer.msg(JSON.stringify(data.field),function(){
                    location.href='index.html'
                });
                return false;
            });
        });
    })
</script>
<!-- 底部结束 -->

</html>


<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin" >

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
