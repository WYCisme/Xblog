<#assign childrenTitle='权限分类' />
<#assign childrenSubTitle='权限分类列表' />
<@override name="middle">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="name" id="name" placeholder="分类名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="permission-type-save"><i
                                    class="layui-icon"></i>增加
                            </button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-hide" id="permission-type-table" lay-filter="permission-type-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>
<script type="text/html" id="permission-type-table-bar">
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
            elem: '#permission-type-table'
            , url: '/back/permission-type/list' //数据接口
            , title: '权限分类表'
            , page: true //开启分页
            , limit: 10
            , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , totalRow: false //开启合计行
            , cols: [[ //表头
                {type: 'checkbox'}
                , {field: 'id', title: 'id', sort: true}
                , {field: 'name', title: '角色名'}
                , {field: 'createDate', title: '创建时间'}
                , {field: 'updateDate', title: '更新时间'}
                , {fixed: 'right', align: 'center', toolbar: '#permission-type-table-bar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(permission-type-table)', function (obj) {
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
                            url: '${request.contextPath}/back/permission-type/' + ids.toString() + '/del',
                            data: {},
                            type: 'post',
                            dataType: 'json',
                            success: function (dataObj) {
                                xadmin.msg_flush(dataObj)
                            }
                        });
                    }
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(permission-type-table)', function (obj) { //注：tool 是工具条事件名，permission-type-table-bar 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    //向服务端发送删除指令
                    $.ajax({
                        url: '${request.contextPath}/back/permission-type/' + data.id + '/del',
                        data: {},
                        type: 'post',
                        dataType: 'json',
                        success: function (dataObj) {
                            xadmin.msg_flush(dataObj.msg)
                        }
                    });
                });
            }
        });

        //监听单元格编辑
        table.on('edit(permission-type-table)', function (obj) {
            var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field; //得到字段

            //向服务端发送删除指令
            $.ajax({
                url: '${request.contextPath}/back/permission-type/edit',
                data: {
                    id: data.id,
                    name: value
                },
                type: 'post',
                dataType: 'json',
                success: function (dataObj) {
                    xadmin.msg_flush(dataObj.msg)
                }
            });
        });

        //监听提交
        form.render(null, 'permission-type-table');
        form.on('submit(permission-type-save)', function (data) {
            var field = data.field;
            console.log(data.field)
            $.ajax({
                url: '${request.contextPath}/back/permission-type/save',
                type: 'post',
                dataType: 'json',
                data: field,
                success: function (dataObj) {
                    xadmin.msg_flush(dataObj.msg)
                }
            });


        });

    });
</script>