<!doctype html>
<html  class="x-admin-sm">
<link rel="stylesheet" href="${request.contextPath}/static-back/xadmin/css/login.css">
<#include '../common/head.ftl' />
<body class="login-bg">
<div class="login layui-anim layui-anim-up">
    <div class="message">${(system.systemName)!''}-管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" action="/back/logining" >
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-xs7">
                    <input type="text" name="code" id="LAY-user-login-vercode" lay-verify="required"
                           placeholder="图形验证码" class="layui-input">
                </div>
                <div class="layui-col-xs5">
                    <div style="margin-left: 10px;">
                        <img src="${request.contextPath}/captcha/captcha"
                             class="layadmin-user-login-codeimg"
                             id="LAY-user-get-vercode" onclick="refresh()">
                    </div>
                </div>
            </div>
        </div>
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<!-- 底部结束 -->
<script type="text/javascript">
    layui.use('form','layer', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(login)', function(data){
            console.log(data)
            layer.msg(JSON.stringify(data.field),function(){
                location.href='index.html'
            });
            return false;
        });


        var layer = layui.layer;
        var message = "${message!''}";
        console.log(message)
        if (message != "") {
            layer.msg(message);
        }

    });

    //刷新验证码
    function refresh() {
        var xmlhttp;
        xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", "${request.contextPath}/captcha/captcha", true);
        xmlhttp.responseType = "blob";
        xmlhttp.onload = function () {
            if (this.status == 200) {
                var blob = this.response;
                $("#LAY-user-get-vercode").attr("src", window.URL.createObjectURL(blob));
            }
        }
        xmlhttp.send();
    }

</script>
</body>
</html>
