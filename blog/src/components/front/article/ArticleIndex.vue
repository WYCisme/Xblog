<template>
    <div>
        <Top></Top>
        <el-container>
            <el-main>
                <ArticleDetail></ArticleDetail>
            </el-main>
            <el-footer>
                <Backtop></Backtop>
            </el-footer>
        </el-container>
    </div>
</template>

<script>
import ArticleDetail from '@/components/front/article/ArticleDetail'
import Top from '@/components/front/Top'
import Backtop from '@/components/front/Backtop'
export default {
    name:'ArticleIndex',
    components:{
        ArticleDetail,
        Top,
        Backtop
    }
}
</script>

<style>

</style>