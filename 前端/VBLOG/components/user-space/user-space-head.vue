<template>
	<view class="user-space-head u-f-ajc">
		<image :src="getBgImg" lazy-load @tap.stop="changBgImg"></image>
		<view class="user-space-head-info u-f-ajc u-f-column">
			<image :src="userinfo.userpic" lazy-load></image>
			<view class="user-space-margin u-f-ac">
				{{userinfo.username}} 
				<tag-sex-age :sex="getSex" :age="getAge"></tag-sex-age> 
			</view>
			<view v-if="!userinfo.isme" class="icon iconfont user-space-head-btn user-space-margin" :class="getGuanZhuClass" @tap.stop="guanzhu">
				{{userinfo.isguanzhu?'已关注':'关注'}}
			</view>
		</view>
	</view>
</template>


<script>
	import tagSexAge from "../common/tag-sex-age.vue";
	export default {
		
		components:{
			tagSexAge
		},
		props:{
			userinfo:Object
		},
		data() {
			return {
			}
		},
		computed:{
			getSex(){
				return this.userinfo.sex;
			},
			getAge(){
				return this.userinfo.age;
			},
			getBgImg(){
				return "../../static/bgimg/"+this.userinfo.bgimg+".jpg";
			},
			getGuanZhuClass(){
				return this.userinfo.isguanzhu?'active':'icon-zengjia';
			}
		},
		methods:{
			// 切换背景
			changBgImg(){
				let no = parseInt(this.userinfo.bgimg);
				this.userinfo.bgimg = no<6 ? ++no : 1;
			},
			// 关注
			async guanzhu(){
				try{
					console.log(this.userinfo.isguanzhu);
					let url = this.userinfo.isguanzhu ? '/fans/unfollow' : '/fans/follow';
					console.log(url);
					uni.showLoading({ title: 'loading...', mask: false });
					let [err,res] = await this.$http.post(url,{
						followId:this.userinfo.id,
						userId:this.User.userinfo.id
					},
					{
						token:true,
						checkToken:true
					});
					// 错误处理
					if (!this.$http.errorCheck(err,res)){
						return uni.hideLoading();
					}
					// 成功
					uni.hideLoading();
					uni.showToast({ 
						title: this.userinfo.isguanzhu ? '取消关注成功' : '关注成功' ,
					});
					// 通知父组件修改状态
					this.$emit('update',!this.userinfo.isguanzhu);
				}catch(e){
					return uni.showToast({
						title:"出错了~",
						icon:"none"
					})
				}
				
			}
		}
	}
</script>

<style scoped>
.user-space-head{
	position: relative;
	height: 500upx;
	overflow: hidden;
}
.user-space-head>image{
	width: 100%;
}
.user-space-head-info{
	position: absolute;
	top: 150upx;
}
.user-space-head-info>image{
	width: 150upx;
	height: 150upx;
	border-radius: 100%;
}
.user-space-head-info>view:first-of-type{
	color: #FFFFFF;
	font-size: 35upx;
	font-weight: bold;
	text-shadow: 2upx 2upx 10upx #333333;
}
.user-space-head-btn{
	background: #00ca94;
	color: #333333;
	border: 1upx solid #00ca94;
	padding: 0 15upx;
	border-radius: 10upx;
	font-size: 28upx;
}
.active{
	background:none;
	color: #FFFFFF;
	border: 1upx solid #FFFFFF;
}
</style>
