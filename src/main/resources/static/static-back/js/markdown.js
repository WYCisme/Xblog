/**
 扩展一个test模块
 **/
layui.define(['jquery'],function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var obj = {
        hello: function(info){
            infoWidth = '90%'
            if(info.width){
                infoWidth = info.width;
            }
            inforHeight = '1000'
            if(info.height){
                inforHeight = info.height;
            }
            var e = editormd(info.id, {
                placeholder:'本编辑器支持Markdown编辑，左边编写，右边预览',  //默认显示的文字，这里就不解释了
                width: infoWidth,
                height: inforHeight,
                syncScrolling: "single",
                path: "/static-back/editor.md-master/lib/",   //你的path路径（原资源文件中lib包在我们项目中所放的位置）
                // theme: "dark",//工具栏主题
                // previewTheme: "dark",//预览主题
                // editorTheme: "pastel-on-dark",//编辑主题
                saveHTMLToTextarea: true,
                emoji: false,
                taskList: true,
                tocm: true,         // Using [TOCM]
                tex: true,                   // 开启科学公式TeX语言支持，默认关闭
                flowChart: true,             // 开启流程图支持，默认关闭
                sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
                emoji : true,
                taskList : true,
                //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "/file/upload",
                onload : function() {
                    // console.log('onload', this);
                    //this.fullscreen();
                    //this.unwatch();
                    //this.watch().fullscreen();

                    //this.setMarkdown("#PHP");
                    //this.width("100%");
                    //this.height(480);
                    //this.resize("100%", 640);
                }

            });
            return e;
        }
        ,
        editormd:function(){
            return editormd;
        }
    };
    //输出test接口
    exports('markdown', obj);
});

