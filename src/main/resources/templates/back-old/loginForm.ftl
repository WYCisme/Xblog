<!DOCTYPE html>
<!-- saved from url=(0045)https://www.layui.com/admin/pro/#/user/forget -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${(system.systemName)!''}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <#include "/back/common/head.ftl" />
    <link rel="stylesheet" href="${request.contextPath}/static-back/layui/css/login.css">
</head>

<body layadmin-themealias="default" class="layui-layout-body">
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login">
    <div class="layadmin-user-login-main" >
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>${(system.systemName)!''}</h2>
            <p>登录界面 </p>
        </div>
        <form class="layui-form" action="/back/logining" method="post" lay-filter="adminLogin">
            <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"
                           for="LAY-user-login-cellphone"></label>
                    <input type="text" name="username"  placeholder="请输入登录名" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"
                           for="LAY-user-login-cellphone"></label>
                    <input type="text" name="password"  placeholder="请输入密码" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
                                   for="LAY-user-login-vercode"></label>
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
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-forget-submit">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use('layer', function () {
        $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到

        var layer = layui.layer;
        var message = "${message!''}";
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
