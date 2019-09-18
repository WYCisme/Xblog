<#assign childrenTitle='角色管理' />
<#assign childrenSubTitle='角色管理列表' />
<@override name="middle">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="role" value="${(role.role)!''}" placeholder="请输入角色名" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="role-sreach">
                                <i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-hide" id="role-table" lay-filter="role-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>
<script type="text/html" id="role-table-bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table','element','jquery'], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
        var $ = layui.jquery;
        var form = layui.form;

        //执行一个 table 实例
        table.render({
            elem: '#role-table'
            ,url: '/back/role/' //数据接口
            ,title: '角色表'
            ,page: true //开启分页
            ,limit: 10
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: false //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox'}
                ,{field: 'id', title: 'id', sort: true}
                ,{field: 'role', title: '角色名' }
                ,{field: 'description', title: '角色备注' }
               ,{fixed: 'right',  align:'center', toolbar: '#role-table-bar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(role-table)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    xadmin.open('添加','${request.contextPath}/back/home/role/save');
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        xadmin.open('编辑','${request.contextPath}/back/role/'+checkStatus.data[0].id+'/edit');
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        var ids = [];
                        $.each(data,function(i,e){
                            ids.push(e.id);
                        });
                        $.ajax({
                            url:'${request.contextPath}/back/role/'+ ids.toString()+'/del',
                            data:{
                            },
                            type:'post',
                            dataType:'json',
                            success:function(dataObj){
                                xadmin.msg(dataObj)
                            }
                        });
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(role-table)', function(obj){ //注：tool 是工具条事件名，role-table-bar 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url:'${request.contextPath}/back/role/'+ data.id+'/del',
                        data:{
                        },
                        type:'post',
                        dataType:'json',
                        success:function(data){
                            xadmin.msg(data)
                        }
                    });
                });
            } else if(layEvent === 'edit'){
                xadmin.open('编辑操作','${request.contextPath}/back/role/'+data.id+'/edit');
            }
        });
        //监听搜索
        form.render(null, 'form-role-list');
        form.on('submit(role-search)', function(data){
            var field = data.field;
            //执行重载
            table.reload('role-table', {
                where: field
            });
        });

    });
</script>