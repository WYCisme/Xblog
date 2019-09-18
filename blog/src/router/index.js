import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/front/Index'
import ArticleIndex from '@/components/front/article/ArticleIndex'

Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      component: Index
    },
    {
      path: '/article/:id',
      component: ArticleIndex
    }
  ]
})
