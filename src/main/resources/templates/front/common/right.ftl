<div class="about" style="background: url('${ (currUser.backImage)!''}')">
    <p class="avatar"> <img src="${(currUser.image)!''}" alt=""> </p>
    <p class="abname"> ${(currUser.username)!''} | ${(currUser.name)!''}</p>
    <p class="abposition"> ${(currUser.job)!''}</p>
    <p class="abtext">  ${(currUser.description)!''} </p>
</div>

<div class="tuijian" id="isUp">

</div>
<div class="tuijian" id="isView">

</div>

<div class="cloud" id="cloud_labels">

</div>
<#--<div class="links">
    <h2 class="hometitle">友情链接</h2>
    <ul>
        <li><a href="/" target="_blank">侠女博客</a></li>
    </ul>
</div>-->

<#--

<div class="guanzhu" id="follow-us">
    <h2 class="hometitle">个人联系方式</h2>
    <ul>
        <li class="qq"><a href="/" target="_blank"><span>QQ号</span>${(currUser.qq)!''}</a></li>
        <li class="email"><a href="/" target="_blank"><span>邮箱帐号</span>${(currUser.email)!''}</a></li>
        <li class="wxgzh"><a href="/" target="_blank"><span>微信号</span>${(currUser.wechat)!''}</a></li>
    </ul>
</div>
-->

<script>
    $(function(){
       $.ajax({
           url:'${request.contextPath}' + "/home/labels",
           type:'get',
           dataType:'html',
           success:function (data) {
               $("#cloud_labels").html(data);
           }
       })

        $.ajax({
            url:'${request.contextPath}' + "/home/isView",
            type:'get',
            dataType:'html',
            success:function (data) {
                $("#isView").html(data);
            }
        })

        $.ajax({
            url:'${request.contextPath}' + "/home/isUp",
            type:'get',
            dataType:'html',
            success:function (data) {
                $("#isUp").html(data);
            }
        })

    });
</script>