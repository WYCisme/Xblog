<template>
    <div class="article-detail">
        <el-col :span="10" :offset="5">
            <el-row :gutter="0">
                <el-col :span="24">
                    <el-card shadow="hover" v-html="content" style="margin:5px;padding:15px;">
                    </el-card>
                </el-col>
            </el-row>
        </el-col>
        <el-col :span="4">
            <el-row :gutter="0">
                <el-col :span="24">
                    <el-card shadow="hover" style="margin:5px;" v-html="toc">
                    </el-card>
                </el-col>
            </el-row>
        </el-col>
    </div>
</template>
<script>
    import marked from 'marked'
    const tocObj = {
        add: function(text, level) {
            var anchor = `#toc${level}toc${++this.index}`;
            console.log('anchor', anchor)
            this.toc.push({
                anchor: anchor,
                level: level,
                text: text
            });
            return anchor;
        },
        toHTML: function() {
            let levelStack = [];
            let result = '';
            const addStartUL = () => {
                result += '<ul>';
            };
            const addEndUL = () => {
                result += '</ul>\n';
            };
            const addLI = (anchor, text) => {
                result += '<li><a href="#' + anchor + '">' + text + '<a></li>\n';
            };
            this.toc.forEach(function(item) {
                let levelIndex = levelStack.indexOf(item.level);
                // 没有找到相应level的ul标签，则将li放入新增的ul中
                if (levelIndex === -1) {
                    levelStack.unshift(item.level);
                    addStartUL();
                    addLI(item.anchor, item.text);
                } // 找到了相应level的ul标签，并且在栈顶的位置则直接将li放在此ul下
                else if (levelIndex === 0) {
                    addLI(item.anchor, item.text);
                } // 找到了相应level的ul标签，但是不在栈顶位置，需要将之前的所有level出栈并且打上闭合标签，最后新增li
                else {
                    while (levelIndex--) {
                        levelStack.shift();
                        addEndUL();
                    }
                    addLI(item.anchor, item.text);
                }
            });
            // 如果栈中还有level，全部出栈打上闭合标签
            while (levelStack.length) {
                levelStack.shift();
                addEndUL();
            }
            // 清理先前数据供下次使用
            this.toc = [];
            this.index = 0;
            return result;
        },
        toc: [],
        index: 0
    };
    let renderer = new marked.Renderer();
    renderer.heading = function(text, level, raw) {
        console.log(text, level, raw)
        let anchor = tocObj.add(text, level);
        return `<a id=${anchor} class="anchor-fix"></a><h${level}>${text}</h${level}>\n`;
    };
    marked.setOptions({
        renderer: renderer,
        gfm: true,
        tables: true,
        breaks: false,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false
    });
    export default {
        name: 'ArticleDetail',
        data: function() {
            return {
                content: '',
                toc: '',
                data
            }
        },
        created: function() {
            tocObj.content = marked(this.data);
            this.content = tocObj.content.replace("[TOC]", "")
            this.toc = tocObj.toHTML();
        },
        components: {},
        methods: {}
    }
</script>

<style scoped>
    .article-detail {
        height: 100vh;
        width: 100%;
        margin-top: 35px;
    }
    h1,
    h2,
    h3,
    h4,
    h5,
    h6,
    p,
    blockquote {
        margin: 0;
        padding: 0;
    }
    body {
        font-family: "Helvetica Neue", Helvetica, "Hiragino Sans GB", Arial, sans-serif;
        font-size: 13px;
        line-height: 18px;
        color: #737373;
        background-color: white;
        margin: 10px 13px 10px 13px;
    }
    table {
        margin: 10px 0 15px 0;
        border-collapse: collapse;
    }
    td,
    th {
        border: 1px solid #ddd;
        padding: 3px 10px;
    }
    th {
        padding: 5px 10px;
    }
    a {
        color: #0069d6;
    }
    a:hover {
        color: #0050a3;
        text-decoration: none;
    }
    a img {
        border: none;
    }
    p {
        margin-bottom: 9px;
    }
    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
        color: #404040;
        line-height: 36px;
    }
    h1 {
        margin-bottom: 18px;
        font-size: 30px;
    }
    h2 {
        font-size: 24px;
    }
    h3 {
        font-size: 18px;
    }
    h4 {
        font-size: 16px;
    }
    h5 {
        font-size: 14px;
    }
    h6 {
        font-size: 13px;
    }
    hr {
        margin: 0 0 19px;
        border: 0;
        border-bottom: 1px solid #ccc;
    }
    blockquote {
        padding: 13px 13px 21px 15px;
        margin-bottom: 18px;
        font-family: georgia, serif;
        font-style: italic;
    }
    blockquote:before {
        content: "\201C";
        font-size: 40px;
        margin-left: -10px;
        font-family: georgia, serif;
        color: #eee;
    }
    blockquote p {
        font-size: 14px;
        font-weight: 300;
        line-height: 18px;
        margin-bottom: 0;
        font-style: italic;
    }
    code,
    pre {
        font-family: Monaco, Andale Mono, Courier New, monospace;
    }
    code {
        background-color: #fee9cc;
        color: rgba(0, 0, 0, 0.75);
        padding: 1px 3px;
        font-size: 12px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
    pre {
        display: block;
        padding: 14px;
        margin: 0 0 18px;
        line-height: 16px;
        font-size: 11px;
        border: 1px solid #d9d9d9;
        white-space: pre-wrap;
        word-wrap: break-word;
    }
    pre code {
        background-color: #fff;
        color: #737373;
        font-size: 11px;
        padding: 0;
    }
    sup {
        font-size: 0.83em;
        vertical-align: super;
        line-height: 0;
    }
    * {
        -webkit-print-color-adjust: exact;
    }
    @media screen and (min-width: 914px) {
        body {
            width: 854px;
            margin: 10px auto;
        }
    }
    @media print {
        body,
        code,
        pre code,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            color: black;
        }
        table,
        pre {
            page-break-inside: avoid;
        }
    }
</style>