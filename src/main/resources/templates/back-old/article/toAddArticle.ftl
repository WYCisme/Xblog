<@override name="middle">
<form class="layui-form" action="/back/article/addArticle" method="post" id="addArticleForm">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>添加文章</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">文章标题</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="required" placeholder="文章标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">副标题</label>
        <div class="layui-input-block">
            <input type="text" name="subtitle" lay-verify="required" placeholder="副标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发布状态</label>
        <div class="layui-input-block">
            <select name="status" lay-verify="required" >
                <option value=""></option>
                  <#list em  as e>
                        <option value="${e.code}">${e.value}</option>
                  </#list>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text" style="margin-top:150px">
        <div class="layui-input-block"  id="context">
            <textarea name="context">
            </textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </div>
    </div>
</form>


</@override>
<@extends name="/back/common/main.ftl"></@extends>
<#include '../common/mdHead.ftl' />
<script type="text/javascript">
    layui.use(['form', 'markdown'], function () {
        var form = layui.form;
        var markdown = layui.markdown;
        //监听提交
        form.on('submit(addArticleForm)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
        form.render();
        var ma = markdown.hello({id: 'context'});


    });

</script>