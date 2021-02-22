<template>
	<view class="paper-head-list u-f-ac animated fadeIn fast" @tap="opendetail(item)">
		<image :src="item.namepic" lazy-load></image>
		<view>
			<view class="u-f-ac u-f-jsb">
				<view>{{item.name}}</view>
				<template v-if="item.noreadnum>0">
					<uni-badge :text="getItemNoreadnum" type="error"></uni-badge>
				</template>
			</view>

		</view>
	</view>






</template>

<script>
	import uniBadge from "../../components/uni-badge/uni-badge.vue";
	export default {
		components: {
			uniBadge,
		},
		props: {
			item: Object,
			index: Number
		},
		// 因为在这个组件里引入了别的组件 所以这样不会出错
		computed: {
			getItemNoreadnum() {
				return this.item.noreadnum.toString();
			}
		},
		methods: {
			opendetail(item) {
				switch (item.name) {
					case '赞':
						break;
					case '评论':
					uni.navigateTo({
						url: '../../pages/comment-list/comment-list',
					});
						break;

				}
				
			}
		}
	}
</script>

<style scoped>
	.paper-head-list {
		border-bottom: 1upx solid #EEEEEE;
		padding: 20upx 0;
	}

	.paper-head-list>image {
		width: 100upx;
		height: 100upx;
		border-radius: 100%;
		margin-right: 20upx;
		flex-shrink: 0;
	}

	.paper-head-list>view {
		flex: 1;
	}

	.paper-head-list>view>view:first-child>view {
		font-size: 35upx;
		color: #222222;
	}
</style>
