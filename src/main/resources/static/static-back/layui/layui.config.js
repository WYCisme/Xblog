
//config的设置是全局的
layui.config({
    base: '/back/js/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    markdown: 'markdown' //相对于上述 base 目录的子目录
});




// //你也可以忽略 base 设定的根目录，直接在 extend 指定路径（主要：该功能为 layui 2.2.0 新增）
// layui.extend({
//     markdown: '{/}/back/js/markdown'
//     markdown: '{/}/back/js/markdown' // {/}的意思即代表采用自有路径，即不跟随 base 路径
// })