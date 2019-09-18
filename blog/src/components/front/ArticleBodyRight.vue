<template>
    <div>
        <el-col :span="5" hidden-sm-and-down>
                <el-row :gutter="0">
                    <el-col :span="24">
                        <AboutMe></AboutMe>
                        <ArticleUp></ArticleUp>
                        <ArticleView></ArticleView>
                        <LabelCloud></LabelCloud>
                    </el-col>
                </el-row>
        </el-col>
    </div>
</template>

<script>
    import AboutMe from '@/components/front/AboutMe'
    import ArticleUp from '@/components/front/ArticleUp'
    import ArticleView from '@/components/front/ArticleView'
    import LabelCloud from '@/components/front/LabelCloud'
    export default {
        components: {
            AboutMe,
            ArticleUp,
            ArticleView,
            LabelCloud
        }
    }
</script>

<style>

</style>