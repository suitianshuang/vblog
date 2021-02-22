<template>
    <div>
        <el-tabs v-model="activeName" type="card">
           <el-tab-pane label="权限管理" name="first">
			   <sys-role></sys-role>
			   </el-tab-pane>
           <el-tab-pane label="相关事宜" name="second">
           			   相关
           			   </el-tab-pane>
         </el-tabs>
    </div>
</template>

<script>
 
 import sysRole from '../../components/sys/sys-role.vue';
    export default {
		components:{
			sysRole
		},
        name: "SysBasic",
		data() {
			return {
				activeName: 'first',
				
			}
		},
    }
</script>

<style scoped>

</style>