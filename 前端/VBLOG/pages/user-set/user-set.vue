<template>
	<view class="body">
		<block v-for="(item,index) in list" :key="index">
			<home-list-item :item="item"
			:index="index"></home-list-item>
		</block>
		<button class="user-set-btn" type="primary" @tap="User.logout()">退出登陆</button>
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
				list:[
					{ icon:"",name:"账号与安全",clicktype:"navigateTo",url:"../../pages/user-safe/user-safe" },
					{ icon:"",name:"资料编辑",clicktype:"navigateTo",url:"../../pages/user-set-userinfo/user-set-userinfo" },
					{ icon:"",name:"清除缓存",clicktype:"clear",url:"" },
					{ icon:"",name:"关于我们",clicktype:"navigateTo",url:"../../pages/user-set-about/user-set-about" },
					{ icon:"",name:"帮助",clicktype:"navigateTo",url:"../../pages/user-set-help/user-set-help" },
			
				]
			}
		},
		methods: {
			
		}
	}
</script>

<style>
@import "../../common/form.css";
</style>
