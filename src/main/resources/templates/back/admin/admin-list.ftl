<#assign childrenTitle='管理员管理' />
<#assign childrenSubTitle='管理员列表' />
<@override name="middle">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                        <input type="text" name="username" value="${(admin.username)!''}" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="admin-sreach">
                                <i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">

                    <table class="layui-hide" id="admin-table"></table>


                </div>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>

<script type="text/html" id="admin-table-bar">
  <#--  <div class = "layui-btn-container" >
        <button class = "layui-btn layui-btn-sm" lay-event = "getCheckData" > 获取选中行数据 </button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button >
        <button class = "layui-btn layui-btn-sm" lay-event = "isAll" > 验证是否全选</button>
    </div >-->
</script>
<script type="text/html" id="switchTpl">
    <input type="checkbox" name="lock" value="{{d.id}}" title="锁定" lay-filter="lockDemo" {{ d.id == 10006 ? 'checked' : '' }}>
</script>
<script>layui.use('table',
    function() {
        var table = layui.table;


        table.render({
            elem: '#admin-table'
            ,url:'/back/admin/list'
            ,toolbar: '#admin-table-bar'
            ,cols: [[
                {type: 'checkbox' }
                ,{field:'id', title: 'ID', sort: true}
                ,{field:'username', title: '用户名'}
                ,{field:'nickname', title: '用户昵称', sort: true}
                ,{field:'status', title: '状态', templet: '#switchTpl'}
                ,{field:'createDate', title: '创建时间'}
                ,{field:'updateDate', title: '更新时间'}
            ]]
            ,page:true
        });

        //监听单元格编辑
        table.on('edit(admin-table)',
            function(obj) {
                var value = obj.value //得到修改后的值
                    ,
                    data = obj.data //得到所在行所有键值
                    ,
                    field = obj.field; //得到字段
                layer.msg('[ID: ' + data.id + '] ' + field + ' 字段更改为：' + value);
            });

        //头工具栏事件
        table.on('toolbar(admin-table)',
            function(obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'getCheckData':
                        var data = checkStatus.data;
                        layer.alert(JSON.stringify(data));
                        break;
                    case 'getCheckLength':
                        var data = checkStatus.data;
                        layer.msg('选中了：' + data.length + ' 个');
                        break;
                    case 'isAll':
                        layer.msg(checkStatus.isAll ? '全选': '未全选');
                        break;
                };
            });
    });</script>

