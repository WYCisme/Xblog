<@override name="middle">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改用户</legend>
</fieldset>
<form class="layui-form" action="/back/admin/editAdmin"  method="post">
    <input type="hidden" value="${(admin.id)!''}" name="id" />
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" placeholder="请输入用户名" value="${(admin.username)!''}" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname" lay-verify="required" placeholder="请输入昵称" value="${(admin.nickname)!''}"  autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</@override>
<@extends name="/back/common/main.ftl"></@extends>
