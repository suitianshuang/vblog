<template>
	<view class="common-list u-f animated fadeIn fast">
		<view class="common-list-l">
			<image @tap.stop="openSpace" :src="item.userpic"  lazy-load></image>
		</view>
		<view class="common-list-r">
			<view>
				<view class="u-f-ac u-f-jsb">
					<view class="u-f-ac">
						{{item.username}} 
						<tag-sex-age :sex="getSex" :age="getAge"></tag-sex-age>
					</view>
					<view v-show="!item.isguanzhu" @tap="guanzhu" 
					class="icon iconfont icon-zengjia">关注</view>
				</view>
				<view class="common-list-r-time">{{item.create_time}}</view>
			</view>
			<view>{{item.content}}</view>
			<view class="u-f-ajc" style="flex-direction: column;">
				<!-- 图片 -->
				<block v-for="(pic,index) in item.morepic" :key="index">
					<image :src="pic" 
					mode="widthFix" 
					lazy-load 
					style="margin-bottom: 20upx;"
					@tap="imgdetail(index)"></image>
				</block>
				<!-- 视频 -->
				<!-- <template v-if="item.video">
					<view class="common-list-play icon iconfont icon-bofang">
					</view>
					<view class="common-list-playinfo">
						{{item.video.looknum}} 次播放 {{item.video.long}}
					</view>
				</template> -->
				<!-- 分享 -->
				<!-- <view class="common-list-share u-f-ac" v-if="item.share">
					<image :src="item.share.titlepic" 
					mode="widthFix" lazy-load></image>
					<view>{{item.share.title}}</view>
				</view> -->
			</view>
			<view class="u-f-ac u-f-jsb">
				<view>{{item.path}}</view>
				<view class="u-f-ac">
					<template v-if="isaccepted">
						<view class="icon iconfont icon-yijieshu"></view>
					</template>
					<view class="icon iconfont icon-qian">
					{{item.kissNum}}</view>
					<view class="icon iconfont icon-zhuanfa">
					{{item.sharenum}}</view>
					<view class="icon iconfont icon-pinglun1">
					{{item.commentnum}}</view>
					
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import tagSexAge from "../common/tag-sex-age.vue"
	export default {
		data() {
			return {
				userid:""
			}
		},
		components:{
			tagSexAge
		},
		props:{
			item:Object,
		},
		computed: {
			getSex() {
				return this.item.sex;
			},
			getAge(){
				return this.item.age;
			},
			isaccepted(){
				if(this.item.isaccepted==1){
					return true;
				}
				return false;
			}
		},
		onLoad(e) {
			this.userid=e.detailData.userid;
		},
		methods:{
			openSpace(){
				uni.navigateTo({
					url: '../../pages/user-space/user-space?userid='+this.item.userid
				});
			},
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
					// 全局通知
					uni.$emit('updateData',resdata);
				}catch(e){ return; }
			},
			imgdetail(index){
				uni.previewImage({
					current:index,
					urls:this.item.morepic
				})
			},
			
		}
	}
</script>

<style scoped>
	@import "../../common/list.css";
.common-list-r{
	border-bottom: 0;
}
.common-list{
	border-bottom: 1upx solid #EEEEEE;
}
.common-list-r-time{
	padding: 15upx 0;
	color: #CCCCCC!important;
	font-size: 25upx;
	background: #FFFFFF!important;
}
.common-list-r>view:nth-child(1)>view:nth-child(1)>view:first-child{
	color: #999999;
	font-size: 32upx;
}

.common-list-r>view:nth-child(1)>view:nth-child(1)>view:last-child{
	background: #EEEEEE;
	padding: 0 10upx;
	font-size: 26upx;
}
.icon-qian{
	font-size: 35upx !important;
	color: #d8d86b;
	padding: 0 40upx;
}
.icon-yijieshu{
	font-size: 55upx !important;
	color: #00AA7F;
	
}
</style>
