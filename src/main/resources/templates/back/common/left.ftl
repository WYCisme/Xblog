<div class="layui-side layui-bg-black" style="position: static !important;height: 4090px;">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">所有商品</a>
                <dl class="layui-nav-child">
                    <dd><a href="${request.contextPath}/back/admin/index">用户管理</a></dd>
                    <dd><a href="${request.contextPath}/back/article/index">文章管理</a></dd>
                    <dd><a href="javascript:;">列表三</a></dd>
                    <dd><a href="">超链接</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">列表一</a></dd>
                    <dd><a href="javascript:;">列表二</a></dd>
                    <dd><a href="">超链接</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">云市场</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">列表一</a></dd>
                    <dd><a href="javascript:;">列表二</a></dd>
                    <dd><a href="">超链接</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a >发布商品</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">列表一</a></dd>
                    <dd><a href="javascript:;">列表二</a></dd>
                    <dd><a href="">超链接</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use(['element', 'layer'], function () {
        var $ = layui.jquery;
        $(".layui-nav-item").click(function () {
            $(this).addClass("layui-nav-itemed").siblings("li").removeClass("layui-nav-itemed");
        });

    });
</script>

