<template>
	<view>
		<view class="body">
			<block v-for="(item,index) in list" :key="index">
				<home-list-item :item="item"
				:index="index" @updateuserbind='__init()' ></home-list-item>
			</block>
		</view>
	</view>
</template>

<script>
	import homeListItem from "../../components/home/home-list-item.vue";
	export default {
		components:{
			homeListItem
		},
		data() {
			return {
				list:[]
			}
		},
		onShow(){
			this.__init();
		},
		methods: {
			async __init(){
				// 获取当前用户绑定情况
				this.list = [
				
					{ icon:"",name:"登录密码",clicktype:"navigateTo",url:"../../pages/user-set-repassword/user-set-repassword?password="+this.User.userinfo.password ,data:this.User.userinfo.password ? "修改密码" : "未设置"},
					{ icon:"",name:"邮箱绑定",clicktype:"navigateTo",url:"../../pages/user-set-email/user-set-email?email="+this.User.userinfo.email || false ,data:this.User.userinfo.email || "未绑定"}
				]
				
			}
		}
	}
</script>

<style>
@import "../../common/form.css";
</style>
