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
                            <form class="layui-form layui-col-space5" action="/back/schedule/list" method="get">
                                <div class="layui-inline layui-show-xs-block">
                                    <input type="text" name="beanName" value="${ beanName!''}" placeholder="请输入任务名称" autocomplete="off"
                                           class="layui-input">
                                </div>
                                <div class="layui-inline layui-show-xs-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div class="layui-card-header">
                            <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除
                            </button>
                            <button class="layui-btn" onclick="xadmin.open('添加','${request.contextPath}/back/schedule/to/save')"><i
                                    class="layui-icon"></i>添加
                            </button>
                        </div>
                        <div class="layui-card-body ">
                            <table class="layui-table layui-form">
                                <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox" name="" lay-skin="primary">
                                    </th>
                                    <th>ID</th>
                                    <th>任务名称</th>
                                    <th>参数</th>
                                    <th>cron表达式</th>
                                    <th>状态</th>
                                    <th>备注</th>
                                    <th>时间</th>
                                    <th>操作</th>
                                </thead>
                            <#if pages??>
                                <#list pages.getRecords() as item >
                            <tbody>
                            <tr>
                                <td>
                                    <input type="checkbox" name="" lay-skin="primary">
                                </td>
                                <td>${item.id}</td>
                                <td>${item.beanName}</td>
                                <td>${(item.params)!''}</td>
                                <td>${item.cronExpression}</td>
                                <td>${(item.status)!''}</td>
                                <td>${(item.remark)!''}</td>
                                <td>${(item.createTime?datetime)!''}</td>
                                <td class="td-manage">
                                    <a title="编辑" onclick="xadmin.open('编辑','${request.contextPath}/back/schedule/${item.id}/to/update')" href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                            </tbody>
                                </#list>
                            </#if>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <@pg.page _url='/back/schedule/list' _data=pages _params='' />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        layui.use(['laydate', 'form'], function () {
            var laydate = layui.laydate;
            var form = layui.form;

            //执行一个laydate实例
            laydate.render({
                elem: '#start' //指定元素
            });

            //执行一个laydate实例
            laydate.render({
                elem: '#end' //指定元素
            });
        });

        /*用户-删除*/
        function member_del(obj, id) {
            layer.confirm('确认要删除吗？', function (index) {
                console.log(index);
                //发异步删除数据
                $(obj).parents("tr").remove();
                layer.msg('已删除!', {icon: 1, time: 1000});
            });
        }


        function delAll(argument) {

            var data = tableCheck.getData();

            layer.confirm('确认要删除吗？' + data, function (index) {
                //捉到所有被选中的，发异步进行删除
                console.log(index);
                layer.msg('删除成功', {icon: 1});
                $(".layui-form-checked").not('.header').parents('tr').remove();
            });
        }
    </script>

    </html>