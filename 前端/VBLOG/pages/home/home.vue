<template>
	<view>
		<template v-if="!loginStatus">
			<!-- 未登录 -->
			<view class="u-f-ajc">登陆之后,体验更多功能</view>
			
			<!-- 账号密码登陆 -->
			<view class="u-f-ajc" @tap="openLogin">账号密码登陆 <view class="icon iconfont icon-jinru"></view> </view>
		</template>
		<template v-else>
			<!-- 登陆 -->
			<home-info :homeinfo="homeinfo"></home-info>
		</template>
		<!-- 数据 -->
		<home-data :homedata="homedata"></home-data>
		<!-- 广告位 -->
		<!-- <view class="home-adv u-f-ajc animated fadeIn fast" >
			<image src="../../static/bgimage/1.jpg" mode="widthFix" lazy-load></image>
		</view> -->
		<swiper class="topic-swiper" :indicator-dots="true" :autoplay="true" :interval="3000" :duration="1000">
			<block v-for="(item,index) in homeimg" :key="index">
				<swiper-item>
						<image :src="item.src"  mode="widthFix" lazy-load></image>
				</swiper-item>
			</block>
		</swiper>
		<!-- 功能列表 -->
		<view class="home-list">
			<block v-for="(item,index) in list" :key="index">
				<home-list-item :item="item" :index="index"></home-list-item>
			</block>
		</view>
		
		
	</view>
</template>

<script>
	import homeListItem from "../../components/home/home-list-item.vue";
	import homeInfo from "../../components/home/home-info.vue";
	import homeData from "../../components/home/home-data.vue";
	export default {
		components:{
			homeListItem,
			homeInfo,
			homeData
		},
		data() {
			return {
				loginStatus:false,
				homeinfo:{
					userpic:"../../static/demo/userpic/11.jpg",
					username:"昵称",
					totalnum:0,
					todaynum:0,
				},
				homeimg:[
					
				],
				homedata:[
					{ name:"发布", num:0 },
					{ name:"评论", num:0 },
					{ name:"粉丝", num:0 },
					{name:"飞吻数",num:0},
				],
				list:[
					{ icon:"liulan",name:"浏览历史",clicktype:"navigateTo",url:"/pages/user-history/user-history"},
			
				]
			};
		},
		onNavigationBarButtonTap(e) {
			if(e.index==0){
				this.User.navigate({
					url: '../user-set/user-set',
				});
			}
		},
		onShow() {
			this.isLogin();
		},
		onLoad() {
			this.getSwiper();
		},
		methods:{
			async getSwiper(){
				let [err,res] = await this.$http.get('/advertising');
				if (!this.$http.errorCheck(err,res)) return;
				let arr = [];
				let list = res.data.obj;
				for (let i = 0; i < list.length; i++) {
					arr.push({
						src:list[i].picture,
						// url:list[i].url
					})
				}
				this.homeimg= arr;
			},
			// 判断用户是否登录
			isLogin(){
				if (!this.User.userinfo){
					this.loginStatus = false;
					this.homeinfo={
						userpic:"",
						username:"",
						totalnum:0,
						todaynum:0,
					};
					this.homedata[0].num = 0;
					this.homedata[1].num = 0;
					this.homedata[2].num = 0;
					this.homedata[3].num = 0;
					return;
				}
				// 用户已登录
				this.loginStatus = true;
				this.homeinfo.id = this.User.userinfo.id;
				this.homeinfo.userpic =this.User.userinfo.userpic;
			
				this.homeinfo.username = this.User.userinfo.username;
				this.homeinfo.totalnum = this.User.counts.articleCount || 0;
				this.homeinfo.todaynum = this.User.counts.todayArticleCount || 0;
				this.homedata[0].num = this.User.counts.articleCount || 0;
				this.homedata[1].num = this.User.counts.commentCount || 0;
				this.homedata[2].num = this.User.counts.fensCount || 0;
				this.homedata[3].num = this.User.counts.kissNum|| 0;
			},
			openLogin(){
				uni.navigateTo({
					url: '../login/login'
				});
			}
		}
	}
</script>

<style>

.home-list{
	padding: 20upx;
}
.home-adv{
	padding: 20upx;
}
.home-adv>image{
	border-radius: 20upx;
	height: 150upx;
}
.topic-swiper{
	padding:0 20upx 20upx 20upx;
}
.topic-swiper image{
	width: 100%;
	border-radius:10upx;
}
</style>
