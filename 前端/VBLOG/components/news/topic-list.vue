<template>
	<view class="topic-list u-f animated fadeIn fast" @tap="opendetail">
		<image :src="item.titlepic" mode="widthFix" lazy-load></image>
		<view>
			<view>#{{item.title}}#</view>
			<view>{{item.desc}}</view>
			<view>动态 {{item.totalnum}} 今日 {{item.todaynum}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			ischange: {
				type: Boolean,
				default: false
			},
			item: Object,
			index: Number,
			fromsearch: {
				type: Boolean,
				default: false
			},
			notfound: {
				type: Boolean,
				default: false
			}
		},
		methods: {
			opendetail() {
				if (this.ischange) {
					// 通知并返回
					console.log(this.item.id);
					uni.$emit('changeTopic', {
						isnewtopic:this.notfound ? true : false,
						id: this.item.id,
						title: this.item.title
					});
					if (this.fromsearch) {
						uni.navigateBack({
							delta: 1
						});
					}
					uni.navigateBack({
						delta: 1
					});

					return;
				}
				uni.navigateTo({
					url: '../../pages/topic-detail/topic-detail?detail=' + JSON.stringify(this.item),
				});
			}
		}
	}
</script>

<style scoped>
	.topic-list {
		padding: 20upx 0;
		border-bottom: 1upx solid #EEEEEE;
	}

	.topic-list image {
		width: 150upx;
		height: 150upx;
		border-radius: 10upx;
		margin-left: 20upx;
		margin-right: 20upx;
	}

	.topic-list>view>view {
		color: #A4A4A4;
	}

	.topic-list>view>view:first-child {
		color: #333333;
		font-size: 32upx;
	}
</style>
