<@override name="middle" >
 <div class="layui-card">
     <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="listForm">
         <div class="layui-form-item">
             <div class="layui-inline">
                 <label class="layui-form-label">文章标题</label>
                 <div class="layui-input-block">
                     <input type="text" name="title" placeholder="文章标题" autocomplete="off" class="layui-input">
                 </div>
             </div>
             <div class="layui-inline">
                 <label class="layui-form-label">文章副标题</label>
                 <div class="layui-input-block">
                     <input type="text" name="subtitle" placeholder="文章副标题" autocomplete="off" class="layui-input">
                 </div>
             </div>

             <div class="layui-inline">
                 <button class="layui-btn layuiadmin-btn-useradmin" lay-submit="" lay-filter="listSearch">
                     <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                 </button>
             </div>
         </div>
     </div>
     <div class="layui-card-body">
        <table class="layui-hide" id="list" lay-filter="test"></table>
     </div>
 </div>
<script type="text/html" id="listBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</@override>
<@extends name="/back/common/main.ftl" ></@extends>
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
            elem: '#list'
            ,height: 700
            ,url: '/back/article/list' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,limit: 20
            ,cellMinWidth: 80
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: false //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'id', width:'10%', sort: true, fixed: 'left'}
                ,{field: 'title', title: '文章标题', width:'10%'}
                ,{field: 'subtitle', title: '文章副标题', width: '10%', sort: true, }
                ,{field: 'status', title: '状态', width:'10%', sort: true}
                ,{field: 'createDate', title: '创建时间', width: '10%', sort: true,}
                ,{field: 'updateTime', title: '更新时间', width:'10%'}
               ,{fixed: 'right',  align:'center', toolbar: '#listBar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    window.location.href='${request.contextPath}/back/article/toAddArticle';
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        window.location.href='${request.contextPath}/back/article/'+checkStatus.data[0].id+'/toEditArticle';
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
                            url:'${request.contextPath}/back/article/'+ ids.toString()+'/removeArticle',
                            data:{
                            },
                            type:'post',
                            dataType:'json',
                            success:function(dataObj){
                                layer.msg(dataObj.msg);
                                if(dataObj.code> 0){
                                    table.reload('list');
                                }
                            }
                        });
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url:'${request.contextPath}/back/article/'+ data.id+'/removeArticle',
                        data:{
                        },
                        type:'post',
                        dataType:'json',
                        success:function(data){
                            layer.msg(data.msg);
                            if(data.code> 0){
                                table.reload('list');
                            }
                        }
                    });
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
                window.location.href='${request.contextPath}/back/article/'+data.id+'/toEditArticle';
            }
        });
        //监听搜索
        form.render(null, 'listForm');
        form.on('submit(listSearch)', function(data){
            var field = data.field;
            //执行重载
            table.reload('list', {
                where: field
            });
        });



    });
</script>