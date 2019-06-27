<@override name="middle">
<#--markdown-->
<#include "../common/mdHead.ftl">
<article>
    <h1 class="t_nav"><span>您现在的位置是：首页 > ${(article.title)!''}</span>
    </h1>
    <div class="infosbox">
        <div class="newsview">
            <h3 class="news_title">${(article.title)!''}</h3>
            <div class="bloginfo">
                <ul>
                    <li class="author"><a href="">${article.adminName}</a></li>
                    <li class="lmname"><a href="">${article.labels}</a></li>
                    <li class="timer">${article.createDate?date}</li>
                    <li class="view"><span>${article.viewCount}</span></li>
                    <li class="like" id="upCount" onclick="upCount()">${article.upCount}</li>
                </ul>
            </div>
            <#-- 内容主体 -->
            <div class="news_con" id="context">
                <textarea style="display:none;" name="test-editormd-markdown-doc">   ${(article.content?html)!''}</textarea>
            </div>
        </div>
       <#-- <div class="share">
            <p class="diggit"><a href="JavaScript:makeRequest('/e/public/digg/?classid=3&amp;id=19&amp;dotop=1&amp;doajax=1&amp;ajaxarea=diggnum','EchoReturnedText','GET','');"> 很赞哦！ </a>(<b id="diggnum"><script type="text/javascript" src="/e/public/ViewClick/?classid=2&id=20&down=5"></script>13</b>)</p>
            <p class="dasbox"><a href="javascript:void(0)" onClick="dashangToggle()" class="dashang" title="打赏，支持一下">打赏本站</a></p>
            <div class="hide_box"></div>
            <div class="shang_box"> <a class="shang_close" href="javascript:void(0)" onclick="dashangToggle()" title="关闭">关闭</a>
                <div class="shang_tit">
                    <p>感谢您的支持，我会继续努力的!</p>
                </div>
                <div class="shang_payimg"> <img src="images/alipayimg.jpg" alt="扫码支持" title="扫一扫"> </div>
                <div class="pay_explain">扫码打赏，你说多少就多少</div>
                <div class="shang_payselect">
                    <div class="pay_item checked" data-id="alipay"> <span class="radiobox"></span> <span class="pay_logo"><img src="images/alipay.jpg" alt="支付宝"></span> </div>
                    <div class="pay_item" data-id="weipay"> <span class="radiobox"></span> <span class="pay_logo"><img src="images/wechat.jpg" alt="微信"></span> </div>
                </div>
                <script type="text/javascript">
                    $(function(){
                        $(".pay_item").click(function(){
                            $(this).addClass('checked').siblings('.pay_item').removeClass('checked');
                            var dataid=$(this).attr('data-id');
                            $(".shang_payimg img").attr("src","images/"+dataid+"img.jpg");
                            $("#shang_pay_txt").text(dataid=="alipay"?"支付宝":"微信");
                        });
                    });
                    function dashangToggle(){
                        $(".hide_box").fadeToggle();
                        $(".shang_box").fadeToggle();
                    }
                </script>
            </div>
        </div>-->
       <#-- <div class="nextinfo">
            <p>上一篇：<a href="/news/life/2018-03-13/804.html">作为一个设计师,如果遭到质疑你是否能恪守自己的原则?</a></p>
            <p>下一篇：<a href="/news/life/">返回列表</a></p>
        </div>-->
       <#-- <div class="otherlink">
            <h2>相关文章</h2>
            <ul>
                <li><a href="/download/div/2018-04-22/815.html" title="html5个人博客模板《黑色格调》">html5个人博客模板《黑色格调》</a></li>
                <li><a href="/download/div/2018-04-18/814.html" title="html5个人博客模板主题《清雅》">html5个人博客模板主题《清雅》</a></li>
                <li><a href="/download/div/2018-03-18/807.html" title="html5个人博客模板主题《绅士》">html5个人博客模板主题《绅士》</a></li>
                <li><a href="/download/div/2018-02-22/798.html" title="html5时尚个人博客模板-技术门户型">html5时尚个人博客模板-技术门户型</a></li>
                <li><a href="/download/div/2017-09-08/789.html" title="html5个人博客模板主题《心蓝时间轴》">html5个人博客模板主题《心蓝时间轴》</a></li>
                <li><a href="/download/div/2017-07-16/785.html" title="古典个人博客模板《江南墨卷》">古典个人博客模板《江南墨卷》</a></li>
                <li><a href="/download/div/2017-07-13/783.html" title="古典风格-个人博客模板">古典风格-个人博客模板</a></li>
                <li><a href="/download/div/2015-06-28/748.html" title="个人博客《草根寻梦》—手机版模板">个人博客《草根寻梦》—手机版模板</a></li>
                <li><a href="/download/div/2015-04-10/746.html" title="【活动作品】柠檬绿兔小白个人博客模板">【活动作品】柠檬绿兔小白个人博客模板</a></li>
                <li><a href="/jstt/bj/2015-01-09/740.html" title="【匆匆那些年】总结个人博客经历的这四年…">【匆匆那些年】总结个人博客经历的这四年…</a></li>
            </ul>
        </div>-->
       <#-- <div class="news_pl">
            <h2>文章评论</h2>
            <ul>
                <div class="gbko"> </div>
            </ul>
        </div>-->
        <div style="line-height:20px;height: 20px;"></div>
    </div>
    <!--右边-->
    <div class="sidebar">
    <#include "../common/right.ftl"/>
    </div>
</article>
</@override>
<@extends name="/front/common/main.ftl"></@extends>
<script type="text/javascript">
    testEditormdView2 = editormd.markdownToHTML("context", {
        htmlDecode      : "style,script,iframe",  // you can filter tags decode
        emoji           : true,
        taskList        : true,
        tex             : true,  // 默认不解析
        flowChart       : true,  // 默认不解析
        sequenceDiagram : true,  // 默认不解析
    });

    function upCount(){
        $.ajax({
            url:'${request.contextPath}/home/upCount',
            data:{
                "id":'${article.id}'
            },
            dataType:"json",
            type:"post",
            success:function(r){
                if(r.code>0){
                    $("#upCount").text(Number($("#upCount").text()) +1)
                }
            }
        })
    }
</script>