<template>
	<view class="common-list u-f animated fadeIn fast">
		<view class="common-list-l">
			<image :src="item.userpic" @tap.stop="openSpace" lazy-load></image>
		</view>
		<view class="common-list-r">
			<view class="u-f-ac u-f-jsb">
				<view class="u-f-ac">
					{{item.username}}
					<tag-sex-age :sex="getSex" :age="getAge"></tag-sex-age>
				</view>
				<view v-show="!item.isguanzhu" @tap="guanzhu" 
				class="icon iconfont icon-zengjia">关注</view>
			</view>
			<view class="common-list-time u-f-ac">{{item.createTime}}</view>
			<view @tap.stop="opendetail">{{item.title}}</view>
			<view class="u-f-ajc" @tap.stop="opendetail">
				<template v-if="item.titlepic">
					<!-- 图片 -->
					<image v-if="item.titlepic" :src="item.titlepic" 
					mode="widthFix" 
					lazy-load></image>
				</template>
				<!-- 视频 -->
				<template v-if="item.video">
					<view class="common-list-play icon iconfont icon-bofang">
					</view>
					<view class="common-list-playinfo">
						{{item.video.looknum}} 次播放 {{item.video.long}}
					</view>
				</template>
				<!-- 分享 -->
				<view class="common-list-share u-f-ac" v-if="item.share">
					<image :src="item.share.titlepic" 
					mode="widthFix" lazy-load></image>
					<view>{{item.share.title}}</view>
				</view>
			</view>
			<view class="u-f-ac u-f-jsb">
				<view>{{item.path}}</view>
				<view class="u-f-ac">
					<view class="icon iconfont icon-zhuanfa">
					{{item.sharenum}}</view>
					<view class="icon iconfont icon-pinglun1">
					{{item.commentnum}}</view>
					<view class="icon iconfont icon-dianzan1"
					:class="{'active':(item.infonum.index==1)}"
					 @tap="caozuo('ding')">
					{{item.infonum.dingnum}}</view>
					
					<!-- <view class="u-f-ac" :class="{'active':(item.infonum.index==1)}"
					@tap="caozuo('ding')">
						<view class="icon iconfont icon-icon_xiaolian-mian"></view>
						{{item.infonum.dingnum}}
					</view> -->
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import tagSexAge from "./tag-sex-age.vue"
	export default {
		components:{
			tagSexAge
		},
		props:{
			nonavigate:{
				type:Boolean,
				default:false
			},
			item:Object,
			index:Number
		},
		computed: {
			getSex() {
				return this.item.sex;
			},
			getAge(){
				return this.item.age;
			}
		},
		methods:{
			async guanzhu(){
				try{
					let [err,res] = await this.$http.post('/fans/follow',{
						followId:this.item.userid,
						userId:this.User.userinfo.id
					},
					{
						token:true,
						checkToken:true
					}
					);
					// 错误处理
					if (!this.$http.errorCheck(err,res)) return;
					// 通知首页修改数据
					uni.showToast({ title: '关注成功' });
					let resdata = {
					 	type:"guanzhu",
					 	userid:this.item.userid,
					 	data:true
					};
					// 通知父组件
					this.$emit('changeevent',resdata);
					// 通知全局
					uni.$emit('updateData',resdata);
				}catch(e){ return; }
			},
			// 进入详情页
			opendetail(){
				uni.navigateTo({
					url: '../../pages/detail/detail?detailData='+JSON.stringify(this.item),
				});
				// 加入浏览历史
				// this.User.addHistoryList(this.item);
			},
			async caozuo(type){
				let index = (type === 'ding') ? 1 : 2; // 当前操作
				if(this.item.infonum.index === index) return; // 状态相同不修改
				let [err,res] = await this.$http.post('/article/support',{
					articleId:this.item.id,
					userId:this.User.userinfo.id,
					type:index-1
				});
				if (!this.$http.errorCheck(err,res)) return;
				uni.showToast({ title: "顶成功" });
				// 通知父组件
				let resdata = {
					type:"support",
					post_id:this.item.id,
					do:type
				};
				this.$emit('changeevent',resdata);
				// 通知全局
				return uni.$emit("updateData",resdata);
			},
			openSpace(){
				if (this.nonavigate) return;
				uni.navigateTo({
					url:"../../pages/user-space/user-space?userid="+this.item.userid
				})
			}
		}
	}
</script>

<style scoped>
.common-list{
	padding: 20upx;
}
.common-list-l{
	flex-shrink: 0;
}
.common-list-l image{
	width: 90upx;
	height: 90upx;
	border-radius:100%;
}
.common-list-r{
	flex: 1;
	margin-left: 15upx;
	border-bottom: 1upx solid #EEEEEE;
	padding-bottom: 10upx;
}
.common-list-r>view:nth-child(4)>image{
	width: 100%;
	border-radius:10upx;
}
.common-list-r>view:nth-child(1)>view:first-child{
	color: #999999;
	font-size: 32upx;
}
.common-list-r>view:nth-child(2){
	font-size: 25upx;
	color: #c5c5c5;
}

.common-list-r>view:nth-child(1)>view:last-child{
	background: #EEEEEE;
	padding: 0 10upx;
	font-size: 26upx;
}
.commin-list-r>viwe:nth-child(2){
	font-size: 32upx;
	padding: 12upx 0;
}

.common-list-r>view:nth-child(3){
	font-size: 32upx;
	padding: 12upx 0;
}
.common-list-r>view:nth-child(4){
	position: relative;
	margin-bottom: 10upx;
}
.common-list-play,.common-list-playinfo{
	position: absolute;
	color: #FFFFFF;
}
.common-list-play{
	font-size: 130upx;
}
.common-list-playinfo{
	right: 10upx;
	bottom: 10upx;
	background: rgba(51, 51, 51, 0.73);
	border-radius: 20upx;
	padding: 0 20upx;
	font-size: 26upx;
}
.common-list-r>view:nth-child(5)>view{
	color: #AAAAAA;
}
.common-list-r>view:nth-child(5)>view:nth-child(2)>view{
	margin-left: 10upx;
	padding-left: 5upx;
	font-size: 28upx;
}
.common-list-share{
	background: #EEEEEE;
	width: 100%;
	padding: 10upx;
	border-radius: 10upx;
}
.common-list-share>image{
	width: 200upx;
	height: 150upx;
	margin-right: 10upx;
}

 .active{
	color: #C5F61C;
}
</style>
