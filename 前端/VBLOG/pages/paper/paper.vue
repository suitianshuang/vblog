<template>
	<view class="body">
		
		<!-- 导航 -->
		<!-- #ifndef APP-PLUS -->
		<uni-nav-bar :shadow="true" :border="false"
		@click-left="clickLeft" @click-right="clickRight" title="消息">
			<!-- 左边图标 -->
			<block slot="left">
				<view class="iconfont icon-user-detail" style="font-size: 22px;margin-left: 20upx;"></view>
			</block>
			<!-- 右边图标 -->
			<block slot="right">
				<view class="icon iconfont icon-zengjia" style="font-size: 22px;"></view>
			</block>
		</uni-nav-bar>
		<!-- #endif -->
		
		<paper-left-popup 
		:show="show"
		@hide="hidepopup"
		@addfriend="addfriend"
		@clear="clear">
		</paper-left-popup>
		<!-- <template v-show=" arr.length>0 ">
			<block v-for="(item,index) in arr" :key="item.name">
				<paper-head-list :item="item" :index="index"></paper-head-list>
			</block>
		</template> -->
		
		<!-- 小纸条列表 -->
		<template v-if=" list.length > 0 ">
			<block v-for="(item,index) in list" :key="index">
				<paper-list :item="item" :index="index"></paper-list>
			</block>
		</template>
		<template v-else-if="!firstload">
			<view style="font-size: 50upx;font-weight: bold;color: #CCCCCC;
			padding-top: 100upx;" class="u-f-ajc">Loading ...</view>
		</template>
		<template v-else>
			<!-- 无内容默认 -->
			<no-thing></no-thing>
		</template>
	</view>
</template>

<script>
	// #ifndef APP-PLUS
	import uniNavBar from "../../components/uni-nav-bar/uni-nav-bar.vue";
	// #endif
	import paperList from "../../components/paper/paper-list.vue";
	import paperLeftPopup from "../../components/paper/paper-left-popup.vue";
	import Time from "../../common/time.js";
	import noThing from "../../components/common/no-thing.vue";
	import  paperHeadList from "../../components/paper/paper-head-list.vue";
	export default {
		components:{
			paperList,
			paperLeftPopup,
			noThing,
			// #ifndef APP-PLUS
			uniNavBar,
			// #endif
			paperHeadList
		},
		data() {
			return {
				firstload:false,
				show:false,
				list:[],
				arr:[
					{name:"赞",namepic:"../../static/common/dianzan.png",noreadnum:1},
					{name:"评论",namepic:"../../static/common/comment.png",noreadnum:1},
					
				],
			};
		},
		
		// 监听导航按钮点击事件
		onNavigationBarButtonTap(e) {
			switch (e.index){
				case 0:
				this.User.navigate({
					url: '../user-list/user-list',
				})
				this.hidepopup();
					break;
				case 1:
				this.showpopup();
					break;
			}
		},
		onShow() {
			// 获取列表
			this.getdata();
		},
		onLoad() {
			// 开启监听
			uni.$on('UserChat',(data)=>{
				// 置顶更新
				let index = this.list.findIndex((val)=>{
					return val.userid === data.fromId;
				});
				// 会话存在
				if (index!==-1) {
					this.list[index].data = data.data;
					this.list[index].time = Time.gettime.gettime(data.time);
					this.list[index].noreadnum++;
					// 置顶
					this.list = this.$chat.__toFirst(this.list,index)
					return;
				}
				// 追加
				let obj = this.$chat.__format(data,{ type:"chatlist" });
				// 格式化时间
				obj.time = Time.gettime.gettime(obj.time);
				obj.noreadnum = 1;
				this.list.unshift(obj);
			})
		},
		methods:{
			// #ifndef APP-PLUS
			clickLeft(){
				this.User.navigate({
					url: '../user-list/user-list',
				})
				this.hidepopup();
			},
			clickRight(){
				this.showpopup();
			},
			// #endif
			// 操作菜单
			addfriend(){
				this.User.navigate({
					url: '../search/search?searchType=user',
				})
				this.hidepopup();
			},
			clear(){
				if (this.User.userinfo.id) {
					uni.removeStorageSync('noreadnum'+this.User.userinfo.id)
					uni.removeStorageSync('chatlist'+this.User.userinfo.id)
					this.$chat.initTabbarBadge();
					this.getdata();
					uni.showToast({
						title: '清除成功'
					});
				}
				this.hidepopup();
			},
			hidepopup(){
				this.show=false;
			},
			showpopup(){
				this.show=true;
			},
			// 获取数据
			getdata(){
				try{
					let userid = this.User.userinfo.id;
					if (!userid){
						this.firstload = true;
						return uni.stopPullDownRefresh();
					} 
					this.list = [];
					let list = uni.getStorageSync('chatlist'+this.User.userinfo.id);
					list = list ? JSON.parse(list) : [];
					for (var i = 0; i < list.length; i++) {
						list[i].time = Time.gettime.gettime(list[i].time);
					}
					this.list = list;
					this.firstload = true;
				}catch(e){
					return uni.stopPullDownRefresh();
				}
				uni.stopPullDownRefresh();
			}
		}
	}
</script>

<style>
.body{
	padding: 0 20upx;
}
</style>
