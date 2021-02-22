<template>
	<div>
		<div style="display: flex; justify-content: center;">
			<el-input prefix-icon="el-icon-search" style="width: 300px; margin-right: 10px;" size="mini" placeholder="请输入用户名..."
			 v-model="keyWords" @keydown.enter.native="doSearch"></el-input>
			<el-button type="primary" icon="el-icon-search" size="mini" @click="doSearch">搜索</el-button>
		</div>

		<div  v-loading="loading"
    element-loading-text="搜索中..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
		 class="admin-container">
			<el-card class="box-card"
			
			 v-for="(admin,index) in adminList" :key="index">
				<div slot="header" class="clearfix">
					<span>{{admin.name}}</span>
					<el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-delete" @click="deleteAdmin(admin)"></el-button>
				</div>

				<div>
					<div class="image-container">
						<img :src="admin.adminPic" style="width: 60px; height: 60px; border-radius: 100%;" />
					</div>
					<div class="text-container">
						<span>用户名：{{admin.username}}</span>
						<span>手机号：{{admin.phone}}</span>
						<span>邮箱：{{admin.email}}</span>
						<span>地址：{{admin.address}}</span>
						<span>用户状态：
							<el-switch v-model="admin.enabled" active-color="#13ce66" inactive-color="#ff4949" active-text="启用" @change="changeEnable(admin)"
							 inactive-text="禁用">
								>
							</el-switch>
						</span>
						<div>
							用户角色:
							<el-tag type="success" style="margin: 4px 4px 0 5px;" v-for="(role,indexj) in admin.roles" :key="indexj">{{role.name_zh}}
							</el-tag>
							<el-popover placement="right-end" title="角色" width="200" @show="showPop(admin)" @hide="hidePop(admin)" trigger="click">
								<el-select v-model="selectedRoles" multiple placeholder="请选择">
									<el-option v-for="(r,indexk) in rolesList" :key="indexk" :label="r.name_zh" :value="r.id">
									</el-option>
								</el-select>
								<el-button slot="reference" type="text" icon="el-icon-more"></el-button>
							</el-popover>

						</div>

						<span>备注：{{admin.mark}}</span>

					</div>
				</div>

			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		name: "SysAdmin",
		data() {
			return {
				loading:false,
				adminList: [],
				rolesList: [],
				selectedRoles: [],
				keyWords: '',
			}
		},
		mounted() {
			this.initAdmins();
		},
		methods: {
			deleteAdmin(admin) {
				this.$confirm('此操作将永久删除_' + admin.name + '_ 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.deleteRequest("/sys/admin/"+admin.id).then(res=>{
						if(res){
							this.initAdmins();
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});

			},
			doSearch() {
				this.initAdmins();
			},
			hidePop(admin) {
				//更新角色，但是如果角色没有变化不要给后端发请求
				let roles = [];
				Object.assign(roles, admin.roles);
				let flag = false;
				if (roles.length != this.selectedRoles.length) {
					flag = true;
				} else {
					for (let i = 0; i < roles.length; i++) {
						let role = roles[i];
						for (let j = 0; j < this.selectedRoles.length; j++) {
							let id = this.selectedRoles[j];
							if (role.id == id) {
								roles.splice(i, 1);
								i--;
								break;
							}
						}
					}
				}
				if (roles.length != 0) {
					flag = true;
				}
				if (this.selectedRoles.length == 0) {
					return;
				}
				if (flag) {
					let url = "/sys/admin/role?id=" + admin.id;
					this.selectedRoles.forEach(rid => {
						url += '&ids=' + rid;
					})
					this.putRequest(url).then(res => {
						if (res) {
							this.initAdmins();
						}
					})
				}




			},
			showPop(admin) {
				this.initRoleLists();
				let roles = admin.roles;
				this.selectedRoles = [];
				roles.forEach(r => {
					console.log(r);
					this.selectedRoles.push(r.id);
				})
			},
			initRoleLists() {
				this.getRequest('/sys/admin/role').then(res => {
					if (res) {
						this.rolesList = res.obj;
					}
				})
			},
			changeEnable(admin) {
				delete admin.roles;
				this.putRequest("/sys/admin/", admin).then(res => {
					if (res) {
						this.initAdmins();
					}
				})
			},
			initAdmins() {
				this.loading = true;
				this.getRequest("/sys/admin/?keyWords=" + this.keyWords).then(res => {
				this.loading = false;
					if (res) {
						this.adminList = res.obj;
					}
				})
			}
		},
	}
</script>

<style>
	.image-container {
		display: flex;
		justify-content: center;
	}

	.text-container {
		color: #00aa7f;
		font-size: 12px;
		display: flex;
		flex-direction: column;
		line-height: 2;
	}

	.admin-container {
		margin: 20px 20px;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;

	}

	.box-card {
		width: 400px;
		margin-bottom: 20px;
	}
</style>
