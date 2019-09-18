<!DOCTYPE html>
<html class="x-admin-sm">
<#include "../common/head.ftl" />
<#import "../common/page.ftl" as pg>
<body>
<div class="x-nav">
			  <span class="layui-breadcrumb">
				<a href="#">首页</a>
				<a href="#">定时任务管理</a>
				<a>
				  <cite>定时任务列表</cite></a>
			  </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                <!-- TODO -->
                </div>
                <div class="layui-card-body ">
                    <table class="layui-hide" id="schedule-table" lay-filter="schedule-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="schedule-table-bar">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'element', 'jquery'], function () {
        var laydate = layui.laydate //日期
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
        var $ = layui.jquery;
        var form = layui.form;

        //执行一个 table 实例
        table.render({
            elem: '#schedule-table'
            , url: '/back/schedule/' //数据接口
            , title: '权限表'
            , page: true //开启分页
            , limit: 10
            , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , totalRow: false //开启合计行
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'id', title: 'id', sort: true}
                , {field: 'beanName', title: '任务名称', edit: 'text'}
                , {field: 'params', title: '参数', edit: 'text'}
                , {field: 'status', title: '状态'}
                , {field: 'cronExpression', title: 'cron表达式'}
                , {field: 'status', title: '状态'}
                , {field: 'remark', title: '备注'}
                , {field: 'createDate', title: '创建时间'}
                , {field: 'updateDate', title: '更新时间'}
                , {fixed: 'right', align: 'center', toolbar: '#schedule-table-bar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(schedule-table)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data; //获取选中的数据
            switch (obj.event) {
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        var ids = [];
                        $.each(data, function (i, e) {
                            ids.push(e.id);
                        });
                        $.ajax({
                            url: '${request.contextPath}/back/schedule/' + ids.toString(),
                            data: {},
                            type: 'DELETE',
                            dataType: 'json',
                            success: function (dataObj) {
                                xadmin.msg_call(dataObj)
                            }
                        });
                    }
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(schedule-table)', function (obj) { //注：tool 是工具条事件名，schedule-table-bar 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    //向服务端发送删除指令
                    $.ajax({
                        url: '${request.contextPath}/back/schedule/' + data.id,
                        data: {},
                        type: 'DELETE',
                        dataType: 'json',
                        success: function (dataObj) {
                            xadmin.msg_call(dataObj)
                        }
                    });
                });
            }
        });

        //监听单元格编辑
        table.on('edit(schedule-table)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段
            //向服务端发送删除指令
            $.ajax({
                url: '${request.contextPath}/back/schedule/' + data.id,
                data: data,
                type: 'PUT',
                dataType: 'json',
                success: function (dataObj) {
                    xadmin.msg_call(dataObj)
                }
            });
        });

        //监听提交
        form.render(null, 'schedule-table');
        form.on('submit(schedule-save)', function (data) {
            var field = data.field;
            $.ajax({
                url: '${request.contextPath}/back/schedule',
                type: 'post',
                dataType: 'json',
                data: field,
                success: function (dataObj) {
                    xadmin.msg_call(dataObj)
                }
            });


        });

    });
</script>