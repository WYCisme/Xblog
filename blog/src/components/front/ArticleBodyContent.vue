<template>
    <div>
        <el-col :span="9" :offset="5">
            <el-row :gutter="0">
                <el-col :span="24">
                    <el-card shadow="hover" style="margin:5px;" v-for="(i,index) in 10" :key="index"  >
                        <el-row :gutter="8">
                            <el-col :span="24">
                                <router-link :to="'/article/'+ i"><h2>vue+element(八)事件监听与计算属性</h2> </router-link>
                            </el-col>
                            <el-col :span="24" class="article-body">
                                有时候需要特别计算值,有时候又需要监听属性
                                <!-- title -->
                                [TOC] ## computed计算属性 一般对vue 对象进行操作, 但是又不想直接在{{}}里面进行表达式操作,这时候就可以用computed属性进行监听. 任何computed下方法里面包含的vue属性发生变化,计算属性都会发生变化
                            </el-col>
                            <el-col :span="24">
                                <el-row :gutter="8" class="article-bottom auto">
                                    <el-col >
                                        <i class="el-icon-user-solid"></i>zhengxin
                                    </el-col>
                                    <el-col >
                                        <i class="el-icon-s-operation"></i>java
                                    </el-col>
                                    <el-col :span="5">
                                        <i class="el-icon-time"></i>2019-09-09
                                    </el-col>
                                    <el-col :span="4">
                                        <i class="el-icon-view"></i>100
                                    </el-col>
                                    <el-col :span="4">
                                        <i class="el-icon-star-on"></i>20
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                    </el-card>
                </el-col>
                <el-col :span="24" style="text-align: center;margin-top:10px">
                    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4" :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400">
                    </el-pagination>
                </el-col>
            </el-row>
        </el-col>
    </div>
</template>

<script>
    export default {
        name: 'ArtileBody',
        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            }
        },
        data() {
            return {
                currentPage1: 5,
                currentPage2: 5,
                currentPage3: 5,
                currentPage4: 4
            };
        },
    }
</script>

<style>
    .article-body {
        height: 88px;
        color: #566573;
        overflow: hidden;
    }
    .article-bottom {
        color: #748594;
        overflow: hidden;
    }
    .article-bottom i {
        margin: 0px 7px;
    }
    .article-bottom.auto div {
        width: auto;
        font-size: 13px;
    }
</style>