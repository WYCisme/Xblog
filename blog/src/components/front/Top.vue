<template>
    <div class="top">
        <div class="top-div">
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                <el-menu-item index="1">主页</el-menu-item>
                 <el-menu-item style="float:right">   <el-button type="primary" @click="onSubmit">搜索</el-button></el-menu-item>
                 <el-menu-item style="float:right"> <el-input ></el-input> </el-menu-item>
            </el-menu>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            onSubmit(){

            }
        }
    }
</script>

<style>
.top{
    width: 100%;
    background: #fff;
    position:fixed;
    z-index: 1000;
    margin: 0px;
    padding: 0px;
    top:0px;
}
.top-title{
    float: left;
}
.top-div{
    width: 60%;
    margin:auto;
}
</style>