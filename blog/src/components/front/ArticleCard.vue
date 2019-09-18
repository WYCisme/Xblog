<template>
    <div class="ArticleCard">
        <el-row :gutter="0">
            <ArticleBodyContent></ArticleBodyContent>
            <ArticleBodyRight></ArticleBodyRight>
        </el-row>
    </div>
</template>

<script>
    import Drawer from '@/components/front/Drawer'
    import ArticleBodyRight from '@/components/front/ArticleBodyRight'
    import ArticleBodyContent from '@/components/front/ArticleBodyContent'
    export default {
        name: "ArticleCard",
        components: {
            Drawer,
            ArticleBodyContent,
            ArticleBodyRight,
        }
    }
</script>

<style>
    .article-body {
        background-color: #FFF;
    }
</style>