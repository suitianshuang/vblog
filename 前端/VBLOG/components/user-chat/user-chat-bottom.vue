<template>
	<view class="user-chat-bottom u-f-ac animated fadeInDown fast">
		<image :src="userpic"></image>
		<input type="text" placeholder="友善发言~" placeholder-class="placeholder-class" :focus="focus" @blur="blur" v-model="text" />
		<view class="icon iconfont icon-fabu u-f-ajc" @tap="submit"></view>
	</view>
</template>

<script>
	export default {
		props: {
			focus: {
				type: Boolean,
				default: false
			},
			userpic:{
				type:String,
				default:"../../static/demo/datapic/36.jpg"
			}
		},
		data() {
			return {
				text: ""
			}
		},
		methods: {
			submit() {
				this.$emit('submit', this.text);
				this.text = "";
			},
			blur() {
				this.$emit('blur');
			}
		}
	}
</script>

<style scoped>
	.placeholder-class {
		font-size: 30upx;
		color: #9d9d9d;
		background: #F7F7F7;
	}
	.user-chat-bottom>image{
		border-radius: 100%;
		height: 70upx;
		width: 70upx;
	}

	.user-chat-bottom {
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		height: 120upx;
		padding: 0 20upx;
		background: #FFFFFF;
		border-top: 1upx solid #EEEEEE;
	}

	.user-chat-bottom>input {
		flex: 1;
		margin-left: 20upx;
		margin-right: 20upx;
		padding: 10upx 20upx;
		border-radius: 10upx;
		background: #F7F7F7;
	}

	.user-chat-bottom>view {
		width: 80upx;
		font-size: 45upx;
	}
</style>
