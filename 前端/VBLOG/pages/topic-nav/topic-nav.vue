<template>
	<view>
		
		<swiper-tab-head 
		:tabBars="tabBars" 
		:tabIndex="tabIndex"
		@tabtap="tabtap">
		</swiper-tab-head>
		
		<view class="uni-tab-bar">
		<swiper class="swiper-box" 
		:style="{height:swiperheight+'px'}" 
		:current="tabIndex"
		@change="tabChange">
			<swiper-item v-for="(items,index) in newslist" :key="index"> 
				<scroll-view scroll-y class="list" @scrolltolower="loadmore(index)">
					<template v-if="items.list.length>0">
						<!-- 话题列表 -->
						<view class="topic-view">
						<block v-for="(item,index1) in items.list" :key="index1">
							<topic-list :ischange="ischange" :item="item" :index="index1"></topic-list>
						</block>	
						</view>
						<!-- 上拉加载 -->
						<load-more :loadtext="items.loadtext"></load-more>
					</template>
					<template v-else-if="!items.firstload">
						<view style="font-size: 50upx;font-weight: bold;color: #CCCCCC;
						padding-top: 100upx;" class="u-f-ajc">Loading ...</view>
					</template>
					<template v-else>
						<!-- 无内容默认 -->
						<no-thing></no-thing>
					</template>
				</scroll-view>
			</swiper-item>
		</swiper>   
		</view>
		
		
	</view>
</template>

<script>
	import swiperTabHead from "../../components/index/swiper-tab-head.vue";
	import noThing from "../../components/common/no-thing.vue";
	import loadMore from "../../components/common/load-more.vue";
	import topicList from "../../components/news/topic-list.vue";
	export default {
		components:{
			swiperTabHead,
			noThing,
			loadMore,
			topicList
		},
		data() {
			return {
				ischange:false,
				swiperheight:500,
				tabIndex:0,
				tabBars:[],
				newslist:[],
			};
		},
		onLoad(e) {
			if (e.ischange) {
				this.ischange = true;
			}
			if(e.tabIndex){
				this.tabIndex = e.tabIndex;
			}
			uni.getSystemInfo({
				success: (res)=> {
					let height=res.windowHeight-uni.upx2px(100)
					this.swiperheight=height;
				}
			});
			this.getNav();
		},
		computed: {
			keepischange() {
				return this.ischange;
			}
		},
		methods:{
			// 监听搜索事件
			onNavigationBarSearchInputClicked() {
				uni.navigateTo({
					// url: '../search/search?searchType=topic&&ischange='+this.ischanges,
					url: `../search/search?searchType=topic&ischange=`+this.keepischange,
				});
			},
			// 获取文章分类
			async getNav(){
				let [err,res] =await this.$http.get('/topic/topicClass');
				if (!this.$http.errorCheck(err,res)) return;
				let list = res.data.obj;
				let arr = [];
				let arr2 = [];
				for (let i = 0; i < list.length; i++) {
					arr.push({
						id:list[i].id,
						name:list[i].classifyName
					})
					arr2.push({
						loadtext:"上拉加载更多",
						list:[],
						page:1,
						firstload:false
					});
				}
				this.tabBars = arr;
				this.newslist = arr2;
				this.tabBars.length > 0 && this.getList();
			},
			// 获取指定列表
			async getList(){
				let url = `/topic/topiclist?id=${this.tabBars[this.tabIndex].id}&&pageNum=${this.newslist[this.tabIndex].page}`;
				let [err,res] = await this.$http.get(url);
				let error = this.$http.errorCheck(err,res,()=>{
					this.newslist[this.tabIndex].loadtext="上拉加载更多";
				},()=>{
					this.newslist[this.tabIndex].loadtext="上拉加载更多";
				});
				if (!error) return;
				let arr = [];
				let list = res.data.obj.list;
				for (let i = 0; i < list.length; i++) {
					arr.push({
						id:list[i].id,
						titlepic:list[i].titlepic,
						title:list[i].title,
						desc:list[i].description,
						totalnum:list[i].totalNum,
						todaynum:list[i].todayNum,
					});
				}
				this.newslist[this.tabIndex].list = this.newslist[this.tabIndex].page > 1 ? this.newslist[this.tabIndex].list.concat(arr) : arr;
				this.newslist[this.tabIndex].firstload = true;
				if (list.length < 10) {
					this.newslist[this.tabIndex].loadtext="没有更多数据了";
				}else{
					this.newslist[this.tabIndex].loadtext="上拉加载更多";
				}
				return;
			},
			loadmore(index){
				if(this.newslist[index].loadtext!="上拉加载更多"){ return; }
				// 修改状态
				this.newslist[index].loadtext="加载中...";
				this.newslist[this.tabIndex].page++;
				this.getList();
			},
			// tabbar点击事件
			tabtap(index){
				this.tabIndex=index;
			},
			// 滑动事件
			tabChange(e){
				this.tabIndex=e.detail.current;
				this.getList();
			}
		}
	}
</script>

<style>
.topic-view{
	padding: 0 20upx;
}
</style>
