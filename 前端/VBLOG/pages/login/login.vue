<template>
	<view>
		<!-- 状态栏 -->
		<uni-status-bar bgcolor="#00aa7f"></uni-status-bar>
		<!-- 关闭按钮 -->
		<view class="icon iconfont icon-guanbi" @tap="back"></view>
		<!-- 引入背景图 -->
		<image class="loginhead" src="../../static/bgimage/2.jpg" mode="widthFix" lazy-load></image>
		<!-- 输入框+按钮 -->
		<view class="body">

			<!-- 用户名和密码登录 -->
			<template v-if="!status">
				<input type="text" v-model="username" class="uni-input common-input" placeholder="用户名" />

				<view class="login-input-box">
					<input type="text" v-model="password" password class="uni-input common-input forget-input" placeholder="请输入密码" />
					<view class="forget u-f-ajc login-font-color" @tap="forgetPassword">忘记密码?</view>
				</view>
				<button class="user-set-btn" :loading="loading" :class="{'user-set-btn-disable':disabled}" type="default" @tap="login"
				 :disabled="disabled">登录</button>
			</template>


			<!-- 注册 -->
			<template v-else>
				<view class="login-input-box">
					<input type="text" v-model="email" class="uni-input common-input" placeholder="邮箱" />
				</view>
				<view class="login-input-box">
					<input type="text" v-model="checknum" class="uni-input common-input forget-input" placeholder="请输入验证码" />
					<view class="forget u-f-ajc login-font-color " @tap="getCheckNum">
						<view class="u-f-ajc yanzhengma">{{!codetime?'获取验证码':codetime+' s'}}</view>
					</view>
				</view>
				<view class="login-input-box">
					<input type="text" v-model="username" class="uni-input common-input" placeholder="设置用户名,用于登录" />
					<input type="text" v-model="password" password class="uni-input common-input " placeholder="设置密码" />
				</view>
				<button class="user-set-btn" :loading="loading" :class="{'user-set-btn-disable':disabled}" type="default" @tap="register"
				 :disabled="disabled">注册</button>
			</template>
		</view>

		<!-- 登录状态切换 -->
		<view class="login-status u-f-ajc login-padding login-font-color" @tap="changeStatus">
			{{status?'登录':'注册'}}
			<view class="icon iconfont icon-jinru login-font-color"></view>
		</view>
		<!-- 协议 -->
		<view class="login-rule u-f-ajc login-padding login-font-color">
			注册即代表您同意<view>《XXX协议》</view>
		</view>

	</view>
</template>

<script>
	import uniStatusBar from "../../components/uni-status-bar/uni-status-bar.vue";
	export default {
		components: {
			uniStatusBar,
		},
		data() {
			return {
				status: false, //false代表用户名密码登录  true代表邮箱注册
				disabled: true,
				loading: false,
				username: "李秀丽",
				password: "123456",
				email: "",
				checknum: "",
				codetime: 0,
				userinfo: ''
			}
		},
		watch: {
			username(val) {
				this.OnBtnChange();
			},
			password(val) {
				this.OnBtnChange();
			},
			email(val) {
				this.OnBtnChange();
			},
			checknum(val) {
				this.OnBtnChange();
			}
		},
		methods: {
			// 验证邮箱号码
			checkemail() {
				if (!this.isemail()) {
					return uni.showToast({
						title: '请输入正确的邮箱号码',
						icon: "none"
					});
				}
			},
			isemail() {
				let mPattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
				return mPattern.test(this.email);
			},
			// 获取验证码
			async getCheckNum() {
				if (this.codetime > 0) return;
				// 验证邮箱号合法性
				if (!this.isemail()) {
					return uni.showToast({
						title: '请输入正确的邮箱号码',
						icon: "none"
					});
				}
				// 请求服务器，发送验证码
				uni.showLoading({
					title: "发送中..."
				});
				let url = '/users/code';
				let [err, res] = await this.$http.post(url, {
					email: this.email
				});
				// 错误处理
				if (!this.$http.errorCheck(err, res)) {
					uni.hideLoading();
				}
				// 成功
				uni.hideLoading();
				uni.showToast({
					title: '验证码发送中',
					icon:"success"
				});
				// 设置倒计时
				this.codetime = 120;
				let timer = setInterval(() => {
					this.codetime--;
					if (this.codetime < 1) {
						clearInterval(timer);
						this.codetime = 0;
					}
				}, 1000);
			},
			// 初始化表单
			initInput() {
				this.username = '';
				this.password = '';
				this.email = '';
				this.checknum = '';
			},
			// 改变按钮状态
			OnBtnChange() {
				if ((this.username && this.password) || (this.email && this.checknum && this.username && this.password)) {
					this.disabled = false;
					return;
				}
				this.disabled = true;
			},
			// 切换登录状态
			changeStatus() {
				this.initInput();
				this.status = !this.status;
			},
			// 返回上一步
			back() {
				uni.navigateBack({
					delta: 1
				});
			},
			// 提交登录
		 async login() {
				
				let result = await this.User.login({
					url: "/users/login",
					data: {
						username: this.username,
						password: this.password
					},
				})
			},
			
			async register() {
				if (!this.isemail()) {
					return uni.showToast({
						title: '请输入正确的邮箱',
						icon: "none"
					});
				}
				let [err, res] = await this.$http.post('/users/register', {
					email: this.email,
					code: this.checknum,
					username:this.username,
					password: this.password
				});
				if (!this.$http.errorCheck(err, res)) return;
			},

			forgetPassword() {
				console.log("忘记密码");
			}
		}
	}
</script>

<style>
	@import "../../common/form.css";

	.login-font-color {
		color: #BBBBBB;
	}

	.login-padding {
		padding: 20upx 0;
	}

	.icon-guanbi {
		position: fixed;
		top: 60upx;
		left: 30upx;
		font-size: 40upx;
		font-weight: bold;
		color: #332F0A;
		z-index: 100;
	}

	.loginhead {
		width: 100%;
		height: 600upx !important;
	}

/* 	.other-login-title {
		position: relative;
	}

	.other-login-title:before,
	.other-login-title:after {
		content: "";
		position: absolute;
		background: #BBBBBB;
		height: 1upx;
		width: 100upx;
		top: 50%;
	}

	.other-login-title:before {
		left: 25%;
	}

	.other-login-title:after {
		right: 25%;
	}
 */
	.login-input-box {
		position: relative;
	}

	.login-input-box .forget-input {
		padding-right: 150upx;
	}

	.login-input-box .forget,
	.login-input-box .email {
		position: absolute;
		top: 0;
		height: 100%;
		z-index: 100;
	}

	.login-input-box .forget {
		width: 150upx;
		right: 0;
	}

	.login-input-box .email {
		width: 100upx;
		left: 0;
		font-weight: bold;
	}

	.login-input-box .email-input {
		padding: 0upx 10upx;
	}

	.yanzhengma{
		background: #afafaf;
		border-radius: 10upx;
		color: #171606;
		font-size: 25upx;
		width: 150upx;
		padding: 10upx 0;
	}
	
</style>
