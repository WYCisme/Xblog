<#assign childrenTitle='权限管理' />
<#assign childrenSubTitle='权限管理列表' />
<@override name="middle">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="permission" placeholder="权限名" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="description" placeholder="备注" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <select name="permissionTypeId">
                                <#list types as item >
                                     <option value="${(item.id)}">${(item.name)!''}</option>
                                </#list>
                            </select>

                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="permission-save"><i
                                    class="layui-icon"></i>增加
                            </button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-hide" id="permission-table" lay-filter="permission-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>
<script type="text/html" id="permission-table-bar">
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
            elem: '#permission-table'
            , url: '/back/permission/list' //数据接口
            , title: '权限表'
            , page: true //开启分页
            , limit: 10
            , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , totalRow: false //开启合计行
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'id', title: 'id', sort: true}
                , {field: 'permission', title: '权限名',edit: 'text'}
                , {field: 'description', title: '权限备注',edit: 'text'}
                , {field: 'permissionTypeName', title: '权限分类'}
                , {fixed: 'right', align: 'center', toolbar: '#permission-table-bar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(permission-table)', function (obj) {
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
                            url: '${request.contextPath}/back/permission/' + ids.toString() + '/del',
                            data: {},
                            type: 'post',
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
        table.on('tool(permission-table)', function (obj) { //注：tool 是工具条事件名，permission-table-bar 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    //向服务端发送删除指令
                    $.ajax({
                        url: '${request.contextPath}/back/permission/' + data.id + '/del',
                        data: {},
                        type: 'post',
                        dataType: 'json',
                        success: function (dataObj) {
                            xadmin.msg_call(dataObj)
                        }
                    });
                });
            }
        });

        //监听单元格编辑
        table.on('edit(permission-table)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段
            //向服务端发送删除指令
            $.ajax({
                url: '${request.contextPath}/back/permission/edit',
                data: data,
                type: 'post',
                dataType: 'json',
                success: function (dataObj) {
                    xadmin.msg_call(dataObj)
                }
            });
        });

        //监听提交
        form.render(null, 'permission-table');
        form.on('submit(permission-save)', function (data) {
            var field = data.field;
            $.ajax({
                url: '${request.contextPath}/back/permission/save',
                type: 'post',
                dataType: 'json',
                data: field,
                success: function (dataObj) {
                    xadmin.msg_call(dataObj.msg)
                }
            });


        });

    });
</script>