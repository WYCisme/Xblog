<template>
    <div class="cloud">
        <el-card shadow="hover" class="about-me2">
            <h2 class="hometitle">标签云</h2>
            <ul>
                <a href="" v-for="(i,index) in list" :key="index">{{i.name}}</a>
            </ul>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: 'LabelCloud',
        methods: {
            getData() {
                this.$get("home/article/labels").then(res => {
                    this.$resultCheck(res.data, true, true).then(res => {
                        console.log("res",res)
                        this.list = res.data
                    }).catch(res => {})
                })
            }
        },
        created() {
            //获取数据
            this.getData()
        },
        data() {
            return {
                list: []
            };
        },
    }
</script>

<style scoped>
    .about-me2 {
        text-align: center;
        margin: 0px 2px 5px;
    }
    .hometitle {
        font-size: 18px;
        color: #282828;
        font-weight: 600;
        margin: 0;
        text-transform: uppercase;
        padding-bottom: 15px;
        margin-bottom: 25px;
        width: 65px;
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
    .cloud {
        clear: both;
        overflow: hidden;
        margin-bottom: 20px
    }
    .cloud ul {
        overflow: hidden;
        font-size: 14px;
        padding-inline-start: 10px;
    }
    .cloud ul a {
        line-height: 24px;
        height: 24px;
        display: block;
        background: #999;
        float: left;
        padding: 3px 11px;
        text-decoration: none;
        margin: 10px 10px 0 0;
        border-radius: 8px;
        -moz-transition: all .5s ease;
        -webkit-transition: all .5s ease;
        -ms-transition: all .5s ease;
        -o-transition: all .5s ease;
        transition: all .5s ease;
        transition: all 0.5s;
        color: #FFF
    }
    .cloud ul a:nth-child(8n-7) {
        background: #8A9B0F
    }
    .cloud ul a:nth-child(8n-6) {
        background: #EB6841
    }
    .cloud ul a:nth-child(8n-5) {
        background: #3FB8AF
    }
    .cloud ul a:nth-child(8n-4) {
        background: #FE4365
    }
    .cloud ul a:nth-child(8n-3) {
        background: #FC9D9A
    }
    .cloud ul a:nth-child(8n-2) {
        background: #EDC951
    }
    .cloud ul a:nth-child(8n-1) {
        background: #C8C8A9
    }
    .cloud ul a:nth-child(8n) {
        background: #83AF9B
    }
    .cloud ul a:first-child {
        background: #036564
    }
    .cloud ul a:last-child {
        background: #3299BB
    }
    .cloud ul a:hover {
        border-radius: 0;
        text-shadow: #000 1px 1px 1px
    }
</style>