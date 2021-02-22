<template>
	<view>
		<uni-nav-bar :fixed="true" :statusBar="true" :border="false" @click-right="openAdd">
			<!-- 左边 -->
			<!-- <block slot="left">
				<view class="nav-left">
					<view class="icon iconfont icon-qiandao"></view>
				</view>
			</block> -->
			<!-- 中间 -->
			<view class="nav-tab-bar u-f-ajc">
				<block v-for="(tab,index) in tabBars" :key="tab.id">
					<view class="u-f-ajc" :class="{'active':tabIndex==index}" @tap="changeTab(index)">
						{{tab.name}}
						<view v-if="(tabIndex==index)" class="nav-tab-bar-line"></view>
					</view>
				</block>
			</view>
			<!-- 右边 -->
			<block slot="right">
				<view class="nav-right u-f-ajc">
					<view class="icon iconfont icon-bianji"></view>
				</view>
			</block>
		</uni-nav-bar>
	</view>
</template>

<script>
	import uniNavBar from "../../components/uni-nav-bar/uni-nav-bar.vue";
	export default {
		components: {
			uniNavBar
		},
		props: {
			tabBars: Array,
			tabIndex: Number
		},
		methods: {
			changeTab(index) {
				this.$emit('change-tab', index);
			},
			openAdd() {
				// 打开发布页面
				uni.navigateTo({
					url: '../../pages/add-topic/add-topic',
				});
			}
		}
	}
</script>

<style scoped>
	
	.nav-right>view {
		font-size: 40upx;
	}


	.nav-right {
		width: 100%;
	}

	/* .nav-tab-bar{
	width: 100%;
	margin-left: -20upx;
	position: relative;
} */
	.nav-tab-bar {
		width: 100%;
		margin-left: -20upx;
		color: #7e7e7e;
	}

	.nav-tab-bar>view {
		font-size: 32upx;
		padding: 0 15upx;
		font-weight: bold;
		color: #969696;
	}

	.active {
		color: #333333 !important;
	}

	.nav-tab-bar-line {
		border-bottom: 5upx solid #FEDE33;
		border-top: 5upx solid #FEDE33;
		width: 70upx;
		border-radius: 20upx;
		position: absolute;
		bottom: 12upx;
	}

	/* 
	.navleft>view,.navright>view{
		font-size:40upx;
	}
	.navleft{
		margin-left: 16upx;
		color: #4CD964;
	}
	.navright{
		width: 100%;
	}
	.nav-tab-bar{
		width: 100%;
		margin-left: -20upx;
		color: #7e7e7e;
	}
	.nav-tab-bar>view{
		font-size: 35upx;
		padding: 0upx;
		margin: 10upx;
		font-weight: bold;
		
	}
	.active{
		color: #333333 !important;
	} */
</style>
