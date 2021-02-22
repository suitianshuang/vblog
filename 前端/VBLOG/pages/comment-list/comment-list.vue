<template>
	<view>
		<template v-if="list.length > 0">
			<uni-list>
				<block v-for="(item,index) in list" :key="index">
					<uni-list-item :note="item.commentnum+'条评论 '+item.infonum.dingnum+'点赞 '+'创建时间 '+item.createTime" :showExtraIcon="true"
					 :title="'你的《'+item.title+'》有新评论,快去查看吧'" :thumb="item.titlepic" @click="openDetail(item)">
					</uni-list-item>
					<!-- 上拉加载 -->

				</block>
			</uni-list>
			<load-more :loadtext="loadtext"></load-more>
		</template>
		
		<template v-else-if="firstload">
			<view style="font-size: 50upx;font-weight: bold;color: #CCCCCC;
	padding-top: 100upx;" class="u-f-ajc">Loading
				...</view>
		</template>
		<template v-else>
			<!-- 无内容默认 -->
			<no-thing></no-thing>
		</template>
	</view>
</template>
<script>
	import uniList from "../../components/uni-list/uni-list.vue";
	import uniListItem from "../../components/uni-list-item/uni-list-item.vue";
	import noThing from "../../components/common/no-thing.vue";
	import loadMore from "../../components/common/load-more.vue";
	export default {
		components: {
			uniList,
			uniListItem,
			noThing,
			loadMore
		},
		data() {
			return {
				firstload: false,
				loadtext: "上拉加载更多...",
				pageNum: 1,
				list: []
			}
		},
		onReachBottom() {
			this.loadmore();
		},
		onLoad() {
			this.getList();
		},
		methods: {
			openDetail(item) {
				uni.navigateTo({
					url: '/pages/detail/detail?detailData=' + JSON.stringify(item),
				});
			},
			async getList() {
				// 发请求 轮询
				let userId = this.User.userinfo.id;
				// url = `/user/me/hottestarticle?userId=${this.userinfo.id}&&pageNum=${page}`
				let url = `/comment/getusercomment`
				let [err, res] = await this.$http.post(url, {
					userId: userId,
					pageNum: this.pageNum
				}, {
					header: {
						'Content-Type': 'application/x-www-form-urlencoded',
					},
				});
				let error = this.$http.errorCheck(err, res, () => {
					// this.newslist[currentIndex].loadtext = "上拉加载更多";
				}, () => {
					// this.newslist[currentIndex].loadtext = "上拉加载更多";
				});
				if (!error) return;

				let arr = [];

				let list = res.data.obj.list;
				for (let i = 0; i < list.length; i++) {
					arr.push(this.User.__Articleformat(list[i]));
				}
				this.list = arr;
				this.firstload = true;
				if (list.length < 10) {
					this.loadtext = "没有更多数据了";
				} else {
					this.loadtext = "上拉加载更多...";
				}
				return;
			},
			// 上拉加载
			loadmore() {
				if (this.loadtext != "上拉加载更多") {
					return;
				}
				// 修改状态
				this.loadtext = "加载中...";
				this.pageNum++;
				this.getList();
			},


		}
	}
</script>

<style scoped>

</style>
