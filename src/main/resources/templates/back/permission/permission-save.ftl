<@override name="middle">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加权限</legend>
</fieldset>
<form class="layui-form" action="/back/permission/addPermission"  method="post">
   <div class="layui-form-item">
        <label class="layui-form-label">权限</label>
        <div class="layui-input-block">
            <input type="text" name="permission" lay-verify="required" placeholder="请输入权限" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
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
<script>
    layui.use(['jquery'], function(){
        $ = layui.jquery;
        selectMenu("menu_quanxian","menu_permission");
    });
</script>