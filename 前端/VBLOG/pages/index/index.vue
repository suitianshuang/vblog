<template>
	<view>

		<swiper-tab-head :tabBars="tabBars" :tabIndex="tabIndex" @tabtap="tabtap">
		</swiper-tab-head>
		<view class="uni-tab-bar">
			<swiper class="swiper-box" :style="{height:swiperheight+'px'}" :current="tabIndex" @change="tabChange">
				<swiper-item v-for="(items,index) in newslist" :key="index">
					<scroll-view scroll-y class="list" @scrolltolower="loadmore(index)">
						<template v-if="items.list.length>0">
							<!-- 图文列表 -->
							<block v-for="(item,index1) in items.list" :key="index1">
								<index-list @changeevent="updateData" :item="item" :index="index1"></index-list>
							</block>
							<!-- 上拉加载 -->
							<load-more :loadtext="items.loadtext"></load-more>
						</template>
						<template v-else-if="!items.firstload">
							<view style="font-size: 50upx;font-weight: bold;color: #CCCCCC;
					padding-top: 100upx;" class="u-f-ajc">Loading
								...</view>
						</template>
						<template v-else>
							<!-- 无内容默认 -->
							<no-thing></no-thing>
						</template>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
		
		<!-- 操作菜单 -->
		<!-- <Calendar :show="show" :sendYear="2020" :sendMonth="4"   @hide="togleShow" ></Calendar> -->
		
	</view>
</template>


<script>
	import indexList from "../../components/index/index-list.vue";
	import swiperTabHead from "../../components/index/swiper-tab-head.vue";
	import loadMore from "../../components/common/load-more.vue";
	import noThing from "../../components/common/no-thing.vue";
	import Calendar from "../../components/index/Calendar.vue";
	export default {
		components: {
			indexList,
			swiperTabHead,
			loadMore,
			noThing,
			Calendar
		},
		data() {
			return {
				show:false,
				swiperheight: 500,
				tabIndex: 0,
				tabBars: [],
				newslist: [],
			}
		},
		onLoad() {
			uni.getSystemInfo({
				success: (res) => {
					let height = res.windowHeight - uni.upx2px(100)
					this.swiperheight = height;
				}
			});
			this.getNav();
			// 开启监听
			uni.$on('updateData',this.updateData);
		},
		// 监听搜索框点击事件
		onNavigationBarSearchInputClicked() {
			uni.navigateTo({
				url: '../search/search?searchType=article',
			});
		},
		// 监听原生标题导航按钮点击事件
		onNavigationBarButtonTap(e) {
			switch (e.index) {
				case 1:
					// 打开发布页面
					this.User.navigate({
						url: '../add-input/add-input?postclass=' + JSON.stringify(this.tabBars),
					})
					break;
				case 0:
				// 调用签到
				this.togleShow();
				this.sign();
				break;
			}
		},
		methods: {
			togleShow() {
				console.log(this.show);
				this.show = !this.show;
			},
			async sign(){
				console.log("签到");
				if(!this.User){
				return	uni.showToast({
						title: '还没有登录~',
						icon:"none"
					});
				}
				let userId = this.User.userinfo.id;
				let [err, res] = await this.$http.post('/sign/usersign',
				{userId:userId},
				{token:true});
				if (!this.$http.errorCheck(err, res)) return;
				
				console.log(res);
				
				uni.showToast({
					title: '现有飞吻'+res.data.obj.kissNum,
					icon:"success"
				});
				uni.$emit('updateData',{
					type:"changekissNum",
					data:res.data.obj.kissNum
				});
				
			},
			updateData(data){
				switch (data.type){
					case "guanzhu":
					this.updateGuanZhu(data);
						break;
					case "support":
					this.updateSupport(data);
						break;
					case "updateList":
					this.updateList(data);
						break;
					case 'updateComment':
					this.updateComment(data);
						break;
				}
			},
			// // 更新评论数
			updateComment(data){
				// 拿到当前对象
				let obj = this.newslist[this.tabIndex].list.find(value =>{
					return value.id === data.post_id;
				});
				
				if (!obj) return;
				obj.commentnum++; // 评论数+1
			},
			// 更新关注信息
			updateGuanZhu(data){
				this.newslist[this.tabIndex].list.forEach((item,index)=>{
					if (item.userid === data.userid) {
						item.isguanzhu = data.data;
					}
				})
			},
			// // 更新顶踩数据状态
			updateSupport(data){
				// 拿到当前对象
				let obj = this.newslist[this.tabIndex].list.find(value =>{
					return value.id === data.post_id;
				});
				if (!obj) return;
				let oldindex = obj.infonum.index; // 操作前的状态
				obj.infonum.index = (data.do == 'ding') ? 1 : 2; // 操作后的状态
				if (oldindex !== 0) { //之前操作过
					oldindex == 1 ? obj.infonum.dingnum-- : obj.infonum.cainum--;
				}
				if (obj.infonum.index !== 0) {  // 当前操作
					obj.infonum.index == 1 
						? obj.infonum.dingnum++ : obj.infonum.cainum++;
				}
			},
			// 更新用户发布信息
			updateList(data){
				
				// 第二种，重新加载当前post_class_id信息
				if(!isNaN(data)){
					this.tabIndex=data.data.obj-1;
				}
				this.getList();
				
			},
			// 获取文章分类
			async getNav() {
				let [err, res] = await this.$http.get('/article/articleClassify');
				if (!this.$http.errorCheck(err, res)) return;
				let list = res.data.obj;
				let arr = [];
				let arr2 = [];
				for (let i = 0; i < list.length; i++) {
					arr.push({
						id: list[i].id,
						name: list[i].classifyName
					})
					arr2.push({
						loadtext: "上拉加载更多",
						list: [],
						page: 1,
						firstload: false
					});
				}
				this.tabBars = arr;
				this.newslist = arr2;
				this.tabBars.length > 0 && this.getList();
			},
			// 获取指定列表
			async getList() {
				let currentIndex = this.tabIndex;
				let url =
					`/article/indexArticleInfo?id=${this.tabBars[this.tabIndex].id}&&pageNum=${this.newslist[this.tabIndex].page}`;
				let [err, res] = await this.$http.get(url, {});
				let error = this.$http.errorCheck(err, res, () => {
					this.newslist[currentIndex].loadtext = "上拉加载更多";
				}, () => {
					this.newslist[currentIndex].loadtext = "上拉加载更多";
				});
				if (!error) return;
				let arr = [];
				let list = res.data.obj.list;
				// list是一篇文章 ，下面对这篇文章对应的所有信息进行封装
				for (let i = 0; i < list.length; i++) {
					arr.push(this.User.__Articleformat(list[i]));
				}
				this.newslist[currentIndex].list = this.newslist[currentIndex].page > 1 ? this.newslist[currentIndex].list.concat(
					arr) : arr;
				this.newslist[currentIndex].firstload = true;
				if (list.length < 10) {
					this.newslist[currentIndex].loadtext = "没有更多数据了";
				} else {
					this.newslist[currentIndex].loadtext = "上拉加载更多";
				}
				return;
			},
			
			// 上拉加载
			loadmore(index) {
				if (this.newslist[index].loadtext != "上拉加载更多") {
					return;
				}
				// 修改状态
				this.newslist[index].loadtext = "加载中...";
				this.newslist[this.tabIndex].page++;
				this.getList();
			},
			// tabbar点击事件
			tabtap(index) {
				this.tabIndex = index;
			},
			// 滑动事件
			tabChange(e) {
				this.tabIndex = e.detail.current;
				this.getList();
			}
		},
	}
</script>

<style>

</style>
