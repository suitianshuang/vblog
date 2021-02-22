<template>
	<view class="uni-comment-list" :class="{'u-comment-list-child':(item.fid>0)}">
		<view class="uni-comment-face">
			<image @tap.stop="openSpace" :src="item.userpic" mode="widthFix"  ></image>
		</view>
		<view class="uni-comment-body" hover-class="comment-hover-class" @tap="hidePopup" >
			<view class="uni-comment-top"><text>{{item.username}}</text>
			</view>
			<view class="uni-comment-content" hover-class="hover-class" hover-start-time="1000" >{{item.data}}</view>
			<view class="uni-comment-date">
				<view>{{item.time}}</view>
			</view>
		</view>
		
		<uni-popup :show="showpopup" position="middle" mode="fixed" :height="height" @hidePopup="hidePopup">
			<view class="tanchu">
					<view class="comment-list-kissNum u-f-ajc" hover-class="comment-list-hover-class" @tap.stop="pinglun">评论{{item.username}}</view>
					<template v-if="isshow">
						<view class="comment-list-kissNum u-f-ajc" hover-class="comment-list-hover-class" @tap.stop="caina">采纳{{item.username}}</view>
					</template>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import uniPopup from "../../components/uni-popup/uni-popup.vue";
	export default {
		components:{
			uniPopup
		},
		props:{
			item:Object,
			index:Number,
			isme:Boolean,
			isaccepted:Number,
			username:String,
		},
		data() {
			return {
				height:0,
				showpopup:false
			}
		},
		computed: {
			// 是本的帖子，不是本人的评论，并且未结贴 才显示 采纳
			isshow() {
				if(this.item.userid!==this.User.userinfo.id&&this.isme&&this.isaccepted==0){
					return true;
				}
				return false;
			}
		},
		methods: {
		
			pinglun(){
				this.hidePopup();
				this.$emit('reply',this.item.id)
			},
			caina(){
				console.log("caina");
				this.hidePopup();
				this.$emit('caina',this.item.userid)
			},
			openSpace(){
				uni.navigateTo({
					url: '../../pages/user-space/user-space?userid='+this.item.userid
				});
			},
			hidePopup(){
				uni.createSelectorQuery().selectViewport().scrollOffset(res => {
				  // console.log("竖直滚动位置" + res.scrollTop);
				  this.height = res.scrollTop
				}).exec();
				this.showpopup=!this.showpopup;
			}
		},
	}
</script>

<style scoped>
/* 	.uni-comment-face{
		border-radius: 100;
	}
.u-comment-list-child{
	padding: 20upx;
	background: #F4F4F4;
	border-bottom: 1upx solid #EEEEEE;
	box-sizing: border-box;
	margin: 0;
	margin-left: 70upx;
	width: auto;
}
*/
.comment-hover-class{
	border-radius: 10upx;
	background: #d0d0d0;
} 
	.uni-comment-list {
		flex-wrap: nowrap;
		margin: 0;
		margin-right: 20upx;
		/* 这样就不会超出范围了 */
		width: 100%;
		display: flex;
	}
	
	.u-comment-list-child{
		padding: 20upx;
		background-color: #fcfcfc;
		border-bottom: 1upx solid #dddddd;
		/* 不会向外扩张 而是向里面挤 */
		box-sizing: border-box;
		margin-left: 70upx !important;
		margin-right: 20upx !important;
		border-radius: 15upx;
		
	}
	
	.uni-comment-face {
		width: 70upx;
		height: 70upx;
		border-radius: 100%;
		margin-right: 20upx;
		flex-shrink: 0;
		overflow: hidden;
	}
	
	.uni-comment-face image {
		width: 100%;
		border-radius: 100%;
	}
	
	.uni-comment-body {
		width: 100%;
	}
	
	.uni-comment-top {
		line-height: 1.5em;
		justify-content: space-between;
	}
	
	.uni-comment-top text {
		color: #0A98D5;
		font-size: 24upx;
	}
	
	.uni-comment-date {
		line-height: 38upx;
		flex-direction: row;
		justify-content: space-between;
		display: flex !important;
		flex-grow: 1;
	}
	
	.uni-comment-date view {
		color: #666666;
		font-size: 24upx;
		line-height: 38upx;
	}
	
	.uni-comment-content {
		line-height: 1.6em;
		font-size: 28upx;
		padding: 8rpx 0;
	}
	
	.uni-comment-replay-btn {
		background: #FFF;
		font-size: 24upx;
		line-height: 28upx;
		padding: 5rpx 20upx;
		border-radius: 30upx;
		color: #333 !important;
		margin: 0 10upx;
	}

.tanchu{
	width: 400upx;
}
.comment-list-kissNum{
	font-size: 30upx;
	padding: 10upx 0;
	border-bottom: 1upx #555048;
	background: #e2d5c1;
	border-radius: 10upx;
	
}
.comment-list-hover-class{
	background: #cecece;
	border-radius: 10upx;
}


</style>
