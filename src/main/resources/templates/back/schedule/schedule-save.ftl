<!DOCTYPE html>
<html class="x-admin-sm">
    <#include "../common/head.ftl" />
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" action="/back/schedule/save">
            <div class="layui-form-item">
                <label for="beanName" class="layui-form-label">
                    <span class="x-red">*</span>任务名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="beanName" name="beanName" required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="params" class="layui-form-label">
                    <span class="x-red">*</span>参数(JSON格式)
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="params" name="params" required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="cronExpression" class="layui-form-label">
                    <span class="x-red">*</span>cron表达式
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="cronExpression" name="cronExpression" required="" lay-verify="cronExpression"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_remark" class="layui-form-label">
                    <span class="x-red"></span>备注
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="L_remark" name="remark" lay-verify="remark"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                </label>
                <button class="layui-btn" lay-filter="save" lay-submit="">
                    增加
                </button>
            </div>
        </form>
    </div>
</div>
<script>layui.use(['form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                    layer = layui.layer;
            //监听提交
            form.on('submit(save)',
                    function (data) {
                        $.ajax({
                            url: ${request.contextPath} + "/back/schedule/save",
                            type:"POST",
                            contentType: 'application/json',
                            dataType:"json",
                            data: JSON.stringify(data.field),
                            success:function (data) {
                                if(data.code > 1){
                                    layer.alert("增加成功", {
                                                icon: 6
                                    },
                                    function() {
                                        //关闭当前frame
                                        xadmin.close();

                                        // 可以对父窗口进行刷新
                                        xadmin.father_reload();
                                    });
                                }else{
                                    layer.msg(data.msg)
                                }
                            }
                        });

                        return false;
                    });

        });

</script>

</body>

</html>
