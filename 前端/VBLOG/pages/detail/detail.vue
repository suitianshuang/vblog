<template>
	<view>
		<detail-info :item="detail" @changeevent="updateData"></detail-info>
		<view class="u-comment-title">最新评论 {{detail.commentnum}}</view>
		<view class="uni-comment u-comment">
			<block v-for="(item,index) in comment.list" :key="index">
				<comment-list :item="item" :index="index" :isme="isme" :isaccepted="detail.isaccepted" @reply="reply" @caina="caina"></comment-list>
			</block>
		</view>
		<view style="height: 120upx;"></view>
		<!-- 输入框 -->
		<user-chat-bottom @submit="submit" :userpic="userpic" :focus="focus" @blur="blur"></user-chat-bottom>
	</view>
</template>
<script>
	import detailInfo from "../../components/detail/detail-info.vue";
	import time from "../../common/time.js";
	import commentList from "../../components/detail/comment-list.vue";
	import userChatBottom from "../../components/user-chat/user-chat-bottom.vue";
	export default {
		components: {
			detailInfo,
			commentList,
			userChatBottom,
		},
		data() {
			return {
				userpic:'',
				focus: false,
				reply_id: 0, // 回复id
				// 分享内容
				sharedata: {
					title: "",
					url: "",
					titlepic: "",
					shareType: 0,
				},
				shareshow: false,
				comment: {
					count: 0,
					list: []
				},
				detail: {
					id: "",
					userid: "",
					userpic: "",
					username: "",
					sex: 0, //0 男 1 女
					age: 0,
					content: "",
					isguanzhu: false,
					title: "",
					titlepic: "",
					morepic: [],
					video: false,
					share: false,
					path: "",
					sharenum: 0,
					commentnum: 0,
					goodnum: 0,
					cainum: 0,
					create_time: 0,
					kissNum: 0, //赏金
					isaccepted: 0 //默认为0 未被采纳

				},
			}
		},
		onLoad(e) {
			this.initdata(JSON.parse(e.detailData));
			let userpic = this.User.userinfo.userpic;
			this.userpic = userpic;
		},
		// 监听导航右边按钮
		onNavigationBarButtonTap(e) {
			if (e.index == 0) {
				let arr = [];
				this.isme ? arr.push('分享', '删除') : arr.push('分享');
				uni.showActionSheet({
					itemList: arr,
					success: (res) => {
						// 收藏 ，调用收藏的接口
						if (res.tapIndex == 0) {
							console.log("分享");
						} else {
							uni.showModal({
								title: "确认删除?",
								content: "删除后无法撤销~",
								confirmText: '是',
								confirmColor: '#be0000',
								cancelText: '算了',
								cancelColor: '#00CA94',
								success: (res) => {
									if (res.confirm) {
										console.log("删除");
										this.deleteArticle();
									}
									return;
								}
							})
						}

					}

				});
			}
		},
		computed: {
			isme() {
				if (this.detail.userid === this.User.userinfo.id) {
					return true;
				}
				return false;
			}
		},
		methods: {
			updateData(data) {
				switch (data.type) {
					case "guanzhu":
						this.updateGuanZhu(data);
						break;
					case "support":
						this.updateSupport(data);
						break;
				}
			},
			updateGuanZhu(data) {
				this.detail.isguanzhu = data.data;
			},
			updateSupport(data) {
				if (data.do == 'ding') {

					this.detail.infonum.index = 1;
					this.detail.goodnum++;
				}
			},
			// 初始化数据
			initdata(obj) {
				// 修改窗口标题
				uni.setNavigationBarTitle({
					title: obj.title
				});
				uni.showLoading({
					title: 'Loading...',
					mask: true
				});
				let images = [];
				for (let i = 0; i < obj.image.length; i++) {
					images.push(obj.image[i].url);
				}
				this.detail.id = obj.id;
				this.detail.morepic = images;
				this.detail.userid = obj.userid,
					this.detail.goodnum = obj.infonum.dingnum;
				this.detail.cainum = obj.infonum.cainum;
				this.detail.content = obj.content;
				this.detail.userpic = obj.userpic;
				this.detail.username = obj.username;
				this.detail.sex = obj.userinfo.sex;
				this.detail.age = obj.userinfo.age;
				this.detail.content = obj.content;
				this.detail.isguanzhu = obj.isguanzhu;
				this.detail.path = obj.path;
				this.detail.sharenum = obj.sharenum;
				this.detail.commentnum = obj.commentnum;
				this.detail.create_time = obj.createTime;
				this.detail.kissNum = obj.kissNum;
				this.detail.isaccepted = obj.isaccepted;
				// 判断有没有评论
				if (this.detail.commentnum) {
					this.getcomment();
				}
				return uni.hideLoading();


			},

			// 发布评论
			async submit(data) {
				uni.showLoading({
					title: '评论中...',
					mask: false
				});
				let reply_id = this.reply_id;
				let [err, res] = await this.$http.post('/comment/insertComment', {
					userId: this.User.userinfo.id,
					articleId: this.detail.id,
					parentId: reply_id,
					message: data
				}, {
					token: true,
				});
				// 错误处理
				if (err || res.data.errorMsg) {
					let msg = res.data.errorMsg ? res.data.errorMsg : '发送失败，请检查网络~';
					uni.hideLoading();
					return uni.showToast({
						title: msg,
						icon: "none"
					});
				}
				// 发送成功
				uni.hideLoading();
				uni.showToast({
					title: "发送成功~"
				});
				// 通知首页更新评论数，通知会员中心更新评论数
				uni.$emit('updateData', {
					type: 'updateComment',
					post_id: this.detail.id
				});
				let result = {
					// 拿到刚才评论的id ,不需要刷新界面
					id: res.data.obj,
					fid: reply_id,
					userpic: this.User.userinfo.userpic,
					username: this.User.userinfo.username,
					time: time.gettime.gettime(new Date().getTime()),
					data: data,
				};
				// 评论数+1
				this.comment.count++;
				this.detail.commentnum++;
				// 一级评论
				if (reply_id === 0) {
					return this.comment.list.push(result);
				}
				// 二级评论
				// 找出被评论id的索引
				let index = this.comment.list.findIndex((val) => {
					return val.id === reply_id;
				});

				if (index > -1) {
					// 中间插入
					this.comment.list.splice(index + 1, 0, result);
				}

			},
			// 获取评论
			async getcomment() {
				let url =
					`/comment/commentList?articleId=${this.detail.id}`;
				let [err, res] = await this.$http.get(url, {});
				if (!this.$http.errorCheck(err, res)) return;
				let list = res.data.obj;
				this.comment.list = this.comment.list.concat(this.__ArrSort(list));
			},
			__ArrSort(arr, id = 0) {
				var temp = [],
					lev = 0;
				var forFn = function(arr, id, lev) {
					for (var i = 0; i < arr.length; i++) {
						var item = arr[i];
						if (item.parentId == id) {
							item.lev = lev;
							temp.push({
								id: item.id,
								fid: item.parentId,
								userid: item.userId,
								userpic: item.users.userpic,
								username: item.users.username,
								time: time.gettime.gettime(item.createTime),
								data: item.message,
							});
							forFn(arr, item.id, lev + 1);
						}
					}
				};
				forFn(arr, id, lev);
				return temp;
			},
			reply(item) {
				this.reply_id = item;
				this.focus = true;
			},
			blur() {
				this.focus = false;
				// 因为我们目前的测试环境是 开发者工具 不是手机  ，所以他会失去焦点
				// this.reply_id = 0;
			},

			async caina(userid) {
				if (!this.User) {
					return;
				}
				console.log(userid);
				console.log(this.detail.kissNum);
				console.log(this.detail.id);

				let [err, res] = await this.$http.post("/comment/accept", {
					userId: this.User.userinfo.id,
					acceptedUserId: userid,
					rewardNum: this.detail.kissNum,
					articleId: this.detail.id,
				}, )
				if (!this.$http.errorCheck(err, res)) return;

				// 采纳成功
				// 修改当前文章的 isaccepted
				if (res.data.obj == 1) {
					this.detail.isaccepted = res.data.obj;
					return uni.showToast({
						title: '采纳成功',
						icon: "success"
					});
				}
			},
			async deleteArticle() {

				// userId，articleId，token
				let [err, res] = await this.$http.post('/article/delete', {
					userId: this.User.userinfo.id,
					articleId: this.detail.id
				}, {
					token: true
				})
				if (!this.$http.errorCheck(err, res)) return;
				// 删除成功 ，返回上一步
				uni.showToast({
					title: '删除成功',
					icon: "none"
				})
				uni.$emit('updateData',{
					type:"updateList"
				});
				return uni.navigateBack({
					delta: 1
				});
			},
			async collection() {
				let [err, res] = await this.$http.post('/collection/article', {
					userId: this.User.userinfo.id,
					articleId: this.detail.id,

				}, {
					token: true
				})
				if (!this.$http.errorCheck(err, res)) return;
				return uni.showToast({
					title:'收藏好了~',
					icon:'none'
				})
			}
		}
	}
</script>

<style>
	/* 评论 */
	.u-comment {
		padding: 0 20upx;
	}

	.u-comment-title {
		padding: 20upx;
		font-size: 30upx;
		font-weight: bold;
	}
</style>
