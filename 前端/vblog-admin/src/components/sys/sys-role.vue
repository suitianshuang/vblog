<template>
	<div v-loading="loading" element-loading-text="正在添加..." element-loading-spinner="el-icon-loading"
	 element-loading-background="rgba(0, 0, 0, 0.8)">
		<div class="sys-role">
			<el-input size="mini" style="width: 300px;" placeholder="请输入权限英文名" v-model="role.name">
				<template slot="prepend">ROLE_</template>
			</el-input>
			<el-input size="mini" style="width: 300px; margin:0 8px;" placeholder="请输入权限中文名" @keydown.enter.native="insetRole"
			 v-model="role.name_zh"></el-input>
			<el-button size="mini" type="primary" icon="el-icon-plus" @click="insetRole">增加</el-button>

		</div>



		<div>
			<el-collapse v-model="activeName" style="width: 800px;" accordion @change="change">
				<el-collapse-item :title="r.name_zh" :name="r.id" v-for="(r,index) in roles" :key="index">
					<div>
						<el-card>
							<div slot="header">
								<span>可访问的资源</span>
								<el-button style="float: right; padding: 3px 3px;background: #ffaa7f;" @click="deleteRole(r)" icon="el-icon-delete"></el-button>
							</div>
							<div>
								<el-tree :key="index" :data="allMenus" show-checkbox ref="tree" node-key="id" :default-checked-keys="selectMenus"
								 :props="defaultProps"></el-tree>
							</div>
							<div style="display: flex; justify-content: flex-end">
								<el-button size="mini" type="primary" @click="cancelUpdata">取消修改</el-button>
								<el-button size="mini" type="danger" @click="doUpdate(r.id,index)">确认修改</el-button>
							</div>


						</el-card>
					</div>
				</el-collapse-item>
			</el-collapse>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'sys-role',
		data() {
			return {
				loading:false,
				activeName: -1,
				role: {
					name: '',
					name_zh: '',
				},
				roles: [],
				allMenus: [],
				defaultProps: {
					children: 'children',
					label: 'name'
				},
				selectMenus: []

			}
		},
		mounted() {
			this.initRoleList();
		},
		methods: {
			deleteRole(role) {

				this.$confirm('此操作将永久角色删除' + role.name + '是否继续?', {
					confirmButtonText: '继续',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.deleteRequest("/sys/basic/role/" + role.id).then(res => {
						if (res) {
							this.initRoleList();
						}
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			insetRole() {
				if (!this.role.name || !this.role.name_zh) {
					return this.$notify({
						message: '身份不能为空!',
						type: 'warning'
					});
				}
				this.loading = true;
				this.postRequest("/sys/basic/role", this.role).then(res => {
					this.loading = false;
					if (res) {
						this.initRoleList();
						this.role = {
							name: '',
							name_zh: '',
						}
					}
				})
			},
			cancelUpdata() {
				this.activeName = -1;
			},
			doUpdate(rid, index) {
				let tree = this.$refs.tree[index];
				let selectIds = tree.getCheckedKeys(true);
				let url = '/sys/basic/?rid=' + rid;
				selectIds.forEach(Key => {
					url += '&mids=' + Key;
				})

				this.putRequest(url).then(res => {
					if (res) {
						this.activeName = -1;

					}
				})

			},
			change(rid) {
				if (rid) {
					this.getallMenus();
					this.initselectMenus(rid);
				}
			},
			initselectMenus(rid) {
				this.getRequest("/sys/basic/rid/" + rid).then(res => {
					if (res) {
						this.selectMenus = res;
					}
				})
			},
			getallMenus() {
				this.getRequest("/sys/basic/menus/").then(res => {
					if (res) {
						this.allMenus = res.obj;
					}
				})
			},
			initRoleList() {
				this.getRequest('/sys/basic/role/').then(res => {
					if (res.obj) {
						console.log(res.obj);
						this.roles = res.obj;
					}
				})
			}
		},
	}
</script>

<style>
	.sys-role {
		display: flex;
		/* justify-items: ; */
	}
</style>
