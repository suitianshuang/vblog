<template>
	<view>
		<!-- 操作菜单 -->
		<view class="papar-left-popup-mask" v-show="show" @tap="hidepopup"></view>
		<view class="papar-left-popup" v-show="show">
			<template v-if="!isme">
				<view class="u-f-ac" hover-class="papar-left-popup-h" @tap="lahei">
					<view class="icon iconfont icon-sousuo"></view> 
					{{isblack ? '移除黑名单' : '加入黑名单'}}
				</view>
			</template>
			<template v-if="!isme">
				<view v-show="!isblack" class="u-f-ac"
				hover-class="papar-left-popup-h" @tap="chat">
					<view class="icon iconfont icon-qingchu"></view> 
					聊天
				</view>
			</template>
			<template v-if="isme">
				<view  class="u-f-ac"
				hover-class="papar-left-popup-h" @tap="chongzhi">
					<view class="icon iconfont icon-huiyuanvip"></view> 
					成为会员
				</view>
			</template>
			
			
		</view>
	</view>
</template>

<script>
	export default {
		props:{
			isblack:Boolean,
			show:Boolean,
			isme:Boolean
		},
		
		methods:{
			hidepopup(){
				this.$emit('hide');
			},
			lahei(){
				this.$emit('lahei');
			},
			chat(){
				this.$emit('chat');
			},
			chongzhi(){
				this.$emit('chongzhi');
			}
		}
	}
</script>

<style>
.papar-left-popup-mask{
	position: fixed;
	right: 10;
	left: 0;
	top: 0;
	bottom: 0;
	z-index: 1999;
}
.papar-left-popup{
	color: #ffffff;
	position: fixed;
	margin-right: 20upx;
	right: 0;
	top: 100upx;
	background: #868686;
	z-index: 2000;
	width: 55%;
	border-radius: 20upx;
}
.papar-left-popup>view{
	padding: 20upx;
	font-size: 25upx;
}
.papar-left-popup>view>view{
	margin-right: 10upx;
	font-weight: bold;
}
.papar-left-popup-h{
	background: #313131;
}
</style>
