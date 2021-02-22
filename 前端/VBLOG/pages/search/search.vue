<template>
	<view>
		<template v-if="list.length>0">
			<block v-for="(item,index) in list" :key="index">
				<template v-if="searchType == 'article'">
					<index-list @changeevent="updateData" :item="item" :index="index"></index-list>
				</template>
				<template v-else-if="searchType == 'topic'">
					<view style="padding:0 20upx;">
						<!-- <topic-list :item="item" :index="index"></topic-list> -->
						<topic-list :ischange="keepischange" :fromsearch="fromsearch" :item="item" :index="index"></topic-list>
					</view>
				</template>
				<template v-else>
					<view style="padding:0 20upx;">
						<user-list :item="item" :index="index" hidden></user-list>
					</view>
				</template>
			</block>
			<load-more :loadtext="loadtext"></load-more>
		</template>
		<template v-else-if="issearch && list.length<1&&keepischange&&searchType=='topic'">
				<topic-list :ischange="keepischange" :fromsearch="fromsearch" :item="newTopicList"
				 :notfound="true" :index="newTopicList.id"></topic-list>
		</template>
		<template v-else-if="issearch && list.length<1">
				<no-thing></no-thing>
		</template>
	</view>
</template>

<script>
	import indexList from "../../components/index/index-list.vue";
	import noThing from "../../components/common/no-thing.vue";
	import loadMore from "../../components/common/load-more.vue";
	import topicList from "../../components/news/topic-list.vue";
	import userList from "../../components/user-list/user-list.vue";
	export default {
		components:{
			indexList,
			noThing,
			loadMore,
			topicList,
			userList
		},
		data() {
			return {
				// 标记是从search而来
				fromsearch:true,
				ischange:false,
				issearch:false,
				loadtext:"上拉加载更多",
				list:[],
				searchtext:"",
				page:1,
				searchType:"article",
				//为搜索到话题时渲染新话题的title
				newTopicList:{
					id:0,
					title:'',
					titlepic:"../../static/common/topic1.jpg",
					desc:"还没来的及描述~",
					totalnum:0,
					todaynum:0,
				},
			};
		},
		// 监听原生标题导航按钮点击事件(取消按钮)
		onNavigationBarButtonTap(e){
			if(e.index==0){
				uni.navigateBack({
					delta: 1
				});
			}
		},
		onNavigationBarSearchInputChanged(e) {
			// e.text;//每一个字都会被监听
			if(e.text){
				this.searchtext=e.text;
				this.newTopicList.title=e.text;
				this.getdata(e.text);
			}
			
		},
	
		// 监听页面触底事件
		onReachBottom() {
			this.loadmore();
		},
		// 监听下拉刷新
		onPullDownRefresh() {
			this.getdata();
			uni.stopPullDownRefresh();
		},
		onLoad(e) {
			console.log(JSON.stringify(e));
			console.log(e.searchType);
			console.log(e.ischange);
			if(e.ischange=='true'){
				this.ischange = true;
			}
			if (!e) return;
			this.searchType = e.searchType || 'article';
			// #ifdef APP-PLUS
			let pageTitle = '搜索文章';
			if (this.searchType == 'topic') {
				pageTitle = '搜索话题';
			} else if(this.searchType == 'user'){
				pageTitle = '搜索用户';
			}
			
			let currentWebview = this.$mp.page.$getAppWebview();  
			let tn = currentWebview.getStyle().titleNView;  
			tn.searchInput.placeholder = pageTitle; 
			currentWebview.setStyle({  
				titleNView: tn  
			});
			// #endif
			// 开启监听
			uni.$on('updateData',this.updateData);
		},
		computed: {
			keepischange() {
				return this.ischange;
			}
		},
		methods:{
			updateData(data){
				switch (data.type){
					case "guanzhu":
					this.updateGuanZhu(data)
						break;
					case "support":
					this.updateSupport(data);
						break;
					case 'updateComment':
					this.updateComment(data);
						break;
				}
			},
			// 更新评论数
			updateComment(data){
				// 拿到当前对象
				let obj = this.list.find(value =>{
					return value.id === data.post_id;
				});
				if (!obj) return;
				obj.commentnum++; // 评论数+1
			},
			// 更新顶踩数据状态
			updateSupport(data){
				// 拿到当前对象
				let obj = this.list.find(value =>{
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
			// 更新关注信息
			updateGuanZhu(data){
				this.list.forEach((item,index)=>{
					if (item.userid === data.userid) {
						item.isguanzhu = data.data;
					}
				})
			},
			// 搜索事件
			async getdata(searchtext){
				uni.showLoading({title:"Loading..."});
				let url = `/search/${this.searchType}`;
				console.log(url);
				let [err,res] =await this.$http.post(url,{
					keyWord:this.searchtext,
					pageNum:this.page
				},
				{
					header:{
						'Content-Type':'application/x-www-form-urlencoded',						
					},
				}
				);
				let error = this.$http.errorCheck(err,res,()=>{
					uni.hideLoading();
					this.issearch=true;
				},()=>{
					uni.hideLoading();
					this.issearch=true;
				});
				if (!error) return;
				let arr = [];
				let list = res.data.obj.list;
				for (let i = 0; i < list.length; i++) {
					arr.push(this.__format(list[i]));
				}
				this.list = this.page > 1 ? this.list.concat(arr) : arr;
				this.issearch = true;
				this.loadtext= list.length < 10 ? "没有更多数据了" : "上拉加载更多";
				uni.hideLoading();
			},
			// 上拉加载
			loadmore(){
				if(this.loadtext!="上拉加载更多"){ return; }
				// 修改状态
				this.loadtext="加载中...";
				this.page++;
				this.getdata(this.searchtext);
			},
			// 格式转化
			__format(item){
				switch (this.searchType){
					case "article":
					return this.User.__Articleformat(item);
						break;
					case "topic":
					return {
						id:item.id,
						titlepic:item.titlepic,
						title:item.title,
						desc:item.description,
						totalnum:item.totalNum,
						todaynum:item.todayNum,
					}
						break;
					case "user":
					return {
						id:item.id,
						userpic:item.userpic,
						username:item.username,
						age:item.userinfo.age,
						sex:item.userinfo.sex,
						isguanzhu:false
					}
						break;
				}
				
			},
		}
	}
</script>

<style>

</style>
