import $http from "./request.js";
import time from "./time.js";
import $chat from "./chat.js";
import config from "./config.js";

export default {
	baseUrl: config.webUrl,
	// 用户token 测试token：4cd36bf70649475ac0cd6fae78250954474a4350
	token: false,
	// 用户信息
	userinfo: false,
	// 用户相关统计
	counts: {},
	// 初始化
	__init() {
		// 获取用户信息
		this.userinfo = uni.getStorageSync("userinfo");
		this.counts = uni.getStorageSync("counts");
		this.token = uni.getStorageSync("token");
		this.OnUserCounts();
		// 如果当前用户的id存在 就连接socket
		if (this.userinfo.id) {
			// 连接socket
			$chat.Open();
		}
	},

	// 自定义权限验证跳转
	navigate(options, type = "navigateTo") {
		// 是否登录验证
		if (!$http.checkToken(true)) return;
		// 跳转
		switch (type) {
			case "navigateTo":
				uni.navigateTo(options);
				break;
			case "redirectTo":
				uni.redirectTo(options);
				break;
			case "reLaunch":
				uni.reLaunch(options);
				break;
			case "switchTab":
				uni.switchTab(options);
				break;
		}
	},
	// 登录
	async login(options = {}) {
		uni.showLoading({
			title: '登录中...',
			mask: true
		});
		// 请求登录
		let [err, res] = await $http.post(options.url, options.data);
		// 登录失败
		if (!$http.errorCheck(err, res)) {
			uni.hideLoading();
			return false;
		}
		// console.log(JSON.stringify(res));
		// 登录成功 保存状态
		this.token = res.data.token;
		this.userinfo = this.__formatUserinfo(res.data);
		// 本地存储
		uni.setStorageSync("token", this.token);
		// console.log(this.token);
		uni.getLocation({
			type: 'gcj02',
			geocode: true,
			success: (res) => {
				console.log(res);
				console.log(res.address);
			}
		});
		console.log("获取地理位置");
		uni.setStorageSync("userinfo", this.userinfo);
		// console.log(this.userinfo);
		// 获取用户相关统计
		await this.getCounts();
		// 连接socket
		$chat.Open();
		// 成功提示
		uni.hideLoading();
		uni.showToast({
			title: '登录成功'
		});
		// 返回上一步
		if (!options.Noback) {
			uni.navigateBack({
				delta: 1
			});

		}
		return true;
	},
	// 退出登录
	async logout(showToast = true) {
		// 退出登录
		// await $http.post('/user/logout',{},{ 
		// 	token:true,
		// 	checkToken:true ,
		// });
		// 清除缓存
		uni.removeStorageSync('userinfo');
		uni.removeStorageSync('token');
		uni.removeStorageSync('counts');
		// 清除状态
		this.token = false;
		this.userinfo = false;
		this.counts = {};
		// 关闭socket
		$chat.Close();
		// 返回home页面
		uni.switchTab({
			url: "/pages/home/home"
		})
		// 退出成功
		if (showToast) {
			return uni.showToast({
				title: '退出登录成功'
			});
		}
	},
	// 获取用户相关统计信息
	async getCounts() {
		// 统计获取用户相关数据（总文章数，今日文章数，评论数 ，关注数，粉丝数，文章总点赞数）
		let url = `/users/getcounts?userId=${this.userinfo.id}`
		let [err, res] = await $http.get(url, {
			token: true,
			checkToken: true
		})
		// 请求错误处理
		if (!$http.errorCheck(err, res)) return;
		// 成功
		this.counts = res.data.obj;
		// 存储缓存
		uni.setStorageSync("counts", this.counts);
	},
	// 监听用户统计变化
	OnUserCounts() {
		uni.$on('updateData', (data) => {
			// 文章数+1
			if (data.type == 'updateList') {
				this.counts.articleCount++;
				this.counts.todayArticleCount++;
			}
			// 评论数+1
			if (data.type == 'updateComment') {
				this.counts.commentCount++;
			}
			// // 粉丝数和关注数变化
			if (data.type == 'guanzhu') {

				data.data ?
					this.counts.fensCount++
					:
					this.counts.fensCount--;
			}
			if (data.type == 'updatekissNum') {
				console.log(data);
				this.counts.kissNum = this.counts.kissNum - data.data;
			}
			if (data.type == 'changekissNum') {
				console.log(data);
				this.counts.kissNum = data.data;
			}

			// 更新缓存
			uni.setStorageSync("counts", this.counts);
		})
	},
	// 加入浏览历史
	addHistoryList(item) {
		// 取出缓存
		let list = uni.getStorageSync('HistoryList_' + this.userinfo.id);
		list = list ? JSON.parse(list) : [];
		// 查看当前记录是否存在,存在就不要继续添加了
		let index = 0;
		if (list) {
			index = list.findIndex((val) => {
				return val.id === item.id;
			})
		}
		// 不存在直接追加
		if (index == -1) {
			list.push(item);
			uni.setStorageSync('HistoryList_' + this.userinfo.id, JSON.stringify(list))
		}
	},
	// 清除浏览历史
	clearHistory() {
		uni.removeStorageSync('HistoryList_' + this.userinfo.id);
	},

	// userinfo格式转换
	__formatUserinfo(item) {

		if (item) {
			return {
				id: item.user.id,
				username: item.user.username,
				// userpic:baseUrl+'/'+item.user.userpic,
				userpic:item.user.userpic,
				email: item.user.email,
				createTime: item.user.createTime,
				password: item.user.password,
				token: item.token,
				userinfo: {
					id: item.user.userinfo.id,
					user_id: item.user.userinfo.userId,
					age: item.user.userinfo.age,
					sex: item.user.userinfo.sex,
					qg: item.user.userinfo.emotion,
					job: item.user.userinfo.job,
					path: item.user.userinfo.hometown,
					birthday: item.user.userinfo.birthday,

				}
			}
		}
	},

	__Articleformat(item) {
		// 在support对象数组中找到当前用户的type
		// 如果找到了就把type给前端
		console.log(item);
		let supportType = 0;
		if (item.support !== null) {
			for (let i = 0; i < item.support.length; i++) {
				if (item.support[i].userId == this.userinfo.id) {
					supportType = item.support[i].type + 1;
				}
			}
		}

		// 在fens对象数组中找当前用户的id
		// 判断当前用户有没有关注遍历的文章作者
		let isguanzhu = false;
		if (item.users !== null) {
			for (let i = 0; i < item.users.fans.length; i++) {
				if (item.users.fans[i].userId == this.userinfo.id) {
					isguanzhu = true;
				}
			}
		}

		let arr3 = [];
		for (let i = 0; i < item.images.length; i++) {
			arr3[i] = item.images[i]
		}
		// for (var i = 0; i < arr3.length; i++) {
		// 	// console.log(arr3[i]);
		// }
		// if (!item.titlepic) {
		// 	return {
		// 		userid: item.users.id,
		// 		userinfo: item.userinfo,
		// 		userpic: item.users.userpic,
		// 		username: item.users.username,
		// 		isguanzhu: isguanzhu,
		// 		id: item.id,
		// 		title: item.title,
		// 		type: "img", // img:图文,video:视频
		// 		image: arr3,
		// 		content: item.content,
		// 		video: false,
		// 		path: item.location,
		// 		share: !!item.share,
		// 		infonum: {
		// 			index: (item.support.length > 0) ? supportType : 0, //0:没有操作，1:顶,2:踩；            
		// 			dingnum: item.dingCount,
		// 			cainum: item.caiCount,
		// 		},
		// 		createTime: time.gettime.gettime(item.createTime),
		// 		commentnum: item.commentCount,
		// 		sharenum: item.sharenum,
		// 		pageviewNum: item.pageviewNum,
		// 		userinfo: {
		// 			id: item.users.userinfo.id,
		// 			userid: item.users.userinfo.userid,
		// 			sex: item.users.userinfo.sex,
		// 			age: item.users.userinfo.age,
		// 			job: item.users.userinfo.job,
		// 			emotion: item.users.userinfo.emotion,
		// 			birthday: item.users.userinfo.birthday,
		// 			hometown: item.users.userinfo.hometown,

		// 		}
		// 	}
		// }

		return {
			userid: item.users.id,
			userpic: item.users.userpic,
			username: item.users.username,
			isguanzhu: isguanzhu,
			id: item.id,
			title: item.title,
			pageviewNum: item.pageviewNum,
			type: "img", // img:图文,video:视频
			titlepic: item.titlepic,
			content: item.content,
			image: arr3,
			video: false,
			path: item.location,
			kissNum: item.rewardNum,
			// share:!!item.share,
			isaccepted: item.isAccepted,
			infonum: {
				index: (item.support.length > 0) ? supportType : 0, //0:没有操作，1:顶,2:踩；            
				dingnum: item.dingCount,
				cainum: item.caiCount,
			},
			userinfo: {
				id: item.users.userinfo.id,
				userid: item.users.userinfo.userid,
				sex: item.users.userinfo.sex,
				age: item.users.userinfo.age,
				job: item.users.userinfo.job,
				emotion: item.users.userinfo.emotion,
				birthday: item.users.userinfo.birthday,
				hometown: item.users.userinfo.hometown,
			},
			createTime: time.gettime.gettime(item.createTime),
			commentnum: item.commentCount,
			sharenum: item.sharenum,
		}
	},
}
