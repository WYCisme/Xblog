<div class="menu">
    <nav class="nav" id="topnav">
        <h1 class="logo"><a href="/">${(currUser.name)!''}博客</a></h1>
        <li><a href="${request.contextPath}/">首页</a> </li>
        <li><a href="${request.contextPath}/home/time"> 时间轴</a> </li>
       <#-- <li><a href="${request.contextPath}/home/aboutMe">关于我</a> </li>

        <li><a href="list.html">学无止境</a>
            <ul class="sub-nav">
                <li><a href="list.html">心得笔记</a></li>
                <li><a href="list.html">CSS3|Html5</a></li>
                <li><a href="list.html">推荐工具</a></li>
                <li><a href="list.html">JS实例索引</a></li>
            </ul>
        </li>
        <li><a href="life.html">慢生活</a>
            <ul class="sub-nav">
                <li><a href="life.html">日记</a></li>
                <li><a href="life.html">欣赏</a></li>
                <li><a href="life.html">程序人生</a></li>
                <li><a href="life.html">经典语录</a></li>
            </ul>
        </li>-->

        <div id="search_bar" class="search_bar <#if title?? >search_open</#if>"  >
           <form  id="searchform" action="${request.contextPath}/home/" method="post" name="searchform">
                <input class="input<#if title?? > search_open </#if>" style="width: 10px;"  placeholder="" type="text" name="title" value="${title!''}" id="title">
                <input type="hidden" name="labels" id="labels" value="${labels!''}">
                <input type="hidden" name="Submit" value="搜索" />
                <span class="search_ico"></span>
            </form>
        </div>
        <!--search end-->
    </nav>
</div>


<!--mnav begin-->
<div id="mnav">
  <h2 ><a href="/" class="mlogo"> ${(currUser.name)!''}博客</a><span class="navicon"></span></h2>
  <dl class="list_dl">
      <dt class="list_dt"> <a href="${request.contextPath}/">首页</a> </dt>
      <dt class="list_dt"> <a href="${request.contextPath}/home/time"> 时间轴</a> </dt>
  </dl>
</div>
<!--mnav end-->

<script>
    $(document).ready(function(){
        /*search*/
        $('.search_ico').click(function () {
            $('.search_bar').toggleClass('search_open');
            $('#title').toggleClass('search_open');

            if ($('.search_bar').hasClass("search_open") == false) {
                $("#searchform").submit();
            }
        });
    })
</script>