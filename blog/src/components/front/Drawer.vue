<template>
    <div>
        <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
            点我查看时间轴
        </el-button>
        <el-drawer title="时光轴" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
            <Timeline></Timeline>
        </el-drawer>
    </div>
</template>

<script>
import Timeline from '@/components/front/Timeline'
    export default {
        name: 'Drawer',
        data() {
            return {
                drawer: false,
                direction: 'rtl',
            };
        },
        methods: {
            handleClose(done) {
                 done();
            }
        },
        components:{
            Timeline
        }
    }
</script>

<style>

</style>