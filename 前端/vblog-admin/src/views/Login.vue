<template>
	<div>
		<el-form 
		v-loading="loading" 
		element-loading-text="登录中..."
		 element-loading-spinner="el-icon-loading"
		 element-loading-background="rgba(0, 0, 0, 0.8)" :rules="rules" ref="loginForm" :model="loginForm" class="loginClass">
			<h3>VBLOG系统登录</h3>
			<el-form-item prop="username">
				<el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名..."></el-input>
			</el-form-item>
			<el-form-item prop="password">
				<el-input type="text"  v-model="loginForm.password" auto-complete="off" show-password placeholder="请输入密码..."
				 @keydown.enter.native="submitForm"></el-input>
			</el-form-item>
			<el-form-item>
				<el-checkbox v-model="checked"></el-checkbox>
				<el-button type="primary" style="width: 100%;" @click="submitForm">登录</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name: "Login",
		data() {
			return {
				loading:false,
				checked: true,
				loginForm: {
					username: 'admin',
					password: '123',
				},
				rules: {
					username: [{
						required: true,
						message: "请输入用户名",
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: "请输入密码",
						trigger: 'blur'
					}],
				}
			}
		},
		methods: {
			submitForm(formName) {
				this.$refs.loginForm.validate((valid) => {
					if (valid) {
						this.loading = true;
						this.postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
							this.loading = false;
							if (resp) {
								window.sessionStorage.setItem("user", JSON.stringify(resp.obj))
								this.$store.commit('INIT_CURRENTADMIN', resp.obj);
								let path = this.$route.query.redirect;
								this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
							}
						})
					} else {
						this.$message.error("请输入所有字段！")
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		},
	}
</script>

<style scoped>
	h3 {
		text-align: center;
		color: #00aaff;
	}

	.loginClass {
		border-radius: 15px;
		background-clip: padding-box;
		width: 400px;
		margin: 180px auto;
		padding: 15px 35px 15px 35px;
		background: #fff;
		border: #eaeaea;
		box-shadow: 0 0 25px #cbcbcb;
	}
</style>
