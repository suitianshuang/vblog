<template>
	<view style="overflow: hidden;">
		<!-- 背景图 + 用户基本信息 -->
		<user-space-head :userinfo="userinfo" @update="updateGuanZhu"></user-space-head>
		<!-- 统计 -->
		<view class="user-space-data">
			<home-data :homedata="spacedata"></home-data>
		</view>
		<view style="height: 20upx; background: #F4F4F4;"></view>
		<!-- tab导航 -->
		<swiper-tab-head :tabBars="tabBars" :tabIndex="tabIndex" @tabtap="tabtap" scrollItemStyle="width:33.33%;" scrollStyle="border-bottom:0;">
		</swiper-tab-head>

		<block v-for="(item,index) in tablist" :key="index">
			<template v-if="tabIndex==0">
				<!-- 主页 -->
				<user-space-userinfo :userinfo="userinfo"></user-space-userinfo>
			</template>
			<template v-else-if="tabIndex==index">
				<template v-if="item.list.length > 0">
					<!-- 列表 -->
					<block v-for="(list,listindex) in item.list" :key="listindex">
						<common-list nonavigate  @changeevent="updateData" :item="list" :index="listindex"></common-list>
					</block>
					<!-- 上拉加载 -->
					<load-more :loadtext="item.loadtext"></load-more>
				</template>
				<template v-else-if="!item.firstload">
					<view style="font-size: 50upx;font-weight: bold;color: #CCCCCC;
					padding-top: 100upx;" class="u-f-ajc">Loading
						...</view>
				</template>
				<template v-else>
					<!-- 无内容默认 -->
					<no-thing></no-thing>
				</template>
			</template>
		</block>

		<!-- 操作菜单 -->
		<user-space-popup :show="show" :isblack="userinfo.isblack" :isme="userinfo.isme" @hide="togleShow" @lahei="lahei"
		 @chat="goToChat" @chongzhi="chongzhi"></user-space-popup>

	</view>
</template>

<script>
	import userSpaceHead from "../../components/user-space/user-space-head.vue";
	import homeData from "../../components/home/home-data.vue";
	import swiperTabHead from "../../components/index/swiper-tab-head.vue";
	import userSpaceUserinfo from "../../components/user-space/user-space-userinfo.vue";
	import commonList from "../../components/common/common-list.vue";
	import loadMore from "../../components/common/load-more.vue";
	import userSpacePopup from "../../components/user-space/user-space-popup.vue";
	import Time from "../../common/time.js";
	import noThing from "../../components/common/no-thing.vue"
	export default {
		components: {
			userSpaceHead,
			homeData,
			swiperTabHead,
			userSpaceUserinfo,
			commonList,
			loadMore,
			userSpacePopup,
			noThing
		},
		data() {
			return {
				show: false,
				userinfo: {
					bgimg: 1,
					userpic: "",
					username: "",
					sex: 0,
					age: 20,
					isguanzhu: false,
					regtime: "",
					id: 0,
					birthday: "",
					job: "",
					path: "",
					qg: ""
				},
				spacedata: [{
						name: "发布",
						num: 0
					},
					{
						name: "关注",
						num: 0
					},
					{
						name: "粉丝",
						num: 0
					},
				],
				tabIndex: 0,
				tabBars: [{
						name: "主页",
						id: "zhuye"
					},
					{
						name: "最热",
						id: "zuire"
					},
					{
						name: "最新",
						id: "zuixin"
					},
				],
				tablist: [{},
					{
						loadtext: "上拉加载更多",
						list: [],
						page: 1,
						firstload: false
					},
					{
						loadtext: "上拉加载更多",
						list: [],
						page: 1,
						firstload: false
					},
				]
			}
		},
		// 上拉触底事件
		onReachBottom() {
			// 上拉加载
			this.loadmore();
		},
		
		onNavigationBarButtonTap(e) {
			if (e.index == 0) {
				this.togleShow();
			}
		},
		onLoad(e) {

			this.__init(e.userid);
			// 开启监听
			uni.$on('updateData',this.updateData);
		},
		methods: {
			updateData(data){
				switch (data.type){
					
					case "support":
					this.updateSupport(data);
						break;
				}
			},
			 // 更新顶踩数据状态
			updateSupport(data){
				// 拿到当前对象
				let obj = this.tablist[this.tabIndex].list.find(value =>{
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
			__init(userid) {
				// 初始化用户信息
				this.getUserInfo(userid)
				// 初始化统计数据
				this.getCounts(userid)
			},
			// 初始化用户信息
			async getUserInfo(userid) {
				let sexArr = ['男', '女', '未知'];
				let qgArr = ['秘密', '未婚', '已婚'];
				// 用户本人
				let isme, info, isguanzhu, isblack;

				// 获取用户信息
				let [err, res] = await this.$http.get('/users/userinfo', {
					userId: userid,
					// 在后端查询出当前访问的这个用户粉丝列表里面有没有自己，有就把自己放到这个用户的fans数组里面
					visitId: this.User.userinfo.id
				})
				// 错误处理
				if (!this.$http.errorCheck(err, res)) return;
				info = res.data.obj.users;
				isme = false;
				isguanzhu = !!(res.data.obj.isfans == 1);
				isblack = !!(res.data.obj.isblack == 1);
				if (userid == this.User.userinfo.id) {
					isme = true;
					isguanzhu = false;
					isblack = false;
				}
				let regtime = info.createTime ? Time.gettime.dateFormat(new Date(info.createTime * 1000), '{Y}-{MM}-{DD}') : "未知";
				this.userinfo = {
					isme: isme,
					bgimg: 1,
					userpic: info.userpic,
					username: info.username,
					sex: (sexArr[info.userinfo.sex] == '女' ? 1 : 0),
					age: info.userinfo.age,
					isguanzhu: isguanzhu,
					isblack: isblack,
					regtime: regtime,
					id: info.id,
					birthday: info.userinfo.birthday || "未知",
					job: info.userinfo.job || "未知",
					path: info.userinfo.path || "未知",
					qg: qgArr[info.userinfo.qg] || '秘密'
				}
			},
			// 初始化统计数据
			async getCounts(id) {
				let counts;
				if (id == this.User.userinfo.id) {
					counts = this.User.counts;
				} else {
					let [err, res] = await this.$http.get(`/users/getcounts?userId=${id}`);
					if (!this.$http.errorCheck(err, res)) return;
					counts = res.data.obj;
				}
				if (counts) {

					this.spacedata[0].num = counts.articleCount;
					this.spacedata[1].num = counts.followCount;
					this.spacedata[2].num = counts.fensCount;
				}
			},
			// 更新关注状态
			updateGuanZhu(val) {
				this.userinfo.isguanzhu = val;
				// 通知全局
				let resdata = {
					type: "guanzhu",
					userid: this.userinfo.id,
					data: val
				};
				uni.$emit('updateData', resdata);
			},
			// 操作菜单显示隐藏
			togleShow() {
				this.show = !this.show;
			},
			// 拉黑

			async lahei() {
				uni.showLoading({
					title: 'Loading...',
					mask: false
				});
				let url = this.userinfo.isblack ? '/blackList/removeblacklist' : '/blackList/addblaclist';
				let msg = this.userinfo.isblack ? '移除黑名单' : '加入黑名单';
				let [err, res] = await this.$http.post(url, {
					blackId: this.userinfo.id,
					userId: this.User.userinfo.id,
					
				}, {
					token: true,
					checkToken: true
				});
				// 错误处理
				if (!this.$http.errorCheck(err, res)) {
					uni.hideLoading();
					return this.togleShow();
				}
				// 成功
				uni.hideLoading();
				uni.showToast({
					title: msg + '成功'
				});
				this.userinfo.isblack = !this.userinfo.isblack;
				this.togleShow();
			},
			// 聊天
			goToChat() {
				let obj = {
					userid: this.userinfo.id,
					userpic: this.userinfo.userpic,
					username: this.userinfo.username
				};
				this.User.navigate({
					url: "/pages/user-chat/user-chat?userinfo=" + JSON.stringify(obj)
				})
				this.togleShow()
			},
			// 上拉加载更多
			loadmore() {
				if (this.tablist[this.tabIndex].loadtext != "上拉加载更多") return;
				// 修改状态
				this.tablist[this.tabIndex].loadtext = "加载中...";
				this.tablist[this.tabIndex].page++;
				this.getList();
			},
			tabtap(index) {
				this.tabIndex = index;
				if (this.tablist[index].firstload || index === 0) return;
				this.getList();



			},
			// 获取列表
			async getList() {
				let page = this.tablist[this.tabIndex].page;
				let url = "";
				if (this.userinfo.isme && this.tabIndex == 1) {
					// 自己的最热
					url = `/user/me/hottestarticle?userId=${this.userinfo.id}&&pageNum=${page}`
				}
				if (this.userinfo.isme && this.tabIndex == 2) {
					url = `/user/me/newestarticle?userId=${this.userinfo.id}&&pageNum=${page}`
				}
				if (!this.userinfo.isme && this.tabIndex == 1) {
					url = `/user/others/hottestarticle?userId=${this.userinfo.id}&&pageNum=${page}`
				}
				if (!this.userinfo.isme && this.tabIndex == 2) {
					url = `/user/others/newestarticle?userId=${this.userinfo.id}&&pageNum=${page}`
				}
				let index = this.tabIndex;
				let [err, res] = await this.$http.get(url, {},
					// {
					// 	token: true,
					// 	checkToken: true
					// },
				 );
				if (!this.$http.errorCheck(err, res)) {
					return this.tablist[index].loadtext = "上拉加载更多";
				}
				let arr = [];
				let list = res.data.obj.list;
				for (let i = 0; i < list.length; i++) {
					arr.push(this.User.__Articleformat(list[i]));
				}
				this.tablist[index].list = page > 1 ? this.tablist[index].list.concat(arr) : arr;
				this.tablist[index].firstload = true;
				this.tablist[index].loadtext = list.length < 10 ? "没有更多数据了" : "上拉加载更多";
				return;
			},
			chongzhi(){
				console.log("充值");
			}

		}
	}
</script>

<style>
	.user-space-margin {
		margin: 15upx 0;
	}

	.user-space-data {
		background: #FFFFFF;
		position: relative;
		z-index: 10;
		border-top-left-radius: 20upx;
		border-top-right-radius: 20upx;
		margin-top: -15upx;
	}
</style>
