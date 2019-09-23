<template>
    <div class="article-up">
        <el-card shadow="hover" class="about-me">
            <h2 class="hometitle">最多收藏</h2>
            <el-avatar class="tjpic" shape="square" >
                <a style="bottom" href="">1234222222222222222233333333333333333222222222256</a>
            </el-avatar>
            <li v-for="i in 6 ">
                <el-avatar :size="70" shape="square"  style="float:left; margin-right: 5px;">
                </el-avatar>
                <div class="title">1111111111111111ffffffffffffffffff11111</div>
                <div class="title title2"> <i class="el-icon-time"></i> 2019-09-09</div>
            </li>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: 'ArticleUp'
    }
</script>

<style scpoed>
    .article-up li {
        float: left;
        width: 100%;
        list-style: none;
        height: 65px;
        margin-bottom: 13px;
    }
    .title {
        margin-left: 10px;
        width: 100%;
        white-space: normal;
        word-break: break-all;
        text-align: left;
        height: 40px;
    }
    .title2 {
        width: 100%;
        text-align: left;
        font-size: 12px;
        margin-top: 10px;
    }
    .el-icon-time {
        margin: 2px;
    }
    .hometitle {
        font-size: 18px;
        color: #282828;
        font-weight: 600;
        margin: 0;
        text-transform: uppercase;
        padding-bottom: 15px;
        margin-bottom: 25px;
        width: 75px;
        position: relative;
    }
    .hometitle:hover::after {
        content: "";
        background-color: #282828;
        left: 0;
        width: 80px;
        height: 2px;
        bottom: 0;
        position: absolute;
        -webkit-transition: 0.5s;
        -moz-transition: 0.5s;
        -ms-transition: 0.5s;
        -o-transition: 0.5s;
        transition: 0.5s;
    }
    .tjpic {
        width: 100%;
        height: 170px;
        margin-bottom: 20px;
        overflow: hidden;
        display: block;
        clear: both;
        position: relative;
    }
    .tjpic a {
        bottom: 0px;
        position: absolute;
        margin: auto;
        left: 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        width: 92%;
        text-decoration: none;
        color: #FFF;
        font-size: 16px;
    }
    .tjpic img {
        width: 100%;
        min-height: 170px;
        -moz-transition: all .5s ease;
        -webkit-transition: all .5s ease;
        -ms-transition: all .5s ease;
        -o-transition: all .5s ease;
        transition: all .5s ease;
    }
</style>