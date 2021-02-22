<template>
	<view>
		<uni-collapse>
			<block v-for="(item,index) in list" :key="index">
				<uni-collapse-item :title="item.title" :show-animation="true" :contentdata="item.content"></uni-collapse-item>
			</block>
		</uni-collapse>
		<view class="body">
			<button class="user-set-btn" 
			type="primary" @tap="openFeedback">意见反馈</button>
		</view>
	</view>
</template>

<script>
	import uniCollapse from "../../components/uni-collapse/uni-collapse.vue"
	import uniCollapseItem from "../../components/uni-collapse-item/uni-collapse-item.vue"
	export default {
		components:{
			uniCollapse,
			uniCollapseItem
		},
		data() {
			return {
				list:[
					{
						title:"客服什么时候上线？",
						content:"每天晚上八点"
					},
					{
						title:"忘记账号/昵称/密码怎么办？",
						content:"到找回密码界面找回"
					},
					{
						title:"为什么不能发送图片",
						content:"系统将会持续更新"
					}
				]
			}
		},
		methods: {
			openFeedback(){
				uni.navigateTo({
					url: '/pages/user-feedback/user-feedback',
				});
			}
		}
	}
</script>

<style>
@import "../../common/form.css";
</style>
