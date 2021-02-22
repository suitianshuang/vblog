<template>
	<div>
		<el-container>
			<el-header class="home-header">
				<div class="title">VBLOG后台管理</div>
				<div>
					<el-button @click="goChat" icon="el-icon-bell" type="text" size="medium" style="margin-right: 15px; color:#ffffff;font-size: 25px;"></el-button>
					<el-dropdown class="dropdown" @command="commandHandler">
						<span class="el-dropdown-link">
							<span class="welcome">欢迎 {{user.name}} 登录</span><i><img :src="user.adminPic" height="40px" width="40px" style="border-radius: 100%; margin-left: 18px;" /></i>
						</span>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item style="margin: 0 20px;" command="userinfo">个人中心</el-dropdown-item>
								<el-dropdown-item style="margin: 0 20px;" command="logout">注销登录</el-dropdown-item>
							</el-dropdown-menu>
						
					</el-dropdown>
				</div>
				
			</el-header>
			<el-container>
				<el-aside width="200px">
					<el-menu unique-opened class="el-menu-vertical-demo" router background-color="#ffffff" text-color="#000000"
					 active-text-color="#ff0000">
						<el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
							<template slot="title">
								<i :class="item.iconCls" style="padding: 0 8px; color: #ffaa00;"></i>
								<span>{{item.name}}</span>
							</template>
							<el-menu-item :index="child.path" v-for="(child,index) in item.children" :key="index">{{child.name}}</el-menu-item>
						</el-submenu>
					</el-menu>
				</el-aside>
				<el-container>
					<el-main>
						<el-breadcrumb separator="/" v-if="this.$router.currentRoute.path!='/home'" style="padding: 10px 0 0 10px;">
							<el-breadcrumb-item :to="{ path: '/home' }" style="font-size: 17px;">首页</el-breadcrumb-item>
							<el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
						</el-breadcrumb>
						<div  v-if="this.$router.currentRoute.path == '/home'" style="display: flex; flex-direction:column;align-items: center;">
							<div class="home-text">
									欢迎来到VBLOG-ADMIN!!!
							</div>
							<div class="el-calendar-div">
								<el-calendar v-model="todayTime">
								</el-calendar>
								<el-divider content-position="right">让别人来享受生活，我们来追逐梦想</el-divider>
							</div>
						</div>
						<router-view class="home-router-view"></router-view>
					</el-main>

				</el-container>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	export default {
		name: 'Home',
		data() {
			return {
				todayTime:new Date(),
				user: JSON.parse(window.sessionStorage.getItem("user"))
			}
		},
		computed: {
			routes() {
				return this.$store.state.routes;
			}
		},
		methods: {
			goChat(){
				this.$router.push('/chat');
			},
			commandHandler(cmd) {
				if (cmd == 'logout') {
					this.$confirm('将要注销登录, 是否继续?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						this.getRequest("/logout");
						window.sessionStorage.removeItem("user");
						this.$store.commit('initRoutes', []);
						this.$router.replace("/");
					})
				}
			}
		},
	}
</script>

<style scoped>
	* {
		margin: 0;
		padding: 0;
	}

	.home-router-view {
		padding: 20px 0 20px 20px;
	}

	.home-header {

		background: #007cba;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 0px 30px;

	}

	.home-header .title {
		font-size: 30px;
		font-family: 华文行楷;
		color: #FFFFFF;
	}

	.home-header .dropdown {
		cursor: pointer;
	}

	.el-dropdown-link {
		display: flex;
		align-items: center;
	}

	.welcome {
		font-size: 25px;
		font-family: 华文行楷;
		color: #EAEAEA;
	}

	.home-text {
		font-size: 35px;
		font-weight: bolder;
		text-align: center;
		padding: 50px 0;
		color: #00AA7F;
		font-family: 华文行楷;

	}
	.el-calendar-div{
	
		
		width: 50%;
		height: 25%;
		font-size: 30px;
		color: #00AA7F;
		font-family: 华文行楷;
	}
	.el-calendar-table .el-calendar-day{
		height: 0px !important;
	}
	
</style>
